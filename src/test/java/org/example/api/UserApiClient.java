package org.example.api;

import io.qameta.allure.Step;
import org.example.config.Config;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserApiClient {

    private static final String BASE_URL = Config.get("api.url");
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Pattern ACCESS_TOKEN_PATTERN = Pattern.compile("\"accessToken\":\"([^\"]+)\"");

    @Step("Создать тестового пользователя через API")
    public String register(User user) {
        String body = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}",
                user.getEmail(), user.getPassword(), user.getName());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/auth/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return extractAccessToken(response.body());
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать тестового пользователя через API", e);
        }
    }

    @Step("Войти под тестовым пользователем через API")
    public String login(String email, String password) {
        String body = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/auth/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                return null;
            }
            return extractAccessToken(response.body());
        } catch (Exception e) {
            throw new RuntimeException("Не удалось выполнить вход тестового пользователя через API", e);
        }
    }

    @Step("Удалить тестового пользователя через API")
    public void delete(String accessToken) {
        if (accessToken == null) {
            return;
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/auth/user"))
                .header("Authorization", accessToken)
                .DELETE()
                .build();

        try {
            CLIENT.send(request, HttpResponse.BodyHandlers.discarding());
        } catch (Exception e) {
            throw new RuntimeException("Не удалось удалить тестового пользователя через API", e);
        }
    }

    private String extractAccessToken(String responseBody) {
        Matcher matcher = ACCESS_TOKEN_PATTERN.matcher(responseBody);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalStateException("Ответ API регистрации не содержит accessToken: " + responseBody);
    }
}
