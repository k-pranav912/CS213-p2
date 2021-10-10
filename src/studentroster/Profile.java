package studentroster;

public class Profile {
    private String name;
    private Major major;

    public Profile(String name, Major major) {
        this.name = name;
        this.major = major;
    }

    public Profile() {};

    @Override
    public boolean equals(Object object) {
        if ((object == null) || (this == null)) return false;

        if (!(object instanceof Profile)) return false;

        Profile obj = (Profile) object;

        if ((obj.name == null) || (this.name == null)) return false;
        if ((obj.major == null) || (this.major ==  null)) return false;

        return this.name.equals(obj.name) && (this.major == obj.major);
    }

    @Override
    public String toString() {
        return this.name + ":" + this.major;
    }
}
