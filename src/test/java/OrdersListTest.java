import org.junit.Test;
import static org.hamcrest.Matchers.notNullValue;

public class OrdersListTest {

    @Test
    public void checkReturnListOrdersTest(){
        Orders
                .ordersCount()
                .assertThat()
                .statusCode(200)
                .and()
                .body("orders", notNullValue());  //Тело ответа не пустое
    }
}
