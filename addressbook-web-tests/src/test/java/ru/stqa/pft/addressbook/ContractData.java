package ru.stqa.pft.addressbook;

public class ContractData {
  private final String firstname;
  private final String lastname;
  private final String email;
  private final String homePhone;

  public ContractData(String firstname, String lastname, String email, String homePhone) {
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
