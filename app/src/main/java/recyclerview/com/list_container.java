package recyclerview.com;

import java.util.ArrayList;

public class list_container {
     static ArrayList<Contact> list;


    public ArrayList<Contact> getList() {
        return list;
    }

    public void setList(ArrayList<Contact> list) {
        this.list = list;
    }

    public list_container(ArrayList<Contact> list) {
        this.list = list;
    }

}
