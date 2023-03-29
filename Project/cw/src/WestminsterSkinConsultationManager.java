import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    static Scanner input = new Scanner(System.in);       //take input from the standard input
    protected static ArrayList<Doctor> westminsterArrayList = new ArrayList<>();   //empty array list for store data

    public static void main(String[] args) throws IOException {
        fileLoad();
        while (true) {
            System.out.println("-------------SKIN CONSULTATION CENTRE-----------------");
            System.out.println("Press 'M' to display the MENU SYSTEM");
            System.out.println("Press 'Q' to QUIT");
            System.out.println("------------------------------------------------------");
            System.out.print("\nEnter (M Q): ");
            String enter = input.next();
            if (enter.equalsIgnoreCase("M")) {
                WestminsterSkinConsultationManager.console();
            } else if (enter.equalsIgnoreCase("Q")) {
                break;
            } else {
                System.out.println("INVALID INPUT !!! TRY AGAIN...");
            }
        }
    }
    private static void console() throws IOException {
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
            System.out.println("----------------------Menu-----------------------");
            System.out.println("A  = Add a new doctor");
            System.out.println("D  = Delete a doctor ");
            System.out.println("P  = Print the list of the doctors");
            System.out.println("S  = Save in a file");
            System.out.println("G  = Open GUI file");
            System.out.println("B  = Back ");
            System.out.println("------------------------------------------------");
            while (true) {
                Scanner input = new Scanner(System.in);
                System.out.print("\nEnter (A D P S G B): ");
                String enter = input.next();
                switch (enter.toUpperCase()) {         //switch case
                    case "A" -> {
                        System.out.println("---ADDING A DOCTOR---");
                        manager.AddNewDoctor();
                    }
                    case "D" -> {
                        System.out.println("---DELETING A DOCTOR---");
                        manager.DeleteDoctor();
                    }
                    case "P" -> {
                        System.out.println("---LIST OF DOCTORS---");
                        manager.PrintDoctor();
                    }
                    case "S" -> {
                        System.out.println("---SAVING DATA---");
                        manager.fileSave("save Doctor.txt");
                    }
                    case "G" -> manager.gui();
                }
                if (enter.equalsIgnoreCase("B")) {
                    break;
            }
        }
    }
    public void gui() throws FileNotFoundException {
        Consultation consultation = new Consultation();
    }
    @Override
    public void AddNewDoctor() {   //method to add new doctor
        if (westminsterArrayList.size() >= 10) {
            System.out.println("THE LIST IS FULL !!!");
        } else {
            try {
                System.out.print("Enter the first name: ");
                String FirstName = input.next();
                System.out.print("Enter the surname: ");
                String SurName = input.next();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                System.out.print("Enter the date of birth [DD/MM/YYYY]: ");
                String DateOfBirth = input.next();
                try {
                    Date date = dateFormat.parse(DateOfBirth);
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("try again");

                }
                System.out.print("Enter the mobile number: ");
                int mobile = input.nextInt();
                System.out.print("Enter the medical licence number: ");
                int licence = input.nextInt();
                System.out.print("Enter the specialisation: ");
                String specialisation = input.next();
                Doctor doctor = new Doctor(FirstName, SurName, DateOfBirth, mobile, licence, specialisation);
                westminsterArrayList.add(doctor);
            }catch (Exception e){
                System.out.println("INVALID INPUT TRY AGAIN...");
            }
            System.out.println("---Successfully added " + westminsterArrayList.size() + " doctor to the list---");
        }
    }
    @Override
    public void DeleteDoctor(){  //method to delete a doctor
        System.out.print("Enter the medical licence number: ");
        int deleteLicence = input.nextInt();
        for (int i = 0; i < westminsterArrayList.size(); i++) {
            if (westminsterArrayList.get(i).getMedicalLicenseNumber() == deleteLicence) {
                System.out.println("Successfully removed " + westminsterArrayList.get(i));
                westminsterArrayList.remove(i);
            }
        }
        System.out.println("remaining doctors " + westminsterArrayList.size());
    }
    @Override
    public void PrintDoctor(){  //method to print all the doctors
        if (westminsterArrayList.isEmpty()) {
            System.out.println("No doctors at this moment !!!");
        } else {
            Comparator<Doctor>compareBySurname=Comparator.comparing(Doctor::getSurName);  // sorting list by alphabetical order
            Collections.sort(westminsterArrayList,compareBySurname);
            westminsterArrayList.forEach(System.out::println);
            }
        }
    @Override
    public void fileSave(String fileName) throws IOException {    //method to save data in a file
        PrintWriter out=new PrintWriter(fileName);
        for (int i=0; i<westminsterArrayList.size(); i++){
            out.println(westminsterArrayList.get(i));
        }
        out.close();
        System.out.println("Saved successfully");
    }

    public static void fileLoad() throws FileNotFoundException {   //method to load data
        try {
            File file = new File("C:\\Users\\DELL\\Desktop\\w1867421 OOP Coursework\\Project\\cw\\save Doctor.txt");
            Scanner data = new Scanner(file);
            int start;              //index starter
            int end;               //index ending
            String Brackets = "}";
            String Equal = "=";
            String Comma = ",";
            while (data.hasNextLine()) {
                String newline = data.nextLine();
                start = newline.indexOf(Equal);
                end = newline.indexOf(Comma);
                String MedicalLicenceNumber = newline.substring(start + 1, end);
                int MedicalLicenceNumberConverted = Integer.parseInt(MedicalLicenceNumber);
                start = end + 18;
                end = newline.indexOf(Comma, start);
                String Specialisation = newline.substring(start, end - 1);
                start = end + 13;
                end = newline.indexOf(Comma, start);
                String Firstname = newline.substring(start, end - 1);
                start = end + 11;
                end = newline.indexOf(Comma, start);
                String SurName = newline.substring(start, end - 1);
                start = end + 15;
                end = newline.indexOf(Comma, start);
                String DateOfBirth = newline.substring(start, end-1);
                start = end + 15;
                end = newline.indexOf(Brackets);
                String MobileNumber = newline.substring(start, end );
                int MobileNumberConverted = Integer.parseInt(MobileNumber);
                Doctor doctor = new Doctor(Firstname, SurName, DateOfBirth, MobileNumberConverted, MedicalLicenceNumberConverted, Specialisation);
                westminsterArrayList.add(doctor);
            }
        }catch (IOException exception) {
            System.out.println("NO SAVED DOCTORS !!!");
        }
    }
}

