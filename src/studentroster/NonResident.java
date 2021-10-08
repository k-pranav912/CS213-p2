package studentroster;

public class NonResident extends Student{

    private static double tuitionDue;

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
            tuitionDue = (tuitionRate * super.getCreditHours()) + super.getUniversityFeeFullTime();
        }
    }
}
