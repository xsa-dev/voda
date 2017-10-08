package com.alekseysavin.voda;

import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.telegram.telegrambots.api.methods.send.SendInvoice;
import org.telegram.telegrambots.api.methods.send.SendLocation;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.payments.LabeledPrice;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

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

    String resultString = "";

    public void onUpdateReceived(Update update) {

        long chat_id = update.getMessage().getChatId();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();

        KeyboardButton nearbly = new KeyboardButton("Ближайший клуб");
        nearbly.getRequestLocation();

        KeyboardButton inClub = new KeyboardButton("Клуб");
        KeyboardButton friends = new KeyboardButton("Приведи друга");
        KeyboardButton recoveryStrong = new KeyboardButton("Восстановление силы");
        KeyboardButton invoice = new KeyboardButton("Инвойс");
        KeyboardButton hommies = new KeyboardButton("Нет денег");

        if (update.getMessage().getText().equals("/start")) {

            SendMessage message0 = new SendMessage() // Create a message object object
                    .setText("Нажми на кнопку соответсвующему твоему желанию")
                    .setChatId(chat_id);
            // клавиатура


            row1.add(nearbly);
            row2.add(inClub);
            row2.add(friends);

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

        if (update.getMessage().hasLocation()) {


            // location methods

            // отправляет локацию
            // по локации бот определяет клуб (отправляет приветствие клубнику)
            // если сейчас в расписании время примерно равно окончанию фита, спрашивает будет ли человек восстанавливать силу? с кнопками да / нет
            // если будет, то отправляет сообщение организатору клуба (или в канал клуба (нужно создать) сообщение "Я буду восстанавливаться!"
            // отправляет в ответ чек на оплату восстановления силы

            chat_id = update.getMessage().getChatId(); //chat_id
            Location CurrentInMessageLocation = update.getMessage().getLocation(); // CurrentInMessageLocation

            String locationLatitude = CurrentInMessageLocation.getLatitude().toString().substring(0, 5);
            String locationLongtitude = CurrentInMessageLocation.getLongitude().toString().substring(0, 5);


            if (locationLatitude.equals(0f) && locationLongtitude.equals(0f)) {
                SendMessage message1 = new SendMessage();
                message1.setChatId(chat_id);
                message1.setText("You on 0,0 location");
                try {
                    sendMessage(message1);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            } else if (locationLatitude.equals("55.87") && (locationLongtitude.equals("37.55"))) {
                SendMessage message1 = new SendMessage();
                message1.setText("Ты в клубе у Леши");
                message1.setChatId(chat_id);
                try {
                    sendMessage(message1);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            } else if (locationLatitude.equals("55.79") && (locationLongtitude.equals("37.71"))) {
                SendMessage message1 = new SendMessage();
                message1.setChatId(chat_id);
                message1.setText("Ты в клубе у Оли.");
                try {
                    sendMessage(message1);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            } else {
                SendMessage message1 = new SendMessage();
                message1.setChatId(chat_id);
                message1.setText("You on location Latitude: " + locationLatitude + " Longtitude: " + locationLongtitude);
                try {
                    sendMessage(message1);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            }

        }


        if (update.hasMessage() && update.getMessage().hasText()) {


            String CurrentInMessage = update.getMessage().getText();
            chat_id = update.getMessage().getChatId(); //chat_id

            SendMessage message = new SendMessage();
            message.setChatId(chat_id);


            if (CurrentInMessage.equals("Ближайший")) {

            }
            else if (CurrentInMessage.contains("Клуб")) {

                // запись на восстановление силы
                SendMessage message0 = new SendMessage() // Create a message object object
                        .setText("Ты в клубе.")
                        .setChatId(chat_id);

                keyboard.clear();
                row1.add(recoveryStrong);
                keyboard.add(row1);
                keyboardMarkup.setKeyboard(keyboard);
                message0.setReplyMarkup(keyboardMarkup);

                try {
                    sendMessage(message0);
                }
                catch (TelegramApiException e) {
                    System.out.println(e);
                }
            }

            else if (CurrentInMessage.equals("Восстановление силы")) {
                SendMessage message0 = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Цена разового абонемента 250рублей, готов оплатить?");

                keyboard.clear();
                row1.add(invoice);
                row1.add(hommies);
                keyboard.add(row1);
                keyboardMarkup.setKeyboard(keyboard);
                message0.setReplyMarkup(keyboardMarkup);
                try {
                    sendMessage(message0);
                }
                catch (TelegramApiException e) {
                    System.out.println(e);
                }

            }

            else if (CurrentInMessage.equals("Инвойс")) {

                LabeledPrice RecoveryPrice = new LabeledPrice();
                RecoveryPrice.setLabel("Коктель");
                RecoveryPrice.setAmount(100);

//                LabeledPrice AloePrice = new LabeledPrice();
//                AloePrice.setLabel("Алое");
//                AloePrice.setAmount(75);
//
//                LabeledPrice TeePrice = new LabeledPrice();
//                TeePrice.setLabel("Чай");
//                TeePrice.setAmount(75);


                List<LabeledPrice> labeledPricesList = new ArrayList<LabeledPrice>();

                labeledPricesList.add(RecoveryPrice);
//                labeledPricesList.add(AloePrice);
//                labeledPricesList.add(TeePrice);

                SendInvoice Invoice = new SendInvoice()
                        .setChatId(Integer.parseInt(String.valueOf(chat_id)))
                        .setTitle("Разовый абонемент")
                        .setDescription("Разовое посещение клуба на Преображенке")
                        .setNeedEmail(true)
                        .setNeedName(true)
                        .setNeedPhoneNumber(true)
                        .setPhotoUrl("")
                        .setPhotoHeight(100)
                        .setPhotoWidth(100)
                        .setProviderToken("401643678:TEST:fce2bbd8-a2d1-46d6-a13c-b93155cf7168")
                        .setPrices(labeledPricesList)
                        .setCurrency("RUB")
                        .setPayload("EarlyAbonement")
                        .setStartParameter("BotStartParam");




                try {
                    sendInvoice(Invoice);
                }
                catch (TelegramApiException e) {
                    System.out.println(e);
                }




            }

            else if (CurrentInMessage.equals("/location")) {

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

            // end of location methods

            else if (CurrentInMessage.equals("/start")) {
                message.setChatId((chat_id));
                message.setText("Привет, я работаю в тестовом режиме." + "\n" +
                        "Мой создатель @xsd14, просит тебя отправлять все найденные неточности ему в личку" + "\n" +
                        "Для того чтобы мною пользоваться нажми -> /help" + "\n" +
                        "Добро пожаловать!");
            }

            if (CurrentInMessage.equals("/reset")) {
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

            if (CurrentInMessage.equals("/help")) {
                message.setChatId(chat_id);
                String helpText = "На кнопках всё написано" + "\n" + "\n";


                        helpText = helpText + "Как пользоваться дневником питания:" + "\n"
                        + "Строка: + хлеб 10б 150э 5в - добавит 10 белка, 150 калорий, 5 пищевых волокон" + "\n"
                        + "Строка: * вода 500 - добавит 500 мл воды (кофе и чай - не вода, нужно выпить столько же!)" + "\n"
                        + "& 500 - Активность" + "\n"
                        + "% 1.1 2.2 3.3 4.4 5.5 6.6 7.7 8.8 9.9 - Замеры" + "\n"
                        + "$ 8 - Добавит время сна"
                        + "\n" +
                        "Для дневника нужны: дневной рацион, вода, активность, сон";


                message.setText(helpText);
                message.setChatId(chat_id);
                try {
                    sendMessage(message);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            }
            if (CurrentInMessage.equals("/results")) {
                message.setChatId(chat_id);
                String outMesasge = "Результаты:" + "\n";
                message.setText(outMesasge + "\n" + resultString);
            }
            if (CurrentInMessage.equals("/status")) {
                message.setChatId(chat_id);
                String outMessage = "Дневник: " + "\n";
                message.setText(outMessage + "\n"
                        + "Белка за день: " + bSum + "\n"
                        + "Энергия: " + eSum + "\n"
                        + "П.В.: " + vSum + "\n"
                        + "Воды: " + wSum + "\n"
                        + "Активность: " + aSum + "\n"
                );
                try {
                    sendMessage(message);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            } else if (CurrentInMessage.startsWith("*")) {
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
                    message.setText("Не разобрал что ты написал:" + "\n" + error);
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
