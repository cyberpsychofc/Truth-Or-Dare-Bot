package org.cyberpsych.updatehandlers;
import org.cyberpsych.BotConfig;
import org.cyberpsych.Commands;
import org.cyberpsych.services.ToD;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
public class TruOrDare extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        //check if the update has a message
        if(update.hasMessage()){
            Message message = update.getMessage();

            //check if the message contains a text
            if(message.hasText()){
                SendMessage msg = new SendMessage();
                String id = String.valueOf(update.getMessage().getFrom().getId());
                msg.setChatId(id);

                String input = message.getText();

                if(input.equals(Commands.truthCommand)){
                    msg.setText("Choose from below");
                    //Options
                    InlineKeyboardMarkup optMarkUp = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
                    // Creating a list for buttons
                    List<InlineKeyboardButton> Buttons = new ArrayList<InlineKeyboardButton>();

                    InlineKeyboardButton truth = new InlineKeyboardButton("Truth");
                    truth.setCallbackData("presstruth");
                    InlineKeyboardButton dare = new InlineKeyboardButton("Dare");
                    dare.setCallbackData("pressdare");
                    InlineKeyboardButton random = new InlineKeyboardButton("Random");
                    random.setCallbackData("pressrandom");

                    Buttons.add(truth);
                    Buttons.add(dare);
                    Buttons.add(random);

                    keyboard.add(Buttons);
                    optMarkUp.setKeyboard(keyboard);
                    msg.setReplyMarkup(optMarkUp);

                    try {
                        // Send the message
                        execute(msg);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if(update.hasCallbackQuery()){
            CallbackQuery callbackquery = update.getCallbackQuery();
            SendMessage res = new SendMessage();
            String id = String.valueOf(callbackquery.getMessage().getChatId());
            res.setChatId(id);
            ToD td = new ToD();
            String data = callbackquery.getData();

            System.out.println("Callback data: " + data); // Log the callback data

            if(data.equals("presstruth")){
                String randTru = td.getRandomTruth();
                res.setText(randTru);
                try {
                    execute(res);
                    System.out.println("Callback Initiated Successfully!");
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                    System.out.println("Failed to send message"); // Log failure
                }
            }
        }
    }
    @Override
    public String getBotToken(){
        return BotConfig.TOD_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BotConfig.TOD_USER;
    }
    public void checkOnline(String chatId){
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText("The Bot is online. Continue playing...");
        try {
            execute(msg);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}