import edu.praktikum.sprint7.client.ApiClient;
import edu.praktikum.sprint7.model.Courier;
import edu.praktikum.sprint7.model.CourierLoginResponse;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static edu.praktikum.sprint7.generator.CourierGenerator.randomCourier;
import static edu.praktikum.sprint7.model.CourierCreds.credsFrom;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreateCourierTest {
    private ApiClient apiClient = new ApiClient();
    private String id;

    @Test
    @DisplayName("Создание курьера")
    @Description("Проверяем, что курьера можно создать с валидными данными")
    public void createNewCourierTest() {

        Courier courier = randomCourier();
        Response response = apiClient.createNewCourierStep(courier);
        assertEquals(201, response.statusCode());

        Response loginResponse = apiClient.loginCourierStep(credsFrom(courier));
        id = loginResponse.as(CourierLoginResponse.class).getId();
        assertEquals(201, response.statusCode());
    }

    @AfterEach
    public void tearDown() {
        apiClient.deleteCourierStep(id);
    }
}

