package domain;

public class Assertion extends Assumption {

    @Override
    public final boolean isRetractable() {
        return false;
    }

}
