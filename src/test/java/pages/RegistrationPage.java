package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import util.AllDrivers;
import com.opencsv.CSVReader;
import org.junit.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class RegistrationPage extends AllDrivers implements FileManager{
   public RegistrationPage() {
        PageFactory.initElements(driver, this);
    }

// Below are the objects on the page
    @FindBy(name="Vrm")
    private WebElement regDetailsTextField;

    @FindBy(name="Continue")
    private WebElement regPageContinueButton;

    @FindBy(css= ".list-summary-item>span>strong")
    private WebElement vehicleMake;

    @FindBy (xpath=("//*[contains(text(),'GREY')]"))
    private WebElement vehicleColour;

    @FindBy(xpath="//a[@href='https://www.vehicleenquiry.service.gov.uk']")
    private WebElement startNowButton;


    //Base Url and the file path where am reading the data from
    String baseUrl = "https://www.gov.uk/get-vehicle-information-from-dvla";

// Below are the methods
    public void launchUrl(){
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    public void assertHomePage(){
        String expectedTitle = "Get vehicle information from DVLA - GOV.UK";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    public void clickStartNowButton() {
        startNowButton.click();
    }

   // Method that reads the data and send the data
    public void enterRegDetailsTextField() throws IOException, InterruptedException {
        CSVReader reader = new CSVReader(new FileReader(callFileDownload(nameOfFile, csvfileExtension)));
        String [] cell;
        while ((cell=reader.readNext())!=null){
            for(int i=0;i<1;++i){
                String regDetail = cell[i];
                regDetailsTextField.sendKeys(regDetail);
            }

        }
    }

    public void clickRegPageContinueButton(){
        regPageContinueButton.click();
    }

    // I have parameterise to allow different file type name and extension
    public File callFileDownload(String fileName, String extension) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8081/"+fileName+extension,
                HttpMethod.GET, entity, byte[].class, "1");
        File file = new File("download"+extension);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(response.getBody());
        }
        return file;
    }

    // confirm Make
    public void verifyMake() throws IOException {
        CSVReader reader = new CSVReader(new FileReader(callFileDownload(nameOfFile, csvfileExtension)));
        String[] cell;
        while ((cell = reader.readNext()) != null) {
            for (int i = 1; i < 2; ++i) {
                String dataSent = cell[i];
                String expectedMake = dataSent;
                String actualMake = vehicleMake.getText();
                Assert.assertEquals(expectedMake, actualMake);

            }
        }
    }
    // Assert Colour
    public void verifyColour() throws IOException {
                CSVReader reader = new CSVReader(new FileReader(callFileDownload(nameOfFile, csvfileExtension )));
                String [] cell;
                while ((cell=reader.readNext())!=null){
                    for(int i = 2; i <3;++i){
                        String dataSent = cell[i];
                String expectedColour = dataSent;
                String actualColour = vehicleColour.getText();
                Assert.assertEquals(expectedColour, actualColour);
            }
        }
    }
}
