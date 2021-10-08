package studentroster;

public class NonResident extends Student{
    private static final int tuition = 29737;
    private static final int tuitionRate = 966;

    public NonResident(Profile profile, int credits) {
        super(profile, credits);
    }
}
