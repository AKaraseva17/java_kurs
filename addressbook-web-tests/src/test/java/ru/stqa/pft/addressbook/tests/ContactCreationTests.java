package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/cry.jpg");
    list.add(new Object[]{new ContactData().withLastname("lastname 1").withFirstname("firstname 1")
            .withHomePhone("homePhone 1").withEmail("email 1").withAddress("address 1").withPhoto(photo)});
    list.add(new Object[]{new ContactData().withLastname("lastname 2").withFirstname("firstname 2")
            .withHomePhone("homePhone 2").withEmail("email 2").withAddress("address 2").withPhoto(photo)});
    list.add(new Object[]{new ContactData().withLastname("lastname 3").withFirstname("firstname 3")
            .withHomePhone("homePhone 3").withEmail("email 3").withAddress("address 3").withPhoto(photo)});
    return list.iterator();
  }
  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {
    Contacts before = app.contact().all();
    app.goTo().addNewPage();
    app.contact().createContact(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
