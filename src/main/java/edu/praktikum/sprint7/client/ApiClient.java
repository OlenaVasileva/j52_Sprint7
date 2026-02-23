package edu.praktikum.sprint7.client;

import edu.praktikum.sprint7.model.Courier;
import edu.praktikum.sprint7.model.CourierCreds;
import edu.praktikum.sprint7.model.Order;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ApiClient {

    public ApiClient() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Step("Создание курьера")
    public Response createNewCourierStep(Courier courier) {
        return given()
                .contentType(JSON)
                .body(courier)
                .when()
                .post("api/v1/courier");
    }
    @Step("Авторизация курьера")
    public Response loginCourierStep(CourierCreds creds){
        return given()
                .contentType(JSON)
                .body(creds)
                .when()
                .post("/api/v1/courier/login");
    }
    @Step("Удаление курьера")
    public Response deleteCourierStep(String id) {
        return given()
                .contentType(JSON)
                .when()
                .delete("api/v1/courier + / + id");
    }
    @Step("Создание заказа")
    public Response createOrderStep(Order id) {
        return given()
                .contentType(JSON)
                .when()
                .post("/api/v1/orders");
    }
    @Step("Получение всех заказов")
    public Response getOrderStep() {
        return given()
                .contentType(JSON)
                .when()
                .get("/api/v1/orders");
    }
    @Step("Получение заказа")
    public Response getOrderIdNumberStep() {
        return given()
                .contentType(JSON)
                .when()
                .queryParam("t", 320578)
                .get("/api/v1/orders/track");
    }
    @Step("Не перадаем номер заказа")
    public Response getOrderNotIdNumberStep() {
        return given()
                .contentType(JSON)
                .when()
                .get("/api/v1/orders/track");
    }
    @Step("Не существующий номер заказа")
    public Response getOrderNotFoundIdNumberStep() {
        return given()
                .contentType(JSON)
                .when()
                .queryParam("t", 00000)
                .get("/api/v1/orders/track");
    }
    @Step("Отмена заказа")
    public Response canceleOrderStep(String id) {
        return given()
                .contentType(JSON)
                .when()
                .put("/api/v1/orders/cancel");
    }

}
