package studentroster;

import java.text.DecimalFormat;

public class NonResident extends Student{

    private double tuitionDue = 0;

    private static final int tuition = 29737;
    private static final int tuitionRate = 966;

    public NonResident(Profile profile, int credits) {
        super(profile, credits);
    }

    @Override
    public void tuitionDue() {
        if (super.getCreditHours() > super.getMaxPartTimeCredits()) {
            tuitionDue = tuition + super.getUniversityFeeFullTime();
            int over16Credits = super.getCreditHours() - super.getFreeFullTimeCredits();
            if (over16Credits > 0) {
                tuitionDue += over16Credits * tuitionRate;
            }
        } else {
            tuitionDue = (tuitionRate * super.getCreditHours()) + super.getUniversityFeePartTime();
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
        DecimalFormat df = new DecimalFormat("#,##0.00");

        result += ":tuition due:" + df.format(tuitionDue);

        result += ":total payment:" + df.format(getTuitionPaid());

        result += ":last payemnt:";

        if (getLastPayment() == null) {
            result += "--/--/--";
        } else {
            result += getLastPayment().toString() + ":";
        }

        result += ":non-resident";

        return result;
    }

    public static void main(String[] args) {
        Major major = Major.toMajor("CS");
        Profile profile = new Profile("Pranav", major);
        NonResident student = new NonResident(profile, 15);
        student.tuitionDue();
        System.out.println(student.getTuition());
        if (student instanceof TriState) {
            System.out.println("pee");
        }
        System.out.println(student);
    }
}
