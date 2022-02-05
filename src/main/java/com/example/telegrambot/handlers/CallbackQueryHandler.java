package com.example.telegrambot.handlers;

import com.example.telegrambot.message_sender.MessageSender;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;

@Component
public class CallbackQueryHandler implements Handler<CallbackQuery> {

private final MessageSender messageSender;

    public CallbackQueryHandler(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public void choose(CallbackQuery callbackQuery) {
        callbackQuery.getData().equals("/next_poem");
        String poemText="І смеркає, і світає,\n" +
                "День божий минає,\n" +
                "І знову люд потомлений,\n" +
                "І все спочиває.\n" +
                "Тілько я, мов окаянний,\n" +
                "І день і ніч плачу\n" ;
        Integer messageId = callbackQuery.getMessage().getMessageId();
        var editMessageText = new EditMessageText();
        editMessageText.setChatId(String.valueOf(callbackQuery.getMessage().getChatId()));
        editMessageText.setMessageId(messageId);
        editMessageText.setText(poemText);
        editMessageText.setReplyMarkup(
                InlineKeyboardMarkup.builder()
                        .keyboardRow(
                                Collections.singletonList(
                                        InlineKeyboardButton.builder()
                                                .text("Посилання")
                                                .url("https://maximum.fm/najkrashchi-virshi-tarasa-grigorovicha-shevchenka_n137337")
                                                .build()
                )).build());
        messageSender.sendEditMessage(editMessageText);

    }
}
