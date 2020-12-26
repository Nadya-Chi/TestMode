package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {



    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSubmitRequest() {
        RegistrationDto registrationDto = Auth.statusActive();
        $("[data-test-id='login'] input").setValue(registrationDto.getLogin());
        $("[data-test-id='password'] input").setValue(registrationDto.getPassword());
        $("[data-test-id='action-login']").click();
        $(".heading").shouldHave(exactText("  Личный кабинет"));
    }

    @Test
    public void shouldNotSubmitRequest() {
        RegistrationDto registrationDto = Auth.statusBlocked();
        $("[data-test-id='login'] input").setValue(registrationDto.getLogin());
        $("[data-test-id='password'] input").setValue(registrationDto.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification'] [class='notification__content']").waitUntil(Condition.visible, 15000).shouldHave(exactText("Ошибка! Пользователь заблокирован"));
    }
}
