package studentroster;

public class Student {
    private Profile profile;
    private int creditHours;
    private double tuitionPaid;
    private Date lastPayment;

    private static final int minCredits = 3;
    private static final int maxCredits = 24;
    private static final int maxPartTimeCredits = 11;
    private static final int freeFullTimeCredits = 16;
    private static final int universityFeeFullTime = 3268;
    private static final double universityFeePartTime = 0.80 * universityFeeFullTime;

    public Student (Profile profile, int credits) {
        this.profile = profile;
        this.creditHours = credits;
        this.tuitionPaid = 0;
        this.lastPayment = null;
    }

    public Student (Profile profile) {
        this.profile = profile;
    }

    public void tuitionDue(){
    }

    public double getTuitionPaid() {
        return tuitionPaid;
    }

    public void addTuitionPaid(double inputValue, Date date) {
        this.tuitionPaid += inputValue;
        lastPayment = date;
    }

    public static int getMinCredits() {
        return minCredits;
    }

    public static int getMaxCredits() {
        return maxCredits;
    }

    public int getMaxPartTimeCredits() {
        return maxPartTimeCredits;
    }

    public int getCreditHours() {
        return this.creditHours;
    }

    public static int getUniversityFeeFullTime() {
        return universityFeeFullTime;
    }

    public static double getUniversityFeePartTime() {
        return universityFeePartTime;
    }

    public static int getFreeFullTimeCredits() {
        return freeFullTimeCredits;
    }

    protected double getTuition(){return 0;}

    public boolean isPartTime() {
        if (creditHours > maxPartTimeCredits) return false;
        return true;
    }

    public int getDateInt() {
        return lastPayment.getDateIndex();
    }

    @Override
    public String toString() {
        return this.profile.toString() + ":" + creditHours + " credit hours";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || this == null) return false;

        if (!(object instanceof Student)) return false;

        Student student = (Student) object;

        return student.profile.equals(this.profile);
    }
}
