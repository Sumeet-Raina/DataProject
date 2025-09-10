package org.readwriteemployees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCsvReader {
    // Parse one CSV line into Employee
    public Employee createEmployee(String line) {
        String[] parts = line.split(",");

        int employeeId = Integer.parseInt(parts[0]);
        String prefix = parts[1];
        String firstName = parts[2];
        String middleInitial = parts[3];
        String lastName = parts[4];
        String gender = parts[5];
        String email = parts[6];
        String dob = parts[7];
        String doj = parts[8];
        int salary = Integer.parseInt(parts[9]);

        return new Employee(employeeId, prefix, firstName, middleInitial, lastName,
                gender, email, dob, doj, salary);
    }

    // Read file into list of lines
    public ArrayList<String> readFileLines(String fileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            return new ArrayList<>(lines);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }

    // Read Employees directly
    public ArrayList<Employee> readEmployees(String fileName) {
        ArrayList<String> lines = readFileLines(fileName);

        // skip header row
        lines.remove(0);

        ArrayList<Employee> employees = new ArrayList<>();
        for (String line : lines) {
            employees.add(createEmployee(line));
        }
        return employees;
    }


//    public static Employee createEmployee(String line) {
//        var splitLine = line.split(",");
//        return new Employee(
//                splitLine[0].trim(),
//                splitLine[1].trim(),
//                splitLine[2].trim(),
//                splitLine[3].trim(),
//                splitLine[4].trim(),
//                splitLine[5].trim(),
//                splitLine[6].trim(),
//                splitLine[7].trim(),
//                splitLine[8].trim(),
//                splitLine[9].trim()
//        );
//    }
//
//    public ArrayList<String> readFileLines(String fileName) {
//        fileName =
//        ArrayList<String>
//
//        return null;
//    }
//
//    public ArrayList<Employee> readEmployees(String fileName) {
//        return null;
//    }
}
