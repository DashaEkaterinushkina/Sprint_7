import org.junit.Test;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.IsNot.not;

public class OrdersListTest {

    @Test
    public void checkReturnListOrdersTest(){
        Orders
                .ordersCount()
                .assertThat()
                .statusCode(200)
                .and()
                //.body("orders", notNullValue());  //Тело ответа не пустое
                .body("orders", not(empty()));      //Изменено по рекомендациям наставника
    }
}
