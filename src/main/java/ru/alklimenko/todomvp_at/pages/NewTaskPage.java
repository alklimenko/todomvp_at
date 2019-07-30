package ru.alklimenko.todomvp_at.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.alklimenko.todomvp_at.DriverManager.getDriver;

public class NewTaskPage {
    private static final String CAPTION = "New TO-DO";

    @FindBy(xpath = "//android.widget.TextView")
    private WebElement caption;

    @FindBy(xpath = "//android.widget.EditText[@resource-id='com.example.android.architecture.blueprints.todomvp.mock:id/add_task_title']")
    private WebElement title;

    @FindBy(xpath = "//android.widget.EditText[@resource-id='com.example.android.architecture.blueprints.todomvp.mock:id/add_task_description']")
    private WebElement description;

    @FindBy(xpath = "//android.widget.ImageButton[@resource-id='com.example.android.architecture.blueprints.todomvp.mock:id/fab_edit_task_done']")
    private WebElement done;

    public NewTaskPage() {
        PageFactory.initElements(getDriver(), this);
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.textToBePresentInElement(caption, CAPTION));
    }

    public void newTitle(String title) {
        this.title.sendKeys(title);
    }

    public void newDescription(String description) {
        this.description.sendKeys(description);
    }

    public void clickDoneButton() {
        done.click();
    }
}
