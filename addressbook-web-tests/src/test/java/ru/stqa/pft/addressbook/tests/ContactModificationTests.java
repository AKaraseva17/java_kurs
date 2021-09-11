package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Понин", "Василий", "kotovic@mail.ru", "87777666666",null), false);
    app.getContactHelper().outputContactModificationForm();
    app.getContactHelper().returnToContactPage();

  }
}
