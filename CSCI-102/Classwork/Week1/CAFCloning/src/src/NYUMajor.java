package src;
public class NYUMajor {
    // NYUMajor with properties of  Majorname (String), Chair(Faculty), RequiredCredits(int)

    private String majorName;
    private NYUFaculty chair;
    private int requiredCredits;

    public NYUMajor(String majorName, NYUFaculty chair, int requiredCredits) {
        this.majorName = majorName;
        this.chair = chair;
        this.requiredCredits = requiredCredits;
    }

    public String getMajorName() {
        return this.majorName;
    }

    public NYUFaculty getChair() {
        return this.chair;
    }

    public int getRequiredCredits() {
        return this.requiredCredits;
    }

    public void setChair(NYUFaculty chair) {
        this.chair = chair;
    }
}
