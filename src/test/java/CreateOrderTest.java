import edu.praktikum.sprint7.client.ApiClient;
import edu.praktikum.sprint7.model.CourierLoginResponse;
import edu.praktikum.sprint7.model.CreateOrderResponse;
import edu.praktikum.sprint7.model.Order;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateOrderTest {
    private ApiClient apiClient = new ApiClient();
    private String track;

    @ParameterizedTest
    @MethodSource("argumentsForCreateOrderTest")
    @DisplayName("Создание заказа")
    @Description("Проверяем, что заказ можно создать с валидными данными")

    public void createOrderTest( String firstName, String lastName, String address, String metroStation, String phone, String deliveryDate, Number rentTime, String comment, List<String> colors) {
        Order order = new Order( firstName, lastName, address, metroStation, phone, deliveryDate, rentTime, comment, colors);
        Response response = apiClient.createOrderStep(order);
        assertEquals(201, response.statusCode());
        track = response.as(CreateOrderResponse.class).getTrack();

    }
    private static Stream<Arguments> argumentsForCreateOrderTest() {
        return Stream.of(
                Arguments.of("Гарри", "Поттер", "ул. Зеленая, д. 12", "1", "79562363636", "02.02.2026", 5, "блаблабла", List.of("BLACK")),
                Arguments.of("Рон", "Уизли", "Косой переулок, д. 13", "2", "79857895232", "03.02.2026", 2, "блаблабла", List.of("GREY")),
                Arguments.of("Гермиона", "Грейнджер", "Тайный проспект, д. 7", "3", "79857895232", "03.02.2026", 8, "блаблабла",List.of("BLACK", "GREY")),
                Arguments.of("Полумна", "Лавгуд", "Косая аллея, д. 18", "4", "79857895232", "03.02.2026", 2, "блаблабла", Collections.emptyList())
        );
    }
    @AfterEach
    public void tearDown() {
        apiClient.canceleOrderStep(track);
    }
}


