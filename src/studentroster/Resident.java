package studentroster;

import java.text.DecimalFormat;

public class Resident extends Student{

    private boolean hasTakenAid = false;
    private double tuitionDue = 0;

    private static final int maxfinAid = 10000;
    private static final int tuition = 12536;
    private static final int tuitionRate = 404;

    private static double finAid;

    public Resident(Profile profile, int credits) {
        super(profile, credits);
        this.finAid = 0;
    }

    public Resident(Profile profile, int credits, int finAid) {
        super(profile, credits);
        this.finAid = finAid;
        hasTakenAid = true;
    }

    public static int getMaxfinAid() {
        return maxfinAid;
    }

    public double getFinAid() {
        return finAid;
    }

    public boolean setFinAid(double amount) {
        if (finAid > 0) return false;
        finAid = amount;
        this.tuitionDue();
        return true;
    }

    @Override
    public void tuitionDue() {
        if (super.getCreditHours() > super.getMaxPartTimeCredits()) {
            tuitionDue = tuition + super.getUniversityFeeFullTime();
            int over16Credits = super.getCreditHours() - super.getFreeFullTimeCredits();
            if (over16Credits > 0) {
                tuitionDue += over16Credits * tuitionRate;
            }
            tuitionDue -= finAid;
        } else {
            tuitionDue = (tuitionRate * super.getCreditHours()) + super.getUniversityFeePartTime();
            tuitionDue -= finAid;
        }
        tuitionDue -= finAid;
        tuitionDue -= getTuitionPaid();
    }

    @Override
    public double getTuition() {return tuitionDue;}

    public boolean hasTakenAid() {
        return hasTakenAid;
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
