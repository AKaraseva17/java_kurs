package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String email;
  private final String homePhone;


  public ContactData(String firstname, String lastname, String email, String homePhone) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.homePhone = homePhone;
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

  }

