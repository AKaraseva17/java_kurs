package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToContactPage() {
    click(By.linkText("home page"));
  }

  public void outputContactForm() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("email"), contactData.getEmail());
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[5]/td[8]/a/img"));
  }

  public void outputContactModificationForm() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void initIDContactModification(String id) {
    click(By.id(id));
  }

  public void outputContactDeletionForm() {
    click(By.xpath("//input[@value='Delete']"));
  }
  public boolean isAlertPresent()
  {
    try {
      wd.switchTo().alert().accept();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}

