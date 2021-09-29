package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactHelper().createContact(new ContactData("Медведь", "Балалайкин", "balalaikin@mail.ru", "87777654352"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().chooseContactEdit(before.get(before.size() - 1).getId());
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Понин", "Василий", "kotovic@mail.ru", "87777666666");
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().updateContact();
    app.getContactHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
