package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String email;
  private final String homePhone;
  private int id;


  public ContactData(int id, String firstname, String lastname, String email, String homePhone) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.homePhone = homePhone;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }


  public ContactData(String firstname, String lastname, String email, String homePhone) {
    this.id = Integer.MAX_VALUE;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", id=" + id +
            '}';
  }

}

