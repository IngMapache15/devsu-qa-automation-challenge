package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.EvidenceUtils;
import utils.ReportManager;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseFlowTest {

    private WebDriver driver;
    private static ExtentReports report;
    private ExtentTest test;

    @BeforeAll
    public static void beforeAll() {
        report = ReportManager.getReportInstance();
    }

    @BeforeEach
    public void setUp() {
        test = report.createTest("Complete purchase flow - SauceDemo");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    public static void afterAll() {
        report.flush();
    }

    @Test
    public void shouldCompletePurchaseSuccessfully() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        loginPage.open();
        EvidenceUtils.visualDelay();
        test.info("SauceDemo login page opened")
                .addScreenCaptureFromPath(EvidenceUtils.takeScreenshot(driver, "01_login_page"));

        loginPage.login("standard_user", "secret_sauce");
        EvidenceUtils.visualDelay();
        assertTrue(driver.getCurrentUrl().contains("inventory"));
        test.pass("User logged in successfully")
                .addScreenCaptureFromPath(EvidenceUtils.takeScreenshot(driver, "02_inventory_page"));

        inventoryPage.addTwoProductsToCart();
        EvidenceUtils.visualDelay();
        test.pass("Two products added to cart")
                .addScreenCaptureFromPath(EvidenceUtils.takeScreenshot(driver, "03_products_added"));

        inventoryPage.goToCart();
        EvidenceUtils.visualDelay();
        assertTrue(driver.getCurrentUrl().contains("cart"));
        assertEquals(2, cartPage.getCartItemsCount());
        test.pass("Cart page displayed with two products")
                .addScreenCaptureFromPath(EvidenceUtils.takeScreenshot(driver, "04_cart_page"));

        cartPage.clickCheckout();
        EvidenceUtils.visualDelay();
        test.pass("Checkout process started")
                .addScreenCaptureFromPath(EvidenceUtils.takeScreenshot(driver, "05_checkout_step_one"));

        checkoutPage.fillCheckoutInformation("Alejandro", "Rodriguez", "110111");
        EvidenceUtils.visualDelay();
        test.pass("Checkout information completed")
                .addScreenCaptureFromPath(EvidenceUtils.takeScreenshot(driver, "06_checkout_overview"));

        checkoutPage.finishPurchase();
        EvidenceUtils.visualDelay();

        assertEquals("Thank you for your order!", checkoutPage.getConfirmationMessage());
        test.pass("Purchase completed successfully")
                .addScreenCaptureFromPath(EvidenceUtils.takeScreenshot(driver, "07_purchase_completed"));
    }
}