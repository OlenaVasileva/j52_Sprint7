import edu.praktikum.sprint7.client.ApiClient;
import edu.praktikum.sprint7.model.Courier;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NegativCreateTest {
    private ApiClient apiClient = new ApiClient();
    private String id;


    @Test
    @DisplayName("Создание двух курьеров")
    @Description("Попытка создать двух курьеров с одинаковым набором данных. Создание второго курьера должно провалиться")
    public void createTwoIdenticalCouriers() {

        Courier courier = new Courier("frtg", "12345", "Колбаса");
        Response response = apiClient.createNewCourierStep(courier);
        assertEquals(409, response.statusCode());


    }

    @Test
    @DisplayName("Создание курьера без логина")
    @Description("Попытка создать курьера не заполнив логин")
    public void createCourierWithoutLogin() {

        Courier courier = new Courier(null, "12345", "Колбаса")
                ;
        Response response = apiClient.createNewCourierStep(courier);
        assertEquals(400, response.statusCode());

    }

    @Test
    @DisplayName("Создание курьера без пароля")
    @Description("Попытка создать курьера не заполнив пароль")
    public void createCourierWithoutPassword() {

        Courier courier = new Courier("нпеааеп", null, "Колбаса");
        Response response = apiClient.createNewCourierStep(courier);
        assertEquals(400, response.statusCode());

    }
}
