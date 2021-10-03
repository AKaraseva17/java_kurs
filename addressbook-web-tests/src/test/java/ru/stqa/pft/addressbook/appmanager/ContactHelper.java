package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void outputContactModificationForm() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }
  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
  }
  public void chooseContactEditById(int id) {
    wd.findElement(By.cssSelector("a[href=\"edit.php?id=" + id + "\"]")).click();
  }

  public void outputContactDeletionForm() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert().accept();
      wd.findElement(By.cssSelector("div.msgbox"));
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void createContact(ContactData contact) {
    fillContactForm(contact);
    outputContactForm();
    returnToContactPage();
  }
  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    chooseContactEditById(contact.getId());
    fillContactForm(contact);
    updateContact();
    returnToContactPage();
  }
  public void delete(int index) {
    selectContact(index);
    outputContactDeletionForm();
    isAlertPresent();
  }
  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    outputContactDeletionForm();
    isAlertPresent();
  }

  public void updateContact() {
    click(By.name("update"));
  }

  private void clickEnter() {
    click(By.name("submit"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;
  }
  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;
  }

  public void check() {
    wd.findElement(By.cssSelector("div.msgbox"));
  }


}




