package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoAddNewPage();
    app.getContactHelper().fillContactForm(new ContactData("Медведь", "Балалайкин", "balalaikin@mail.ru", "87777654352", "test1"), true);
    app.getContactHelper().outputContactForm();
    app.getContactHelper().returnToContactPage();
  }

}
