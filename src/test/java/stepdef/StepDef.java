package stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class StepDef {
    WebDriver driver;
    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        driver = new ChromeDriver();

        driver.get("https://adactinhotelapp.com/HotelAppBuild2/");
    }

    @When("I enter valid credentials with username {string} and password {string}")
    public void iEnterValidCredentialsWithUsernameAndPassword(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() {
        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();

    }

    @Then("I should be logged in to my account")
    public void iShouldBeLoggedInToMyAccount() {
        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logoutLink.isDisplayed());

    }

    @When("I search for hotels with {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void iSearchForHotelsWith(String location, String hotel, String roomType, String numberOfRooms, String checkInDate, String checkOutDate, String adultsPerRoom, String childrenPerRoom) {

        driver.findElement(By.id("location")).sendKeys(location);

        driver.findElement(By.id("hotels")).sendKeys(hotel);

        driver.findElement(By.id("room_type")).sendKeys(roomType);

        driver.findElement(By.id("room_nos")).sendKeys(numberOfRooms);

        driver.findElement(By.id("datepick_in")).sendKeys(checkInDate);

        driver.findElement(By.id("datepick_out")).sendKeys(checkOutDate);

        driver.findElement(By.id("adult_room")).sendKeys(adultsPerRoom);

        driver.findElement(By.id("child_room")).sendKeys(childrenPerRoom);

        driver.findElement(By.id("Submit")).click();

    }

    @And("I select a hotel")
    public void iSelectAHotel() {
        driver.findElement(By.id("radiobutton_0")).click();
        driver.findElement(By.id("continue")).click();

    }

    @And("I book the hotel")
    public void iBookTheHotel() {
        driver.findElement(By.id("first_name")).sendKeys("John");
        driver.findElement(By.id("last_name")).sendKeys("Doe");
        driver.findElement(By.id("address")).sendKeys("123 Main St, Anytown, USA");
        driver.findElement(By.id("cc_num")).sendKeys("4111111111111111");
        new Select(driver.findElement(By.id("cc_type"))).selectByVisibleText("VISA");
        new Select(driver.findElement(By.id("cc_exp_month"))).selectByVisibleText("January");
        new Select(driver.findElement(By.id("cc_exp_year"))).selectByVisibleText("2023");
        driver.findElement(By.id("cc_cvv")).sendKeys("123");

        driver.findElement(By.id("book_now")).click();

    }

    @Then("I should see a confirmation of my booking")
    public void iShouldSeeAConfirmationOfMyBooking() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.id("my_itinerary")).click();

        // Find the booking you want to verify
        WebElement booking = driver.findElement(By.name("check_all"));
        // Check if the booking is displayed
        if (booking.isDisplayed()) {
            System.out.println("Booking is displayed.");
        } else {
            System.out.println("Booking is not displayed.");
        }
    }

    @When("I click on Logout")
    public void iClickOnLogout() {
        driver.findElement(By.id("logout")).click();
    }

    @Then("I should be logged out of my account")
    public void iShouldBeLoggedOutOfMyAccount() {
        WebElement loginButton = driver.findElement(By.id("login"));
        Assert.assertTrue(loginButton.isDisplayed());
    }
}
