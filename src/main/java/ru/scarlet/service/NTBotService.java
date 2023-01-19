package ru.scarlet.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.scarlet.telegram.TelegramConfig;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class NTBotService {

    private final TelegramConfig telegramConfig;

    @Data
    public static class ToDto {
        String chatId;
        String text;
    }

    public ResponseEntity<?> send(ToDto toDto) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = new FormBody.Builder()
                .add("chat_id", toDto.getChatId())
                .add("text", toDto.getText()).build();
        Request request = new Request.Builder()
                .url(String.format(telegramConfig.getUrl(), telegramConfig.getBotToken()))
                .method("POST", body).build();
        Response response = client.newCall(request).execute();
        if (response.code()==400)
            return ResponseEntity.badRequest().body("Ошибка в запросе");
        if (response.code()==200)
            return ResponseEntity.ok().body(response);
        return ResponseEntity.status(500).body("Что-то пошло не так");
    }
}
