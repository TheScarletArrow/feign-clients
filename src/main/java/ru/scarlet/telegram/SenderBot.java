package ru.scarlet.telegram;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.scarlet.entity.NTBot;


@Getter
@Setter
@RequiredArgsConstructor
@Component
@Slf4j
public class SenderBot extends TelegramLongPollingBot {

    private final ru.scarlet.repository.NTBotRepository NTBotRepository;
    private final TelegramConfig telegramConfig;


    @Override
    public String getBotUsername() {
        return telegramConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return telegramConfig.getBotToken();
    }

    @Override
    @Transactional
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sm = new SendMessage();
            Long chatId = update.getMessage().getChatId();
            sm.setChatId(chatId);
            String text = update.getMessage().getText();
            NTBot ntBot = new NTBot();
                String s = update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName();
            if (text.equals("/start")) {
                ntBot.setTgId(chatId.toString());
                ntBot.setTgName(s);
                ntBot.setIsActive(true);
                if (!NTBotRepository.existsByTgId(chatId.toString())) {
                    NTBotRepository.save(ntBot);
                    sm.setText("Здравствуйте, " + s + "! Ваши данные были сохранены");
                } else sm.setText("Вы уже сохранены");
            }
            if (text.equals("/stop")){
                NTBot ntBot1 = NTBotRepository.findByTgId(chatId.toString());
                ntBot1.setIsActive(false);
                NTBotRepository.save(ntBot1);
                sm.setText(s + " был отключен");

            }
            if (text.isBlank() || !text.equals(("/start"))) return;
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                System.out.println(e.getMessage());
            }
            log.info(sm.getText());
            System.out.println(update);
        }

    }

}
