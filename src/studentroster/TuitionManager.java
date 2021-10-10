package studentroster;

import java.util.Scanner;
import java.util.StringTokenizer;

public class TuitionManager {

    /**
     * Exits the process. Called when the user enters "Q"
     */
    private static void exitManager()
    {
        System.out.println("Tuition Manager terminated.");
        System.exit(0);
    }

    private static Profile makeProfile(StringTokenizer strTokens)
    {
        if (strTokens.hasMoreTokens() == false) {
            System.out.println("Missing Data in command line");
            return null;
        }
        String name = strTokens.nextToken();
        if (strTokens.hasMoreTokens() == false) {
            System.out.println("Missing Data in command line");
            return null;
        }
        String majorString = strTokens.nextToken();
        Major major = Major.toMajor(majorString);
        if (major == null)
        {
            System.out.println("'" + majorString + "' is not a valid major.");
            return null;
        }
        Profile profile = new Profile(name, major);
        return profile;
    }



    private static int checkCredits(StringTokenizer strTokens, boolean isInternational)
    {
        if (strTokens.hasMoreTokens() == false) {
            System.out.println("Missing Data in command line");
            return -1;
        }

        String creditHours = strTokens.nextToken();
        int credits;
        try {
            credits = Integer.parseInt(creditHours);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid Credit Hours");
            return -1;
        }
        if (credits < 0) {
            System.out.println("Credit Hours cannot be negative.");
            return -1;
        }
        else if (credits < Student.getMinCredits()) {
            System.out.println("Minimum credit hours is " + Student.getMinCredits() + ".");
            return -1;
        }
        else if (isInternational == true && credits < International.getMinInternationalCredits())
        {
            System.out.println("International students must entroll at least "
                    + International.getMinInternationalCredits() + " credits.");
            return -1;
        }
        else if (credits > Student.getMaxCredits())
        {
            System.out.println("Credit Hours exceed the maximum " + Student.getMaxCredits() + ".");
            return -1;
        }
        return credits;
    }

    private static int checkState(StringTokenizer strTokens) {
        if (strTokens.hasMoreTokens() == false) {
            System.out.println("Missing Data in command line");
            return -1;
        }
        String state = strTokens.nextToken();

        if (state.toUpperCase().equals("NY"))
            return 1;
        else if (state.toUpperCase().equals("CT"))
            return 0;

        System.out.println("Not part of the tri-state area.");
        return -1;
    }

    private static int checkStudyAbroad(StringTokenizer strTokens) {
        if (strTokens.hasMoreTokens() == false) {
            System.out.println("Missing Data in command line");
            return -1;
        }
        String studyAbroad = strTokens.nextToken();

        if (studyAbroad.equals("true"))
            return 1;
        else if (studyAbroad.equals("false"))
            return 0;
        else {
            System.out.println("Invalid status for studying abroad.");
            return -1;
        }
    }

    private static void addStudent(Student student, Roster studentRoster)
    {
        if (studentRoster.add(student) == false)
            System.out.println("Student is already in the roster.");
        else
            System.out.println("Student added.");
    }

    private static void addResident(StringTokenizer strTokens, Roster studentRoster) {
        Profile profile = makeProfile(strTokens);
        if (profile == null) return;

        int credits = checkCredits(strTokens, false);
        if (credits < 0) return;
        Resident student = new Resident(profile, credits);
        addStudent(student, studentRoster);
    }

    private static void addNonResident(StringTokenizer strTokens, Roster studentRoster) {
        Profile profile = makeProfile(strTokens);
        if (profile == null) return;

        int credits = checkCredits(strTokens, false);
        if (credits < 0) return;

        NonResident student = new NonResident(profile, credits);

        addStudent(student, studentRoster);
    }

    private static void addTriState(StringTokenizer strTokens, Roster studentRoster) {
        Profile profile = makeProfile(strTokens);
        if (profile == null) return;

        int credits = checkCredits(strTokens, false);
        if (credits < 0) return;

        boolean stateBoolean = true;
        int stateInteger = checkState(strTokens);
        if(stateInteger < 0) return;
        else if (stateInteger == 0) stateBoolean = false;

        TriState student = new TriState(profile, credits, stateBoolean);

        addStudent(student, studentRoster);
    }

    private static void addInternational(StringTokenizer strTokens, Roster studentRoster) {
        Profile profile = makeProfile(strTokens);
        if (profile == null) return;

        int credits = checkCredits(strTokens, true);
        if (credits < 0) return;

        boolean studyAbroadBoolean = true;
        int studyAbroadInteger = checkStudyAbroad(strTokens);
        if (studyAbroadInteger < 0) return;
        else if (studyAbroadInteger == 0) studyAbroadBoolean = false;

        International student = new International(profile, credits, studyAbroadBoolean);

        addStudent(student, studentRoster);
    }

    private static void removeStudent(StringTokenizer strTokens, Roster studentRoster) {
        Profile profile = makeProfile(strTokens);
        if (profile == null) return;

        Student student = new Student(profile);

        if (studentRoster.remove(student) == false) System.out.println("Student is not in the roster.");
        else System.out.println("Student removed from the roster.");
    }

    private static void payTuition(StringTokenizer strTokens, Roster studentRoster) {
        Profile profile = makeProfile(strTokens);
        if (profile == null) return;

        Student student = new Student(profile);
         //PAUSING HERE, NEED TO CHECK FOR PAYMENT VALIDITY, AMOUNT, AND DATE
    }

    /**
     * Parses through the string the user inputted and calls the appropriate command from the roster class, sending
     * it to the appropriate helper method based on the command
     * @param strTokens the tokenized string, by commas, the user inputted
     * @param studentRoster the roster for this user
     * @return false if the user entered an invalid command, true otherwise
     */
    private boolean parseTokens(StringTokenizer strTokens, Roster studentRoster) {
        if(strTokens.hasMoreTokens() == false) return true;
        switch (strTokens.nextToken()) {
            case "AR":
                addResident(strTokens, studentRoster);
                break;
            case "AN":
                addNonResident(strTokens, studentRoster);
                break;
            case "AT":
                addTriState(strTokens, studentRoster);
                break;
            case "AI":
                addInternational(strTokens, studentRoster);
                break;
            case "R":
                removeStudent(strTokens, studentRoster);
                break;
            case "C":
                studentRoster.calculate();
                break;
            case "T":
                payTuition(strTokens, studentRoster);
                break;
            case "S":
                //setStudyAbroad(strTokens, studentRoster);
                break;
            case "F":
                //setFinancialAid(strTokens, studentRoster);
                break;
            case "P":
                studentRoster.printRoster();
                break;
            case "PN":
                studentRoster.printByName();
                break;
            case "PT":
                studentRoster.printByDate();
                break;
            default:
                return false;
        }
        return true;
    }

    /**
     * The method called by RunProject2 that continuously runs in a while loop, taking in user inputs and calls
     * parseTokens for every line entered, until the user quits using "Q"
     */
    public void run() {
        Roster studentRoster = new Roster();
        System.out.println("Tuition Manager starts running.");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("Q")) exitManager();
            StringTokenizer strTokens = new StringTokenizer(input, ",");

            if (parseTokens(strTokens, studentRoster) == false)
                System.out.println("Command '" + input + "' is not supported");
        }
    }

}
