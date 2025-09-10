package org.readwriteemployees;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeCsvReaderTest {
    EmployeeCsvReader reader = new EmployeeCsvReader();

    @Test
    void testCreateEmployee() {
        String line = "198429,Mrs.,Serafina,I,Bumgarner,F,serafina.bumgarner@exxonmobil.com,9/21/1982,2/1/2008,69294";
        Employee emp = reader.createEmployee(line);

        assertEquals(198429, emp.getEmployeeID());
        assertEquals("Mrs.", emp.getNamePrefix());
        assertEquals("Serafina", emp.getFirstName());
        assertEquals("I", emp.getMiddleInitial());
        assertEquals("Bumgarner", emp.getLastName());
        assertEquals("F", emp.getGender());
        assertEquals("serafina.bumgarner@exxonmobil.com", emp.getEmail());
        assertEquals(LocalDate.of(1982, 9, 21), emp.getDateOfBirth());
        assertEquals(LocalDate.of(2008, 2, 1), emp.getDateOfJoining());
        assertEquals(69294, emp.getSalary());
    }

    @Test
    void testReadFileLines() {
        ArrayList<String> lines = reader.readFileLines("src/main/resources/employees_short.csv");
        assertEquals(11, lines.size());  // header + 10 rows
        assertTrue(lines.get(0).contains("Emp ID,Name Prefix"));
        assertTrue(lines.get(lines.size()-1).contains("744723,Hon.,Bibi,H,Paddock,F"));
    }

    @Test
    void testReadEmployees() {
        ArrayList<Employee> employees = reader.readEmployees("src/main/resources/employees_short.csv");
        assertEquals(10, employees.size());

        assertEquals(198429, employees.get(0).getEmployeeID());
        assertEquals(744723, employees.get(employees.size()-1).getEmployeeID());
    }

}