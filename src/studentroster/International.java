package studentroster;

public class International extends NonResident{
    private boolean isStudyAbroad;
    private double tuitionDue;

    private static final int minInternationalCredits = 12;
    private static final int additionalFee = 2650;

    public International(Profile profile, int credits, boolean studyAbroad) {
        super(profile, credits);
        this.isStudyAbroad = studyAbroad;
    }

    public static int getMinInternationalCredits() { return minInternationalCredits; }

    @Override
    public void tuitionDue() {
        if (!isStudyAbroad) {
            // TODO check if the below method changes the instance var for this subclass.
            super.tuitionDue();
            tuitionDue = super.getTuition();
            tuitionDue += additionalFee;
            //System.out.println("a\n");
        } else {
            tuitionDue += additionalFee;
            tuitionDue += super.getUniversityFeeFullTime();
            //System.out.println("b\n");
        }
    }

    @Override
    public double getTuition() {
        return tuitionDue;
    }

    public static void main(String[] args) {
        Major major = Major.toMajor("CS");
        Profile profile = new Profile("Pranav", major);
        International student = new International(profile, 17, true);
        student.tuitionDue();
        System.out.println(student.getTuition());
    }
}
