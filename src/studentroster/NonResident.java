package studentroster;

import java.text.DecimalFormat;

/**
 * Non-resident class, builds upon the student class.
 * Defines and creates further methods specific to non-residents on top of students.
 * @author Neel Prabhu, Saipranav Kalapala
 */
public class NonResident extends Student{

    private double tuitionDue = 0;

    private static final int tuition = 29737;
    private static final int tuitionRate = 966;

    /**
     * Constructor method, creates an instance of Non-resident class based off of student superclass.
     * @param profile Profile instance; contains name and major.
     * @param credits Number of credits the student is taking.
     */
    public NonResident(Profile profile, int credits) {
        super(profile, credits);
    }

    /**
     * Calculates the tuition due for a non-resident based off of the fee structure.
     */
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

    /**
     * Method to return the current tuition due for a non-resident student instance.
     * @return
     */
    @Override
    public double getTuition() {
        return tuitionDue;
    }

    /**
     * String representation of the Student instance.
     * Subclasses add onto this.
     * format: Name:Major:Credits:Tuition-Due:Total-Payment:Last-Payment-Date:Residency-Status
     * @return
     */
    @Override
    public String toString() {
        String result = super.toString();
        DecimalFormat df = new DecimalFormat("#,##0.00");

        result += ":tuition due:" + df.format(tuitionDue);

        result += ":total payment:" + df.format(getTuitionPaid());

        result += ":last payment:";

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
