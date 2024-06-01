import io.restassured.response.ValidatableResponse;
import java.io.File;
import static io.restassured.RestAssured.given;

public class Orders {

    public static ValidatableResponse create(File json) {
        return    given()
                .header("Content-type", "application/json")
                .baseUri(RestConfig.HOSTSCOOTER)
                .and()
                .body(json) // передача файла
                .when()
                .post("/api/v1/orders") // отправка POST-запроса
                .then(); // проверка кода ответа
    }

    //Получить количество заказов курьера
    public static ValidatableResponse ordersCount() {
        return    given()
                .header("Content-type", "application/json")
                .baseUri(RestConfig.HOSTSCOOTER)
                .when()
                .get("/api/v1/orders")
                .then();
    }
}
