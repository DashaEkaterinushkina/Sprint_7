import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

public class CourierErrorTest {
    Courier courier;

    @Before
    public void setUp() {
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(10));
    }
    @Test
    @DisplayName("Check error field - create courier ") // имя теста
    public void checkCreateCourierErrorFieldTest() {

        courier.setPassword(null);
        new CourierSteps()
                .createCourier(courier)
                .statusCode(400);    //Недостаточно данных для создания учетной записи
        //    если одного из полей нет, запрос возвращает ошибку;
    }

    //если создать пользователя с логином, который уже есть, возвращается ошибка.
    @Test
    @DisplayName("Check create courier double login") // имя теста
    public void checkCreateCourierDoubleLoginTest() {

        new CourierSteps()
                .createCourier(courier);

        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
        new CourierSteps()
                .createCourier(courier)
                .statusCode(409);  //409 Этот логин уже используется. Попробуйте другой.
    }

    //система вернёт ошибку, если неправильно указать логин или пароль
    @Test
    @DisplayName("Authorize error login/password")
    public void checkErrorAuthorizeTest() {
        new CourierSteps()
                .createCourier(courier);

        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
        new CourierSteps()
                .login(courier)
                .statusCode(404); //Учетная запись не найдена
    }

    @Test
    @DisplayName("Authorize without null field login/password")
    public void checkErrorWithoutFieldAuthorizeTest(){
        new CourierSteps()
                .createCourier(courier);

        courier.setLogin(null);
        new CourierSteps()
                .login(courier)
                .statusCode(400);   //Недостаточно данных для входа
    }

    //если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;
    @Test
    @DisplayName("Authorize error login and passowrd")
    public void checkErrorAuthorizeNotFoundTest(){
        new CourierSteps()
                .login(courier)
                .statusCode(404);   //Учетная запись не найдена
    }
}
