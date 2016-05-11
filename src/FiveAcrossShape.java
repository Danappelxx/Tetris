/**
 * Created by dan on 5/9/16.
 */

public class FiveAcrossShape extends Shape {
    public FiveAcrossShape() {
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
