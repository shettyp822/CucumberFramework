package com.cucumberFramework.pageObjects;

import com.cucumberFramework.helper.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import static com.cucumberFramework.stepdefinitions.ServiceHooks.*;

import static com.cucumberFramework.enums.Excel.getExcelValue;

public class UploadDownloadFile {
    private WebDriver driver;

    @FindBy(xpath="//span[contains(text(), 'Choose Files')]")
    public WebElement uploadButton;

    @FindBy(xpath="//span[contains(text(), 'Download')]")
    public WebElement downloadButton;

    @FindBy(xpath="//div[contains(text(), 'Nice! We have successfully converted your Excel file to PDF!')]")
    public WebElement conversionSuccess;

    WaitHelper waitHelper;

    public UploadDownloadFile(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    public void uploadFile() throws InterruptedException, AWTException {
        Thread.sleep(5000);
        uploadButton.click();
        Thread.sleep(5000);
        //sendText(getExcelValue("fileName"));
        sendText(prop.get("filename")+getExcelValue("fileName"));
     //   sendText(prop.getProperty("fileName"));

    }

    public void downloadFile() throws InterruptedException {
        downloadButton.click();

    }

    public static void sendText(String testTOSend) throws AWTException, InterruptedException{
        StringSelection stringSelection= new StringSelection(testTOSend);
        Clipboard clipboard= Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection,stringSelection);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    public void downloadFileSuccess() {
         //File file = new File(getExcelValue("downloadedFilePath"));
        File file = new File(prop.get("downloadedFilePath").toString());
         File downloadedfile = getLastModifiedFile(file);
         boolean downloadStatus=downloadedfile.toString().contains(getExcelValue("downloadedFileName"));
         Assert.assertTrue(downloadStatus);
         System.out.println("Downloaded file location: "+downloadedfile+"\nDownload successful: "+downloadStatus);
    }

    public static File getLastModifiedFile(File directory) {
         File[] files = directory.listFiles();
         if (files.length == 0) return null;
         Arrays.sort(files, new Comparator<File>() {
                public int compare(File o1, File o2) {
         return new Long(o2.lastModified()).compareTo(o1.lastModified());
         }});
         return files[0];
    }
}


