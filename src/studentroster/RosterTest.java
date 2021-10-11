package studentroster;

import org.junit.Test;
import static org.junit.Assert.*;


public class RosterTest {
    @Test
    public void testRoster() {
        Profile p1 = new Profile("Student 1", Major.toMajor("ME"));
        Resident s1 = new Resident(p1, 18);

        Profile p2 = new Profile("Student 2", Major.toMajor("ME"));
        Resident s2 = new Resident(p2, 12);

        Profile p3 = new Profile("Student 3", Major.toMajor("EE"));
        Resident s3 = new Resident(p3, 6);

        Profile p4 = new Profile("Student 4", Major.toMajor("CS"));
        Resident s4 = new Resident(p4, 6);

        Profile p4Similar = new Profile("Student 4", Major.toMajor("EE"));
        Resident s4Similar = new Resident(p4Similar, 23);

        Profile p5 = new Profile("Student 5", Major.toMajor("CS"));
        NonResident s5 = new NonResident(p5, 17);

        Profile p6 = new Profile("Student 6", Major.toMajor("CS"));
        TriState s6 = new TriState(p6, 17, false);

        Profile p7 = new Profile("Student 7", Major.toMajor("CS"));
        International s7 = new International(p7, 17, true);

        Profile p1Copy = new Profile("Student 1", Major.toMajor("ME"));
        Student s1Copy = new Student(p1, 8);
        NonResident s1CopyNonResident = new NonResident(p1, 18);
        TriState s1CopyTriState = new TriState(p1, 18, true);
        International s1CopyInternational = new International(p1, 18, false);

        Roster roster = new Roster();
        roster.printRoster();
        assertTrue(roster.add(s1));
        assertFalse(roster.add(s1));
        roster.printRoster();
        assertTrue(roster.add(s2));
        assertTrue(roster.add(s3));
        assertTrue(roster.add(s4));
        assertTrue(roster.add(s4Similar));
        roster.printRoster();
        assertTrue(roster.add(s5));
        assertTrue(roster.add(s6));
        assertTrue(roster.add(s7));
        roster.printRoster();
        assertFalse(roster.add(s1Copy));
        assertFalse(roster.add(s1CopyNonResident));
        assertFalse(roster.add(s1CopyTriState));
        assertFalse(roster.add(s1CopyInternational));
        roster.printRoster();

        assertTrue(roster.remove(s1));
        assertFalse(roster.remove(s1Copy));
        assertFalse(roster.remove(s1CopyInternational));
        assertTrue(roster.remove(s4));
        roster.printRoster();
        assertTrue(roster.add(s4));
        assertTrue(roster.add(s1CopyInternational));
        roster.printRoster();
    }
}