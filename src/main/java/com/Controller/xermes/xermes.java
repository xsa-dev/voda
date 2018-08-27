package com.Controller.xermes;

import com.View.Keyboards;
import com.Controller.Clubs.Payments;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * RULES:
 * 1) this must to be a long support bot with module logik
 * 2) this myst to be a shop module
 * 3) this must to be a edeedf module
 * 4) this must to be a diary module
 * 5) this must to be a CORE
 * 6) this must to be a Sheduled Notifications
 * 7) this must to be QIWI Payment
 * 8) this must to be YD Payment
 * 9) this must to be ...
 * 10) this must to be a activity diary
 * 11) this must to be a HERMES Conception
 *  a) Partners
 *  b) Referals
 * 12) this must to be a Consultant Module
 * 13) this must to be a Learning A Module
 * 14) this must to be a Learning B Module
 * 15) this must to be a 90 days plan
 * 16) this myst to be a mysql connection
 * 17) this myst to be a Kotlin Lang
 * 18)
 * <p>
 * Emoji here: https://github.com/vdurmont/emoji-java#available-emojis
 */
public class xermes extends TelegramLongPollingBot {

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


    public void sendMessageToOwnerId(String messageText, long fromId, String name) {
        SendMessage message = new SendMessage();
        message.setChatId(getOwnerId());
        message.setText("FIT: " + messageText + "\nfrom id" + fromId + " : " + name);
        try {
            sendMessage(message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static boolean checkWithRegExp(String userNameString){
        Pattern p = Pattern.compile("^/start [0-9]{1,9}$");
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }

    public static String getSponsorIdFromInmessage(String inMessage) {
        String[] messageElements = inMessage.split(" ");
        return messageElements[1];
    }


    public void onUpdateReceived(Update update) {

        String inMessage = update.getMessage().getText();
        SendMessage message = new SendMessage();
        long chat_id = update.getMessage().getChatId();

        if (checkWithRegExp(inMessage)) {
            String sponsor = getSponsorIdFromInmessage(inMessage);
            message.setText("Добро пожаловать в Hermes! Я Вас узнал!" + "\n" + "Ваш id: " + sponsor);
            sendMessageToId(chat_id, message);
        }
        else if (inMessage.startsWith("/start")) {
            onLineUserMap.put(update.getMessage().getChat().getId(), update.getMessage().getMessageId());
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            message.setReplyMarkup(keyboards.getDefaultFitActivityKeybord());
            message.setText("Добро пожаловать в Hermes." +
                    "\n" + "Укажите кодовое слово для регистрации.");
            sendMessageToId(chat_id, message);
        }
        else if (inMessage.startsWith("/help")) {
            onLineUserMap.put(update.getMessage().getChat().getId(), update.getMessage().getMessageId());
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            message.setReplyMarkup(keyboards.getDefaultFitActivityKeybord());
            message.setText("Помощь (в разработке).");
            sendMessageToId(chat_id, message);
        } else if (inMessage.equals("Да")) {
            message.setText("Ты красавчик! На тебе фиткойн! Печенька");
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            message.setReplyMarkup(keyboards.getDefaultFitActivityKeybord());
            sendMessageToId(chat_id, message);
        }
         else if (inMessage.equals("/sponsor")) {
            message.setText("Ваша ссылка для приглаения людей " + "\n" + "http://t.me/xermes_bot?start=" + update.getMessage().getFrom().getId());
            sendMessageToId(chat_id, message);
        }
        else if (inMessage.equals("Нет")) {
            message.setText("Ты красавчик! Но фиткойн только тем кто был!");
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            message.setReplyMarkup(keyboards.getDefaultFitActivityKeybord());
            sendMessageToId(chat_id, message);
        } else {
            message.setText("Я не понимаю о чём ты...");
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            message.setReplyMarkup(keyboards.getDefaultFitActivityKeybord());
            sendMessageToId(chat_id, message);
        }
    }

    public xermes() {
        super();
    }

    public String getBotToken() {
        return "686002098:AAEuJ0JgHo8RJ1Z1BoQqiFrpAFul4WkKo40";

    }

    public String getBotUsername() {
        return "Fitlife24bot";
    }

    public xermes(DefaultBotOptions options) {
        super(options);
    }


}
