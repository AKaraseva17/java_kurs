package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @Test(enabled = false)
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    app.goTo().addNewPage();
    File photo = new File("src/test/resources/cry.jpg");
    ContactData contact = (new ContactData()
            .withLastname("Медведь").withFirstname("Балалайкин")
            .withHomePhone("87777654352").withWorkPhone("111").withMobilePhone("222")
            .withEmail("balalaikin@mail.ru").withEmail2("green-Street@void").withEmail2("viadomus@void.com")
            .withPhoto(photo));
    app.contact().createContact(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/cry.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
