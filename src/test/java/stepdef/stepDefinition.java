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

import java.sql.*;
import java.util.concurrent.TimeUnit;

public class stepDefinition {

    WebDriver driver;
    String url = "jdbc:sqlite:src/test/java/testData/sample.db";

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver = new ChromeDriver();
        driver.get("https://adactinhotelapp.com/HotelAppBuild2/");
    }

    @When("I enter valid credentials with username and password")
    public void i_enter_valid_credentials_with_username_and_password() {

        String sql = "SELECT username, password FROM hotel_booking;";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                driver.findElement(By.id("username")).sendKeys(rs.getString("username"));
                driver.findElement(By.id("password")).sendKeys(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() {
        driver.findElement(By.id("login")).click();
    }

    @Then("I should be logged in to my account")
    public void iShouldBeLoggedInToMyAccount() {
        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logoutLink.isDisplayed());
    }

    @When("I search for hotels with location, hotel, room_type, number_of_rooms, check_in_date, check_out_date, adults_per_room,children_per_room")
    public void iSearchForHotelsWithLocationHotelRoom_typeNumber_of_roomsCheck_in_dateCheck_out_dateAdults_per_roomChildren_per_room() {
        String sql = "SELECT location, hotel, room_type, number_of_rooms, check_in_date, check_out_date, adults_per_room, children_per_room  FROM hotel_booking;";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                driver.findElement(By.id("location")).sendKeys(rs.getString("location"));
                driver.findElement(By.id("hotels")).sendKeys(rs.getString("hotel"));
                driver.findElement(By.id("room_type")).sendKeys(rs.getString("room_type"));
                driver.findElement(By.id("room_nos")).sendKeys(rs.getString("number_of_rooms"));
                driver.findElement(By.id("datepick_in")).sendKeys(rs.getString("check_in_date"));
                driver.findElement(By.id("datepick_out")).sendKeys(rs.getString("check_out_date"));
                driver.findElement(By.id("adult_room")).sendKeys(rs.getString("adults_per_room"));
                driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[9]/td[2]/select")).sendKeys(rs.getString("children_per_room"));

                driver.findElement(By.id("Submit")).click();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

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