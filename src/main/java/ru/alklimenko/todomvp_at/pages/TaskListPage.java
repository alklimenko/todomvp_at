package ru.alklimenko.todomvp_at.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;

import static ru.alklimenko.todomvp_at.DriverManager.getDriver;

public class TaskListPage {
    private static final String CAPTION = "TO-DO-MVP";

    @FindBy(xpath = "//android.widget.ImageButton[@resource-id='com.example.android.architecture.blueprints.todomvp.mock:id/fab_add_task']")
    private WebElement addTask;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView")
    private WebElement caption;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.example.android.architecture.blueprints.todomvp.mock:id/title']")
    private List<WebElement> titleList;

    public TaskListPage() {
        PageFactory.initElements(getDriver(), this);
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.textToBePresentInElement(caption, CAPTION));
    }

    public void addButtonClick() {
        getDriver().findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.example.android.architecture.blueprints.todomvp.mock:id/fab_add_task']")).click();
    }

    public String getCaptionText() {
        return caption.getText();
    }

    private WebElement getItem(String title) {
        assert title != null;
        for (WebElement e : titleList) {
            if (title.equals(e.getText())) {
                return e;
            }
        }
        return null;

    }

    public boolean checkTitleExists(String title) {
        return getItem(title) != null;
    }

    public void openItem(String title) {
        Objects.requireNonNull(getItem(title)).click();
    }
}
