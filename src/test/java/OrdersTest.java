import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.*;
import java.util.List;

@RunWith(Parameterized.class)
public class OrdersTest {
    private final List<String> colour;
    private int track;
    private Orders orders;

    public OrdersTest(List<String> colour) {
        this.colour = colour;
    }

    @Parameterized.Parameters()
    public static Object[][] data() {
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("GRAY")},
                {List.of("BLACK, GRAY")},
                {List.of()}
        };
    }

    @Before
    public void setUp() {
        orders = new Orders();
    }

    @Test
    public void checkCreateOrderWithColorTest() {
        String json = "{\"firstName\": \"Pupkin\"," +
                " \"lastName\": \"Pupok\"," +
                " \"address\": \"Pupokin 66\", " +
                "\"metroStation\":\"5\", " +
                "\"phone\":\"+79856248571\"," +
                " \"rentTime\": \"5\", " +
                "\"deliveryDate\": \"2024-06-03\"," +
                " \"comment\": \"Add test OrdersStep\", " +
                "\"color\": [\"}" + colour + "{\"]}" ;;

        track = Orders
                .create(json)
                .statusCode(201)         // Успешное создание заказа
                .extract()
                .body()
                .path("track");
        //   System.out.println("Тело ответа содержит track: " + track);
    }

    //Отменить процесс
    @After
    public void cancelTestOrder() {
        orders.cancelOrder(track);
    }
}