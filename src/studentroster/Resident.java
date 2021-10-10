package studentroster;

public class Resident extends Student{

    private boolean hasTakenAid = false;
    private double tuitionDue;

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
        tuitionDue -= getTuitionPaid();
    }

    @Override
    public double getTuition() {return tuitionDue;}

    public static void main(String[] args) {
        Major major = Major.toMajor("CS");
        Profile profile = new Profile("Pranav", major);
        Resident student = new Resident(profile, 18);
        student.tuitionDue();
        System.out.println(student.getTuition());
    }
}
