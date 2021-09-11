package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String email;
  private final String homePhone;
  private String group;

  public ContactData(String firstname, String lastname, String email, String homePhone,String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.homePhone = homePhone;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getGroup() {
    return group;
  }
}
