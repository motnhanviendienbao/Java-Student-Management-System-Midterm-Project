package org.example;

import com.opencsv.CSVWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class StudentManagementSystem extends JFrame implements ActionListener {
    JLabel jtitle;
    JLabel studentName, studentID, studentGrade, dobLabel, genderLabel, contactLabel, emailLabel,facultyLabel,majorLabel,classLabel,studentIDCertLabel,certNameLable,issueDateLable,issueAuthorityLable;
    JTextField jstudentName, jstudentID, jstudentGrade, dobField, contactField, emailField, searchField, classField,studentIDCertField,certNameField,issueDateField,issueAuthorityField;
    JRadioButton maleRadio, femaleRadio;
    ButtonGroup genderGroup;
    JButton addStudent, updateStudent, deleteRecord, searchButton, sortStudent,importStudent,exportStudent, accessStudentDetail,addCert,updateCert,deleteCert,importCert,exportCert,studentIDCertViewButon;
    JTable studentTable,certificateTable;
    DefaultTableModel tableModelStudent,tableModelcertificate ;
    String[] facultyNames = {"Software Engineering", "Science", "Business", "Arts", "Medicine"};
    String[] software = {"Network Computer", "Computer Science", "software technology"};
    String[] science = {"Engineering", "Lab researching"};
    String[] arts = {"architecture", "designer", "constructor Engineering"};
    String[] medicine = { "medicine Science", "Business medicine"};
    String[] business = {"international bussiness", "managae bussiness"};
    String selectedFaculty;
    String selectedMajor;




    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/student?createDatabaseIfNotExist=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private Connection connection;
    private List<Student> studentList = new ArrayList<Student>();


    public StudentManagementSystem() {
        setTitle("Student Management System by Group 5");
        setLayout(null);
        setSize(1000, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jtitle = new JLabel("STUDENT MANAGEMENT SYSTEM");
        jtitle.setBounds(250, 10, 700, 50);
        jtitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        studentName = new JLabel("Student Name");
        studentName.setBounds(50, 80, 150, 30);

        studentID = new JLabel("Student ID");
        studentID.setBounds(50, 120, 150, 30);

        studentGrade = new JLabel("Student Grade");
        studentGrade.setBounds(50, 160, 150, 30);

        dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(50, 200, 150, 30);



        studentIDCertLabel = new JLabel("Student ID");
        studentIDCertLabel.setBounds(920, 80, 150, 30);

        certNameLable = new JLabel("Certificate Name");
        certNameLable.setBounds(920, 120, 150, 30);

        issueDateLable = new JLabel("Issue Date");
        issueDateLable.setBounds(920, 160, 150, 30);

        issueAuthorityLable = new JLabel("Issue Authority");
        issueAuthorityLable.setBounds(920, 200, 150, 30);





        genderLabel = new JLabel("Gender");
        genderLabel.setBounds(50, 240, 150, 30);

        contactLabel = new JLabel("Contact number");
        contactLabel.setBounds(50, 280, 150, 30);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 320, 150, 30);

        facultyLabel = new JLabel("Faculty");
        facultyLabel.setBounds(50, 360, 150, 30);

        majorLabel = new JLabel("Major");
        majorLabel.setBounds(50, 400, 150, 30);

        classLabel = new JLabel("Class");
        classLabel.setBounds(50, 440, 150, 30);


        jstudentName = new JTextField();
        jstudentName.setBounds(200, 80, 200, 30);

        jstudentID = new JTextField();
        jstudentID.setBounds(200, 120, 200, 30);

        jstudentGrade = new JTextField();
        jstudentGrade.setBounds(200, 160, 200, 30);

        dobField = new JTextField();
        dobField.setBounds(200, 200, 200, 30);


        studentIDCertField = new JTextField();
        studentIDCertField.setBounds(1070, 80, 200, 30);



        certNameField = new JTextField();
        certNameField.setBounds(1070, 120, 200, 30);

        issueDateField = new JTextField();
        issueDateField.setBounds(1070, 160, 200, 30);

        issueAuthorityField = new JTextField();
        issueAuthorityField.setBounds(1070, 200, 200, 30);

        maleRadio = new JRadioButton("Male");
        maleRadio.setBounds(200, 240, 80, 30);

        femaleRadio = new JRadioButton("Female");
        femaleRadio.setBounds(290, 240, 100, 30);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);




        contactField = new JTextField();
        contactField.setBounds(200, 280, 200, 30);

        emailField = new JTextField();
        emailField.setBounds(200, 320, 200, 30);

        // Set font and size
        JComboBox<String> facultySelectionBox = new JComboBox<>(facultyNames);
        facultySelectionBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        facultySelectionBox.setBounds(200, 360, 200, 30);
        add(facultySelectionBox);

        facultySelectionBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item
                selectedFaculty = (String) facultySelectionBox.getSelectedItem();

                // Find the index of the selected faculty in the array
                int selectedIndex = facultySelectionBox.getSelectedIndex();
                if(selectedIndex == 0){
                    JComboBox<String> SoftwareSelectionBox = new JComboBox<>(software);
                    SoftwareSelectionBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
                    SoftwareSelectionBox.setBounds(200, 400, 200, 30);
                    add(SoftwareSelectionBox);
                    selectedMajor = (String) SoftwareSelectionBox.getSelectedItem();


                } else if (selectedIndex == 1) {

                    JComboBox<String> scieneSelectionBox = new JComboBox<>(science);
                    scieneSelectionBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
                    scieneSelectionBox.setBounds(200, 400, 200, 30);
                    add(scieneSelectionBox);
                    selectedMajor = (String) scieneSelectionBox.getSelectedItem();

                } else if(selectedIndex ==2){

                    JComboBox<String> businessSelectionBox = new JComboBox<>(business);
                    businessSelectionBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
                    businessSelectionBox.setBounds(200, 400, 200, 30);
                    add(businessSelectionBox);
                    selectedMajor = (String) businessSelectionBox.getSelectedItem();

                } else if(selectedIndex == 3){

                    JComboBox<String> artsSelectionBox = new JComboBox<>(arts);
                    artsSelectionBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
                    artsSelectionBox.setBounds(200, 400, 200, 30);
                    add(artsSelectionBox);
                    selectedMajor = (String) artsSelectionBox.getSelectedItem();

                } else if(selectedIndex == 4){

                    JComboBox<String> medicineSelectionBox = new JComboBox<>(medicine);
                    medicineSelectionBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
                    medicineSelectionBox.setBounds(200, 400, 200, 30);
                    add(medicineSelectionBox);
                    selectedMajor = (String) medicineSelectionBox.getSelectedItem();

                }


            }
        });

        classField = new JTextField();
        classField.setBounds(200, 440, 200, 30);

        addStudent = new JButton("Add Student");
        addStudent.setBounds(650, 80, 150, 30);

        updateStudent = new JButton("Update Student");
        updateStudent.setBounds(650, 120, 150, 30);

        deleteRecord = new JButton("Delete Record");
        deleteRecord.setBounds(650, 160, 150, 30);

        sortStudent = new JButton("Sort Student List");
        sortStudent.setBounds(650, 200, 150, 30);

        accessStudentDetail = new JButton("Student Detail");
        accessStudentDetail.setBounds(650, 240, 150, 30);
