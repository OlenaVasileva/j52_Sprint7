import edu.praktikum.sprint7.client.ApiClient;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;


public class GetOrderNumberTest {

    private ApiClient apiClient = new ApiClient();

    @Test
    @DisplayName("Получение заказа по номеру")
    @Description("Проверяем, что данные приходят")
    public void getOrderNumber() {

        Response response = apiClient.getOrderIdNumberStep();
        response.then()
                .assertThat()
                .body("order.track", equalTo(320578))
                .statusCode(200);
        System.out.println(response.body().asString());
    }


    @Test
    @DisplayName("Получение заказа без номера заказа ")
    @Description("Проверяем, что приходит ошибка 400")
    public void getOrderNotNumber() {

        Response response = apiClient.getOrderNotIdNumberStep();
        response.then()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для поиска"))
                .statusCode(400);
        System.out.println(response.body().asString());
    }

    @Test
    @DisplayName("Получение заказа по несуществующему номеру")
    @Description("Проверяем, что приходит ошибка 404")
    public void getOrderNotFoundNumber() {

        Response response = apiClient.getOrderNotFoundIdNumberStep();
        response.then()
                .assertThat()
                .body("message", equalTo("Заказ не найден"))
                .statusCode(404);
        System.out.println(response.body().asString());
    }

}

