package pl.edu.agh.to.mastermind.model.user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Objects;

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
            return user;
        else throw new UserManagementException("Failed to add user to database.");
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

    public static boolean checkPasswordMatch(String email, String password) throws SQLException, NoSuchAlgorithmException, UserManagementException {
        return Objects.equals(getPasswordHash(email), hash(password));
    }
    
    public static String getPasswordHash(String email) throws SQLException, UserManagementException {
        try (Connection connection = getConnection()) {
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

}