// detail student here
        addCert = new JButton("Add Certificate");
        addCert.setBounds(1370, 80, 150, 30);

        updateCert = new JButton("Update Certificate");
        updateCert.setBounds(1370, 120, 150, 30);

        deleteCert = new JButton("Delete Certificate");
        deleteCert.setBounds(1370, 160, 150, 30);

        importCert = new JButton("Import Certificate ");
        importCert.setBounds(1370, 200, 150, 30);

        exportCert = new JButton("Export Certificate");
        exportCert.setBounds(1370, 240, 150, 30);

        studentIDCertViewButon = new JButton("View Certificate By ID Student Above");
        studentIDCertViewButon.setBounds(920, 240, 350, 30);



        importStudent = new JButton("Import Student");
        importStudent.setBounds(650, 280, 150, 30);

        exportStudent = new JButton("Export Student");
        exportStudent.setBounds(650, 320, 150, 30);


        searchField = new JTextField();
        searchField.setBounds(50, 500, 300, 30);

        searchButton = new JButton("Search by ID");
        searchButton.setBounds(360, 500, 150, 30);

        add(jtitle);
        add(studentName);
        add(studentID);
        add(studentGrade);
        add(dobLabel);
        add(genderLabel);
        add(contactLabel);
        add(emailLabel);
        add(facultyLabel);
        add(majorLabel);
        add(classLabel);
        add(classField);
        add(studentIDCertLabel);
        add(studentIDCertField);
        add(certNameLable);
        add(certNameField);
        add(issueDateLable);
        add(issueDateField);
        add(issueAuthorityLable);
        add(issueAuthorityField);

        add(jstudentName);
        add(jstudentID);
        add(jstudentGrade);
        add(dobField);
        add(maleRadio);
        add(femaleRadio);
        add(contactField);
        add(emailField);
        add(addStudent);
        add(updateStudent);
        add(deleteRecord);
        add(importStudent);
        add(exportStudent);
        add(addCert);
        add(updateCert);
        add(deleteCert);
        add(importCert);
        add(exportCert);
        add(studentIDCertViewButon);





        add(sortStudent);
        add(accessStudentDetail);
        add(searchField);
        add(searchButton);

        String[] columnNames = {"Student Name", "Student ID", "Student Grade", "Date of Birth", "Gender", "Contact Name", "Email", "Falcuty", "Major", "Class"};
        tableModelStudent = new DefaultTableModel(columnNames, 0);

        String[] columnNameCert = {"Student ID", "Certificate Name", "Issue Date", "Issue Authority"};
        tableModelcertificate = new DefaultTableModel(columnNameCert,0);

        studentTable = new JTable(tableModelStudent);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(50, 550, 850, 150);
        add(scrollPane);

        certificateTable = new JTable(tableModelcertificate);
        JScrollPane scrollPaneCert = new JScrollPane(certificateTable);
        scrollPaneCert.setBounds(920, 280, 600, 420);
        add(scrollPaneCert);

        addStudent.addActionListener(this);
        updateStudent.addActionListener(this);
        deleteRecord.addActionListener(this);
        searchButton.addActionListener(this);
        importStudent.addActionListener(this);
        exportStudent.addActionListener(this);
        sortStudent.addActionListener(this);
        addCert.addActionListener(this);
        updateCert.addActionListener(this);
        deleteCert.addActionListener(this);
        importCert.addActionListener(this);
        exportCert.addActionListener(this);
        studentIDCertViewButon.addActionListener(this);

        connectToDatabase();
        loadStudentDataFromDatabase();
        loadCertDataFromDatabase();
    }
