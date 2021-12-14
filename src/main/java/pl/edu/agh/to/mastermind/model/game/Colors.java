package pl.edu.agh.to.mastermind.model.game;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.Map;

public enum Colors {
    RED(Color.RED),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    ORANGE(Color.ORANGE),
    BLACK(Color.BLACK),
    WHITE(Color.WHITE);

    private final Paint value;
    private static Map map = new HashMap<Colors, Color>();

    Colors(Paint value) {
        this.value = value;
    }

    static {
        for (Colors col : Colors.values()) {
            map.put(col.value, col);
        }
    }

    public static Colors valueOf(Paint col) {
        return (Colors) map.get(col);
    }

    public Paint getValue() {
        return value;
    }
}
