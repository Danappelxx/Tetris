package Shapes;

/**
 * Created by dan on 5/18/16.
 */

public class ThreeAcrossOneDownRight extends Shape {
    public ThreeAcrossOneDownRight() {
        super();
    }

    protected String[] getStringRepresentation() {
        return new String[]{
                "-----",
                "-----",
                "-xxx-",
                "---x-",
                "-----"
        };
    }
}
