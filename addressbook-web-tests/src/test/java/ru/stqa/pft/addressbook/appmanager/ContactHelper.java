package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

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

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getHomePhone());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());

    if(creation) {
      if (contactData.getGroups().size() >0){
        Assert.assertTrue(contactData.getGroups().size() == 1);
      }
      new Select(wd.findElement(By.name("new_group")))
              .selectByVisibleText(contactData.getGroups().iterator().next().getName());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

 /* public boolean isElementPresent(By locator){
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }*/

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
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void selectListGroup(){
    List<WebElement> options =  wd.findElement(By.name("to_group")).findElements(By.tagName("option"));
    WebElement option = options.get(0);
    String value = option.getText();
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(value);
    click(By.name("add"));

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
    fillContactForm(contact,true);
    outputContactForm();
    contactCache = null;
    returnToContactPage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    chooseContactEditById(contact.getId());
    fillContactForm(contact,false);
    updateContact();
    contactCache = null;
    returnToContactPage();
  }


  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    outputContactDeletionForm();
    contactCache = null;
    isAlertPresent();
  }
  public void addInGroup(ContactData contact){
    selectContactById(contact.getId());
    selectListGroup();
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;
  }

  private Contacts contactCache = null;

  public void setContactCache(Contacts contactCache) {
    this.contactCache = contactCache;
  }

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  public void check() {
    wd.findElement(By.cssSelector("div.msgbox"));
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail2(email2).withEmail(email).withEmail3(email3)
            .withAddress(address);

  }
    private void initContactModificationById(int id) {
      wd.findElement(By.cssSelector("a[href=\"edit.php?id=" + id + "\"]")).click();
    }
  }





