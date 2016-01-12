package contacts.xiaozuzu.github.io.contactsbook.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/12.
 */
public class Contact implements Serializable{
    String name;
    String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
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
