package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private String login;
    private String password;
    private String status;
}
