package StepDefinition;


import com.cucumber.listener.Reporter;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;


import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Implementation {


    static WebDriver webdriver;
    static Properties prop;


    @When("^type randomText into 'mercyBasicInfo\\.providerName'$")
    public void type_randomText_into_mercyBasicInfo_providerName() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        RandomStringUtils.randomAlphabetic(3);


    }




    @When("^type randomNummber length '(\\d+)' into '(.+)'$")
    public void type_randomNummber_length_into_mercyBasicInfo_providerName(int length, String object) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String dynamicData = RandomStringUtils.randomNumeric(length);
        getObject(object).sendKeys(dynamicData);

    }

    @When("^type randomText length '(\\d+)' into '(.+)'$")
    public void type_randomText_length_into_mercyBasicInfo_providerName(int length, String object) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String dynamicData = RandomStringUtils.randomAlphabetic(length);
        getObject(object).sendKeys(dynamicData);

    }



    @When("^select new Order$")
    public void select_new_Order() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
      WebElement table =  webdriver.findElement(By.tagName("table"));
      List<WebElement> allrows=table.findElements(By.tagName("tr"));
      System.out.println("number of rows is "+allrows.size());
      for(int rows=0;rows<allrows.size();rows++)
      {
    List < WebElement>    allcols =  allrows.get(rows).findElements(By.tagName("td"));

    for(int cols=0;cols<allcols.size();cols++)
    {
        System.out.print(allcols.get(cols).getText()+"----");
        if(allcols.get(6).getText().equals("New"))
        {
            allcols.get(7).click();
            break;
        }
    }
    System.out.println();
      }

    }

    @When("^verify '(.+)' selected in '(.+)'$")
    public void verify_wound_care_selected_in_dropbdownobject(String data ,String object) throws Throwable {

        WebElement element=getObject(object);
        Select select =new Select(element);
        String expectedSelectedOption =data;
      String actualSelectedOption=  select.getFirstSelectedOption().getText();
      Assert.assertEquals(expectedSelectedOption,actualSelectedOption);


    }


    @Given("^wait for '(\\d+)' seconds$")
    public void wait_for_seconds(int time) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        Thread.sleep(time);

