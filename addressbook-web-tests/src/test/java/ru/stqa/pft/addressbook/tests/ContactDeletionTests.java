package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
public void testContactDeletion() throws Exception{
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (!app.getContactHelper().isThereAContact()){
    app.getNavigationHelper().gotoAddNewPage();
    app.getContactHelper().createContact(new ContactData("Медведь", "Балалайкин", "balalaikin@mail.ru", "87777654352"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().outputContactDeletionForm();
    app.getContactHelper().isAlertPresent();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before - 1);
}

}
