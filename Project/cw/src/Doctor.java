public class Doctor extends Person {
    private int MedicalLicenseNumber;
    private String Specialisation;

    public Doctor(String firstName, String surName, String dateOfBirth, int mobileNumber, int medicalLicenseNumber, String specialisation) {
        super(firstName, surName, dateOfBirth, mobileNumber);
        MedicalLicenseNumber = medicalLicenseNumber;
        Specialisation = specialisation;
    }

    public int getMedicalLicenseNumber() {
        return MedicalLicenseNumber;
    }

    public void setMedicalLicenseNumber(int medicalLicenseNumber) {
        MedicalLicenseNumber = medicalLicenseNumber;
    }

    public String getSpecialisation() {
        return Specialisation;
    }

    public void setSpecialisation(String specialisation) {
        Specialisation = specialisation;
    }


    @Override
    public String toString() {
        return "Doctor{" +
                "MedicalLicenseNumber=" + MedicalLicenseNumber +
                ", Specialisation='" + Specialisation + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", SurName='" + SurName + '\'' +
                ", DateOfBirth='" + DateOfBirth + '\'' +
                ", MobileNumber=" + MobileNumber +
                '}';
    }
}

