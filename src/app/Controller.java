/*
Programming II Assignment 3
By: Erick Rivera
Due Date: March 9th, 2022
PID: 6311416
 */
package app;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import inter.Student;
import inter.Teacher;
import inter.TA;
public class Controller {
    public static final String input = System.getProperty("user.dir") + "/src/app";

    public static void main(String[] args) throws FileNotFoundException {
        //SCANNER
        Scanner scnr = new Scanner(System.in);
        PrintWriter out = new PrintWriter(input + "/out.csv");
        //Menu Screen where students enter the number of records being stored
        String numOfInfoSTR;
        int numOfInfo = 0;
        System.out.println("""
                -------------------------------------------------
                | Welcome to the University Information Records |
                | Please Enter the desired number of            |
                | Staff & Students information being entered    |
                -------------------------------------------------
                """);


        do {
            System.out.print("#ofRecords: ");
            numOfInfoSTR = scnr.nextLine();
            if (numOfInfoSTR.matches("[0-9]+")) {
                numOfInfo = Integer.parseInt(numOfInfoSTR);
                break;
            } else {
                System.out.println("Invalid - Please Enter a valid number");
            }
        } while (numOfInfoSTR != "TheMagicSchoolBus");
        //Formatting Menu
        System.out.println("""
                ----------------------------------------------------------------
                | Please enter the information below with the following format |
                | Position Name StudentID TeacherID Phone                      |
                | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ |
                | Position: May only contain "Teacher", "Student", or "TA".    |
                | Name: First followed by Last seperated only by a single (,). |
                | StudentID: If position is Student it must contain 5 digit #. |
                | TeacherID: If position is Teacher it must contain 5 digit #. |
                | StudentID/TeacherID: If position is teacher, studentID must  |
                | be 0 and vice versa.                                         |
                | Phone: Must be a 10 digit # with no other characters         |
                ----------------------------------------------------------------
                """);
        int i = 0;
        do {
            System.out.print("Enter Information: ");
            String tempInput = scnr.nextLine();
            String[] tempArray = tempInput.toLowerCase().split(" ");
            if (tempArray.length == 5) {
                //Start of Position
                if (tempArray[0].equals("student")) {
                    //Start of Student NAME
                    if (isNameValid(tempArray[1])) {
                        //Start of Student ID
                        if (isIDValid(tempArray[2])) {
                            if (tempArray[2].length() == 5) {
                                //Start of Teacher ID
                                if (isIDValid(tempArray[3])) {
                                    if(Integer.parseInt(tempArray[3]) == 0) {
                                        //Start of Phone
                                        if(isPhoneValid(tempArray[4])) {
                                            //Construct Student
                                            Student student = new Student(nameReformat(tempArray[1]), Integer.parseInt(tempArray[2]), Long.parseLong(tempArray[4]));
                                            student.csvPrintln(out.append(""));

                                            i++;
                                        } else {
                                            System.out.println("\nPlease follow the correct Phone format!");
                                        }
                                    } else {
                                        System.out.println("\nTeacher ID must be 0!");
                                    }
                                } else {
                                    System.out.println("\nTeacher ID must be 0!");
                                }
                            } else {
                                System.out.println("\nStudent ID needs 5 digits!");
                            }
                        } else {
                            System.out.println("\nPlease follow the correct ID format!");
                        }
                    } else {
                        System.out.println("\nPlease follow the correct NAME format!");
                    }
                } else if (tempArray[0].equals("teacher")) {
                    //Start of Teacher NAME
                    if (isNameValid(tempArray[1])) {
                        //Start of Student ID
                        if (isIDValid(tempArray[2])) {
                            if (Integer.parseInt(tempArray[2]) == 0) {
                                //Start of Teacher ID
                                if (isIDValid(tempArray[3])) {
                                    if (tempArray[3].length() == 5) {
                                        //Start of Phone Number
                                        if(isPhoneValid(tempArray[4])) {
                                            //Construct Teacher
                                            Teacher teacher = new Teacher(nameReformat(tempArray[1]), Integer.parseInt(tempArray[3]), lastFour(tempArray[4]));
                                            teacher.csvPrintln(out.append(""));

                                            i++;
                                        } else {
                                            System.out.println("\nPlease follow the correct Phone format!");
                                        }
                                    } else {
                                        System.out.println("\nTeacher ID needs 5 digits!");
                                    }
                                } else {
                                    System.out.println("\nPlease follow the correct ID format!");
                                }
                            } else {
                                System.out.println("\nStudent ID needs must be 0!");
                            }
                        } else {
                            System.out.println("\nPlease follow the correct ID format!");
                        }
                    } else {
                        System.out.println("\nPlease follow the correct NAME format!");
                    }
                } else if (tempArray[0].equals("ta")) {
                    //Start of TA NAME
                    if (isNameValid(tempArray[1])) {
                        //Start of Student ID
                        if (isIDValid(tempArray[2])) {
                            if (tempArray.length == 5) {
                                //Start of Teacher ID
                                if (isIDValid(tempArray[3])) {
                                    if (tempArray[3].length() == 5) {
                                        //Start of Phone Number
                                        if(isPhoneValid(tempArray[4])) {
                                            //Construct TA
                                            TA ta = new TA(nameReformat(tempArray[1]), Integer.parseInt(tempArray[2]), Integer.parseInt(tempArray[3]), Long.parseLong(tempArray[4]));
                                            ta.csvPrintln(out.append(""));

                                            i++;
                                        } else {
                                            System.out.println("\nPlease follow the correct Phone format!");
                                        }
                                    } else {
                                        System.out.println("\nTeacher ID needs 5 digits!");
                                    }
                                } else {
                                    System.out.println("\nPlease follow the correct ID format!");
                                }
                            } else {
                                System.out.println("\nStudent ID needs 5 digits!");
                            }
                        } else {
                            System.out.println("\nPlease follow the correct ID format!");
                        }
                    } else {
                        System.out.println("\nPlease follow the correct NAME format!");
                    }
                } else {
                    System.out.println("\nPlease select an available position!");
                }
                //End of Position
            } else {
                System.out.println("\nPlease follow the correct format!");
            }
        } while (i < numOfInfo);
        out.flush();
        out.close();
    }

    public static boolean isNameValid(String name) {
        boolean TorF = false;
        if (name.matches("[a-z,]+")) {
            TorF = true;
            for (int i = 0; i < name.length(); i++) {
                for (int j = i + 1; j < name.length(); j++) {
                    if (((name.charAt(i) == name.charAt(j)) && (name.charAt(i) == ','))) {
                        TorF = false;
                        break;
                    }
                }
            }
        }
        return TorF;
    }

    public static boolean isIDValid(String ID) {
        boolean TorF = false;
        if (ID.matches("[0-9]+")) {
            if(ID.length() == 1 || ID.length() == 5) {
                TorF = true;
            }
        }
        return TorF;
    }

    public static boolean isPhoneValid(String number) {
        boolean TorF = false;
        if (number.matches("[0-9]+")) {
            if(number.length() == 10) {
                TorF = true;
            }
        }
        return TorF;
    }
    public static String nameReformat(String name) {
        String[] tempName = name.split(",");
        tempName[0] = tempName[0].substring(0,1).toUpperCase() + tempName[0].substring(1);
        tempName[1] = tempName[1].substring(0,1).toUpperCase() + tempName[1].substring(1);
        return tempName[0] + " " + tempName[1];
    }

    public static int lastFour(String phone) {
        String lastFour = phone.substring(6,10);
        return Integer.parseInt(lastFour);
    }
}
