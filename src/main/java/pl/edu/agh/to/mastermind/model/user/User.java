package pl.edu.agh.to.mastermind.model.user;

import pl.edu.agh.to.mastermind.mail.EmailSender;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class User {
    private int id;

    private String firstName;
    private String lastName;
    private String email;

    private static Connection conn;

    private User(){}

    public static User register(String firstName, String lastName, String email, String password) throws Exception {
        //check for user existence
        try (Connection connection = getConnection()){
           conn = connection;
            if (checkEmailExistsInDatabase(email)) {
                throw new UserManagementException("Cannot register player. Email already exists in database");
            }
        }
        User user = new User();
        user.firstName = firstName;
        user.lastName = lastName;
        user.email = email;
        user.id = addUserToDatabase(firstName, lastName, email, password);
        if (user.id > 0 )
        {
            sendRegistrationEmail(user);
            return user;
        }
        else throw new UserManagementException("Failed to add user to database.");
    }

    private static void sendRegistrationEmail(User user) {
        String content = "Welcome " + user.firstName + "!\nWe're happy to welcome you in our little game.\n" +
                "If you need anything, please contact us at kwadratowekafelki@gmail.com or just reply to this email.\n" +
                "Regards,\nTeam KwadratoweKafelki";

        ExecutorService emailExecutor = Executors.newFixedThreadPool(1);
        emailExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    EmailSender.sendEmail(user.email, "Registered in MasterMind!", content);
                } catch (RuntimeException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
    }

//    public static User login(String email, String password) throws UserManagementException, SQLException, NoSuchAlgorithmException {
//        if (!checkPasswordMatch(email, password)){
//            throw new UserManagementException("Incorrect password.");
//        } else {
//            try (Connection connection = getConnection()) {
//                try (Statement st = conn.createStatement()) {
//                    String query = "select * from Users where email_address = '" + email + "'";
//                    try (ResultSet rs = st.executeQuery(query)) {
//                        if (rs.next()){
//                            return rs.getString(1);
//                        } else {
//                            throw new UserManagementException("There is no user with such email");
//                        }
//                    }
//                }
//            }
//        }
//    }

    public static User getUserByEmail(String email) throws UserManagementException, SQLException{
        try (Connection connection = getConnection()) {
            conn = connection;
            try (Statement st = conn.createStatement()) {
                String query = "select * from Users where email_address = '" + email + "'";
                try (ResultSet rs = st.executeQuery(query)) {
                    if (rs.next()){
                        User user = new User();
                        user.id = rs.getInt(1);
                        user.firstName = rs.getString(2);
                        user.lastName = rs.getString(3);
                        user.email = email;
                        return user;
                    } else {
                        throw new UserManagementException("There is no user with such email");
                    }
                }
            }
        }
    }

    public static User getUserByID(String id) throws UserManagementException, SQLException{
        try (Connection connection = getConnection()) {
            conn = connection;
            try (Statement st = conn.createStatement()) {
                String query = "select * from Users where userID = '"+id+"'";
                try (ResultSet rs = st.executeQuery(query)) {
                    if (rs.next()){
                        User user = new User();
                        user.id = rs.getInt(1);
                        user.firstName = rs.getString(2);
                        user.lastName = rs.getString(3);
                        return user;
                    } else {
                        throw new UserManagementException("There is no user with such id");
                    }
                }
            }
        }
    }

    public static boolean checkPasswordMatch(String email, String password) throws SQLException, NoSuchAlgorithmException, UserManagementException {
        return Objects.equals(getPasswordHash(email), hash(password));
    }
    
    public static String getPasswordHash(String email) throws SQLException, UserManagementException {
        try (Connection connection = getConnection()) {
            conn = connection;
            try (Statement st = conn.createStatement()) {
                String query = "select password_hash from Users where email_address = '" + email + "'";
                try (ResultSet rs = st.executeQuery(query)) {
                    if (rs.next()){
                        return rs.getString(1);
                    } else {
                        throw new UserManagementException("There is no user with such email");
                    }
                }
            }
        }
    }

    private static int addUserToDatabase(String firstName, String lastName, String email, String password) throws Exception {
        try (Connection connection = getConnection()){
            conn = connection;
            String hash = hash(password);
            String query = "insert into Users (first_name, last_name, email_address, password_hash)"
                    + "values ('"+firstName+"', '"+lastName+"', '"+email+"', '"+hash+"')";
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                return rs.getInt(1);
            }
            else return -1;
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl:3306/pmakarew", "pmakarew", "tMiKdJD9H9zKZbTn");
    }

    private static boolean checkEmailExistsInDatabase(String email) throws Exception{
            try(Statement st = conn.createStatement()){
                String query = "select * from Users where email_address = '"+ email +"'";
                try (ResultSet rs = st.executeQuery(query)){
                    return rs.next();
                }
            }
    }

    private static String hash(String input) throws NoSuchAlgorithmException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(
                    input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            String stringHash = hexString.toString();
            return stringHash.substring(0,Math.min(stringHash.length() - 1, 63));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getId(){
        return id;
    }
}
