package com.Controller.Clubs;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Payments {

    public ReplyKeyboardMarkup getDefaultClubPayKeybord() {

        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

        KeyboardRow row = new KeyboardRow();

        KeyboardButton recovery = new KeyboardButton(EmojiParser.parseToUnicode("Восстановление силы"));

        row.add(recovery);

        keyboard.add(row);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup().setResizeKeyboard(true);

        keyboardMarkup.setKeyboard(keyboard).setSelective(true);

        return keyboardMarkup;

    }

    public ReplyKeyboardMarkup getChoisePayVariantKeybord() {

        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

        KeyboardRow row = new KeyboardRow();

        KeyboardButton invoice = new KeyboardButton("Картой");
        KeyboardButton cash = new KeyboardButton("Наличными");

        row.add(invoice);
        row.add(cash);

        keyboard.add(row);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup().setResizeKeyboard(true);

        keyboardMarkup.setKeyboard(keyboard).setSelective(true);

        return keyboardMarkup;

    }

    public ReplyKeyboardMarkup getCashVariantKeybord() {

        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

        KeyboardRow row = new KeyboardRow();

        KeyboardButton ok_button = new KeyboardButton(EmojiParser.parseToUnicode(":ok:").toString());

        row.add(ok_button);

        keyboard.add(row);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup().setResizeKeyboard(true);

        keyboardMarkup.setKeyboard(keyboard).setSelective(true);

        return keyboardMarkup;

    }
}
