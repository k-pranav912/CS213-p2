package studentroster;

public class NonResident extends Student{

    private static double tuitionDue;

    private static final int tuition = 29737;
    private static final int tuitionRate = 966;

    public NonResident(Profile profile, int credits) {
        super(profile, credits);
    }

    @Override
    public void tuitionDue() {

    }
}
