package org.readwriteemployees;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeDataHandler {

  private static final ObjectMapper jsonMapper = new ObjectMapper()
    .registerModule(new JavaTimeModule()); // for LocalDate in JSON

  private static final XmlMapper xmlMapper;

  static {
    JacksonXmlModule module = new JacksonXmlModule();
    module.setDefaultUseWrapper(false); // cleaner XML (no extra ArrayList wrapper tags)
    xmlMapper = new XmlMapper(module);
    xmlMapper.registerModule(new JavaTimeModule()); // for LocalDate in XML
  }

  // 1️⃣ Serialize ArrayList<Employee> to JSON file
  public static void writeEmployeesToJson(ArrayList<Employee> employees, String fileName) {
    try {
      jsonMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), employees);
      System.out.println("Employees written to JSON file: " + fileName);
    } catch (IOException e) {
      throw new RuntimeException("Error writing employees to JSON", e);
    }
  }

  // 2️⃣ Deserialize JSON file to ArrayList<Employee>
  public static ArrayList<Employee> readEmployeesFromJson(String fileName) {
    try {
      return jsonMapper.readValue(new File(fileName), new TypeReference<ArrayList<Employee>>() {});
    } catch (IOException e) {
      throw new RuntimeException("Error reading employees from JSON", e);
    }
  }

  // 3️⃣ Serialize ArrayList<Employee> to XML file
  public static void writeEmployeesToXml(ArrayList<Employee> employees, String fileName) {
    try {
      xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), employees);
      System.out.println("Employees written to XML file: " + fileName);
    } catch (IOException e) {
      throw new RuntimeException("Error writing employees to XML", e);
    }
  }

  // 4️⃣ Deserialize XML file to ArrayList<Employee>
  public static ArrayList<Employee> readEmployeesFromXml(String fileName) {
    try {
      return xmlMapper.readValue(new File(fileName), new TypeReference<ArrayList<Employee>>() {});
    } catch (IOException e) {
      throw new RuntimeException("Error reading employees from XML", e);
    }
  }
}
