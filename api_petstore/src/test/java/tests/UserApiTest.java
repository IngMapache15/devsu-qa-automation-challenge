package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.response.Response;

import org.json.JSONObject;

import org.junit.jupiter.api.*;

import utils.JsonEvidenceUtils;
import utils.ReportManager;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserApiTest {

    private static ExtentReports report;
    private ExtentTest test;

    private static final String BASE_URL =
            "https://petstore.swagger.io/v2";

    private static final String USERNAME =
            "alejandroQA";

    @BeforeAll
    public static void beforeAll() {

        report = ReportManager.getReportInstance();
    }

    @BeforeEach
    public void setup() {

        test = report.createTest(
                "PetStore User CRUD");
    }

    @AfterAll
    public static void afterAll() {

        report.flush();
    }

    @Test
    public void completeUserLifecycle() {

        /*
         * CREATE USER
         */

        JSONObject createBody = new JSONObject();

        createBody.put("id", 1001);
        createBody.put("username", USERNAME);
        createBody.put("firstName", "Alejandro");
        createBody.put("lastName", "Rodriguez");
        createBody.put("email", "alejandro@test.com");
        createBody.put("password", "Password123");
        createBody.put("phone", "3000000000");
        createBody.put("userStatus", 1);

        Response createResponse =
                given()
                        .contentType("application/json")
                        .body(createBody.toString())
                        .when()
                        .post(BASE_URL + "/user");

        JsonEvidenceUtils.saveJson(
                "01_create_user_response",
                createResponse.asPrettyString());

        assertEquals(
                200,
                createResponse.getStatusCode());

        test.pass("User created successfully");

        /*
         * GET USER
         */

        Response getResponse =
                given()
                        .when()
                        .get(BASE_URL +
                                "/user/" +
                                USERNAME);

        JsonEvidenceUtils.saveJson(
                "02_get_user_response",
                getResponse.asPrettyString());

        assertEquals(
                200,
                getResponse.getStatusCode());

        test.pass("User found successfully");

        /*
         * UPDATE USER
         */

        createBody.put(
                "firstName",
                "Alejandro Updated");

        createBody.put(
                "email",
                "updated@test.com");

        Response updateResponse =
                given()
                        .contentType("application/json")
                        .body(createBody.toString())
                        .when()
                        .put(BASE_URL +
                                "/user/" +
                                USERNAME);

        JsonEvidenceUtils.saveJson(
                "03_update_user_response",
                updateResponse.asPrettyString());

        assertEquals(
                200,
                updateResponse.getStatusCode());

        test.pass("User updated successfully");

        /*
         * GET UPDATED USER
         */

        Response updatedResponse =
                given()
                        .when()
                        .get(BASE_URL +
                                "/user/" +
                                USERNAME);

        JsonEvidenceUtils.saveJson(
                "04_get_updated_user_response",
                updatedResponse.asPrettyString());

        assertEquals(
                "Alejandro Updated",
                updatedResponse.jsonPath()
                        .getString("firstName"));

        test.pass(
                "Updated user validated");

        /*
         * DELETE USER
         */

        Response deleteResponse =
                given()
                        .when()
                        .delete(BASE_URL +
                                "/user/" +
                                USERNAME);

        JsonEvidenceUtils.saveJson(
                "05_delete_user_response",
                deleteResponse.asPrettyString());

        assertEquals(
                200,
                deleteResponse.getStatusCode());

        test.pass(
                "User deleted successfully");
    }
}