import edu.praktikum.sprint7.client.ApiClient;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.notNullValue;


public class GetOrderTest {
    private ApiClient apiClient = new ApiClient();



    @Test
    @DisplayName("Запрос всех заказов")
    @Description("Проверяем, что данные приходят")
    public void getOrderTest() {

        Response response = apiClient.getOrderStep();
        response.then()
                .assertThat()
                .body("orders",notNullValue())
                .statusCode(200);
        System.out.println(response.body().asString());
    }

}

