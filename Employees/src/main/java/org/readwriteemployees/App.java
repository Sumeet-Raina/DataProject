package org.readwriteemployees;

import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class App {
  public static final Logger logger = Logger.getLogger(App.class.getName());

  public static void main(String[] args) {
    try {
      logger.setLevel(Level.ALL);
      FileHandler fileHandler = new FileHandler("app.log", true);
      fileHandler.setFormatter(new SimpleFormatter());
      logger.addHandler(fileHandler);

      logger.info("Application started.");

      String path = "src/main/resources/employees.csv"; // full test file
      EmployeeCsvReader reader = new EmployeeCsvReader();
      ArrayList<Employee> employees = reader.readEmployees(path);

      // Write employees to JSON file
      EmployeeDataHandler.writeEmployeesToJson(employees, "src/main/resources/employees.json");

      // Read employees back from JSON file
      ArrayList<Employee> loadedEmployeesJson =
        EmployeeDataHandler.readEmployeesFromJson("src/main/resources/employees.json");
      System.out.println("Loaded from JSON file: " + loadedEmployeesJson.size());

      // Write employees to XML file
      EmployeeDataHandler.writeEmployeesToXml(employees, "src/main/resources/employees.xml");

      //  Read employees back from XML file
      ArrayList<Employee> loadedEmployeesXml =
        EmployeeDataHandler.readEmployeesFromXml("src/main/resources/employees.xml");
      System.out.println("Loaded from XML file: " + loadedEmployeesXml.size());

      logger.log(Level.INFO, "Loaded {0} employees successfully from CSV.", employees.size());

      ArrayList<String> faulty = reader.getFaultyLines();
      logger.log(Level.WARNING, "Found {0} faulty lines.", faulty.size());

      for (String badLine : faulty) {
        logger.warning("Faulty: " + badLine);
      }

      logger.info("Application finished.");
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Application error", e);
    }
  }
}
