package ru.alklimenko.todomvp_at.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static ru.alklimenko.todomvp_at.DriverManager.getDriver;

public class TaskDetailPage {
    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.example.android.architecture.blueprints.todomvp.mock:id/task_detail_description']")
    private WebElement description;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.example.android.architecture.blueprints.todomvp.mock:id/task_detail_title']")
    private WebElement title;

    public TaskDetailPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getDescription() {
        return description.getText();
    }
}
