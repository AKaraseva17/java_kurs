package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactGroupTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions (){
    if(app.db().groups().size() ==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      ContactData newContact = new ContactData()
              .withLastname("Медведь").withFirstname("Балалайкин").withEmail("balalaikin@mail.ru")
              .withWorkPhone("111").withMobilePhone("222")
              .withHomePhone("87777654352").withEmail2("green-Street@void").withEmail2("viadomus@void.com")
              .inGroup(groups.iterator().next());
      app.goTo().homePage();
      app.goTo().addNewPage();
      app.contact().createContact(newContact);
    }
  }
@Test
  public void testContact(){
    Groups groups = app.db().groups();
    ContactData contact = app.db().contacts().iterator().next();
    app.goTo().homePage();
    app.contact().addInGroup(contact);
  }

}
