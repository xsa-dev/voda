package com.draft;

import com.erezults.Result;
import com.model.dbmodel;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendInvoice;
import org.telegram.telegrambots.api.methods.send.SendLocation;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.payments.LabeledPrice;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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
public class fitlife24 extends TelegramLongPollingBot {

    HashMap<Long, Integer> onLineUserMap = new HashMap<Long, Integer>();
    HashMap<Long, String> userWorkFlow = new HashMap<Long, String>();

    Diary diary = new Diary();
    Payments payments = new Payments();
    ReplyKeyboardMarkup rkm = new ReplyKeyboardMarkup();

    public static long getAdminId() {
        return 188416619;
    }

    public static long getOwnerId() {
        return 188416619;
    }

    public void sendMessageToId(long receiverid, SendMessage message) {
        try {
            message.setChatId(receiverid);
            message.setReplyMarkup(diary.getDefaultFitActivityKeybord());
            message.setText("Ты был сегодня на фите?");
            sendMessage(message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void sendTextToIdMessage(long receiverid, String messagetext) {
        SendMessage message = new SendMessage();
        message.setChatId(getOwnerId());
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
        message.setText("@@@: " + messageText + "\nfrom id" + fromId + " : " + name);
        try {
            sendMessage(message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void onUpdateReceived(Update update) {


        System.out.println();
        onLineUserMap.put(update.getMessage().getChat().getId(), update.getMessage().getMessageId());
        sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());
        long chat_id = update.getMessage().getChatId();


    }



    public fitlife24() {
        super();
    }

    public String getBotToken() {
        return "409384909:AAGfnWDDYn87b50k8E1twlllX4p7PQTY8WM";

    }

    public String getBotUsername() {
        return "Fitlife24bot";
    }

    public fitlife24(DefaultBotOptions options) {
        super(options);
    }


}
