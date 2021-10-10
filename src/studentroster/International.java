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

    public void setStudyAbroadStatusTrue() {
        this.isStudyAbroad = true;
        if (this.getCreditHours() > minInternationalCredits) this.setCreditHours(minInternationalCredits);
        this.resetTuitionPaid();
        this.tuitionDue();
    }

    @Override
    public void tuitionDue() {
        if (!isStudyAbroad) {
            super.tuitionDue();
            tuitionDue = super.getTuition();
            tuitionDue += additionalFee;
        } else {
            tuitionDue += additionalFee;
            tuitionDue += getUniversityFeeFullTime();
        }
        tuitionDue -= getTuitionPaid();
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
