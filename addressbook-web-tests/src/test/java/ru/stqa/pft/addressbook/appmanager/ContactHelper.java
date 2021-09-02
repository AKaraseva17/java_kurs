package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToContactPage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void outputContactForm() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void fillContactForm(ContactData contractData) {
    type(By.name("firstname"), contractData.getFirstname());
    type(By.name("lastname"), contractData.getLastname());
    type(By.name("home"), contractData.getHomePhone());
    type(By.name("email"), contractData.getEmail());
  }

}

