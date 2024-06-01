import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RunWith(Parameterized.class)
public class OrdersTest {
    private final List<String> color;
    File json;

    public OrdersTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] data(){
        return new Object[][]{
                {List.of("BLACK", "")},
                {List.of("GREY", "")},
                {List.of("BLACK", "GREY")},
                {List.of("","")}

        };
    }
    @Before
    public void setUp(){
        json = new File("src/test/resources/newOrder.json");
    }

    @Test
    public void checkCreateOrderWithColorTest() throws IOException {
        FileWriter writer = new FileWriter(json);
        try {
            writer.write(color.toString());
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }

        Integer track = Orders
                .create(json)
                .statusCode(201)         // Успешное создание заказа
                .extract()
                .body()
                .path("track");
        System.out.println("Тело ответа содержит track: " + track);
    }
}