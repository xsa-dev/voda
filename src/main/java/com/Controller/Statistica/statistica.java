package com.Controller.Statistica;

import com.Adapters.dbmodel;
import com.View.Keyboards;
import com.Controller.Clubs.Payments;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.sql.SQLException;
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

    public static long getGroupId() {
        return -0;
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

    static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public String getRandomPossitiveSentanse() {
        String[] sentanses = {
                "Ты на пути к своей цели!",
                "Цель будет покорена!",
                "Никаких проблем!",
                "Будет круто, когда ты достигнешь цели. Пригласишь меня отметить?",
                "Я восхищаюсь тобой.",
                "Знаешь, это очень сложно следовать намеченной цели и не отступать от плана?"
        };

        int x;

        x = randomWithRange(0, 5);


        return sentanses[x];
    }

    public String getRandomNegativeSentanse() {
        String[] sentanses = {
                "Значит завтра не будет лучше!",
                "Цель всё дальше и дальше!",
                "У тебя есть главный враг. Ты сам!",
                "Может сформулируешь для себя что-то пороще?",
                "Ну ладно..",
                "Помедетируй и подумай действительно тебе это нужно."
        };

        int x;

        x = randomWithRange(0, 5);


        return sentanses[x];
    }

    public void sendMessageToThisGroupId() {
        SendMessage message = new SendMessage();
        message.setChatId(getGroupId());
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
            message.setText("Привет! Я буду помогать вам вести статистику. Ежедневно я буду интересоваться вашими результатами. Мне нужно отвечать кнопками при ответе на вопрос. Рад быть полезным.");
            sendMessageToId(chat_id, message);
        } else if (inMessage.startsWith("/help")) {
            onLineUserMap.put(update.getMessage().getChat().getId(), update.getMessage().getMessageId());
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            message.setReplyMarkup(keyboards.getDefaultStatisticakActivityKeybord());
            message.setText("Любую помощь по моему обучению оказывает мой создатель, разработчки @xsd14 ");
            sendMessageToId(chat_id, message);
        } else if (inMessage.equals("Да")) {
            onLineUserMap.put(update.getMessage().getChat().getId(), update.getMessage().getMessageId());
            message.setText(getRandomPossitiveSentanse());
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            try {
                dbmodel.MysqlCon.addAnswerForSheduledMessage(getBotUsername(), getBotToken(), update.getMessage().getMessageId(), update.getMessage().getChatId(), update.getMessage().getText(), update.getMessage().getFrom().getId());
            } catch (ClassNotFoundException e) {
                message.setText("ClassNotFoundE" + "\n" + e.toString());
                sendMessageToId(getOwnerId(), message);
            } catch (SQLException e) {
                message.setText("SqlException" + "\n" + e.toString());
                sendMessageToId(getOwnerId(), message);
            }
            sendMessageToId(chat_id, message);
        } else if (inMessage.equals("Нет")) {
            onLineUserMap.put(update.getMessage().getChat().getId(), update.getMessage().getMessageId());
            message.setText(getRandomNegativeSentanse());
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            // message.setReplyMarkup(keyboards.getDefaultPlankActivityKeybord());
            try {
                // to do need testing, because model need update
                dbmodel.MysqlCon.addAnswerForSheduledMessage(getBotUsername(), getBotToken(), update.getMessage().getMessageId(), update.getMessage().getChatId(), update.getMessage().getText(), update.getMessage().getFrom().getId());
            } catch (Exception e) {
                message.setText("Exception: " + "\n" + e.toString());
                sendMessageToId(getOwnerId(), message);
            }
            sendMessageToId(chat_id, message);
        } else {
            message.setText("Я не понимаю о чём ты... Напиши @xsd14 он человек и лучше меня разбирается в словах");
            try {
                dbmodel.MysqlCon.addUnrecognizedQuestion(getBotUsername(), getBotToken(), update.getMessage().getMessageId(), update.getMessage().getChatId(), update.getMessage().getText());
            } catch (Exception e) {
                message.setText("Exception: " + "\n" + e.toString());
                sendMessageToId(getOwnerId(), message);
            }
            sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
            message.setReplyMarkup(keyboards.getDefaultPlankActivityKeybord());
            sendMessageToId(chat_id, message);
        }
    }

    public statistica() {
        super();
    }

    public String getBotToken() {
        return "484036956:AAFSC-G5tn-PexKcFHMtM0bPPE1hQfNWTfM";

    }

    public String getBotUsername() {
        return "statistica";
    }

    public statistica(DefaultBotOptions options) {
        super(options);
    }


}
