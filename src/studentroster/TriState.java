package studentroster;

public class TriState extends Resident{

    private static double tuitionDue;

    private static final int newyorkDiscount = 4000;
    private static final int conneticutDiscount = 5000;

    // true if from NY, false if from CT
    private static boolean fromNY;

    public TriState(Profile profile, int credits) {
        super(profile, credits);
    }
}
