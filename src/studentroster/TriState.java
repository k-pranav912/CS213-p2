package studentroster;

public class TriState extends NonResident{

    private double tuitionDue;

    private static final int newyorkDiscount = 4000;
    private static final int connecticutDiscount = 5000;

    // true if from NY, false if from CT
    private static boolean fromNY;

    public TriState(Profile profile, int credits, boolean nyOrCt) {
        super(profile, credits);
        fromNY = nyOrCt;
    }

    @Override
    public void tuitionDue() {
        // TODO check if the below method changes the instance var for this subclass.
        super.tuitionDue();
        tuitionDue = super.getTuition();
        if (!isPartTime()) {
            if (fromNY) {
                tuitionDue -= newyorkDiscount;
            } else {
                tuitionDue -= connecticutDiscount;
            }
        }
        tuitionDue -= getTuitionPaid();
    }

    @Override
    public double getTuition(){return tuitionDue;}

    public static void main(String[] args) {
        Major major = Major.toMajor("CS");
        Profile profile = new Profile("Pranav", major);
        TriState student = new TriState(profile, 11, true);
        student.tuitionDue();
        System.out.println(student.getTuition());
    }
}
