package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.goTo().addNewPage();
      app.contact().createContact(new ContactData()
              .withLastname("Медведь").withFirstname("Балалайкин").withEmail("balalaikin@mail.ru").withHomePhone("87777654352"));
    }
  }
  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withLastname("Понин").withFirstname("Василий").withEmail("kotovic@mail.ru").withHomePhone("87777666666");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before,after);
  }
}
