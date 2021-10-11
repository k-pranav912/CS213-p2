package studentroster;

import org.junit.Test;

import static org.junit.Assert.*;

public class InternationalTest {

    private static final double defaultStudyAbroadTuition = 5918.0;
    private static final int defaultStudyAbroadCreditHours = 12;
    private static final double defaultInternationalTuition = 35655.0;


    @Test
    public void testInternational() {
        Major major1 = Major.toMajor("CS");
        Profile profile1 = new Profile("abcd", major1);
        International test1 = new International(profile1, 15, true);
        test1.tuitionDue();
        assertTrue(test1.getTuition() == defaultStudyAbroadTuition);

        Major major2 = Major.toMajor("BA");
        Profile profile2 = new Profile("xyz", major2);
        International test2 = new International(profile2, 16, false);
        test2.tuitionDue();
        System.out.println(test2.getTuition());
        assertTrue(test2.getTuition() == defaultInternationalTuition);

        test2.setStudyAbroadStatusTrue();
        assertTrue(test2.getCreditHours() == defaultStudyAbroadCreditHours);

        assertTrue(test1.getTuition() == defaultStudyAbroadTuition);
    }

}