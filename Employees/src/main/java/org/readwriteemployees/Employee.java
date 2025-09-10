package org.readwriteemployees;

public class Employee {
  private int employeeID;
  private String namePrefix;
  private String firstName;
  private String middleInitial;
  private String lastName;
  private String gender;
  private String email;
  private String dateOfBirth;
  private String dateOfJoining;
  private int salary;

  public Employee(int employeeID, String namePrefix, String firstName, String middleInitial, String lastName, String gender, String email, String dateOfBirth, String dateOfJoining, int salary) {
    this.employeeID = employeeID;
    this.namePrefix = namePrefix;
    this.firstName = firstName;
    this.middleInitial = middleInitial;
    this.lastName = lastName;
    this.gender = gender;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
    this.dateOfJoining = dateOfJoining;
    this.salary = salary;
  }

  public int getEmployeeID() {
    return employeeID;
  }

  public void setEmployeeID(int employeeID) {
    this.employeeID = employeeID;
  }

  public String getNamePrefix() {
    return namePrefix;
  }

  public void setNamePrefix(String namePrefix) {
    this.namePrefix = namePrefix;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleInitial() {
    return middleInitial;
  }

  public void setMiddleInitial(String middleInitial) {
    this.middleInitial = middleInitial;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getDateOfJoining() {
    return dateOfJoining;
  }

  public void setDateOfJoining(String dateOfJoining) {
    this.dateOfJoining = dateOfJoining;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  @Override
  public String toString() {
    return "Employee{" +
            "employeeID='" + employeeID + '\'' +
            ", namePrefix='" + namePrefix + '\'' +
            ", firstName='" + firstName + '\'' +
            ", middleInitial='" + middleInitial + '\'' +
            ", lastName='" + lastName + '\'' +
            ", gender='" + gender + '\'' +
            ", email='" + email + '\'' +
            ", dateOfBirth='" + dateOfBirth + '\'' +
            ", dateOfJoining='" + dateOfJoining + '\'' +
            ", salary='" + salary + '\'' +
            '}';
  }
}
