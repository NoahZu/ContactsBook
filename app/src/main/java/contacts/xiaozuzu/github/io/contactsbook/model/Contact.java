package contacts.xiaozuzu.github.io.contactsbook.model;

import android.content.Context;

import java.io.Serializable;

import contacts.xiaozuzu.github.io.contactsbook.application.MyAppliCation;
import contacts.xiaozuzu.github.io.contactsbook.util.CharacterParser;
import contacts.xiaozuzu.github.io.contactsbook.util.ColorUtil;

/**
 * Created by Administrator on 2016/1/12.
 */
public class Contact implements Serializable{
    private String name;
    private String number;
    private String sortLetters;

    public int getHeaderColor() {
        return headerColor;
    }

    private int headerColor;

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public Contact(Context context,String name, String number) {
        this.name = name;
        this.number = number;
        CharacterParser characterParser = CharacterParser.getInstance();
        String pinyin = characterParser.getSelling(name);
        String sortString = pinyin.substring(0, 1).toUpperCase();
        this.sortLetters = sortString;
        this.headerColor = ColorUtil.getRandomColor(context);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
