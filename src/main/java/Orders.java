import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class Orders {

    public static ValidatableResponse create(String orders) {
        return    given()
                .header("Content-type", "application/json")
                .baseUri(RestConfig.HOSTSCOOTER)
                .and()
                .body(orders) // передача файла
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
    //Удаление заказа
    public ValidatableResponse cancelOrder(int track) {
        return  given()
                .header("Content-type", "application/json")
                .baseUri(RestConfig.HOSTSCOOTER)
                .body(track)
                .when()
                .put("/api/v1/orders/cancel")
                .then();
    }
}
