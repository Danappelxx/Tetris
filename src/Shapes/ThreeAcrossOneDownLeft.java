package Shapes;

/**
 * Created by dan on 5/18/16.
 */

public class ThreeAcrossOneDownLeft extends Shape {
    public ThreeAcrossOneDownLeft() {
        super();
    }

    protected String[] getStringRepresentation() {
        return new String[]{
                "-----",
                "-----",
                "-xxx-",
                "-x---",
                "-----"
        };
    }
}
