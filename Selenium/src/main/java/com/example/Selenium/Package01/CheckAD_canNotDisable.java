package com.example.Selenium.Package01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CheckAD_canNotDisable implements Runnable {
    private WebDriver driver;
    private WebDriverWait wait;

    private List<WebElement> element_solve;

    private CountDownLatch countDownLatch;


    public CheckAD_canNotDisable(WebDriver driver, WebDriverWait wait, CountDownLatch countDownLatch) {
        this.driver = driver;
        this.wait = wait;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try { //( không có Iframe , tắt trực tiếp được luôn )
            System.out.println("Xem xét //div[@id='ad_position_box']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ad_position_box']")));
            element_solve = driver.findElements(By.xpath("//div[@id='ad_position_box']"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("Có hiển thị //div[@id='ad_position_box']");
                driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
            }
            countDownLatch.countDown();
        } catch (Exception exception) {
            countDownLatch.countDown();
            System.out.println("Không hiển thị //div[@id='ad_position_box']");
            System.out.println(exception);
        }
    }
}
