package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToContactPage() {
    click(By.linkText("home page"));
  }

  public void fillContactForm(ContactData contractData) {
    type(By.name("firstname"), contractData.getFirstname());
    type(By.name("lastname"), contractData.getLastname());
    type(By.name("home"), contractData.getHomePhone());
    type(By.name("email"), contractData.getEmail());
  }

}

