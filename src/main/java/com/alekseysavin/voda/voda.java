package com.alekseysavin.voda;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

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

        long ChatId = 0;

        try {

            if (update.hasMessage() && update.getMessage().hasText()) {

                ChatId = update.getMessage().getChatId(); //ChatId
                String CurrentInMessage = update.getMessage().getText(); // CurrentInMessage
                SendMessage message = new SendMessage();
                message.setChatId(ChatId);

                if (CurrentInMessage.equals("/start")) {
                    message.setChatId((ChatId));
                    message.setText("Привет, я работаю в тестовом режиме." + "\n" +
                                     "Мой создатель @xsd14, просит тебя отправлять все найденные неточности ему в личку" + "\n" +
                                     "Для того чтобы мною пользоваться нажми -> /help" + "\n" +
                                             "Добро пожаловать!");
                }

                if (CurrentInMessage.equals("/reset")) {
                    message.setChatId((ChatId));
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
                    message.setChatId(ChatId);
                    String helpText = "Как пользоваться:" + "\n"
                            + "Строка: + хлеб 10б 150э 5в - добавит 10 белка, 150 калорий, 5 пищевых волокон" +  "\n"
                            + "Строка: * вода 500 - добавит 500 мл воды (кофе и чай - не вода, нужно выпить столько же!)" + "\n"
                            + "& 500 - Активность" + "\n"
                            + "% 1.1 2.2 3.3 4.4 5.5 6.6 7.7 8.8 9.9 - Замеры" + "\n"
                            + "$ 8 - Добавит время сна"
                            + "\n" +
                            "Для дневника нужны: дневной рацион, вода, активность, сон";


                    message.setText(helpText);
                    message.setChatId(ChatId);
                    sendMessage(message);
                }
                if (CurrentInMessage.equals("/results")) {
                    message.setChatId(ChatId);
                    String outMesasge = "Результаты:" + "\n";
                    message.setText(outMesasge + "\n" + resultString);
                }
                if (CurrentInMessage.equals("/status")) {
                    message.setChatId(ChatId);
                    String outMessage = "Дневник: " + "\n";
                    message.setText(outMessage + "\n"
                            + "Белка за день: " + bSum + "\n"
                            + "Энергия: " + eSum + "\n"
                            + "П.В.: " + vSum + "\n"
                            + "Воды: " + wSum + "\n"
                            + "Активность: " + aSum + "\n"
                    );
                    sendMessage(message);
                } else if (CurrentInMessage.startsWith("*")) {
                    message.setChatId(ChatId);
                    System.out.println(update.getMessage().getText());
                    vStringArray = update.getMessage().getText().split(" ");
                        try {
                            wSum += Integer.parseInt(vStringArray[2]); // добавляем воду
                        }
                        catch (NumberFormatException nfe) {
                            System.out.println(nfe);
                            String error = nfe.toString();
                            message.setText("Не понял." + "\n" + error);
                            message.setChatId(ChatId);
                            sendMessage(message);
                        }
                } else if (CurrentInMessage.startsWith("+")) {
                    message.setChatId(ChatId);
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
                        message.setChatId(ChatId);
                        sendMessage(message);
                    } catch (Exception e) {
                        System.out.println(e);
                        String error = e.toString();
                        message.setText("Не разобрал что ты написал:" + "\n" + error);
                        message.setChatId(ChatId);
                        sendMessage(message);
                    }
                } else if (CurrentInMessage.startsWith("&")) {
                    message.setChatId(ChatId);
                    System.out.println(update.getMessage().getText());
                    aStringArray = update.getMessage().getText().split(" ");
                    aSum += Integer.parseInt(aStringArray[1]); // добавляем активность
                } else if (CurrentInMessage.startsWith("%")) {

                    rStringArray = update.getMessage().getText().split(" ");

                    Result result = new Result(

                            Float.parseFloat(rStringArray[0].replace("%","")),
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
        } catch (Exception e) {

            SendMessage message = new SendMessage();
            message.setChatId(ChatId);
            message.setText(e.toString());
            e.printStackTrace();
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