/* WebDriverWait wait = new WebDriverWait(webdriver, 10);
        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Last 7 Days']")));
        passwordElement.click();;*/


    }


    @Given("^open '(.+)'$")
    public void open_browser(String browser) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\Configuration\\VISN_Suppliers.properties");
        prop = new Properties();
        prop.load(fis);

        switch (browser) {

            case "chrome":


                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

             /*   HashMap<String, Object> a = new HashMap<String, Object>();

                a.put("profile.default_content_settings.popups", 0);
                a.put("download.default_directory", "E:\\fleetjackdownlao");

                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", a);
                options.addArguments("--start-maximized");*/
                webdriver = new ChromeDriver();
                Reporter.addStepLog("sucessfully opened the " + browser);

                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "E:\\firefoxgeko\\geckodriver.exe");
                webdriver = new FirefoxDriver();

                webdriver.manage().window().maximize();
                ;

                break;


            case "Appium":


                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("BROWSER_NAME", "Android");
                capabilities.setCapability("VERSION", "4.4.2");
                capabilities.setCapability("deviceName", "Moto G");
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("appPackage", ".apk");


                //  webdriver   = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

                webdriver.manage().window().maximize();
                ;

                break;

        }


    }

    public WebElement getObject(String objectName) throws Exception {

        String objectProperty = prop.getProperty(objectName);
        System.out.println("object name is " + objectProperty);
        String objectExtract[] = objectProperty.split("\\~");
        String objectType = objectExtract[0];
        System.out.println("objectType is " + objectType);
        String object = objectExtract[1];
        System.out.println("object is " + object);

        switch (objectType) {
            case "xpath":


                return webdriver.findElement(By.xpath(object));


            case "id":


                return webdriver.findElement(By.id(object));


            case "name":


                return webdriver.findElement(By.name(object));


        }
        return null;
    }

    //    return null;


    @Given("^close browser$")
    public void close_browser() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // webdriver.close();;
        webdriver.get("C:\\Users\\admin\\IdeaProjects\\InsprintAuto\\output\\report.html");
    }

    @When("^verify '(.+)' is Displayed$")
    public void verify_object_is_displayed(String object) throws Throwable {

        WebElement element = getObject(object);
        Assert.assertTrue(element.isDisplayed());
        Reporter.addStepLog(object + " " + "is displayed ");
    }






    @When("^verify text '(.+)' displayed'$")
    public void verify_text_please_enter_the_provider_name_displayed(String expectedMessagea) throws Throwable {

        String actualMessage = webdriver.findElement(By.xpath("//*[text()='" + expectedMessagea + "']")).getText();
        System.out.println("actualMessage is " + actualMessage);

        Reporter.addStepLog("expected mesaage is " + expectedMessagea);
        Reporter.addStepLog("actual  mesaage is " + actualMessage);
        Assert.assertEquals(actualMessage, expectedMessagea);
        Reporter.addStepLog("sucessfully displayed the message as " + expectedMessagea);
        System.out.println("sucessfully displayed the message as " + expectedMessagea);

        if (Fielddata.equals(null)) {
            if (actualMessage.equals(expectedMessagea)) {
                System.out.println("sucessfully displayed the message as " + expectedMessagea);
            }
        } else {
            System.out.println("no validation message displayed ");
        }


    }


    @When("^clear '(.+)'$")
    public void clear_object(String Object) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        getObject(Object).clear();
        ;


    }

    @When("^click '(.+)'$")
    public void click_object(String Object) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        getObject(Object).click();
        ;

    }


    @When("^click '(.+)' through javaScript$")
    public void click_object_through_javaScript(String object) throws Throwable {
        // Write code here that turns the phrase above into concrete actions


        WebElement element = getObject(object);
        JavascriptExecutor executor = (JavascriptExecutor) webdriver;
        executor.executeScript("arguments[0].click();", element);

    }


    @When("^navigate into '(.+)'$")
    public void navigate_into_url(String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions


        webdriver.get(url);
    }

    @Given("^move the cursor '(.+)' from x axis and '(.+)' from y axis$")
    public void move_the_cursor_from_x_axis_and_from_y_axis(int arg1, int arg2) throws Throwable {
        initiliazeActions();
        ;
        act.moveByOffset(arg1, arg2).build().perform();
        ;

    }
    // boolean result =  data.matches("^[a-zA-Z0-9]*$");



    @When("^verify email validation on '(.+)'$")
    public void verify_email_validation_on_object(String object) throws Throwable {
        String emailValidation=getObject(object).getAttribute("value");

        String email = "";
        if (isValid(emailValidation))
            System.out.print("valid email ");
        else
            System.out.print("invalid email ");
    }



    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    @When("^verify '(.+)' is alphanumericandcharcters$")
    public void verify_object_is_alphanumericcharcters(String object) throws Throwable {
        String data = getObject(object).getAttribute("value");
        boolean result =  data.matches("^[a-zA-Z0-9]*$");

        if(result==true || data.equals(null))
        {
            Reporter.addStepLog("Acepting alpha nuemirc");
        }else
        {
            Reporter.addStepLog("Acepting alpha nuemirc and speacial chareatcers");
        }







    }




    @When("^verify '(.+)' is numeric$")
    public void verify_object_is_numeric(String object) throws Throwable {
        String data = getObject(object).getAttribute("value");
        boolean result =  data.matches("^[0-9]*$");



        if(result==true || data.equals(null))
        {
            Reporter.addStepLog("Acepting only numeric");
        }else
        {
            Reporter.addStepLog("case failed because of "+data);
        }




    }


    @When("^verify '(.+)' is alpha$")
    public void verify_object_is_alpha(String object) throws Throwable {
       String data = getObject(object).getAttribute("value");
      boolean result =  data.matches("^[a-zA-Z]*$");

      if(result==true || data.equals(null))
      {
          Reporter.addStepLog("Acepting only apha");
      }else
      {
          Reporter.addStepLog("case failed because of "+data);
      }






    }

    @Given("^type '(.+)' into '(.+)'$")
    public void type_data_into_Object(String data, String object) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        getObject(object).sendKeys(data);
        Reporter.addStepLog("Entered  data is " + data);


    }

    @Given("^ElementOnLoaders$")
    public void elementonloaders() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(13000);
    }

    @Given("^ElementOnLoader$")
    public void elementonloader() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(7000);
    }

    String Fielddata;

    @When("^verify '(.+)' is empty$")
    public String verify_object_is_empty(String object) throws Throwable {

        Fielddata = getObject(object).getAttribute("value");
        Reporter.addStepLog("current data in the field is " + Fielddata);
        System.out.println("current data in the field is " + Fielddata);
        return Fielddata;
    }

    @When("^verify text '(.+)' from '(.+)'$")
    public void verify_text_from_object(String expectedTtext, String object) throws Throwable {
        // Write code here that turns the phrase above into concrete actions


        String actualText = getObject(object).getText();
        System.out.println("actualText is "+actualText);
        System.out.println("expected text is"+expectedTtext);
        Assert.assertEquals(actualText, expectedTtext);
        System.out.println(actualText  + "is equal to " + expectedTtext + "hence the step is passed");
        Reporter.addStepLog(actualText  + "is equal to " + expectedTtext + "hence the step is passed");

    }


    @When("^verify provider name in webTable$")
    public void verify_provider_name_in_webtable() throws Throwable {

/*
        String xpathStart = "(//*[@id='CopyflxProvList_lblProviderName'])[";
        String xpathEnd = "]";


        for (int x = 1; x < 10; x++) {
            String ProvidersList = webdriver.findElement(By.xpath(xpathStart + x + xpathEnd)).getText();

            //    Reporter.addStepLog(ProvidersList);

            if (ProvidersList.equals("enteredValueInTheField")) {
                Reporter.addStepLog("provider details were saved ");
            } else {
                Reporter.addStepLog("provider details were not saved ");
            }

        }*/

    }


    String enteredValueInTheField;

    @When("^get Enteredtext from field '(.+)'$")
    public String get_enteredtext_from_field_object(String object) throws Throwable {

        WebElement element = getObject(object);
        enteredValueInTheField = element.getAttribute("value");
        Reporter.addStepLog("entered value on the field is " + enteredValueInTheField);


        return enteredValueInTheField;

    }
    int  enteredValueCount;
    String enterdValue;

    @When("^get Enteredtextcount from field '(.+)'$")
    public int get_enteredtextcount_from_field_object(String object) throws Throwable {

        WebElement element = getObject(object);
        enterdValue   = element.getAttribute("value");
        enteredValueCount=enterdValue.length();
        Reporter.addStepLog("entered value on the field is " + enterdValue);
        Reporter.addStepLog("entered value lenght on the field is " + enteredValueCount);


        return enteredValueCount;

    }






        @When("^verify Enteredtextcount from '(.+)' is '(.+)'$")
        public void verify_enteredtextcount_from_object_is_40(String object ,int exepetcedCount) throws Throwable {
        Reporter.addStepLog("expected count data is "+exepetcedCount);
            Reporter.addStepLog("actual count data is "+enteredValueCount);

            Assert.assertEquals(enteredValueCount,exepetcedCount);

        }

    String disabledFieldValue;

    @When("^get Enteredtext from Disabledfield '(.+)'$")
    public String get_enteredtext_from_Disabledfield_object(String object) throws Throwable {

        WebElement element = getObject(object);
        disabledFieldValue = element.getText();


        disabledFieldValue = webdriver.findElement(By.cssSelector("#frmProvider_txtboxprovidername")).getText();
        Reporter.addStepLog("entered value on the field is " + disabledFieldValue);

        return disabledFieldValue;

    }


    @When("^get text from '(.+)'$")
    public String get_text_from_object(String object) throws Throwable {
        // Write code here that turns the phrase above into concrete actions


        String selectedData = getObject(object).getText();
        System.out.println("selected data is " + selectedData);
        Reporter.addStepLog("selected data is " + selectedData);
        return selectedData;

    }


    @When("^select '(.+)' into '(.+)'$")
    public void select_data_into_object() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    Actions act;

    public void initiliazeActions() {
        act = new Actions(webdriver);
    }

    @When("^MouseOver '(.+)'$")
    public void mouseover_object(String object) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        initiliazeActions();
        WebElement element = getObject(object);
        act.moveToElement(element).build().perform();
    }


    @Given("^clickThroughJavaScript$")
    public void clickthroughjavascript() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebElement element = webdriver.findElement(By.xpath("//*[@id='submit']"));
        JavascriptExecutor executor = (JavascriptExecutor) webdriver;
        executor.executeScript("arguments[0].click();", element);
    }


}





