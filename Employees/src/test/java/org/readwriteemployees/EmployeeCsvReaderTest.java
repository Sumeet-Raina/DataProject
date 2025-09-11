package org.readwriteemployees;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeCsvReaderTest {
    EmployeeCsvReader reader = new EmployeeCsvReader();

    @Test
    void testCreateEmployee_validLine() {
        String line = "198429,Mrs.,Serafina,I,Bumgarner,F,serafina.bumgarner@exxonmobil.com,9/21/1982,2/1/2008,69294";
        Employee emp = reader.createEmployee(line);

        assertNotNull(emp);
        assertEquals(198429, emp.getEmployeeID());
        assertEquals("Mrs.", emp.getNamePrefix());
        assertEquals("Serafina", emp.getFirstName());
        assertEquals("I", emp.getMiddleInitial());
        assertEquals("Bumgarner", emp.getLastName());
        assertEquals("F", emp.getGender());
        assertEquals("serafina.bumgarner@exxonmobil.com", emp.getEmail());
        assertEquals(69294, emp.getSalary());
    }

    @Test
    void testCreateEmployee_invalidEmail() {
        String line = "111111,Mr.,John,D,Doe,M,john.doe[at]email.com,1/1/1980,1/1/2000,50000";
        Employee emp = reader.createEmployee(line);

        assertNull(emp, "Invalid email should not create employee");
        assertTrue(reader.getFaultyLines().contains(line));
    }

    @Test
    void testCreateEmployee_invalidGender() {
        String line = "222222,Ms.,Jane,D,Doe,X,jane.doe@email.com,2/2/1985,3/3/2005,60000";
        Employee emp = reader.createEmployee(line);

        assertNull(emp, "Invalid gender should not create employee");
        assertTrue(reader.getFaultyLines().contains(line));
    }

    @Test
    void testCreateEmployee_invalidSalary() {
        String line = "333333,Dr.,Alex,Z,Smith,M,alex.smith@email.com,5/5/1990,6/6/2010,notANumber";
        Employee emp = reader.createEmployee(line);

        assertNull(emp, "Invalid salary should not create employee");
        assertTrue(reader.getFaultyLines().contains(line));
    }

    @Test
    void testReadFileLines_shortFile() {
        ArrayList<String> lines = reader.readFileLines("src/main/resources/employees_short.csv");
        assertEquals(11, lines.size());  // header + 10 rows
        assertTrue(lines.get(0).contains("Emp ID,Name Prefix"));
        assertTrue(lines.get(lines.size() - 1).contains("744723,Hon.,Bibi,H,Paddock,F"));
    }

    @Test
    void testReadEmployees_shortFile() {
        ArrayList<Employee> employees = reader.readEmployees("src/main/resources/employees_short.csv");
        assertEquals(10, employees.size());
        assertEquals(198429, employees.get(0).getEmployeeID());
        assertEquals(744723, employees.get(employees.size() - 1).getEmployeeID());
    }

    @Test
    void testReadEmployees_longFile_withErrors() {
        ArrayList<Employee> employees = reader.readEmployees("src/main/resources/employees.csv");
        ArrayList<String> faulty = reader.getFaultyLines();

        System.out.println("Valid employees: " + employees.size());
        System.out.println("Faulty lines: " + faulty.size());

        // sanity check: employees + faulty = total rows - header
        int totalRows = reader.readFileLines("src/main/resources/employees.csv").size() - 1;
        assertEquals(totalRows, employees.size() + faulty.size());
    }
}
