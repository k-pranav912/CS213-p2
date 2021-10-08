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
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow() {
        Student[] tempRoster = new Student[roster.length];
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
}