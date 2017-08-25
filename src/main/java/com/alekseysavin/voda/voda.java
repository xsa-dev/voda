package com.alekseysavin.voda;


import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TimerTask;

/**
 * Created by User on 11.07.2017.
 * Updated by User on 19.07.2017.
 */
public class voda extends TelegramLongPollingBot {
    private long ChatId;
    public String[] inString;
    public HashMap<String, Integer> vodaList = new	HashMap<String, Integer>();
    public int vodaCounter = 0;



    public void onUpdateReceived(Update update) {

        ArrayList<String> messages = new ArrayList<String>();

        if (update.hasMessage() && update.getMessage().hasText()) {
            messages.add(update.getMessage().getText());
        }

        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                ChatId = update.getMessage().getChatId(); //ChatId
                String CurrentInMessage = update.getMessage().getText(); // CurrentInMessage
                SendMessage message = new SendMessage();
                message.setChatId(ChatId);

//                DEBUG DATA
//                vodaList.put("test", 1);
//                vodaList.put("test2", 2);

                if (CurrentInMessage.equals("/status")) {
                    message.setChatId(ChatId);
                    //message.setText("Ok, is is status;");
                    //need update
                    String outMessage = "";
                    int sum = 0;
                    for (Entry<String, Integer> voda : vodaList.entrySet()) {
                        outMessage += voda.getKey() + " " + voda.getValue() + "\n";
						sum += voda.getValue();
                    }
                    message.setText(outMessage + "\n" + "Всего за сегодня= " + sum);
                    sendMessage(message);

                } else if (CurrentInMessage.startsWith("*")) {
                	//need update
                	   	message.setChatId(ChatId);
                		System.out.println(update.getMessage().getText());
                		vodaCounter++;
                		inString = update.getMessage().getText().split(" ");
                		for (int i = 0; i < inString.length; i++) {
                		    //parse this format [+ "string" 50]
                			vodaList.put(vodaCounter + ". " + inString[1], Integer.parseInt(inString[2]));
                		}

                } else if (CurrentInMessage.startsWith("+")) {
                        message.setChatId(ChatId);
                        inString = update.getMessage().getText().split(" ");
                        for (int i = 0; i < inString.length; i++) {
                            System.out.print(inString[i]);
                        }
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
        return "380086304:AAEMlMHDuZSzPFzICJNB7mW_qXJrMNqGwGk";
    }

    public String getBotUsername() {
        return "voda_bot";
    }

    public voda(DefaultBotOptions options) {
        super(options);
    }
}
