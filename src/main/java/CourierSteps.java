import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierSteps {

    public ValidatableResponse login(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(RestConfig.HOSTSCOOTER)
                .body(courier)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

    public ValidatableResponse createCourier(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .baseUri(RestConfig.HOSTSCOOTER)
                .body(courier)
                .when()
                .post("/api/v1/courier")//чтобы создать курьера, нужно передать в ручку все обязательные поля;
                .then();
    }

    public void deleteCourier(Courier courier){
        given()
                .header("Content-type", "application/json")
                .baseUri(RestConfig.HOSTSCOOTER)
                .pathParam("id", courier.getId())
                .when()
                .delete("/api/v1/courier/{id}")
                .then();

    }
}
