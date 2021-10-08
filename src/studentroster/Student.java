package studentroster;

public class Student {
    private static Profile profile;
    private static int creditHours;

    private static final int minCredits = 3;
    private static final int maxCredits = 12;

    public Student (Profile profile) {
        this.profile = profile;
    }

    public void tuitionDue(){
    }

    @Override
    public String toString() {
        return this.profile.toString() + ":" + creditHours + " credit hours";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || this == null) return false;

        if (!(object instanceof Student)) return false;

        Student student = (Student) object;

        return student.profile.equals(this.profile);
    }
}
