package com.draft.Statistica;

import com.View.Keyboards;
import com.draft.Clubs.Payments;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.HashMap;


/**
 * Created by User on 11.07.2017
 * Updated by User on 19.07.2017
 * Updated by iko in 25.08.2017
 * Updated by xsd in 26.08.2017
 * Updated by iko in 01.09.2017
 * Updated by xsa in 10.12.2017
 * <p>
 * Emoji here: https://github.com/vdurmont/emoji-java#available-emojis
 */
public class statistica extends TelegramLongPollingBot {

    HashMap<Long, Integer> onLineUserMap = new HashMap<Long, Integer>();
    HashMap<Long, String> userWorkFlow = new HashMap<Long, String>();

    Keyboards keyboards = new Keyboards();
    Payments payments = new Payments();
    ReplyKeyboardMarkup rkm = new ReplyKeyboardMarkup();
    String[] WorkFlow = {"INIT", "WAITANSWER", "ANSWERED"};

    public static long getAdminId() {
        return 188416619;
    }

    public static long getOwnerId() {
        return 188416619;
    }

    public void sendMessageToId(long receiverid, SendMessage message) {
        try {
            message.setChatId(receiverid);
            sendMessage(message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Deprecated
    public void sendTextToIdMessage(long receiverid, String messagetext) {
        SendMessage message = new SendMessage();
        message.setChatId(receiverid);
        message.setText(messagetext + "\n" + "to tid: " + receiverid);
        try {
            sendMessage(message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void sendMessageToPlankGroupId() {
        SendMessage message = new SendMessage();
        message.setChatId(-1001108159484L);
        message.setText("Добрый день, все цели достигнуты?");
        try {
            sendMessage(message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void sendMessageToOwnerId(String messageText, long fromId, String name) {
        SendMessage message = new SendMessage();
        message.setChatId(getOwnerId());
        message.setText("STATISTICA: " + messageText + "\nfrom id" + fromId + " : " + name);
        try {
            sendMessage(message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void onUpdateReceived(Update update) {

        String inMessage = update.getMessage().getText();
        SendMessage message = new SendMessage();
        message.setReplyMarkup(keyboards.getDefaultPlankActivityKeybord());
        long chat_id = update.getMessage().getChatId();

        if (inMessage.startsWith("/start")) {
            onLineUserMap.put(update.getMessage().getChat().getId(), update.getMessage().getMessageId());
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            message.setReplyMarkup(keyboards.getDefaultPlankActivityKeybord());
            message.setText("Привет! Я буду помогать вам вести статистику. Ежедневно я буду интересоваться вашими результатами, мне нужно отвечать кнопками при ответе на вопрос. Рад быть полезным.");
            sendMessageToId(chat_id, message);
        } else if (inMessage.startsWith("/help")) {
            onLineUserMap.put(update.getMessage().getChat().getId(), update.getMessage().getMessageId());
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            message.setReplyMarkup(keyboards.getDefaultPlankActivityKeybord());
            message.setText("Любую помощь по моему обучению оказывает мой создатель, разработчки @xsd14 ");
            sendMessageToId(chat_id, message);
        } else if (inMessage.equals("Да")) {
            message.setText("Ты достиг своей цели. Это похвально. Твоя главная цель будет обязательно достигнута.");
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            message.setReplyMarkup(keyboards.getDefaultPlankActivityKeybord());
            sendMessageToId(chat_id, message);
        } else if (inMessage.equals("Нет")) {
            message.setText("Ты просто себя не любишь.");
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            message.setReplyMarkup(keyboards.getDefaultPlankActivityKeybord());
            sendMessageToId(chat_id, message);
        } else {
            message.setText("Я не понимаю о чём ты... Напиши @xsd14 он человек и лучше меня разбирается в словах");
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            message.setReplyMarkup(keyboards.getDefaultPlankActivityKeybord());
            sendMessageToId(chat_id, message);
        }
    }

    public statistica() {
        super();
    }

    public String getBotToken() {
        return "390532084:AAEqQC6-XOqFTRtRZnyipy-qsZR5XsuK1BY";

    }

    public String getBotUsername() {
        return "P30D_bot";
    }

    public statistica(DefaultBotOptions options) {
        super(options);
    }


}
