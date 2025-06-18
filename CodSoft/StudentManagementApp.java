import java.io.*;
import java.util.*;

class Student {
    String rollNumber;
    String name;
    String grade;

    public Student(String rollNumber, String name, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return rollNumber + "," + name + "," + grade;
    }

    public static Student fromString(String data) {
        String[] parts = data.split(",");
        if (parts.length == 3) {
            return new Student(parts[0], parts[1], parts[2]);
        }
        return null;
    }
}

class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students_data.txt";

    public StudentManagementSystem() {
        loadStudentsFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
        System.out.println("Student added successfully.");
    }

    public void removeStudent(String rollNumber) {
        boolean removed = students.removeIf(s -> s.rollNumber.equals(rollNumber));
        if (removed) {
            saveStudentsToFile();
            System.out.println("Student removed successfully.");
        } else {
            System.out.println(" Student not found.");
        }
    }

    public void searchStudent(String rollNumber) {
        for (Student s : students) {
            if (s.rollNumber.equals(rollNumber)) {
                System.out.println("Found: Roll: " + s.rollNumber + ", Name: " + s.name + ", Grade: " + s.grade);
                return;
            }
        }
        System.out.println(" Student not found.");
    }

    public void editStudent(String rollNumber, String newName, String newGrade) {
        for (Student s : students) {
            if (s.rollNumber.equals(rollNumber)) {
                s.name = newName;
                s.grade = newGrade;
                saveStudentsToFile();
                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println(" Student not found.");
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println(" No students to display.");
        } else {
            System.out.println("\n All Students:");
            for (Student s : students) {
                System.out.println("Roll: " + s.rollNumber + ", Name: " + s.name + ", Grade: " + s.grade);
            }
        }
    }

    private void loadStudentsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student s = Student.fromString(line);
                if (s != null) {
                    students.add(s);
                }
            }
        } catch (IOException e) {
            // File may not exist initially, that's okay
        }
    }

    private void saveStudentsToFile() {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            for (Student s : students) {
                fw.write(s.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println(" Error saving data.");
        }
    }
}

public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        boolean running = true;
        while (running) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("Add Student");
            System.out.println(" Remove Student");
            System.out.println(" Search Student");
            System.out.println(" Edit Student");
            System.out.println("Display All Students");
            System.out.println(" Exit");
            System.out.print("Select an option: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    addStudentUI(sms, sc);
                    break;
                case "2":
                    System.out.print("Enter Roll Number to remove: ");
                    String rollRemove = sc.nextLine().trim();
                    sms.removeStudent(rollRemove);
                    break;
                case "3":
                    System.out.print("Enter Roll Number to search: ");
                    String rollSearch = sc.nextLine().trim();
                    sms.searchStudent(rollSearch);
                    break;
                case "4":
                    System.out.print("Enter Roll Number to edit: ");
                    String rollEdit = sc.nextLine().trim();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine().trim();
                    System.out.print("Enter new grade: ");
                    String newGrade = sc.nextLine().trim();
                    if (newName.isEmpty() || newGrade.isEmpty()) {
                        System.out.println(" Name and grade cannot be empty.");
                    } else {
                        sms.editStudent(rollEdit, newName, newGrade);
                    }
                    break;
                case "5":
                    sms.displayAllStudents();
                    break;
                case "6":
                    running = false;
                    System.out.println(" Exiting...");
                    break;
                default:
                    System.out.println(" Invalid choice. Try again.");
            }
        }

        sc.close();
    }

    private static void addStudentUI(StudentManagementSystem sms, Scanner sc) {
        System.out.print("Enter Roll Number: ");
        String roll = sc.nextLine().trim();
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Grade: ");
        String grade = sc.nextLine().trim();

        if (roll.isEmpty() || name.isEmpty() || grade.isEmpty()) {
            System.out.println(" Roll number, name, and grade cannot be empty.");
            return;
        }

        Student s = new Student(roll, name, grade);
        sms.addStudent(s);
    }
}
