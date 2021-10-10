package studentroster;

public class NonResident extends Student{

    private double tuitionDue;

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

    public static void main(String[] args) {
        Major major = Major.toMajor("CS");
        Profile profile = new Profile("Pranav", major);
        NonResident student = new NonResident(profile, 18);
        student.tuitionDue();
        System.out.println(student.getTuition());
    }
}
