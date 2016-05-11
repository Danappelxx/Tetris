/**
 * Created by dan on 5/10/16.
 */
public class ThreeAcrossOneUpShape extends Shape {
    public ThreeAcrossOneUpShape() {
        super();
    }

    protected String[] getStringRepresentation() {
        return new String[]{
                "-x---",
                "xxx--",
                "-----",
                "-----",
                "-----"
        };
    }
}