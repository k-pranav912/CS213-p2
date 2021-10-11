package studentroster;

import java.text.DecimalFormat;

public class International extends NonResident{
    private boolean isStudyAbroad;
    private double tuitionDue = 0;

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

    @Override
    public String toString() {
        String result = super.toString();
        System.out.println(result);
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String replacementString = df.format(super.getTuition());
        result = result.replaceAll(replacementString, df.format(this.tuitionDue));

        result += ":international";

        if (isStudyAbroad) {
            result += ":study-abroad";
        }
        return result;
    }

    public static void main(String[] args) {
        Major major = Major.toMajor("CS");
        Profile profile = new Profile("Pranav", major);
        International student = new International(profile, 17, true);
        student.tuitionDue();
        System.out.println(student.getTuition());
    }
}