//    importStudent.addActionListener(e -> importCSV());

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStudent) {
            String name = jstudentName.getText();
            String id = jstudentID.getText().toUpperCase();
            String grade = jstudentGrade.getText();
            String dob = dobField.getText();
            String contact = contactField.getText();
            String email = emailField.getText();
            String classText = classField.getText().toUpperCase();
            String gender = maleRadio.isSelected() ? "Male" : "Female";
            String falcuty = selectedFaculty;
            String major = selectedMajor;

            if (name.isEmpty() || grade.isEmpty() || dob.isEmpty() || contact.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(this, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidDate(dob)) {
                JOptionPane.showMessageDialog(this, "Invalid date of birth. Use the format 'dd-MM-yyyy'.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidGrade(grade)) {
                JOptionPane.showMessageDialog(this, "Invalid student grade. It should be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidStudentID(id)) {
                JOptionPane.showMessageDialog(this, "Invalid student ID. It should be corect format.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidContactNumber(contact)) {
                JOptionPane.showMessageDialog(this, "Invalid contact number. It should be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidClass(classText)) {
                JOptionPane.showMessageDialog(this, "Invalid Class Format.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (checkDuplicateInDatabase(id)) {
                JOptionPane.showMessageDialog(this, "Student ID Duplicated", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                if(insertStudentData(name, id, grade, dob, gender, contact, email,falcuty,major,classText))
                {
                    String[] data = {name, id, grade, dob, gender, contact, email,falcuty,major,classText};
                    tableModelStudent.addRow(data);
                }

            }
        }

        if (e.getSource() == updateStudent) {

                String name = jstudentName.getText();
                String id = jstudentID.getText().toUpperCase();
                String grade = jstudentGrade.getText();
                String dob = dobField.getText();
                String contact = contactField.getText();
                String email = emailField.getText();
                String classText = classField.getText().toUpperCase();
                String gender = maleRadio.isSelected() ? "Male" : "Female";
                String falcuty = selectedFaculty;
                String major = selectedMajor;

                if (name.isEmpty() || grade.isEmpty() || dob.isEmpty() || contact.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(this, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!isValidDate(dob)) {
                    JOptionPane.showMessageDialog(this, "Invalid date of birth. Use the format 'dd-MM-yyyy'.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!isValidGrade(grade)) {
                    JOptionPane.showMessageDialog(this, "Invalid student grade. It should be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!isValidStudentID(id)) {
                    JOptionPane.showMessageDialog(this, "Invalid student ID. It should be corect format.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!isValidContactNumber(contact)) {
                    JOptionPane.showMessageDialog(this, "Invalid contact number. It should be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!isValidClass(classText)) {
                    JOptionPane.showMessageDialog(this, "Invalid Class Format.", "Error", JOptionPane.ERROR_MESSAGE);
                }  else {

                    updateStudentDataInDatabase(name, id, grade, dob, gender, contact, email,falcuty,major,classText);
                }


        }

        if (e.getSource() == deleteRecord) {
            String studentIDToDelete = null;
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow >= 0) {
                studentIDToDelete = tableModelStudent.getValueAt(selectedRow, 1).toString();
                tableModelStudent.removeRow(selectedRow);

                deleteStudentData(studentIDToDelete);
            }
        }

        if (e.getSource() == sortStudent) {
            System.out.println("sort method called");
            loadStudentDataFromDatabaseSortFucntion();
        }

        if (e.getSource() == importStudent) {
            System.out.println("ImportCSV method called");
            importCSV();
        }

        if (e.getSource() == exportStudent) {
            exportToCsv();
        }
// student detial button here
        if (e.getSource() == addCert) {

            String name = certNameField.getText().toUpperCase();
            String id = studentIDCertField.getText().toUpperCase();
            String issuedate = issueDateField.getText();
            String issueAuthor = issueAuthorityField.getText().toUpperCase();


            if (id.isEmpty() || name.isEmpty() || issuedate.isEmpty() || issueAuthor.isEmpty() ) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }  else if (!isValidDate(issuedate)) {
                JOptionPane.showMessageDialog(this, "Invalid date. Use the format 'dd-MM-yyyy'.", "Error", JOptionPane.ERROR_MESSAGE);
            }  else if (!isStudentIdExists(id)) {
                JOptionPane.showMessageDialog(this, "Student ID not exist", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (isStudentIdExistsToCheckDuplicateCert(id,name)) {
                JOptionPane.showMessageDialog(this, "Certificate existed with this student", "Error", JOptionPane.ERROR_MESSAGE);

            }else if(!isStudentIdExistsToCheckDuplicateCert(id,name) && insertCertData(id, name, issuedate, issueAuthor))
                {
                    tableModelcertificate.setRowCount(0);
                    loadCertDataFromDatabase();
                    certNameField.setText("");
                    studentIDCertField.setText("");
                    issueDateField.setText("");
                    issueAuthorityField.setText("");
                }



        }
        if (e.getSource() == studentIDCertViewButon) {

            String id = studentIDCertField.getText().toUpperCase();


            if (id.isEmpty() ) {
                JOptionPane.showMessageDialog(this, "Please fill in Student ID fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }  else
            if (!isStudentIdExistsToView(id)) {
                JOptionPane.showMessageDialog(this, "Student ID does not have Certificate or not exist", "Error", JOptionPane.ERROR_MESSAGE);
                studentIDCertField.setText("");
            } else {

                    tableModelcertificate.setRowCount(0);
                    loadCertOnlyOneIDDataFromDatabase(id);

            }
        }
        if (e.getSource() == updateCert) {

            String name = certNameField.getText().toUpperCase();
            String id = studentIDCertField.getText().toUpperCase();
            String issuedate = issueDateField.getText();
            String issueAuthor = issueAuthorityField.getText().toUpperCase();


            if (id.isEmpty() || name.isEmpty() || issuedate.isEmpty() || issueAuthor.isEmpty() ) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }  else if (!isValidDate(issuedate)) {
                JOptionPane.showMessageDialog(this, "Invalid date. Use the format 'dd-MM-yyyy'.", "Error", JOptionPane.ERROR_MESSAGE);
            }  else if (!isStudentIdExists(id)) {
                JOptionPane.showMessageDialog(this, "Student ID not exist", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (!isStudentIdExists(id)) {
                JOptionPane.showMessageDialog(this, "some thing went wrong, student does not exist", "Error", JOptionPane.ERROR_MESSAGE);

            }else if(isStudentIdExistsToCheckDuplicateCert(id,name) )
            {
//                trong truong hop ton tai ca name va id( sua ngay va/ nguoi)
                updateStudenCertDateAndAuthorDataInDatabase(id,name,issuedate,issueAuthor);
                certNameField.setText("");
                studentIDCertField.setText("");
                issueDateField.setText("");
                issueAuthorityField.setText("");
            }else
            {
//                trong truong hop ton tai ca name va id( sua ngay va/ nguoi)
                updateStudenCerttDataInDatabase(id,name,issuedate,issueAuthor);
                certNameField.setText("");
                studentIDCertField.setText("");
                issueDateField.setText("");
                issueAuthorityField.setText("");
            }

        }

        if (e.getSource() == deleteCert) {
            String nameCertToDelete, idCertToDelete= null;
            int selectedRow = certificateTable.getSelectedRow();
            if (selectedRow >= 0) {
                nameCertToDelete = tableModelcertificate.getValueAt(selectedRow, 1).toString();
                idCertToDelete = tableModelcertificate.getValueAt(selectedRow, 0).toString();
                tableModelcertificate.removeRow(selectedRow);

                deleteStudentCertData(idCertToDelete,nameCertToDelete);
            }
        }

        if (e.getSource() == importCert) {
            importCSVCert();
        }

        if (e.getSource() == exportCert) {
            exportToCsvCert();
        }


        if (e.getSource() == searchButton) {
            String searchId = searchField.getText();
            for (int row = 0; row < tableModelStudent.getRowCount(); row++) {
                if (tableModelStudent.getValueAt(row, 1).equals(searchId)) {
                    studentTable.setRowSelectionInterval(row, row);
                    studentTable.setSelectionBackground(Color.YELLOW);
                    studentTable.setSelectionForeground(Color.BLACK);
                    break;
                }
            }
        }
        }




    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    private boolean checkDuplicateInDatabase(String input) {
        // Kết nối đến cơ sở dữ liệu
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Thực hiện truy vấn SQL
            String sql = "SELECT COUNT(*) FROM students WHERE student_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, input);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0; // Trả về true nếu có sự trùng lặp
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isValidGrade(String grade) {
        try {
            Double.parseDouble(grade);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidStudentID(String id) {
        return id.matches("^\\d{3}[A-Za-z]\\d{4}$");
    }

    private boolean isValidContactNumber(String contact) {
        return contact.matches("^[0-9]+$");
    }




    private void importCSV(String filePath) {
        System.out.println("insert method in import CSV");


            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {


                System.out.println("insert method in import CSV in try");

                String line;
                while ((line = br.readLine()) != null) {
                    // Tách dữ liệu từ dòng CSV
                    System.out.println("insert method in import CSV in loop");

                    String[] data = line.split(",");
                    // Thiết lập giá trị cho PreparedStatemen
                    insertStudentData(data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9]);
                }

                // Sau khi import, làm mới table để hiển thị danh sách sinh viên mới
                tableModelStudent.setRowCount(0);
                loadStudentDataFromDatabase();
            }
         catch ( IOException e) {
            e.printStackTrace();
        }
    }

    private void importCSVCert(String filePath) {
        System.out.println("insert method in import CSV");


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {


            System.out.println("insert method in import CSV in try");

            String line;
            while ((line = br.readLine()) != null) {
                // Tách dữ liệu từ dòng CSV

                String[] data = line.split(",");
                // Thiết lập giá trị cho PreparedStatemen
                if(!isStudentIdExistsToCheckDuplicateCert(data[1],data[2])){
                    System.out.println("insert method in import CSV in loop");
                    insertCertData(data[1],data[2],data[3],data[4]);
                }

            }

            // Sau khi import, làm mới table để hiển thị danh sách sinh viên mới
            tableModelcertificate.setRowCount(0);
            loadCertDataFromDatabase();
        }
        catch ( IOException e) {
            e.printStackTrace();
        }
    }

    private void importCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select CSV File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Chỉ chọn file, không chọn folder

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Call the import method with the selected file path
            importCSV(selectedFile.getAbsolutePath());
        }
    }

    private void importCSVCert() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select CSV File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Chỉ chọn file, không chọn folder

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Call the import method with the selected file path
            importCSVCert(selectedFile.getAbsolutePath());
        }
    }

    private  void exportToCsv( ) {
        try (
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM students ")) {

            // Tạo một đối tượng CSVWriter với đường dẫn file CSV
            try (CSVWriter writer = new CSVWriter(new FileWriter("output.csv"))) {

                // Xuất tiêu đề
                writer.writeAll(resultSet, true);
            }
            JOptionPane.showMessageDialog(this, "Export student list to CSV successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

            System.out.println("Export to CSV successful!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void exportToCsvCert( ) {
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Certificates ")) {

            // Tạo một đối tượng CSVWriter với đường dẫn file CSV
            try (CSVWriter writer = new CSVWriter(new FileWriter("output_Certificates.csv"))) {

                // Xuất tiêu đề
                writer.writeAll(resultSet, true);
            }
            JOptionPane.showMessageDialog(this, "Export student certificate to CSV successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

            System.out.println("Export to CSV successful!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sortTableByGrade() {
        // Sắp xếp bảng theo cột "Grade" (cột thứ 2)
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModelStudent);
        studentTable.setRowSorter(sorter);

        // Sắp xếp giảm dần theo điểm
        sorter.setComparator(2, (Integer o1, Integer o2) -> o2.compareTo(o1));
        sorter.sort();

    }
    private boolean isValidClass(String classtext) {
        return classtext.matches("^\\d{2}[A-Za-z]\\d{5}$");
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean insertStudentData(String name, String id, String grade, String dob, String gender, String contact, String email, String faculty, String major, String classS) {
        String insertQuery = "INSERT INTO students (name, student_id,grade, date_of_birth, gender, contact, email,falcuty,major,class) VALUES (?, ?, ?, STR_TO_DATE(?, '%d-%m-%Y'), ?, ?, ?, ?, ?, ?)";
        System.out.println("insert method called");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, id);
            preparedStatement.setString(3, grade);
            preparedStatement.setString(4, dob);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, contact);
            preparedStatement.setString(7, email);
            preparedStatement.setString(8, faculty);
            preparedStatement.setString(9, major);
            preparedStatement.setString(10, classS);


            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student data inserted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                jstudentName.setText("");
                jstudentID.setText("");
                jstudentGrade.setText("");
                dobField.setText("");
                genderGroup.clearSelection();
                contactField.setText("");
                emailField.setText("");
                classField.setText("");
                System.out.println("insert method success");
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Failed to insert student data", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("insert method falure");
                return false;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    private boolean insertCertData(String id, String name,  String dob, String author) {
        String insertQuery = "INSERT INTO Certificates (student_id, certificate_name,issue_date, issuing_authority) VALUES (?, ?, STR_TO_DATE(?, '%d-%m-%Y'), ?)";
        System.out.println("insert method called");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, dob);
            preparedStatement.setString(4, author);


            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student Certidicate inserted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                studentIDCertField.setText("");
                certNameField.setText("");
                issueDateField.setText("");
                issueAuthorityField.setText("");

                System.out.println("insert method success");
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Failed to insert student data", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("insert cert method falure");
                return false;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void updateStudentDataInDatabase(String name, String id, String grade, String dob, String gender, String contact, String email, String faculty, String major, String classS) {
        try {
            // Câu lệnh SQL để cập nhật dữ liệu sinh viên trong bảng students
            String updateQuery = "UPDATE students SET name = ?, grade = ?, date_of_birth = STR_TO_DATE(?, '%d-%m-%Y'), gender = ?, "
                                 +
                                 "contact = ?, email = ?, falcuty = ?, major = ?, class = ? WHERE student_id = ?";

            // Chuẩn bị câu lệnh SQL sử dụng PreparedStatement để tránh SQL injection
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

            // Thiết lập giá trị cho các tham số trong câu lệnh SQL
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, grade);
            preparedStatement.setString(3, dob);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, contact);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, faculty);
            preparedStatement.setString(8, major);
            preparedStatement.setString(9, classS);
            preparedStatement.setString(10, id);

            // Thực hiện câu lệnh UPDATE
            int affectedRows = preparedStatement.executeUpdate();

            // Kiểm tra xem có bao nhiêu dòng đã bị ảnh hưởng
            if (affectedRows > 0) {
                tableModelStudent.fireTableDataChanged();
                JOptionPane.showMessageDialog(this, "Student Data Update Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Cập nhật lại dữ liệu trong tableModel
                tableModelStudent.setRowCount(0);
                loadStudentDataFromDatabase();

            } else {
                JOptionPane.showMessageDialog(this, "Student data Update Failure", "Fail", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
        }
    }

    private void updateStudenCerttDataInDatabase(String id, String name, String dob, String author) {
        try {
            if(isStudentIdExistsToCheckDuplicateCert(id,name)){
                JOptionPane.showMessageDialog(this, "Infor Update was exist", "Fail", JOptionPane.INFORMATION_MESSAGE);

            }else
            {
                // Câu lệnh SQL để cập nhật dữ liệu sinh viên trong bảng students
                String updateQuery = "UPDATE Certificates SET  certificate_name = ?, issue_date = STR_TO_DATE(?, '%d-%m-%Y'), issuing_authority = ?  WHERE student_id = ? AND certificate_name = ?";

                // Chuẩn bị câu lệnh SQL sử dụng PreparedStatement để tránh SQL injection
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

                // Thiết lập giá trị cho các tham số trong câu lệnh SQL

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, dob);
                preparedStatement.setString(3, author);
                preparedStatement.setString(4, id);


                // Thực hiện câu lệnh UPDATE
                int affectedRows = preparedStatement.executeUpdate();

                // Kiểm tra xem có bao nhiêu dòng đã bị ảnh hưởng
                if (affectedRows > 0) {
                    tableModelcertificate.fireTableDataChanged();
                    JOptionPane.showMessageDialog(this, "Student Data Cerificate Update Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Cập nhật lại dữ liệu trong tableModel
                    tableModelcertificate.setRowCount(0);
                    loadCertOnlyOneIDDataFromDatabase(id);

                } else {
                    JOptionPane.showMessageDialog(this, "Student data Update Failure", "Fail", JOptionPane.INFORMATION_MESSAGE);
                }

            }

        } catch (SQLException e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
        }
    }

    private void updateStudenCertDateAndAuthorDataInDatabase(String id, String name, String dob, String author) {
        try {

                // Câu lệnh SQL để cập nhật dữ liệu sinh viên trong bảng students
                String updateQuery = "UPDATE Certificates SET   issue_date = STR_TO_DATE(?, '%d-%m-%Y'), issuing_authority = ?  WHERE student_id = ? AND certificate_name = ?";

                // Chuẩn bị câu lệnh SQL sử dụng PreparedStatement để tránh SQL injection
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

                // Thiết lập giá trị cho các tham số trong câu lệnh SQL

                preparedStatement.setString(1, dob);
                preparedStatement.setString(2, author);
                preparedStatement.setString(3, id);
                preparedStatement.setString(4, name);


                // Thực hiện câu lệnh UPDATE
                int affectedRows = preparedStatement.executeUpdate();

                // Kiểm tra xem có bao nhiêu dòng đã bị ảnh hưởng
                if (affectedRows > 0) {
                    tableModelcertificate.fireTableDataChanged();
                    JOptionPane.showMessageDialog(this, "Student Data Cerificate Update Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Cập nhật lại dữ liệu trong tableModel
                    tableModelcertificate.setRowCount(0);
                    loadCertOnlyOneIDDataFromDatabase(id);

                } else {
                    JOptionPane.showMessageDialog(this, "Student data Update Failure", "Fail", JOptionPane.INFORMATION_MESSAGE);
                }



        } catch (SQLException e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
        }
    }

    private void loadStudentDataFromDatabase() {
        try {
            String selectQuery = "SELECT name, student_id, grade, DATE_FORMAT(date_of_birth, '%d-%m-%Y'), gender, contact, email,falcuty,major,class FROM students";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String id = resultSet.getString(2);
                String grade = resultSet.getString(3);
                String dob = resultSet.getString(4);
                String gender = resultSet.getString(5);
                String contact = resultSet.getString(6);
                String email = resultSet.getString(7);
                String falcuty = resultSet.getString(8);
                String major = resultSet.getString(9);
                String classSql = resultSet.getString(10);


                String[] data = {name, id, grade, dob, gender, contact, email,falcuty,major,classSql};
                tableModelStudent.addRow(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadCertDataFromDatabase() {
        try {
            String selectQuery = "SELECT student_id , certificate_name, DATE_FORMAT(issue_date, '%d-%m-%Y'), issuing_authority FROM Certificates";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String id = resultSet.getString(1);
                String dob = resultSet.getString(3);
                String isssueAuthor = resultSet.getString(4);



                String[] data = {id, name, dob, isssueAuthor};
                tableModelcertificate.addRow(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadCertOnlyOneIDDataFromDatabase(String student_id) {
        try {
            String selectQuery = "SELECT student_id , certificate_name, DATE_FORMAT(issue_date, '%d-%m-%Y'), issuing_authority FROM Certificates WHERE student_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, student_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String id = resultSet.getString(1);
                String dob = resultSet.getString(3);
                String isssueAuthor = resultSet.getString(4);



                String[] data = {id, name, dob, isssueAuthor};
                tableModelcertificate.addRow(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadStudentDataFromDatabaseSortFucntion() {
        try {
            String selectQuery = "SELECT name, student_id, grade, DATE_FORMAT(date_of_birth, '%d-%m-%Y'), gender, contact, email,falcuty,major,class FROM students";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String id = resultSet.getString(2);
                String grade = resultSet.getString(3);
                String dob = resultSet.getString(4);
                String gender = resultSet.getString(5);
                String contact = resultSet.getString(6);
                String email = resultSet.getString(7);
                String falcuty = resultSet.getString(8);
                String major = resultSet.getString(9);
                String classSql = resultSet.getString(10);


                Student student = new Student(name, id, grade, dob, gender, contact, email, falcuty, major, classSql);
                studentList.add(student);
            }

            Collections.sort(studentList, (s1, s2) -> s1.getGrade().compareTo(s2.getGrade()));
            tableModelStudent.setRowCount(0);
            for (Student student : studentList) {
                String[] data = {student.getName(), student.getId(), student.getGrade(), student.getDob(), student.getGender(), student.getContact(), student.getEmail(), student.getFaculty(), student.getMajor(), student.getClassSql()};
                tableModelStudent.addRow(data);
            }
            studentList.clear();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteStudentData(String studentID) {
        String deleteQuery = "DELETE FROM students WHERE student_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, studentID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student data deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete student data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteStudentCertData(String id, String name) {
        String deleteQuery = "DELETE FROM Certificates WHERE student_id = ? AND certificate_name = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student data certificate deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete student data certificate", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private  boolean isStudentIdExists(String studentId) {
        try  {
            String query = "SELECT COUNT(*) FROM Students WHERE student_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentId);
                System.out.print("function of add certificate");
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        System.out.print(resultSet.getInt(1));
                        System.out.print("exist this ID");

                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private  boolean isStudentIdExistsToView(String studentId) {
        try  {
            String query = "SELECT COUNT(*) FROM Certificates WHERE student_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentId);
                System.out.print("function of add certificate");
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        System.out.print(resultSet.getInt(1));
                        System.out.print("exist this ID");

                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private  boolean isStudentIdExistsToCheckDuplicateCert(String studentId, String certName) {
        try  {
            String query = " SELECT COUNT(*) FROM Certificates WHERE student_id = ? AND certificate_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentId);
                preparedStatement.setString(2, certName);

                System.out.print("function of add certificate");
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        System.out.print(resultSet.getInt(1));
                        System.out.print("exist this ID");

                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private void closeDatabaseConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementSystem();
        });
    }
}
