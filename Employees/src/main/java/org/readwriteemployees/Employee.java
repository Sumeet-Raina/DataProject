package org.readwriteemployees;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {
  private int employeeID;
  private String namePrefix;
  private String firstName;
  private String middleInitial;
  private String lastName;
  private String gender;
  private String email;
  private LocalDate dateOfBirth;
  private LocalDate dateOfJoining;
  private int salary;

  // ✅ No-arg constructor for Jackson
  public Employee() {}

  public Employee(int employeeID, String namePrefix, String firstName, String middleInitial,
                  String lastName, String gender, String email,
                  String dobString, String dojString, int salary) {

    DateTimeFormatter parser = DateTimeFormatter.ofPattern("M/d/yyyy");
    this.employeeID = employeeID;
    this.namePrefix = namePrefix;
    this.firstName = firstName;
    this.middleInitial = middleInitial;
    this.lastName = lastName;
    this.gender = gender;
    this.email = email;
    this.dateOfBirth = LocalDate.parse(dobString, parser);
    this.dateOfJoining = LocalDate.parse(dojString, parser);
    this.salary = salary;
  }

  // Getters
  public int getEmployeeID() { return employeeID; }
  public String getNamePrefix() { return namePrefix; }
  public String getFirstName() { return firstName; }
  public String getMiddleInitial() { return middleInitial; }
  public String getLastName() { return lastName; }
  public String getGender() { return gender; }
  public String getEmail() { return email; }
  public LocalDate getDateOfBirth() { return dateOfBirth; }
  public LocalDate getDateOfJoining() { return dateOfJoining; }
  public int getSalary() { return salary; }

  // ✅ Add setters so Jackson can populate fields
  public void setEmployeeID(int employeeID) { this.employeeID = employeeID; }
  public void setNamePrefix(String namePrefix) { this.namePrefix = namePrefix; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  public void setMiddleInitial(String middleInitial) { this.middleInitial = middleInitial; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  public void setGender(String gender) { this.gender = gender; }
  public void setEmail(String email) { this.email = email; }
  public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
  public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }
  public void setSalary(int salary) { this.salary = salary; }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
    return "Employee{" +
      "employeeID=" + employeeID +
      ", namePrefix='" + namePrefix + '\'' +
      ", firstName='" + firstName + '\'' +
      ", middleInitial='" + middleInitial + '\'' +
      ", lastName='" + lastName + '\'' +
      ", gender='" + gender + '\'' +
      ", email='" + email + '\'' +
      ", dateOfBirth='" + dateOfBirth.format(formatter) + '\'' +
      ", dateOfJoining='" + dateOfJoining.format(formatter) + '\'' +
      ", salary=" + salary +
      '}';
  }
}
