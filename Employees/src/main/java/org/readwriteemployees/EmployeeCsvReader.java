package org.readwriteemployees;

import java.util.ArrayList;

public class EmployeeCsvReader {

    public static Employee createEmployee(String line) {
        var splitLine = line.split(",");
        return new Employee(
                splitLine[0].trim(),
                splitLine[1].trim(),
                splitLine[2].trim(),
                splitLine[3].trim(),
                splitLine[4].trim(),
                splitLine[5].trim(),
                splitLine[6].trim(),
                splitLine[7].trim(),
                splitLine[8].trim(),
                splitLine[9].trim()
        );
    }

    public ArrayList<String> readFileLines(String fileName) {

        return null;
    }

    public ArrayList<Employee> readEmployees(String fileName) {
        return null;
    }
}
