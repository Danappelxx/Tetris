package Shapes;

/**
 * Created by dan on 5/10/16.
 */

public class ThreeAcrossOneUp extends Shape {
    public ThreeAcrossOneUp() {
        super();
    }

    protected String[] getStringRepresentation() {
        return new String[]{
                "-----",
                "--x--",
                "-xxx--",
                "-----",
                "-----"
        };
    }
}