package studentroster;

import java.text.DecimalFormat;

/**
 * Resident class, builds upon the student class.
 * Defines and creates further methods specific to residents on top of students.
 * @author Neel Prabhu, Saipranav Kalapala
 */
public class Resident extends Student{

    private boolean hasTakenAid = false;
    private double tuitionDue = 0;

    private static final int maxfinAid = 10000;
    private static final int tuition = 12536;
    private static final int tuitionRate = 404;

    private static double finAid;

    /**
     * Constructor method, creates an instance of resident class based off of student superclass.
     * @param profile Profile instance; contains name and major.
     * @param credits Number of credits the student is taking.
     */
    public Resident(Profile profile, int credits) {
        super(profile, credits);
        this.finAid = 0;
    }

    /**
     * Constructor method, creates an instance of Non-resident class based off of student superclass.
     * @param profile Profile instance; contains name and major.
     * @param credits Number of credits the student is taking.
     * @param finAid Amount of one-time financial aid a student receives.
     */
    public Resident(Profile profile, int credits, int finAid) {
        super(profile, credits);
        this.finAid = finAid;
        hasTakenAid = true;
    }

    /**
     * Getter method to return the max amount of financial aid a student can receive.
     * @return
     */
    public static int getMaxfinAid() {
        return maxfinAid;
    }

    /**
     * Getter method to return the amount of financial aid a student has received.
     * @return
     */
    public double getFinAid() {
        return finAid;
    }

    /**
     * Setter mehtod to set the amount of financial aid a student receives.
     * @param amount amount of financial aid
     * @return true if student has not already taken aid.
     */
    public boolean setFinAid(double amount) {
        if (finAid > 0) return false;
        finAid = amount;
        tuitionDue -= finAid;
        return true;
    }

    /**
     * Calculates the tuition due for a resident based off of the fee structure.
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
     * Method to return the current tuition due for a resident student instance.
     * @return
     */
    @Override
    public double getTuition() {return tuitionDue;}

    public boolean hasTakenAid() {
        return hasTakenAid;
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

        result += ":last payemnt:";

        if (getLastPayment() == null) {
            result += "--/--/--";
        } else {
            result += getLastPayment().toString() + ":";
        }

        result += ":resident";

        return result;
    }

    public static void main(String[] args) {
        Major major = Major.toMajor("CS");
        Profile profile = new Profile("Pranav", major);
        Resident student = new Resident(profile, 18);
        student.tuitionDue();
        System.out.println(student.getTuition());
    }
}
