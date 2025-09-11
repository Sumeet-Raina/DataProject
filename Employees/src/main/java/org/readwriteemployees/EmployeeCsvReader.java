package org.readwriteemployees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class EmployeeCsvReader {
    private static final Logger logger = Logger.getLogger(EmployeeCsvReader.class.getName());

    private final ArrayList<String> faultyLines = new ArrayList<>();

    // Regex for a very simple email validation
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    public ArrayList<String> getFaultyLines() {
        return faultyLines;
    }

    // Validate employee fields
    private boolean validateLine(String[] parts) {
        try {
            if (parts.length != 10) {
                logger.warning("Invalid column count: " + parts.length);
                return false;
            }

            // Validate numeric fields
            Integer.parseInt(parts[0]); // employeeId
            Integer.parseInt(parts[9]); // salary

            // Gender check
            if (!(parts[5].equalsIgnoreCase("M") || parts[5].equalsIgnoreCase("F"))) {
                logger.warning("Invalid gender: " + parts[5]);
                return false;
            }

            // Email check
            if (!EMAIL_PATTERN.matcher(parts[6]).matches()) {
                logger.warning("Invalid email: " + parts[6]);
                return false;
            }

            // TODO: Add LocalDate.parse check for dob/doj if required

            return true;

        } catch (Exception e) {
            logger.log(Level.WARNING, "Validation failed", e);
            return false;
        }
    }

    // Parse one CSV line into Employee
    public Employee createEmployee(String line) {
        String[] parts = line.split(",");

        if (!validateLine(parts)) {
            faultyLines.add(line);
            logger.warning("Skipping invalid line: " + line);
            return null;
        }

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

        logger.log(Level.FINE, "Created Employee: {0} {1}", new Object[]{firstName, lastName});
        return new Employee(employeeId, prefix, firstName, middleInitial, lastName,
                gender, email, dob, doj, salary);
    }

    // Read file into list of lines
    public ArrayList<String> readFileLines(String fileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            logger.log(Level.INFO, "Read {0} lines from file: {1}", new Object[]{lines.size(), fileName});
            return new ArrayList<>(lines);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + fileName, e);
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }

    // Read Employees directly
    public ArrayList<Employee> readEmployees(String fileName) {
        ArrayList<String> lines = readFileLines(fileName);

        if (lines.isEmpty()) {
            logger.warning("Empty file: " + fileName);
            return new ArrayList<>();
        }

        lines.remove(0); // skip header row
        logger.log(Level.INFO, "Processing {0} employees from file.", lines.size());

        ArrayList<Employee> employees = new ArrayList<>();
        for (String line : lines) {
            Employee emp = createEmployee(line);
            if (emp != null) {
                employees.add(emp);
            }
        }
        logger.log(Level.INFO, "Finished reading employees. Valid: {0}, Invalid: {1}",
                new Object[]{employees.size(), faultyLines.size()});
        return employees;
    }
}
