import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;

public class CourierTest {

    private CourierSteps courierSteps = new CourierSteps();
    Courier courier;

    @Before
    public void setUp() {
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(10));
    }
    @Test
    @DisplayName("Check create courier and return ok")
    public void checkCreateCourierAndTrueTest() {

        new CourierSteps()
                .createCourier(courier)
                .statusCode(201)   //запрос возвращает правильный код ответа
                .body("ok", is(true)); //успешный запрос возвращает ok: true
    }

    @Test
    @DisplayName("Check create double courier")
    public void checkCreateDoubleCourierTest() {

        new CourierSteps()
                .createCourier(courier);

        new CourierSteps()
                .createCourier(courier)
                .statusCode(409);  //409 Этот логин уже используется. Попробуйте другой.
    }

    @Test
    @DisplayName("Check authorize courier")
    public void checkAuthorizeCourier(){

        new CourierSteps()
                .createCourier(courier);

        //передаём все поля курьера для авторизации
        Integer id = courierSteps.login(courier)
                .extract().body().path("id");
        System.out.println("Успешный запрос возвращает id: " + id);
    }

    @After
    public void tearDown() {        //удаление курьера
        Integer id = courierSteps.login(courier)
                .extract().body().path("id");
        courier.setId(id);
        courierSteps.deleteCourier(courier);

    }
}