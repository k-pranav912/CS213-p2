package studentroster;

public class Resident extends Student{
    private static final int maxfinAid = 10000;
    private static boolean hasTakenAid = false;
    private static final int tuition = 29737;
    private static final int tuitionRate = 404;

    private static int finAid;

    public Resident(Profile profile, int credits) {
        super(profile, credits);
        this.finAid = 0;
    }

    public Resident(Profile profile, int credits, int finAid) {
        super(profile, credits);
        this.finAid = finAid;
    }
}
