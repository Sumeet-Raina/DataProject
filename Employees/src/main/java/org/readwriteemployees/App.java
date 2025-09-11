package org.readwriteemployees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

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

            logger.log(Level.INFO, "Loaded {0} employees successfully.", employees.size());

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
