package Shapes;

/**
 * Created by dan on 5/18/16.
 */

public class TwoAcrossTwoAcrossRight extends Shape {
    public TwoAcrossTwoAcrossRight() {
        super();
    }

    protected String[] getStringRepresentation() {
        return new String[]{
                "-----",
                "-xx--",
                "--xx-",
                "-----",
                "-----"
        };
    }
}
