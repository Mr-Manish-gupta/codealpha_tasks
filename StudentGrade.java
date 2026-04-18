import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

class Student {
    String name;
    ArrayList<Integer> marks;

    Student(String name) {
        this.name = name;
        marks = new ArrayList<>();
    }

    void addMark(int mark) {
        marks.add(mark);
    }

    double getAverage() {
        int sum = 0;
        for (int m : marks) {
            sum += m;
        }
        return (double) sum / marks.size();
    }

    int getHighestMark() {
        return Collections.max(marks);
    }

    int getLowestMark() {
        return Collections.min(marks);
    }

    String getGrade() {
        double av = getAverage();
        if (av >= 90) return "A";
        else if (av >= 70) return "B";
        else if (av >= 50) return "C";
        else if (av >= 30) return "D";
        else return "Fail";
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
        System.out.println("Average: " + getAverage());
        System.out.println("Highest Mark: " + getHighestMark());
        System.out.println("Lowest Mark: " + getLowestMark());
        System.out.println("Grade: " + getGrade());
        System.out.println("---");
    }
}

public class StudentGrade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("Student Grade Tracker");

        while (true) {
            System.out.println("\n---- MENU ----");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            String input = sc.nextLine().trim();
            int choice = 0;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            if (choice == 1) {
                System.out.print("Enter student name: ");
                String name = sc.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty.");
                    continue;
                }

                Student s = new Student(name);

                System.out.print("How many subjects? ");
                input = sc.nextLine().trim();
                int n = 0;
                try {
                    n = Integer.parseInt(input);
                    if (n <= 0) {
                        System.out.println("Number of subjects must be positive.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number.");
                    continue;
                }

                for (int i = 0; i < n; i++) {
                    System.out.print("Enter mark " + (i + 1) + " (0-100): ");
                    input = sc.nextLine().trim();
                    int mark = 0;
                    try {
                        mark = Integer.parseInt(input);
                        if (mark < 0 || mark > 100) {
                            System.out.println("Mark must be between 0 and 100.");
                            i--;
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid mark.");
                        i--;
                        continue;
                    }
                    s.addMark(mark);
                }

                students.add(s);
                System.out.println("Student added successfully!");

            } else if (choice == 2) {
                if (students.isEmpty()) {
                    System.out.println("No students to display.");
                } else {
                    System.out.println("\n--- All Students ---");
                    for (Student s : students) {
                        s.display();
                    }
                }
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Try 1-3.");
            }
        }
        sc.close();
    }
}

