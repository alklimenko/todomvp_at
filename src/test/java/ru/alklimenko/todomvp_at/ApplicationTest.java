package ru.alklimenko.todomvp_at;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.alklimenko.todomvp_at.pages.NewTaskPage;
import ru.alklimenko.todomvp_at.pages.TaskDetailPage;
import ru.alklimenko.todomvp_at.pages.TaskListPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ApplicationTest {
    private TaskListPage taskListPage;
    private NewTaskPage newTaskPage;
    private TaskDetailPage taskDetailPage;
    // К title и description добавляем иногда цифры, что бы получить ошибку
    private static final String TITLE = "Able was I ere I saw Elba" + (Math.random() < 0.2 ? "_1" : "");
    private static final String DESCRIPTION = "Never odd or even" + (Math.random() < 0.2 ? "_2" : "");

    @BeforeClass
    public void setUp() {
        DriverManager.addDriver(AndroidDriverProvider.class);
    }

    @AfterClass
    public void tearDown() {
        DriverManager.removeDriver();
    }

    @Test(description = "Проверяем заголовок в начале работы")
    public void step01_check_caption() {
        taskListPage = new TaskListPage();
    }

    @Test(dependsOnMethods = "step01_check_caption",
          description = "Нажимаем кнопку добавления нового задания")
    public void step02_add_task() {
        taskListPage.addButtonClick();
    }

    @Test(dependsOnMethods = "step02_add_task",
          description = "Проверяем заголовок страницы нового задания")
    public void step03_check_caption_new_to_do() {
        newTaskPage = new NewTaskPage();
    }

    @Test(dependsOnMethods = "step03_check_caption_new_to_do",
            description = "Вводим данные нового задания")
    public void step04_type_data_new_task() {
        newTaskPage.newTitle(TITLE);
        newTaskPage.newDescription(DESCRIPTION);
        newTaskPage.clickDoneButton();
    }

    @Test(dependsOnMethods = "step04_type_data_new_task",
          description = "Проверяем наличие задания в списке")
    public void step05_check_new_task_exist() {
        taskListPage = new TaskListPage();
        assertTrue(taskListPage.checkTitleExists(TITLE));
    }

    @Test(dependsOnMethods = "step05_check_new_task_exist",
            description = "Открываем задание")
    public void step06_open_task() {
        taskListPage.openItem(TITLE);
    }

    @Test(dependsOnMethods = "step06_open_task",
            description = "Проверяем задание")
    public void step07_check_task() {
        taskDetailPage = new TaskDetailPage();
        assertEquals(taskDetailPage.getTitle(), TITLE);
        assertEquals(taskDetailPage.getDescription(), DESCRIPTION);
    }
}