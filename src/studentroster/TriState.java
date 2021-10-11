package studentroster;

import java.text.DecimalFormat;

/**
 * Tri-State class, builds upon the non-resident class.
 * Defines and creates further methods specific to non-resident tri-state students on top of residents.
 * @author Neel Prabhu, Saipranav Kalapala
 */
public class TriState extends NonResident{

    private double tuitionDue = 0;

    private static final int newyorkDiscount = 4000;
    private static final int connecticutDiscount = 5000;

    // true if from NY, false if from CT
    private static boolean fromNY;

    /**
     * Constructor method, creates an instance of tri-state class based off of non-resident superclass.
     * @param profile Profile instance; contains name and major.
     * @param credits Number of credits the student is taking.
     * @param nyOrCt true if the student is from NY, false if from CT.
     */
    public TriState(Profile profile, int credits, boolean nyOrCt) {
        super(profile, credits);
        fromNY = nyOrCt;
    }

    /**
     * Calculates the tuition due for a non-resident tri-state student based off of the fee structure.
     */
    @Override
    public void tuitionDue() {
        // TODO check if the below method changes the instance var for this subclass.
        super.tuitionDue();
        tuitionDue = super.getTuition();
        if (!isPartTime()) {
            if (fromNY) {
                tuitionDue -= newyorkDiscount;
            } else {
                tuitionDue -= connecticutDiscount;
            }
        }
        tuitionDue -= getTuitionPaid();
    }

    /**
     * Method to return the current tuition due for a non-resident tri-state student instance.
     * @return
     */
    @Override
    public double getTuition(){return tuitionDue;}

    /**
     * String representation of the Student instance.
     * Subclasses add onto this.
     * format: Name:Major:Credits:Tuition-Due:Total-Payment:Last-Payment-Date:Residency-Status
     * @return
     */
    @Override
    public String toString() {
        String result = super.toString();
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String replacementString = df.format(super.getTuition());
        result = result.replaceAll(replacementString, df.format(this.tuitionDue));

        result += "(tri-state):";

        if (fromNY) {
            result += "NY";
        } else {
            result += "CT";
        }
        return result;
    }

    public static void main(String[] args) {
        Major major = Major.toMajor("CS");
        Profile profile = new Profile("Pranav", major);
        TriState student = new TriState(profile, 12, true);
        //student.tuitionDue();
        System.out.println(student.getTuition());
        if (student instanceof TriState) {
            System.out.println("boob");
        }
        System.out.println(student);
    }
}
