public class Patient extends Person{
    private int UniqueId;

    public Patient(String firstName, String surName, String dateOfBirth, int mobileNumber, int uniqueId) {
        super(firstName, surName, dateOfBirth, mobileNumber);
        UniqueId = uniqueId;
    }

    public int getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(int uniqueId) {
        UniqueId = uniqueId;
    }

    @Override
    public String toString() {
        return "Patient{" + super.toString()+
                "UniqueId=" + UniqueId +
                '}';
    }
}
