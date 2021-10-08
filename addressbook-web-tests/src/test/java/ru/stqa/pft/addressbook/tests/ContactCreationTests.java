package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    app.goTo().addNewPage();
    ContactData contact = (new ContactData()
            .withLastname("Медведь").withFirstname("Балалайкин")
            .withHomePhone("87777654352").withWorkPhone("111").withMobilePhone("222")
            .withEmail("balalaikin@mail.ru").withEmail2("green-Street@void").withEmail2("viadomus@void.com"));
    app.contact().createContact(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
