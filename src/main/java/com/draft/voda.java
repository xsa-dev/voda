package com.draft;

import com.erezults.Result;
import org.apache.maven.plugin.lifecycle.Execution;
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
import com.vdurmont.emoji.EmojiParser;
import com.timer.*;

import java.sql.SQLException;
import java.util.*;

import com.model.*;

import javax.xml.bind.SchemaOutputResolver;

/**
 * Created by User on 11.07.2017
 * Updated by User on 19.07.2017
 * Updated by iko in 25.08.2017
 * Updated by xsd in 26.08.2017
 * Updated by iko in 01.09.2017
 */
public class voda extends TelegramLongPollingBot {

    int bSum = 0; // белок
    int eSum = 0; // энергия
    int vSum = 0; // клетчатка
    int wSum = 0; // вода
    int aSum = 0; // активность

    String[] vStringArray;
    String[] eStringArray;
    String[] aStringArray;
    String[] rStringArray;

    HashMap<Long, Integer> onLineUserMap = new HashMap<Long, Integer>();

    public long getOwnerId() {
        return 188416619;
    }

    public void sendMessageToOwnerId(String messageText, long fromId, String name) {
        SendMessage message = new SendMessage();
        message.setChatId(getOwnerId());
        message.setText(messageText +"\nfrom id" + fromId + " : "+ name);
        try {
            sendMessage(message);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    String resultString = "";

    private List<Integer> admins_ids;

    public void onUpdateReceived(Update update) {

        SendMessage message = new SendMessage();

        onLineUserMap.put(update.getMessage().getChat().getId(), update.getMessage().getMessageId());

        sendMessageToOwnerId(update.getMessage().getText(), Long.valueOf(update.getMessage().getFrom().getId()), update.getMessage().getFrom().getFirstName());

        long chat_id = update.getMessage().getChatId();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup().setResizeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();

        KeyboardButton nearbly = new KeyboardButton("Ближайший клуб " + EmojiParser.parseToUnicode(":earth_asia:"));
        nearbly.setRequestLocation(true);

        KeyboardButton contact = new KeyboardButton("Мой консультант " + EmojiParser.parseToUnicode(":sports_medal:")).setRequestContact(true);

        KeyboardButton inClub = new KeyboardButton("Я в клубе");
        KeyboardButton friends = new KeyboardButton("Приведи друга");
        KeyboardButton recoveryStrong = new KeyboardButton("Восстановление силы");
        KeyboardButton invoice = new KeyboardButton("Картой");
        KeyboardButton cash = new KeyboardButton("Наличными");
        KeyboardButton hommies = new KeyboardButton("Нет денег");

        //region работа с локацией
        if (update.getMessage().hasLocation()) {

            chat_id = update.getMessage().getChatId(); //chat_id
            Location CurrentInMessageLocation = update.getMessage().getLocation(); // CurrentInMessageLocation

            String locationLatitude = CurrentInMessageLocation.getLatitude().toString().substring(0, 5);
            String locationLongtitude = CurrentInMessageLocation.getLongitude().toString().substring(0, 5);

            if (locationLatitude.equals(0f) && locationLongtitude.equals(0f)) {
                message.setChatId(chat_id);
                message.setText("You on 0,0 location");
                try {
                    sendMessage(message);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            } else if (locationLatitude.equals("55.87") && (locationLongtitude.equals("37.55"))) {
                message.setText("Ты в клубе у Лёши! (Дегунино)");
                message.setChatId(chat_id);
                try {
                    sendMessage(message);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            } else if (locationLatitude.equals("55.79") && (locationLongtitude.equals("37.71"))) {
                message.setChatId(chat_id);
                message.setText("Ты в клубе у Оли! (Преображенка).");
                try {
                    sendMessage(message);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            } else if (locationLatitude.equals("55.78") && (locationLongtitude.equals("37.70"))) {
                message.setChatId(chat_id);
                message.setText("Рядом учебный центр! (Электрозаводская).");
                try {
                    sendMessage(message);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }

            } else {
                message.setChatId(chat_id);
                message.setText("You on location Latitude: " + locationLatitude + " Longtitude: " + locationLongtitude);
                try {
                    sendMessage(message);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            }
        }
        //endregion

        // region работа с телефоном

        if (update.getMessage().getContact() != null) {

        }

        // end region

        //region работа с текстом
        if (update.hasMessage() && update.getMessage().hasText()) {
            String CurrentInMessage = update.getMessage().getText();
            chat_id = update.getMessage().getChatId(); //chat_id
            message.setChatId(chat_id);

            if (update.getMessage().getText().equals("/start")) {
                dbmodel.MysqlCon conn = new dbmodel.MysqlCon();
                try {
                    String tname = update.getMessage().getFrom().getFirstName();
                    long tid = update.getMessage().getFrom().getId();
                    String tnum = null;
                    System.out.println("tname: " + " " + "\n" + "tid: " + tid + " " + "\n" + "tnumber: " + tnum);

                    try {
                        conn.addUser(tid, tname, tnum);
                    } catch (SQLException e) {
                        sendMessage(new SendMessage().setChatId(chat_id).setText(e.toString()));
                    }

                    message.setChatId((chat_id));
                    message.setText("Привет, я работаю в тестовом режиме." + "\n" +
                            "Мой создатель @xsd14, просит тебя отправлять все найденные неточности ему в личку" + "\n" +
                            "Для того чтобы мною пользоваться нажми -> /help" + "\n" +
                            "Добро пожаловать!" + "\n" + EmojiParser.parseToUnicode(":smile:").toString());
                    try {
                        sendMessage(message);
                    } catch (TelegramApiException e) {
                        System.out.println("Exption" + e.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                SendMessage message0 = new SendMessage() // Create a message object object
                        .setText("Выбери подходящий вариант")
                        .setChatId(chat_id);
                // клавиатура
                row1.add(nearbly);
                row1.add(inClub);
                row2.add(friends);
                row2.add(contact);

                keyboard.add(row1);
                keyboard.add(row2);

                keyboardMarkup.setKeyboard(keyboard);
                message0.setReplyMarkup(keyboardMarkup);

                try {
                    sendMessage(message0);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }

                // клавиатура end
            }

            else if (CurrentInMessage.startsWith("/addProduct")) {

                dbmodel.MysqlCon conn = new dbmodel.MysqlCon();
                try {
                    conn.addProduct("addFromBot", 1, 1, 1);
                    message.setText("testProductAdded");
                } catch (SQLException sqle) {
                    message.setText("SQL" + sqle);

                }
                try {
                    sendMessage(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (CurrentInMessage.equals("Ближайший")) {
                // todo this
                String nearClubName = "This";


                SendMessage message0 = new SendMessage()
                        .setText("Отправь местоположение и я найду ближайший к тебе клуб")
                        .setChatId(chat_id);

                try {
                    sendMessage(message0);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }


            } else if (CurrentInMessage.startsWith("Я в клубе")) {

                // запись на восстановление силы
                SendMessage message0 = new SendMessage() // Create a message object object
                        .setText("Ты в клубе!")
                        .setChatId(chat_id);

                keyboard.clear();
                row1.add(recoveryStrong);
                keyboard.add(row1);
                keyboardMarkup.setKeyboard(keyboard);
                message0.setReplyMarkup(keyboardMarkup);

                try {
                    sendMessage(message0);
                } catch (TelegramApiException e) {
                    System.out.println(e);
                }
            } else if (CurrentInMessage.equals("Восстановление силы")) {
                SendMessage message0 = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Цена разового абонемента (восстановление, алоэ, чай) 250 рублей. Готов оплатить?");

                keyboard.clear();
                row1.add(invoice);
                row1.add(cash);
                keyboard.add(row1);
                keyboardMarkup.setKeyboard(keyboard);
                message0.setReplyMarkup(keyboardMarkup);
                try {
                    sendMessage(message0);
                } catch (TelegramApiException e) {
                    System.out.println(e);
                }

            }
            else if (CurrentInMessage.equals("Наличными")) {
                // message to admins
                admins_ids = dbmodel.MysqlCon.getAdmins();

                for (Integer admin_id : admins_ids ) {
                    message.setText(update.getMessage().getChat().getFirstName().toString() + " хочет восстановление силы (наличные)");
                    message.setChatId(admin_id.toString());

                    KeyboardButton ok_button = new KeyboardButton(EmojiParser.parseToUnicode(":ok:").toString());
                    keyboard.clear();
                    row1.clear();
                    row1.add(ok_button);
                    keyboard.add(row1);

                    keyboardMarkup.setKeyboard(keyboard);
                    message.setReplyMarkup(keyboardMarkup);

                    try {
                        sendMessage(message);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }

                }

                // message to client
                message
                        .setChatId(chat_id)
                        .setText("Ok" + EmojiParser.parseToUnicode(":heart_eyes:").toString());
                    try {
                        sendMessage(message);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
            }

            else if (CurrentInMessage.equals(EmojiParser.parseToUnicode(":ok:").toString())) {
                message.setText("/start").setChatId(chat_id);
                try {
                    sendMessage(message);
                }catch (Exception e) {
                    e.toString();
                }
            }

            else if (CurrentInMessage.equals("Картой")) {

                LabeledPrice RecoveryPrice = new LabeledPrice();
                RecoveryPrice.setLabel("Восстановление силы");
                RecoveryPrice.setAmount(10000);

                LabeledPrice AloePrice = new LabeledPrice();
                AloePrice.setLabel("Алое");
                AloePrice.setAmount(7500);

                LabeledPrice TeePrice = new LabeledPrice();
                TeePrice.setLabel("Чай");
                TeePrice.setAmount(7500);

                List<LabeledPrice> labeledPricesList = new ArrayList<LabeledPrice>();

                labeledPricesList.add(RecoveryPrice);
                labeledPricesList.add(AloePrice);
                labeledPricesList.add(TeePrice);

                SendInvoice Invoice = new SendInvoice()
                        .setChatId(Integer.parseInt(String.valueOf(chat_id)))
                        .setTitle("Разовый абонемент")
                        .setDescription("Разовое посещение клуба на Преображенке")
                        .setNeedEmail(true)
                        .setNeedName(true)
                        .setNeedPhoneNumber(true)
                        .setPhotoUrl("http://hlfstore.co.uk/wp-content/uploads/2015/11/Herbalife-24-Rebuild-Strength.jpg")
                        .setPhotoHeight(100)
                        .setPhotoWidth(100)
                        .setProviderToken("401643678:TEST:fce2bbd8-a2d1-46d6-a13c-b93155cf7168")
                        .setPrices(labeledPricesList)
                        .setCurrency("RUB")
                        .setPayload("EarlyAbonement")
                        .setStartParameter("BotStartParam");
                try {
                    sendInvoice(Invoice);
                } catch (TelegramApiException e) {
                    System.out.println(e);
                }

            } else if (CurrentInMessage.equals("/location")) {

                SendLocation sendLocation = new SendLocation();
                sendLocation.setChatId(chat_id);
                sendLocation.setLatitude(0f);
                sendLocation.setLongitude(0f);
                try {
                    sendLocation(sendLocation);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            }

            else if (CurrentInMessage.equals("/reset")) {
                message.setChatId((chat_id));
                try {
                    aSum = 0;
                    bSum = 0;
                    eSum = 0;
                    wSum = 0;
                    vSum = 0;
                } catch (Error e) {
                    System.out.println(e);
                    String error = e.toString();
                    message.setText("Что-то случилось:" + "\n" + error);
                }

            }

            else if (CurrentInMessage.equals("/help")) {
                message.setChatId(chat_id);
                message.setChatId(chat_id);
                String helpText1 = "На кнопках всё написано";

                String helpText2 = "Как пользоваться дневником:" + "\n"
                        + "Напиши: + хлеб 10б 150э 5в - добавит в дневник питания 10 гр белка, 150 каллорий, 5 гр пищевых волокон." + "\n"
                        + "Напиши: * вода 500 - добавит в дневник питания 500 мл воды." + "\n"
                        + "Напиши: & 500 - добавит 500 килокаллорий активности." + "\n"
                        + "Напиши: /status и получи отчёт по дневнику питания за сутки." + "\n"
                        + "Напиши: $ 8 - добавит время сна."
                        + "Добавить результаты замера: % 1.1 2.2 3.3 4.4 5.5 6.6 7.7 8.8 9.9" + "\n"

                        + "\n";
                        //"Для дневника нужны: дневной рацион, вода, активность, сон";
                        message.setText(helpText1);
                        message.setText(helpText2);
                try {
                    sendMessage(message);
                    sendMessage(message);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            }
            else if (CurrentInMessage.equals("/results")) {
                message.setChatId(chat_id);
                String outMesasge = "Результаты:" + "\n";
                message.setText(outMesasge + "\n" + resultString);
            }
            else if (CurrentInMessage.equals("/status")) {
                message.setChatId(chat_id);
                String outMessage = "Дневник: " + "\n";
                message.setText(outMessage + "\n"
                        + "Белок: " + bSum + "\n"
                        + "Калорийность: " + eSum + "\n"
                        + "Пищевые волокна.: " + vSum + "\n"
                        + "Вода: " + wSum + "\n"
                        + "Активность: " + aSum + "\n"
                );
                try {
                    sendMessage(message);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            }
            else if (CurrentInMessage.startsWith("*")) {
                message.setChatId(chat_id);
                System.out.println(update.getMessage().getText());
                vStringArray = update.getMessage().getText().split(" ");
                try {
                    wSum += Integer.parseInt(vStringArray[2]); // добавляем воду
                } catch (NumberFormatException nfe) {
                    System.out.println(nfe);
                    String error = nfe.toString();
                    message.setText("Не понял." + "\n" + error);
                    message.setChatId(chat_id);
                    try {
                        sendMessage(message);
                    } catch (TelegramApiException e1) {
                        e1.printStackTrace();
                    }
                }
            } else if (CurrentInMessage.startsWith("+")) {
                message.setChatId(chat_id);
                System.out.println(update.getMessage().getText());
                eStringArray = update.getMessage().getText().split(" ");
                try {
                    for (int i = 2; i < eStringArray.length; i++) {
                        if (eStringArray[i].endsWith("б")) {
                            String string = eStringArray[i].toString();
                            bSum += Integer.parseInt(string.replace("б", ""));
                        }
                        if (eStringArray[i].contains("э")) {
                            String string = eStringArray[i].toString();
                            eSum += Integer.parseInt(string.replace("э", ""));
                        }
                        if (eStringArray[i].endsWith("в")) {
                            String string = eStringArray[i].toString();
                            vSum = Integer.parseInt(string.replace("в", ""));
                        }
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println(nfe.toString());
                    String error = nfe.toString();
                    message.setText("Не понял:" + "\n" + error);
                    message.setChatId(chat_id);
                    try {
                        sendMessage(message);
                    } catch (TelegramApiException e1) {
                        e1.printStackTrace();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    String error = e.toString();
                    message.setText("Не разобрал, что ты написал:" + "\n" + error);
                    message.setChatId(chat_id);
                    try {
                        sendMessage(message);
                    } catch (TelegramApiException e1) {
                        e1.printStackTrace();
                    }
                }
            } else if (CurrentInMessage.startsWith("&")) {
                message.setChatId(chat_id);
                System.out.println(update.getMessage().getText());
                aStringArray = update.getMessage().getText().split(" ");
                aSum += Integer.parseInt(aStringArray[1]); // добавляем активность
            } else if (CurrentInMessage.startsWith("%")) {

                rStringArray = update.getMessage().getText().split(" ");

                Result result = new Result(

                        Float.parseFloat(rStringArray[0].replace("%", "")),
                        Float.parseFloat(rStringArray[1]),
                        Float.parseFloat(rStringArray[2]),
                        Float.parseFloat(rStringArray[3]),
                        Integer.parseInt(rStringArray[4]),
                        Integer.parseInt(rStringArray[5]),
                        Integer.parseInt(rStringArray[6]),
                        Integer.parseInt(rStringArray[7]),
                        Integer.parseInt(rStringArray[8]),
                        Float.parseFloat(rStringArray[9]));
                resultString = result.toString();
                System.out.println(result.toString());
            }
            else {
                message.setChatId(chat_id);
                message.setText("Я не понял \"" + update.getMessage().getText() + "\"?");

                ReplyKeyboardMarkup rkbm = new ReplyKeyboardMarkup();
                rkbm.setKeyboard(keyboard);
                message.setReplyMarkup(keyboardMarkup);
                // long botid, int messageid, long chatid, String messagetext
                try {
                    dbmodel.MysqlCon.addUnrecognizedQuestion(getBotUsername(), getBotToken(), update.getMessage().getMessageId(), update.getMessage().getChatId(), update.getMessage().getText());
                } catch (Exception e) {
                    message.setText("From: " + update.getMessage().getFrom().getFirstName() + "\n" + e.toString());
                    message.setChatId(getOwnerId());
                }

                try {
                    sendMessage(message);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }
    }

    public voda() {
        super();
    }

    public String getBotToken() {
        return "380086304:AAFf6KjuyStoDG2UavmeuoX6TZjClEZsCqo";

    }

    public String getBotUsername() {
        return "voda_bot";
    }

    public voda(DefaultBotOptions options) {
        super(options);
    }


}
