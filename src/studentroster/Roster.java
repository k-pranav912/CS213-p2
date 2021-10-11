package studentroster;

/**
 * Roster Class; handles the implementation of a roster of students.
 * Includes different students such as residents, non-residents, tri-state non-residents and international students.
 */
public class Roster {
    private Student[] roster;
    private int size;

    private static final int ROSTER_INCREASE_SIZE = 4;
    private static final int NOT_FOUND = -1;

    /**
     * Constructor method that initializes a Roster instance.
     */
    public Roster() {
        this.size = 0;
        this.roster = new Student[ROSTER_INCREASE_SIZE];
    }

    private int find(Student student){
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] != null && student.equals(roster[i])) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow() {
        Student[] tempRoster;
        tempRoster = roster;
        roster = new Student[tempRoster.length + ROSTER_INCREASE_SIZE];

        for (int i = 0; i < tempRoster.length; i++) {
            roster[i] = tempRoster[i];
        }

    }

    private int findNextEmpty() {
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] == null) return i;
        }
        return NOT_FOUND;
    }

    public boolean add(Student student) {
        if (find(student) >= 0) return false;
        int newStudentIndex = findNextEmpty();
        if (newStudentIndex < 0) {
            grow();
            newStudentIndex = findNextEmpty();
        }
        size++;
        roster[newStudentIndex] = student;
        return true;
    }

    public boolean remove(Student student) {
        int deletionIndex = find(student);
        if (deletionIndex < 0) return false;
        size--;
        roster[deletionIndex] = null;
        return true;
    }

    public void calculate() {
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] != null) roster[i].tuitionDue();
        }
    }

    public boolean pay(Student student, double amountPaid, Date lastPayment) {
        int studentIndex = find(student);
        if (studentIndex < 0) return false;
        student.addTuitionPaid(amountPaid, lastPayment);
        student.tuitionDue();
        return true;
    }

    public boolean setAbroad(Student student) {
        int studentIndex = find(student);
        if (studentIndex < 0) return false;
        if (roster[studentIndex] instanceof International) {
            ((International) roster[studentIndex]).setStudyAbroadStatusTrue();
            return true;
        }
        return false;
    }

    public int setFinancialAid(Student student, double finAidAmount) {
        int studentIndex = find(student);
        if (studentIndex < 0) return -1;

        if (!(roster[studentIndex] instanceof Resident)) {
            return -2;
        }
        if (roster[studentIndex].isPartTime()) {
            return -3;
        }
        if (((Resident) student).setFinAid(finAidAmount) == false) {
            return -4;
        }
        return 0;
    }

    public void printRoster() {
        if (size <= 0) {
            System.out.println("Student roster is empty!");
            return;
        }
        System.out.println("* list of students in the roster **");
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] != null) System.out.println(roster[i]);
        }
        System.out.println("* end of roster **");
    }

    public void printByName() {
        if (size <= 0) {
            System.out.println("Student roster is empty!");
            return;
        }

        String[] result = new String[size];

        int counterIndex = 0;
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] != null) {
                result[counterIndex] = roster[i].toString();
                counterIndex++;
            }
        }

        for(int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j++) {
                if(result[i].compareTo(result[j]) > 0) {
                    String temp = result[i];
                    result[i] = result[j];
                    result[j] = temp;
                }
            }
        }

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

    }

    private boolean checkArray(int[] arr, int key)
    {
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] == key) return true;
        }
        return false;
    }

    public static int[] arrSort(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = i+1; j < arr.length; j++)
            {
                int temp = 0;
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }

    private int[] genDateArray()
    {
        int[] tempArr = new int[size];
        int arrIndex = 0;

        for (int i = 0; i < roster.length; i++)
        {
            if ((roster[i] != null && roster[i].getLastPayment() != null) && !checkArray(tempArr, roster[i].getDateInt()))
            {
                tempArr[arrIndex] = roster[i].getDateInt();
                arrIndex++;
            }
        }

        tempArr = arrSort(tempArr);

        return tempArr;
    }

    public void printByDate()
    {
        if (size <= 0)
        {
            System.out.println("Student roster is empty!");
            return;
        }

        int[] releaseDates = genDateArray();

        System.out.println("*List of students in the roster.");
        for (int i = 0; i < releaseDates.length; i++)
        {
            if (releaseDates[i] == 0) continue;

            for (int j = 0; j < roster.length; j++)
            {
                if ((roster[j] != null && roster[i].getLastPayment() != null) && roster[j].getDateInt() == releaseDates[i]) System.out.println(roster[j]);
            }
        }
        System.out.println("*End of list");

    }

    public static void main(String[] args) {
        Major major = Major.toMajor("CS");
        Profile profile = new Profile("Pranav", major);
        Student student1 = new Resident(profile, 18, 1000);
        System.out.println(student1);

        Major major2 = Major.toMajor("EE");
        Profile profile2 = new Profile("Neel", major2);
        Student student2 = new Resident(profile2, 17, 1234);
        System.out.println(student2);

        Major major3 = Major.toMajor("EE");
        Profile profile3 = new Profile("Ishan", major3);
        Student student3 = new Resident(profile3, 15, 12);
        System.out.println(student3);

        Major major4 = Major.toMajor("EE");
        Profile profile4 = new Profile("Sanjiv", major4);
        Student student4 = new Resident(profile4, 13, 154);
        System.out.println(student2);

        System.out.println();

        Roster roster1= new Roster();
        roster1.add(student1);
        roster1.pay(student1, 10, new Date("08/31/2000"));
        roster1.add(student2);
        roster1.pay(student2, 10, new Date("09/01/2000"));
        roster1.add(student3);
        roster1.pay(student3, 10, new Date("08/02/2000"));
        roster1.add(student4);
        roster1.pay(student4, 10, new Date("09/03/2000"));
        roster1.printRoster();

        System.out.println("name:");

        roster1.printByName();

        System.out.println("date:");

        roster1.printByDate();
    }
}
