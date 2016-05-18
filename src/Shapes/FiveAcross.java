package Shapes;

/**
 * Created by dan on 5/9/16.
 */

public class FiveAcross extends Shape {
    public FiveAcross() {
        super();
    }

    protected String[] getStringRepresentation() {
        return new String[]{
                "-----",
                "-----",
                "xxxxx",
                "-----",
                "-----"
        };
    }
}
