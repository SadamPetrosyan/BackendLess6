import api.SwaggerApi;
import clients.SwaggerClient;
import io.restassured.RestAssured;
import net.javacrumbs.jsonunit.JsonAssert;
import net.javacrumbs.jsonunit.core.ConfigurationWhen;
import org.junit.jupiter.api.Test;
import swagger_model.SwaggerProductsRequest;
import swagger_model.SwaggerProductsResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static org.hamcrest.Matchers.lessThan;
public class SwaggerTest {

    private static final String BASE_URL = "https://minimarket1.herokuapp.com/market/swagger-ui.html#/";
    private SwaggerClient client;

    @Test
    void testProductSearchGrocery() throws IOException {

        SwaggerProductsResponse products = client.findAllProducts(
                SwaggerProductsRequest.builder()
                        .id(11)
                        .title("Burger")
                        .prise(100)
                        .categoryTitle("food")
                        .build()
        );


    }

    @Test
    void testPostBurger(){
        RestAssured.given()
                .queryParam("title", "Burger")
                .queryParam("id", "22")
                .queryParam("prise","120")
                .post("https://minimarket1.herokuapp.com/market/swagger-ui.html#/product-controller/createNewProductUsingPOST")
                .body()
                .as(SwaggerProductsResponse.class);


    }

    @Test
    void testPutBurger(){
        RestAssured.given()
                .queryParam("title", "BigBurger")
                .queryParam("id", "22")
                .queryParam("prise","120")
                .put("https://minimarket1.herokuapp.com/market/swagger-ui.html#/product-controller/modifyProductUsingPUT")
                .body()
                .as(SwaggerProductsResponse.class);


    }
    @Test
    void testDeleteBurger(){
        RestAssured.given()
                .queryParam("title", "BigBurger")
                .queryParam("id", "22")
                .queryParam("prise","120")
                .delete("https://minimarket1.herokuapp.com/market/swagger-ui.html#/product-controller/deleteByIdUsingDELETE")
                .body()
                .as(SwaggerProductsResponse.class);


    }

}