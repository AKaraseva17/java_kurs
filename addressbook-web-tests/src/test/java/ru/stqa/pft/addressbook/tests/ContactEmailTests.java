package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
      app.goTo().homePage();
      if (app.contact().list().size() == 0) {
        app.goTo().addNewPage();
        app.contact().createContact(new ContactData()
                .withLastname("Медведь").withFirstname("Балалайкин").withEmail("balalaikin@mail.ru").withHomePhone("87777654352")
                .withWorkPhone("111").withMobilePhone("222").withEmail2("green-Street@void"));
      }
    }
    @Test
    public void testContactEmail() {
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

  private String  mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactEmailTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String email) {
      return email.replaceAll("\\s","");
    }
  }

