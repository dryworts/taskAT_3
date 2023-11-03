package ru.netology.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppOrderNegativeTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldBeFailedIncorrectNameInput() throws InterruptedException {
        driver.findElement(By.xpath("//span[@data-test-id='name']//input")).sendKeys("Vasya");
        driver.findElement(By.xpath("//span[@data-test-id='phone']//input")).sendKeys("+79048889988");
        driver.findElement(By.xpath("//label[@data-test-id='agreement']")).click();
        driver.findElement(By.xpath("//button[contains(@class, 'button')]")).click();
        String text = driver.findElement(By.xpath("//span[@data-test-id='name'][contains(@class,'input_invalid')]//span[@class='input__sub']")).getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text);
        Thread.sleep(8000);


    }

    @Test
    public void shouldBeFailedEmptyNameInput() throws InterruptedException {

        driver.findElement(By.xpath("//span[@data-test-id='phone']//input")).sendKeys("+79048889988");
        driver.findElement(By.xpath("//label[@data-test-id='agreement']")).click();
        driver.findElement(By.xpath("//button[contains(@class, 'button')]")).click();
        String text = driver.findElement(By.xpath("//span[@data-test-id='name'][contains(@class,'input_invalid')]//span[@class='input__sub']")).getText().trim();

        assertEquals("Поле обязательно для заполнения", text);
        Thread.sleep(8000);


    }



    @Test
    public void shouldBeFailedIncorrectPhoneInput() throws InterruptedException {
    //driver.findElement(By.xpath("//span[@data-test-id='name']//input")).sendKeys("Иванов Иван");
    //driver.findElement(By.xpath("//span[@data-test-id='phone']//input")).sendKeys("Foo");
    //driver.findElement(By.xpath("//label[@data-test-id='agreement']")).click();
    //driver.findElement(By.xpath("//button[contains(@class, 'button')]")).click();
    //String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();
    //String text = driver.findElement(By.xpath("//span[@data-test-id='phone'][contains(@class,'input_invalid')]//span[@class='input__sub']")).getText().trim();
    //assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text);



        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Петров Иван");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("Foo");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text);


        Thread.sleep(8000);
    }


    @Test
    public void shouldBeFailedEmptyPhoneInput() throws InterruptedException {
        driver.findElement(By.xpath("//span[@data-test-id='name']//input")).sendKeys("Иванов Петр");

        driver.findElement(By.xpath("//label[@data-test-id='agreement']")).click();
        driver.findElement(By.xpath("//button[contains(@class, 'button')]")).click();
        String text = driver.findElement(By.xpath("//span[@data-test-id='phone'][contains(@class,'input_invalid')]//span[@class='input__sub']")).getText().trim();

        assertEquals("Поле обязательно для заполнения", text);
        Thread.sleep(8000);
    }

    @Test
    public void shouldBeFailedUncheckedCheckBox() throws InterruptedException {
        driver.findElement(By.xpath("//span[@data-test-id='name']//input")).sendKeys("Петров Иван");
        driver.findElement(By.xpath("//span[@data-test-id='phone']//input")).sendKeys("+79048889988");

        driver.findElement(By.xpath("//button[contains(@class, 'button')]")).click();

        assertTrue(driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid")).isDisplayed());
        Thread.sleep(8000);


    }


}
