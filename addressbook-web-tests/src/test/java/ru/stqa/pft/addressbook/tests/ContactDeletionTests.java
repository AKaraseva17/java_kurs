package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
public void testContactDeletion(){
  app.getNavigationHelper().gotoHomePage();
  app.getContactHelper().initIDContactModification("8");
  app.getContactHelper().outputContactDeletionForm();
  app.getContactHelper().isAlertPresent();
}

}
