package ru.netology;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class Auth {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    @BeforeAll
    static void setUpAll(RegistrationDto registrationDto) {
        given()
                .spec(requestSpec)
                .body(registrationDto)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    static Faker faker = new Faker(new Locale("en"));

    public static RegistrationDto statusActive() {
        String login = faker.name().firstName();
        String password = faker.internet().password();
        setUpAll(new RegistrationDto(login,password,"active"));
        return new RegistrationDto(login,password,"active");
    }

    public static RegistrationDto statusBlocked() {
        String login = faker.name().firstName();
        String password = faker.internet().password();
        setUpAll(new RegistrationDto(login,password,"blocked"));
        return new RegistrationDto(login,password,"blocked");
    }

    public static RegistrationDto invalidLogin() {
        String login = "логин";
        String password = faker.internet().password();
        setUpAll(new RegistrationDto(login, password,"active"));
        return new RegistrationDto("login",password,"active");
    }

    public static RegistrationDto invalidPassword() {
        String login = faker.name().firstName();
        String password = "пароль";
        setUpAll(new RegistrationDto(login,password,"active"));
        return new RegistrationDto(login,"password","active");
    }

}
