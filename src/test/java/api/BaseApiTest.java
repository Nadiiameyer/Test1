package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeMethod;


public class BaseApiTest {
    protected RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecification;

    @BeforeMethod
    public void setSpecs() {
        requestSpecification = (RequestSpecification) new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setBaseUri("https://api.novaposhta.ua/v2.0/json/")
                .build();

        responseSpecification = (ResponseSpecification) new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
