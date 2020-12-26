package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {

    RegistrationDto registrationDto = Auth.statusActive();

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSubmitRequest() {
        $("[data-test-id='login'] input").setValue(registrationDto.getLogin());
        $("[data-test-id='password'] input").setValue(registrationDto.getPassword());
        $("[data-test-id='action-login']").click();
        $(".heading").shouldHave(Condition.exactText("  Личный кабинет"));
    }
}
