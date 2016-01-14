package contacts.xiaozuzu.github.io.contactsbook.model;

import java.io.Serializable;

import contacts.xiaozuzu.github.io.contactsbook.util.CharacterParser;

/**
 * Created by Administrator on 2016/1/12.
 */
public class Contact implements Serializable{
    String name;
    String number;
    String sortLetters;

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
        CharacterParser characterParser = CharacterParser.getInstance();
        String pinyin = characterParser.getSelling(name);
        String sortString = pinyin.substring(0, 1).toUpperCase();
        this.sortLetters = sortString;
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
