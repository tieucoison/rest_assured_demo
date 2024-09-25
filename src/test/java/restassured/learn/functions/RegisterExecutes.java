package restassured.learn.functions;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Test;
import restassured.learn.base.baseTest;
import restassured.learn.features.RegisterTestCase;

public class RegisterExecutes extends baseTest {
    private final RegisterTestCase registerTestCase = new RegisterTestCase();

    @Test(testName = "RegisterSuccess")
    public void Register_TC_00_Success() {
        registerTestCase.registerSuccess();
    }

    @Test(testName = "Register_MissingFieldHeaders")
    public void Register_TC_01_MissingFieldHeaders() throws JsonProcessingException {
        registerTestCase.register_WithMissingHeader();
    }

    @Test(testName = "Register_ValidationFieldBody")
    public void Register_TC_02_MissingFieldBody() throws JsonProcessingException {
        registerTestCase.register_WithMissingFieldBody();
    }

    @Test(testName = "Register_ValidationFieldUsername")
    public void Register_TC_03_ValidationFieldUsername() throws JsonProcessingException {
        registerTestCase.register_ValidationFieldUserName();
    }
}
