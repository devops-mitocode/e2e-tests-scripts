package com.mycompany.pages.owners;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class OwnerPage extends PageObject {

    // List owners
    @FindBy(xpath = "//*[@id=\"main-navbar\"]/ul/li[2]/a")
    WebElementFacade ownerMenu;

    @FindBy(xpath = "//*[@id=\"search-owner-form\"]/div[2]/div/button")
    WebElementFacade ownerSearchOptionMenu;

    @FindBy(id = "ownersTable")
    WebElementFacade ownersTable;

    // Add owner
    @FindBy(xpath = "/html/body/div/div/a")
    WebElementFacade addOwnerButtonOption;

    @FindBy(id = "firstName")
    WebElementFacade firstNameField;

    @FindBy(id = "lastName")
    WebElementFacade lastNameField;

    @FindBy(id = "address")
    WebElementFacade addressField;

    @FindBy(id = "city")
    WebElementFacade cityField;

    @FindBy(id = "telephone")
    WebElementFacade telephoneField;

    @FindBy(xpath = "//*[@id=\"add-owner-form\"]/div[2]/div/button")
    WebElementFacade addOwnerButton;

    @FindBy(xpath = "/html/body/div/div/table[1]")
    WebElementFacade ownersInformationTable;

    public void clickOnOwnerMenu() {
        ownerMenu.waitUntilVisible().waitUntilClickable().click();
    }

    public void clickOnOwnerSearchOptionMenu() {
        ownerSearchOptionMenu.waitUntilClickable().click();
    }

    public int getOwnersTableRowsCount() {
        return getOwnerTableRows().size();
    }

    // Add Owner
    public void clickAddOwnerButtonOption() {
        addOwnerButtonOption.waitUntilClickable().click();
    }

    public void enterFirstName(String firstName) {
        firstNameField.waitUntilVisible().type(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.waitUntilVisible().type(lastName);
    }

    public void enterAddress(String address) {
        addressField.waitUntilVisible().type(address);
    }

    public void enterCity(String city) {
        cityField.waitUntilVisible().type(city);
    }

    public void enterTelephone(String telephone) {
        telephoneField.waitUntilVisible().type(telephone);
    }

    public void enterOwnerData() {
        String firstName = Serenity.sessionVariableCalled("firstName");
        String lastName = Serenity.sessionVariableCalled("lastName");
        String address = Serenity.sessionVariableCalled("address");
        String city = Serenity.sessionVariableCalled("city");
        String telephone = Serenity.sessionVariableCalled("telephone");
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddress(address);
        enterCity(city);
        enterTelephone(telephone);
    }

    public void clickAddOwnerButton() {
        addOwnerButton.waitUntilClickable().click();
    }

    public void scrollToBottom() {
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.END).perform();
    }

    public String getOwnerInformationValue(String header) {
        List<WebElementFacade> rows = getOwnerInformationTableRows();
        for (WebElementFacade row : rows) {
            WebElementFacade th = row.find(By.tagName("th"));
            if (th.getText().equalsIgnoreCase(header)) {
                return row.find(By.tagName("td")).getText();
            }
        }
        return null;
    }

    private List<WebElementFacade> getOwnerTableRows() {
        waitFor(ownersTable).shouldBeVisible();
//        waitFor(ownersTable).withTimeoutOf(Duration.ofSeconds(20)).shouldBeVisible();
        return ownersTable.thenFindAll("tbody tr");
    }

    private List<WebElementFacade> getOwnerInformationTableRows() {
        waitFor(ownersInformationTable).shouldBeVisible();
        return ownersInformationTable.thenFindAll("tbody tr");
    }
}
