import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Consultation extends JFrame {
    ImageIcon image=new ImageIcon("src/clinicImage.jpg");
    private JTextField patientFirstNameTextField;
    private JTextField patientSurNameTextField;
    private JTextField dobTextField;
    private JTextField mobileTextField;
    private JTextField NICTextField;
    private JTextField noteTestField;
    private JComboBox<String> comboBox;
    private JRadioButton button1;
    private JRadioButton button2;
    private JRadioButton button3;
    private JRadioButton button4;
    private JRadioButton button5;
    private JRadioButton hourButton1;
    private JRadioButton hourButton2;
    private JRadioButton costButton1;
    private JRadioButton costButton2;
    private JButton bookButton;
    private String date;
    private String time;
    private String cost;
    private String notes;
    private Doctor doctor;
    private Patient patient;
    protected static String[] consultationHour = new String[1];
    protected static String[] consultationDay = new String[1];
    protected static String[] consultationCost = new String[1];
    protected static String nameSelectionDoctor;   //combo box input
    protected static ArrayList<Consultation> bookedConsultations=new ArrayList<>();   //store data

    public Consultation(Doctor doctor, Patient patient, String date, String time, String cost, String notes) {
        this.doctor=doctor;
        this.patient=patient;
        this.date = date;
        this.time = time;
        this.cost = cost;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "doctor=" + doctor +
                ", patient=" + patient +
                ", date=" + date +
                ", time=" + time +
                ", cost=" + cost +
                ", notes='" + notes + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getCost() {
        return cost;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    Consultation() throws FileNotFoundException {
        fileLoad();
        JTabbedPane tabbedPane=new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(Color.BLUE);
        tabbedPane.setForeground(Color.GREEN);
        tabbedPane.add("List of doctors",listOfDoctors());
        tabbedPane.add("Book consultation",bookConsultation());
        tabbedPane.add("Visualized booked info",visualizedData());
        getContentPane().add(tabbedPane);
        this.add(tabbedPane);
        this.setTitle("GUI Application");
        this.setSize(1500, 700);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(image.getImage());
    }

    public JPanel bookConsultation() {    //channeling the doctor
        JPanel panel1=new JPanel(new BorderLayout());
        JPanel panel2=new JPanel(new GridLayout(10,2));
        panel2.setBackground(Color.GREEN);
        JLabel doctorLabel=new JLabel("NAME OF THE DOCTOR: ");
        doctorLabel.setFont(new Font("times new roman",Font.BOLD,25));
        String[] names = new String[10];
        for (int i = 0; i < WestminsterSkinConsultationManager.westminsterArrayList.size(); i++) {
            names[i]= WestminsterSkinConsultationManager.westminsterArrayList.get(i).getFirstName();
        }
        comboBox=new JComboBox<String>(names);
        comboBox.setBackground(Color.BLUE);
        comboBox.setFont(new Font("times new roman", Font.PLAIN, 25));
        comboBox.setForeground(Color.GREEN);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==comboBox){
                    nameSelectionDoctor= Objects.requireNonNull(comboBox.getSelectedItem()).toString();
                }
            }
        });
        JLabel patientFirstNameLabel=new JLabel("FIRST NAME OF THE PATIENT: ");
        patientFirstNameLabel.setFont(new Font("times new roman",Font.BOLD,25));
        patientFirstNameTextField=new JTextField();
        patientFirstNameTextField.setFont(new Font("times new roman", Font.PLAIN, 25));
        patientFirstNameTextField.setForeground(new Color(0x00FF00));
        patientFirstNameTextField.setBackground(Color.BLUE);
        JLabel patientSurNameLabel=new JLabel("SURNAME OF THE PATIENT: ");
        patientSurNameLabel.setFont(new Font("times new roman",Font.BOLD,25));
        patientSurNameTextField =new JTextField();
        patientSurNameTextField.setFont(new Font("times new roman", Font.PLAIN, 25));
        patientSurNameTextField.setForeground(new Color(0x00FF00));
        patientSurNameTextField.setBackground(Color.BLUE);
        JLabel dObLabel=new JLabel("DATE OF BIRTH OF THE PATIENT: ");
        dObLabel.setFont(new Font("times new roman",Font.BOLD,25));
        dobTextField =new JTextField();
        dobTextField.setFont(new Font("times new roman", Font.PLAIN, 25));
        dobTextField.setForeground(new Color(0x00FF00));
        dobTextField.setBackground(Color.BLUE);
        JLabel mobileNoLabel=new JLabel("MOBILE NO OF THE PATIENT: ");
        mobileNoLabel.setFont(new Font("times new roman",Font.BOLD,25));
        mobileTextField=new JTextField();
        mobileTextField.setFont(new Font("times new roman", Font.PLAIN, 25));
        mobileTextField.setForeground(new Color(0x00FF00));
        mobileTextField.setBackground(Color.BLUE);
        JLabel NICLabel=new JLabel("NIC NO OF THE PATIENT:");
        NICLabel.setFont(new Font("times new roman",Font.BOLD,25));
        NICTextField=new JTextField();
        NICTextField.setFont(new Font("times new roman", Font.PLAIN, 25));
        NICTextField.setForeground(new Color(0x00FF00));
        NICTextField.setBackground(Color.BLUE);
        JLabel day=new JLabel("SELECT THE DAY: ");
        day.setFont(new Font("times new roman",Font.BOLD,25));
        JPanel jPanel=new JPanel(new GridLayout(1,5));
        button1=new JRadioButton("MONDAY");
        button1.setBackground(Color.BLUE);
        button1.setForeground(Color.GREEN);
        button1.setFocusable(false);
        button2=new JRadioButton("TUESDAY");
        button2.setBackground(Color.BLUE);
        button2.setForeground(Color.GREEN);
        button2.setFocusable(false);
        button3=new JRadioButton("WEDNESDAY");
        button3.setBackground(Color.BLUE);
        button3.setForeground(Color.GREEN);
        button3.setFocusable(false);
        button4=new JRadioButton("THURSDAY");
        button4.setBackground(Color.BLUE);
        button4.setForeground(Color.GREEN);
        button4.setFocusable(false);
        button5=new JRadioButton("FRIDAY");
        button5.setBackground(Color.BLUE);
        button5.setForeground(Color.GREEN);
        button5.setFocusable(false);
        JLabel Hour =new JLabel("SELECT THE TIME: ");
        Hour.setFont(new Font("times new roman",Font.BOLD,25));
        JPanel jPanel2=new JPanel(new GridLayout(1,3));
        hourButton1=new JRadioButton("8 am - 9 am");
        hourButton1.setBackground(Color.BLUE);
        hourButton1.setForeground(Color.GREEN);
        hourButton1.setFocusable(false);
        hourButton2=new JRadioButton("9 am - 10 am");
        hourButton2.setBackground(Color.BLUE);
        hourButton2.setForeground(Color.GREEN);
        hourButton2.setFocusable(false);
        JLabel doctorCost=new JLabel("SELECT THE COST: ");
        doctorCost.setFont(new Font("times new roman",Font.BOLD,25));
        JPanel jPanel3=new JPanel(new GridLayout(1,1));
        costButton1=new JRadioButton("£ 15");
        costButton1.setBackground(Color.BLUE);
        costButton1.setForeground(Color.GREEN);
        costButton1.setFocusable(false);
        costButton2=new JRadioButton("£ 25");
        costButton2.setBackground(Color.BLUE);
        costButton2.setForeground(Color.GREEN);
        costButton2.setFocusable(false);
        JLabel noteLabel=new JLabel("NOTES: ");
        noteLabel.setFont(new Font("times new roman",Font.BOLD,25));
        noteTestField=new JTextField();
        noteTestField.setFont(new Font("times new roman", Font.PLAIN, 25));
        noteTestField.setForeground(new Color(0x00FF00));
        noteTestField.setBackground(Color.BLUE);
        ButtonGroup buttonGroup =new ButtonGroup();
        buttonGroup.add(button1);
        buttonGroup.add(button2);
        buttonGroup.add(button3);
        buttonGroup.add(button4);
        buttonGroup.add(button5);
        jPanel.add(button1);
        jPanel.add(button2);
        jPanel.add(button3);
        jPanel.add(button4);
        jPanel.add(button5);
        ButtonGroup buttonGroup2=new ButtonGroup();
        buttonGroup2.add(hourButton1);
        buttonGroup2.add(hourButton2);
        jPanel2.add(hourButton1);
        jPanel2.add(hourButton2);
        ButtonGroup buttonGroup3=new ButtonGroup();
        buttonGroup3.add(costButton1);
        buttonGroup3.add(costButton2);
        jPanel3.add(costButton1);
        jPanel3.add(costButton2);
        panel2.add(doctorLabel);
        panel2.add(comboBox);
        panel2.add(patientFirstNameLabel);
        panel2.add(patientFirstNameTextField);
        panel2.add(patientSurNameLabel);
        panel2.add(patientSurNameTextField);
        panel2.add(dObLabel);
        panel2.add(dobTextField);
        panel2.add(mobileNoLabel);
        panel2.add(mobileTextField);
        panel2.add(NICLabel);
        panel2.add(NICTextField);
        panel2.add(day);
        panel2.add(jPanel);
        panel2.add(Hour);
        panel2.add(jPanel2);
        panel2.add(doctorCost);
        panel2.add(jPanel3);
        panel2.add(noteLabel);
        panel2.add(noteTestField);
        panel1.add(panel2,BorderLayout.NORTH);
        bookButton=new JButton("BOOK CONSULTATION");
        bookButton.setBackground(Color.BLUE);
        bookButton.setForeground(Color.GREEN);
        bookButton.setFocusable(false);
        panel1.add(bookButton,BorderLayout.EAST);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==button1){
                    consultationDay[0] ="monday";
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==button2){
                    consultationDay[0] ="tuesday";
                }
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==button3){
                    consultationDay[0] ="wednesday";
                }
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==button4){
                    consultationDay[0] ="thursday";
                }
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==button5){
                    consultationDay[0] ="friday";
                }
            }
        });
        hourButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==hourButton1){
                    consultationHour[0] ="8 - 9";
                }
            }
        });
        hourButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==hourButton2){
                    consultationHour[0] ="9 - 10";
                }
            }
        });
        costButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==costButton1){
                    consultationCost[0] ="15";
                }
            }
        });
        costButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==costButton2){
                    consultationCost[0] ="25";
                }
            }
        });
        bookButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource()==bookButton) {
                    Doctor newDoctor =null;
                    for (int i = 0; i < WestminsterSkinConsultationManager.westminsterArrayList.size(); i++) {
                        String nameInput = WestminsterSkinConsultationManager.westminsterArrayList.get(i).getFirstName();
                        if (nameInput.equals(nameSelectionDoctor)) {
                            newDoctor = WestminsterSkinConsultationManager.westminsterArrayList.get(i);
                        }
                    }
                    String patientNotes=noteTestField.getText();                      //encrypting text
                    byte[] getNotes = Base64.getEncoder().encode(patientNotes.getBytes());
                    String encrypted=new String(getNotes);

                    for (Consultation value : bookedConsultations) {                  //cost generating
                        if (value.patient.getUniqueId() == Integer.parseInt(NICTextField.getText())) {
                            consultationCost[0] = "25";
                        }
                    }
                    Patient patient1 = new Patient(patientFirstNameTextField.getText(),patientSurNameTextField.getText(),dobTextField.getText(),Integer.parseInt(mobileTextField.getText()),Integer.parseInt(NICTextField.getText()));
                    Consultation consultation = new Consultation(newDoctor, patient1, consultationDay[0], consultationHour[0], consultationCost[0], encrypted);
                    bookedConsultations.add(consultation);
                    try {
                        fileSave();
                    } catch (FileNotFoundException er) {
                        throw new RuntimeException(er);
                    }
                }
            }
        });
        return panel1;
    }

    public void fileSave() throws FileNotFoundException {    //saving the channeling data
        PrintWriter out=new PrintWriter("save Channelling Info.txt");
        for (Consultation consultation : bookedConsultations) {
            out.println(consultation);
        }
        out.close();
    }

    public static void fileLoad() throws FileNotFoundException{  //loading the channeling data
        try {
            File file = new File("C:\\Users\\DELL\\Desktop\\w1867421 OOP Coursework\\Project\\cw\\save Channelling Info.txt");
            Scanner inputData = new Scanner(file);
            int start;
            int end;
            String Brackets = "}";
            String Equal = "=";
            String Comma = ",";
            while (inputData.hasNextLine()) {
                String newline = inputData.nextLine();
                start = newline.indexOf(Equal);
                end = newline.indexOf(Comma);
                String MedicalLicenceNumber = newline.substring(start + 29, end);
                int MedicalLicenceNumberInt = Integer.parseInt(MedicalLicenceNumber);
                start = end + 18;
                end = newline.indexOf(Comma, start);
                String Specialisation = newline.substring(start, end - 1);
                start = end + 13;
                end = newline.indexOf(Comma, start);
                String FirstName = newline.substring(start, end - 1);
                start = end + 11;
                end = newline.indexOf(Comma, start);
                String SurName = newline.substring(start, end - 1);
                start = end + 15;
                end = newline.indexOf(Comma, start);
                String DateOfBirth = newline.substring(start, end-1);
                start = end + 14;
                end = newline.indexOf(Brackets);
                String MobileNumber = newline.substring(start+1, end );
                int MobileNumberInt = Integer.parseInt(MobileNumber);
                Doctor newDoctor=new Doctor(FirstName,SurName,DateOfBirth,MobileNumberInt,MedicalLicenceNumberInt,Specialisation);
                start = end + 37;
                end = newline.indexOf(Comma, start);
                String PatientFirstName = newline.substring(start, end-1);
                start = end + 11;
                end = newline.indexOf(Comma, start);
                String PatientSurName = newline.substring(start, end-1);
                start = end + 15;
                end = newline.indexOf(Comma, start);
                String PatientDateOfBirth = newline.substring(start, end-1);
                start = end + 15;
                end = newline.indexOf(Brackets,start);
                String PatientMobileNumber = newline.substring(start, end);
                int PatientMobileNumberInt = Integer.parseInt(PatientMobileNumber);
                start = end + 10;
                end = newline.indexOf(Comma, start);
                String PatientNIC = newline.substring(start, end-1);
                int PatientNICInt = Integer.parseInt(PatientNIC);
                Patient newPatient=new Patient(PatientFirstName,PatientSurName,PatientDateOfBirth,PatientMobileNumberInt,PatientNICInt);
                start = end + 7;
                end = newline.indexOf(Comma, start);
                String day = newline.substring(start, end);
                start = end + 7;
                end = newline.indexOf(Comma, start);
                String hour = newline.substring(start, end);
                start = end + 7;
                end = newline.indexOf(Comma, start);
                String cost = newline.substring(start, end);
                start = end + 9;
                end = newline.indexOf(Brackets, start);
                String notes = newline.substring(start, end-1);
                Consultation inputConsultation=new Consultation(newDoctor,newPatient,day,hour,cost,notes);  //new consultation
                bookedConsultations.add(inputConsultation);
            }
        }catch (IOException ioe){
            System.out.println("EMPTY SAVE CONSULTATIONS !");
        }
    }
    public JPanel visualizedData(){    //visualise the data
        JPanel panel=new JPanel(new BorderLayout());
        String[] tableHeaders = {"Doctor First name"," Doctor Surname","Date Of Birth","Mobile Number","Medical Licence Number","Specialisation","Patient NIC","Patient First Name"," Patient Surname","Date Of Birth","Mobile Number","Day","Hour","Cost","Notes"};
        DefaultTableModel modelOfTable=new DefaultTableModel(tableHeaders,0);
        JTable table=new JTable(modelOfTable);
        table.setBackground(Color.yellow);
        DefaultTableCellRenderer defaultTableCellRenderer=new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        String[] objects = new String[15];
        for (int i=0; i<bookedConsultations.size(); i++){    //getting objects from arraylist
            objects[0] = bookedConsultations.get(i).doctor.getFirstName();
            objects[1] = bookedConsultations.get(i).doctor.getSurName();
            objects[2] = String.valueOf(bookedConsultations.get(i).doctor.getDateOfBirth());
            objects[3] = String.valueOf(bookedConsultations.get(i).doctor.getMobileNumber());
            objects[4] = String.valueOf(bookedConsultations.get(i).doctor.getSpecialisation());
            objects[5] = bookedConsultations.get(i).doctor.getSpecialisation();
            objects[6] = String.valueOf(bookedConsultations.get(i).patient.getUniqueId());
            objects[7] = bookedConsultations.get(i).patient.getFirstName();
            objects[8] = bookedConsultations.get(i).patient.getSurName();
            objects[9] = String.valueOf(bookedConsultations.get(i).patient.getDateOfBirth());
            objects[10] = String.valueOf(bookedConsultations.get(i).patient.getMobileNumber());
            objects[11] = bookedConsultations.get(i).getDate();
            objects[12] = bookedConsultations.get(i).getTime();
            objects[13] = bookedConsultations.get(i).getCost();
            objects[14]=bookedConsultations.get(i).getNotes();
            modelOfTable.addRow(objects);
            table.setCellSelectionEnabled(false);
            table.setColumnSelectionAllowed(false);
            table.setRowSelectionAllowed(true);
            table.setPreferredScrollableViewportSize(new Dimension(1280,550));
            table.setEnabled(false);
            table.setDefaultRenderer(Object.class,defaultTableCellRenderer);
            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setResizingAllowed(false);
            JScrollPane scrollPane=new JScrollPane(table);
            panel.add(scrollPane,BorderLayout.CENTER);
            JButton button=new JButton("VIEW");
            button.setBackground(Color.BLUE);
            button.setForeground(Color.GREEN);
            button.setFocusable(false);
            panel.add(button,BorderLayout.EAST);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource()==button){
                        modelOfTable.setRowCount(0);
                        String[] objects = new String[15];
                        for (int i=0; i<bookedConsultations.size(); i++) {
                            objects[0] = bookedConsultations.get(i).doctor.getFirstName();
                            objects[1] = bookedConsultations.get(i).doctor.getSurName();
                            objects[2] = String.valueOf(bookedConsultations.get(i).doctor.getDateOfBirth());
                            objects[3] = String.valueOf(bookedConsultations.get(i).doctor.getMobileNumber());
                            objects[4] = String.valueOf(bookedConsultations.get(i).doctor.getSpecialisation());
                            objects[5] = bookedConsultations.get(i).doctor.getSpecialisation();
                            objects[6] = String.valueOf(bookedConsultations.get(i).patient.getUniqueId());
                            objects[7] = bookedConsultations.get(i).patient.getFirstName();
                            objects[8] = bookedConsultations.get(i).patient.getSurName();
                            objects[9] = String.valueOf(bookedConsultations.get(i).patient.getDateOfBirth());
                            objects[10] = String.valueOf(bookedConsultations.get(i).patient.getMobileNumber());
                            objects[11] = bookedConsultations.get(i).getDate();
                            objects[12] = bookedConsultations.get(i).getTime();
                            objects[13] = bookedConsultations.get(i).getCost();
                            String notes = bookedConsultations.get(i).getNotes();
                            byte[] view = Base64.getDecoder().decode(notes.getBytes());
                            objects[14] = new String(view);
                            modelOfTable.addRow(objects);
                        }
                    }
                }
            });
        }
        return panel;
    }

    public JPanel listOfDoctors(){   //list of all the doctors
        JPanel panel1=new JPanel(new BorderLayout());
        JPanel panel2=new JPanel(new BorderLayout());
        JLabel label1 = new JLabel("WESTMINSTER SKIN CONSULTATION CENTRE");
        label1.setFont(new Font("MV Boli",Font.BOLD,35));
        panel2.setBackground(Color.GREEN);
        String[] tableHeaders = {"First Name", "Surname", "Date Of Birth","Mobile Number", "Medical Licence Number", "Specialisation"};
        DefaultTableModel modelOfTable=new DefaultTableModel(tableHeaders,0);
        JTable table=new JTable(modelOfTable);
        table.setBackground(Color.lightGray);
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        String[] objects = new String[6];
        for (int i=0; i<WestminsterSkinConsultationManager.westminsterArrayList.size(); i++){
            objects[0]=WestminsterSkinConsultationManager.westminsterArrayList.get(i).getFirstName();
            objects[1]=WestminsterSkinConsultationManager.westminsterArrayList.get(i).getSurName();
            objects[2]= String.valueOf(WestminsterSkinConsultationManager.westminsterArrayList.get(i).getDateOfBirth());
            objects[3]= String.valueOf(WestminsterSkinConsultationManager.westminsterArrayList.get(i).getMobileNumber());
            objects[4]= String.valueOf(WestminsterSkinConsultationManager.westminsterArrayList.get(i).getMedicalLicenseNumber());
            objects[5]=WestminsterSkinConsultationManager.westminsterArrayList.get(i).getSpecialisation();
            modelOfTable.addRow(objects);
        }
        table.setCellSelectionEnabled(false);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);
        table.setPreferredScrollableViewportSize(new Dimension(1400,500));
        table.setDefaultRenderer(Object.class,defaultTableCellRenderer);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        JScrollPane scrollPane=new JScrollPane(table);
        panel2.add(scrollPane,BorderLayout.NORTH);
        panel2.add(label1);
        panel1.add(panel2,BorderLayout.WEST);
        JButton button=new JButton("NAMES IN ALPHABETICAL ORDER");
        button.setBackground(Color.BLUE);
        button.setForeground(Color.GREEN);
        button.setFocusable(false);
        panel2.add(button,BorderLayout.EAST);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==button) {
                    modelOfTable.setRowCount(0);    //sorting by surname
                    WestminsterSkinConsultationManager.westminsterArrayList.sort(Comparator.comparing(Doctor::getSurName));
                    String[] objects = new String[6];
                    for (int i = 0; i < WestminsterSkinConsultationManager.westminsterArrayList.size(); i++) {
                        objects[0] = WestminsterSkinConsultationManager.westminsterArrayList.get(i).getFirstName();
                        objects[1] = WestminsterSkinConsultationManager.westminsterArrayList.get(i).getSurName();
                        objects[2] = String.valueOf(WestminsterSkinConsultationManager.westminsterArrayList.get(i).getDateOfBirth());
                        objects[3] = String.valueOf(WestminsterSkinConsultationManager.westminsterArrayList.get(i).getMobileNumber());
                        objects[4] = String.valueOf(WestminsterSkinConsultationManager.westminsterArrayList.get(i).getMedicalLicenseNumber());
                        objects[5] = WestminsterSkinConsultationManager.westminsterArrayList.get(i).getSpecialisation();
                        modelOfTable.addRow(objects);
                    }
                }
            }
        });
        JPanel panel3=new JPanel(new GridLayout(3,5));
        JLabel monday=new JLabel("  MONDAY");
        monday.setForeground(Color.RED);
        panel3.add(monday);
        JLabel tuesday=new JLabel("  TUESDAY");
        tuesday.setForeground(Color.RED);
        panel3.add(tuesday);
        JLabel wednesday=new JLabel("  WEDNESDAY");
        wednesday.setForeground(Color.RED);
        panel3.add(wednesday);
        JLabel thursday=new JLabel("  THURSDAY");
        thursday.setForeground(Color.RED);
        panel3.add(thursday);
        JLabel friday=new JLabel("  FRIDAY");
        friday.setForeground(Color.RED);
        panel3.add(friday);
        ArrayList<JLabel> channelingHour1=new ArrayList<>();
        ArrayList<JLabel> channelingHour2=new ArrayList<>();
        for (int i=0; i<5; i++){
            JLabel label=new JLabel("8 am - 9 am");
            channelingHour1.add(label);
            panel3.add(label);
        }
        for (int i=0; i<5; i++){
            JLabel label=new JLabel("9 pm - 10 pm");
            channelingHour2.add(label);
            panel3.add(label);
        }
        panel2.add(panel3,BorderLayout.LINE_START);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable pointer=(JTable)e.getSource();
                int row=pointer.getSelectedRow();
                String doctorName= (String) modelOfTable.getValueAt(row,0);
                for (int x=0; x<5; x++){
                    channelingHour1.get(x).setBackground(Color.green); //available colour green
                    channelingHour1.get(x).setOpaque(true);
                }
                for (int x=0; x<5; x++){
                    channelingHour2.get(x).setBackground(Color.green);
                    channelingHour2.get(x).setOpaque(true);
                }
                for (int i=0; i<bookedConsultations.size(); i++){
                    if (bookedConsultations.get(i).doctor.getFirstName().equals(doctorName)){
                        if (bookedConsultations.get(i).getTime().equals("8 - 9")){
                            if (bookedConsultations.get(i).getDate().equals("monday")){
                                channelingHour1.get(0).setBackground(Color.BLUE);    //not available colour blue
                            }
                            if (bookedConsultations.get(i).getDate().equals("tuesday")){
                                channelingHour1.get(1).setBackground(Color.BLUE);
                            }
                            if (bookedConsultations.get(i).getDate().equals("wednesday")){
                                channelingHour1.get(2).setBackground(Color.BLUE);
                            }
                            if (bookedConsultations.get(i).getDate().equals("thursday")){
                                channelingHour1.get(3).setBackground(Color.BLUE);
                            }
                            if (bookedConsultations.get(i).getDate().equals("friday")){
                                channelingHour1.get(4).setBackground(Color.BLUE);
                            }
                        }
                        if (bookedConsultations.get(i).getTime().equals("9 - 10")) {
                            if (bookedConsultations.get(i).getDate().equals("monday")) {
                                channelingHour2.get(0).setBackground(Color.BLUE);
                            }
                            if (bookedConsultations.get(i).getDate().equals("tuesday")) {
                                channelingHour2.get(1).setBackground(Color.BLUE);
                            }
                            if (bookedConsultations.get(i).getDate().equals("wednesday")) {
                                channelingHour2.get(2).setBackground(Color.BLUE);
                            }
                            if (bookedConsultations.get(i).getDate().equals("thursday")) {
                                channelingHour2.get(3).setBackground(Color.BLUE);
                            }
                            if (bookedConsultations.get(i).getDate().equals("friday")) {
                                channelingHour2.get(4).setBackground(Color.BLUE);
                            }
                        }
                    }
                }
            }
        });
        return panel1;
    }
}