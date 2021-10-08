package studentroster;

public class International extends NonResident{
    private static boolean isStudyAbroad;
    private static double tuitionDue;

    private static final int minCredits = 12;
    private static final int additionalFee = 2650;

    public International(Profile profile, int credits, boolean studyAbroad) {
        super(profile, credits);
        this.isStudyAbroad = studyAbroad;
    }
}
