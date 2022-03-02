package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LogInAndChatSteps {
    WebDriver driver;
    AppiumDriver<WebElement> androidDriver;
    WebDriverWait wait;
    String activationCode;

    @Given("^I navigate to Web Application \"([^\"]*)\" with company \"([^\"]*)\", username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iNavigateToWebApplicationWithCompanyUsernameAndPassword(String urlLeadExpert, String company, String username01, String password) throws Throwable {

        String version = "98.0.4758.102";
        ChromeDriverManager.getInstance().version(version).setup();
        driver = new ChromeDriver();
        driver.get(urlLeadExpert);
        driver.manage().window().maximize();

//        String username02 = "automation_auto_2022";
        String securityCode = "111111";

        wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=Company]")));
        WebElement txtCompany = driver.findElement(By.cssSelector("input[placeholder=Company]"));
        txtCompany.sendKeys(company);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Next']")));
        WebElement btnNext = driver.findElement(By.xpath("//span[text()='Next']"));
        btnNext.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-testid=usernameLogin]")));
        WebElement txtUsername = driver.findElement(By.cssSelector("input[data-testid=usernameLogin]"));
        txtUsername.sendKeys(username01);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-testid=passwordLogin]")));
        WebElement txtPassword = driver.findElement(By.cssSelector("input[data-testid=passwordLogin]"));
        txtPassword.sendKeys(password);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'Login_button') and text()='Login']")));
        WebElement btnLogIn = driver.findElement(By.xpath("//span[contains(@class,'Login_button') and text()='Login']"));
        btnLogIn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='tel'])[1]")));
        WebElement txtSecurityCode = driver.findElement(By.xpath("(//input[@type='tel'])[1]"));
        txtSecurityCode.sendKeys(securityCode);
    }

    @When("^User01 requests the QR code from the Web Application$")
    public void userRequestsTheQRCodeFromTheWebApplication() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-testid=link-to-profile-page] div")));
        WebElement lbAvatar = driver.findElement(By.cssSelector("a[data-testid=link-to-profile-page] div"));
        lbAvatar.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'Account_button__1g') and contains(.,'Devices')]")));
        WebElement lbDevices = driver.findElement(By.xpath("//div[contains(@class,'Account_button__1g') and contains(.,'Devices')]"));
        lbDevices.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Link Device')]")));
        WebElement btnLinkDevices = driver.findElement(By.xpath("//button[contains(text(),'Link Device')]"));
        btnLinkDevices.click();

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'LinkDeviceModal_code-name')]")));
        WebElement lbActivationCode = driver.findElement(By.xpath("//div[contains(@class,'LinkDeviceModal_code-name')]"));
        activationCode = lbActivationCode.getText();


//        WebDriverWait waitMobile = new WebDriverWait(androidDriver, 30);

//        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath("//android.view.ViewGroup[@content-desc='tutorial_skip']")));

    }

    @When("^User01 lanch app and input QR code$")
    public void userLanchAppAndInputQRCode() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platform", "Android");
        capabilities.setCapability("platformVersion", "R");
        capabilities.setCapability("deviceName", "Nexus_6_API_R");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("app",
                "/Users/vunht/Documents/Automation_Team/LeapExpert/Libra_Dragon_TestNG/apps/app-qa-release.apk");
        capabilities.setCapability("appPackage", "com.leapxpert.manager.qa");
        capabilities.setCapability("appActivity", "com.leapxpertapp.MainActivity");
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("noReset", "false");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("newCommandTimeout", "0");
        capabilities.setCapability("uiautomator2ServerLaunchTimeout", "40000");
        capabilities.setCapability("skipUnlock", false);

        String url = "http://127.0.0.1:4723/wd/hub";
        System.out.println("URL: " + url);
        try {
            System.setProperty("webdriver.http.factory", "apache");
            androidDriver = new AndroidDriver<WebElement>(new URL(url), capabilities);
            androidDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        } catch (Exception e) {
        }

        AndroidElement btnSkip = (AndroidElement) androidDriver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='tutorial_skip']"));
        btnSkip.click();

//        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath("(//android.widget.EditText)[1]")));
        AndroidElement txtQRCode = (AndroidElement) androidDriver.findElement(By.xpath("(//android.widget.EditText)[1]"));
        txtQRCode.sendKeys(activationCode);
    }

    @Then("^Quit app and website$")
    public void quitAppAndWebsite() {
        driver.quit();
        androidDriver.quit();
    }

//	@When("^I input email \"(.*?)\"$")
//	public void clearAndInputDynamicTextbox(String dataValue) {
//		registerPage.inputEmail(dataValue + randomEmail() + "@gmail.com");
//	}
//
//	@When("^I click to submit account$")
//	public void i_clicktosubmit_button() {
//		registerPage.clickSubmitAccount();
//	}
//
//	@When("^I get email and password$")
//	public void i_input_email_to_register() {
//		usernameLogin = registerPage.getUserIDInfo();
//		passwordLogin = registerPage.getPasswordIDInfo();
//	}

//	@When("^I open login page$")
//	public void i_Open_Login_Page() {
//		loginPage = registerPage.openLoginPage(loginURL);
//	}

}