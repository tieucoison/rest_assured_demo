package restassured.learn.base;

import io.restassured.RestAssured;
import org.example.endPoint;
import org.testng.annotations.BeforeTest;

public class baseTest {
    @BeforeTest
    public void init() {
        RestAssured.baseURI = endPoint.BASE_URI;
    }
}
