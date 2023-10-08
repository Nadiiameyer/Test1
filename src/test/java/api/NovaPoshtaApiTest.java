package api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeMethod;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NovaPoshtaApiTest extends BaseApiTest {
    String API_key = "2829111b1e12dc2b5a4c61e9de932084";
    Map<String, Object> reqBody = new HashMap<>();

    @BeforeMethod
    public void setReqBody() {
        Map<String, Object> methodProperties = new HashMap<>();
        methodProperties.put("CityName", "Київ");
        methodProperties.put("Limit", "5");

        reqBody.put("apiKey", API_key);
        reqBody.put("modelName", "Adress");
        reqBody.put("calledMethod", "searchSettlements");
        reqBody.put("methodProperties", methodProperties);
    }
    @Test
    @Tag("regression")
    public void verifyThatSuccess() {
     boolean isSuccess =   given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .extract()
                .body().jsonPath().get("success");
        System.out.println(isSuccess);

    }
   @Test
   @Tag("omit")
   public void getCities() {
       given()
               .spec(requestSpecification)
               .contentType(ContentType.JSON)
               .body(reqBody)
               .when()
               .post()
               .then()
               .spec(responseSpecification)
               .statusCode(200)
               .extract()
               .body().jsonPath().get("success");

   }

    @Test
    public void verifyJsonSchema(){
        given()
       .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(reqBody)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(new File(
                        "/Users/didihenkelmann/Desktop/ITEA/Test1/src/main/resources/validators/np_address_schema.json")));
    }
    @Test
    public void verifyPresentValuesInAdress(){
       List<Address> addressList= given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(reqBody)
                .when()
                .post()
                .then()
                .extract()
                .body().jsonPath().getList("pata[0].Addresses", Address.class);
    addressList.forEach(x -> Assertions.assertTrue(x.getPresent().contains("Київ")));
    }
}
