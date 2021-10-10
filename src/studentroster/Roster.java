package studentroster;

public class Roster {
    private Student[] roster;
    private int size;

    private static final int ROSTER_INCREASE_SIZE = 4;
    private static final int NOT_FOUND = -1;

    public Roster() {
        this.size = 0;
        this.roster = new Student[ROSTER_INCREASE_SIZE];
    }

    private int find(Student student){
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] != null && student.equals(roster[i])) {
                System.out.println("" + student + roster[i] + student.equals(roster[i]));
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

    }

    public void pay(Student student, int amountPaid) {

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

    }
    public void printByDate() {

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

        System.out.println();

        Roster roster1= new Roster();
        roster1.add(student1);
        roster1.add(student2);
        roster1.printRoster();
    }
}
