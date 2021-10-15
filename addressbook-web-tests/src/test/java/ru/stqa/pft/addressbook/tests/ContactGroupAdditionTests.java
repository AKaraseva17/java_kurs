package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactGroupAdditionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditionsGroup(){
    if(app.db().groups().size() ==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }
  @DataProvider
  public Iterator<Object[]> validContacts() {
    Groups groups = app.db().groups();
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/cry.jpg");
    list.add(new Object[]{new ContactData().withLastname("lastname 1").withFirstname("firstname 1")
            .withHomePhone("homePhone 1").withEmail("email 1").withAddress("address 1")
            .withPhoto(photo).inGroup(groups.iterator().next())});
    list.add(new Object[]{new ContactData().withLastname("lastname 2").withFirstname("firstname 2")
            .withHomePhone("homePhone 2").withEmail("email 2").withAddress("address 2")
            .withPhoto(photo).inGroup(groups.iterator().next())});
    list.add(new Object[]{new ContactData().withLastname("lastname 3").withFirstname("firstname 3")
            .withHomePhone("homePhone 3").withEmail("email 3").withAddress("address 3")
            .withPhoto(photo).inGroup(groups.iterator().next())});
    return list.iterator();
  }
  @BeforeMethod
  public void ensurePreconditionsContact(){
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().addNewPage();
      app.contact().createContact(new ContactData()
              .withLastname("Медведь").withFirstname("Балалайкин").withEmail("balalaikin@mail.ru")
              .withWorkPhone("111").withMobilePhone("222")
              .withHomePhone("87777654352").withEmail2("green-Street@void").withEmail2("viadomus@void.com"));
    }
  }
  @Test
  public void testContactGroupAddition() throws Exception {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    ContactData contact = before.iterator().next();
    app.contact().addInGroup(contact);
    Contacts after = app.db().contacts();
    verifyContactListInUI();
  }
}
