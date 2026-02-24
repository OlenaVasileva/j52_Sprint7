import edu.praktikum.sprint7.client.ApiClient;
import edu.praktikum.sprint7.model.Courier;
import edu.praktikum.sprint7.model.CourierCreds;
import edu.praktikum.sprint7.model.CourierLoginResponse;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static edu.praktikum.sprint7.generator.CourierGenerator.randomCourier;
import static edu.praktikum.sprint7.model.CourierCreds.credsFrom;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginCourierTest {
    private ApiClient apiClient = new ApiClient();
    private String id;
    Courier courier = randomCourier();

    @BeforeEach
    @DisplayName("Создание курьера перед запуском теста")
    public void setup() {
        Response createResponse = apiClient.createNewCourierStep(courier);
        assertEquals(201, createResponse.statusCode());
    }


    @Test
    @DisplayName("Авторизация курьера")
    @Description("Проверяем, что авторизация прошла успешно")
    public void loginCourier() {
        CourierCreds creds = credsFrom(courier);
        Response loginResponse = apiClient.loginCourierStep(creds);
        id = loginResponse.as(CourierLoginResponse.class).getId();
        assertEquals(200, loginResponse.statusCode());
        assertNotNull(id, "ID курьера должен присутствовать в ответе авторизации");
    }

    @Test
    @DisplayName("Авторизация без логина")
    @Description("Проверяем, что приходит ошибка 400")
    public void loginCourierWithoutLogin() {
        CourierCreds creds = new CourierCreds(null, "some-password");
        Response loginResponse = apiClient.loginCourierStep(creds);
        assertEquals(400, loginResponse.statusCode());

    }

    @Test
    @DisplayName("Авторизация без пароля")
    @Description("Проверяем, что приходит ошибка 400")
    public void loginCourierWithoutPassword() {
        CourierCreds creds = new CourierCreds("jhgyjhgh", null);
        Response loginResponse = apiClient.loginCourierStep(creds);
        assertEquals(400, loginResponse.statusCode());

    }


    @Test
    @DisplayName("Авторизация с неверными данными")
    @Description("Проверяем, что приходит ошибка 404")
    public void loginCourierNotFound() {
        CourierCreds creds = new CourierCreds("ljuhhg", "1234567");
        Response loginResponse = apiClient.loginCourierStep(creds);
        assertEquals(404, loginResponse.statusCode());

    }

    @AfterEach
    public void tearDown() {
        apiClient.deleteCourierStep(id);
    }
}
