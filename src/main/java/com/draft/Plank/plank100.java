package com.draft.Plank;

import com.Model.dbmodel;
import com.View.Keyboards;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.HashMap;


/**
 * Emoji here: https://github.com/vdurmont/emoji-java#available-emojis
 */
public class plank100 extends TelegramLongPollingBot {

    private HashMap<Long, Integer> onLineUserMap = new HashMap<Long, Integer>();
    private HashMap<Long, String> userWorkFlow = new HashMap<Long, String>();
    private Keyboards keyboards = new Keyboards();

    private String[] WorkFlow = {"INIT", "WAITANSWER", "ANSWERED"};

    public static long getAdminId() {
        return 188416619;
    }

    private  static long getOwnerId() {
        return 188416619;
    }

    private void sendMessageToId(long receiverid, SendMessage message) {
        try {
            message.setChatId(receiverid);
            sendMessage(message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void sendMessageGroupId(long groupid, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(groupid); // -1001108159484L
        message.setText(text); //"Ребята, кто сделал сегодня планку? Давай давай! Кубики ждут :)");
        try {
            sendMessage(message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void sendMessageToAdmin(SendMessage message) {
        try {
            message.setChatId(getAdminId());
            sendMessage(message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void sendErrorMessage(Exception e) throws Exception {
    }

    private void sendMessageToOwnerId(String messageText, long fromId, String name) {
        SendMessage message = new SendMessage();
        message.setChatId(getOwnerId());
        message.setText(getBotUsername() + ": " + messageText + "\nfrom id" + fromId + " : " + name);
        try {
            sendMessage(message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private String getStartMessage() {
        return "100 упражнений планки. Добро пожаловать!";
    }
    private String getHelpMessage() {
        return "100 упражнений планки. Добро пожаловать!";
    }
    private String getNotParsedMessage () {
        return "Я не понимаю о чём ты... Напиши @xsd14 он человек и лучше меня разбирается в словах";
    }

    public void onUpdateReceived(Update update) {

        String inMessage = update.getMessage().getText();
        SendMessage outMessage = new SendMessage();
        outMessage.setReplyMarkup(keyboards.getDefaultPlankActivityKeybord());
        long inMessageChatId = update.getMessage().getChatId();

        if (inMessage.startsWith("/start")) {
            onLineUserMap.put(update.getMessage().getChat().getId(), update.getMessage().getMessageId());
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            outMessage.setReplyMarkup(keyboards.getDefaultPlankActivityKeybord());
            outMessage.setText(getStartMessage());
            sendMessageToId(inMessageChatId, outMessage);
        } else if (inMessage.startsWith("/help")) {
            onLineUserMap.put(update.getMessage().getChat().getId(), update.getMessage().getMessageId());
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            outMessage.setReplyMarkup(keyboards.getDefaultPlankActivityKeybord());
            outMessage.setText(getHelpMessage());
            sendMessageToId(inMessageChatId, outMessage);
        } else if (inMessage.equals("Да")) {
            outMessage.setText("Ты красавчик! Кубики ждут :) ");
            try {
                dbmodel.MysqlCon.addAnswerForSheduledMessage(getBotUsername(), getBotToken(), update.getMessage().getMessageId(), update.getMessage().getChatId(), update.getMessage().getText(), update.getMessage().getFrom().getId());
            } catch (Exception e) {
                outMessage.setText("Exception: " + "\n" + e.toString());
                sendMessageToId(getOwnerId(), outMessage);
            }
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            outMessage.setReplyMarkup(keyboards.getDefaultPlankActivityKeybord());
            sendMessageToId(inMessageChatId, outMessage);
        } else if (inMessage.equals("Нет")) {
            outMessage.setText("Ты красавчик! Но у тебя сгорела одна жизнь!");
            try {
                dbmodel.MysqlCon.addAnswerForSheduledMessage(getBotUsername(), getBotToken(), update.getMessage().getMessageId(), update.getMessage().getChatId(), update.getMessage().getText(), update.getMessage().getFrom().getId());
            } catch (Exception e) {
                outMessage.setText("Exception: " + "\n" + e.toString());
                sendMessageToId(getOwnerId(), outMessage);
            }
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            outMessage.setReplyMarkup(keyboards.getDefaultPlankActivityKeybord());
            sendMessageToId(inMessageChatId, outMessage);
        } else {
            outMessage.setText(getNotParsedMessage());
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            outMessage.setReplyMarkup(keyboards.getDefaultPlankActivityKeybord());
            sendMessageToId(inMessageChatId, outMessage);
        }
    }

    public plank100() {
        super();
    }

    public String getBotToken() {
        return "390532084:AAEqQC6-XOqFTRtRZnyipy-qsZR5XsuK1BY";

    }

    public String getBotUsername() {
        return "P30D_bot";
    }

    public plank100(DefaultBotOptions options) {
        super(options);
    }

}
