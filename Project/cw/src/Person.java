public abstract class  Person {
    protected String FirstName;
    protected String SurName;
    protected String DateOfBirth;
    protected int MobileNumber;

    public Person(String firstName, String surName, String dateOfBirth, int mobileNumber) {
        FirstName = firstName;
        SurName = surName;
        DateOfBirth = dateOfBirth;
        MobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public int getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        MobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "FirstName='" + FirstName + '\'' +
                ", SurName='" + SurName + '\'' +
                ", DateOfBirth='" + DateOfBirth + '\'' +
                ", MobileNumber=" + MobileNumber +
                '}';
    }
}
