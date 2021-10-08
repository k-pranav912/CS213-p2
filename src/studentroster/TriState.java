package studentroster;

public class TriState extends NonResident{

    private static double tuitionDue;

    private static final int newyorkDiscount = 4000;
    private static final int connecticutDiscount = 5000;

    // true if from NY, false if from CT
    private static boolean fromNY;

    public TriState(Profile profile, int credits) {
        super(profile, credits);
    }

    @Override
    public void tuitionDue() {
        // TODO check if the below method changes the instance var for this subclass.
        super.tuitionDue();
        if (fromNY) {
            tuitionDue -= newyorkDiscount;
        } else {
            tuitionDue -= connecticutDiscount;
        }

    }
}
