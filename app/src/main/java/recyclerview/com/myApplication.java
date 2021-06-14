package recyclerview.com;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class myApplication extends Application {
    private List<Contact> data;

    public void storeData(List<Contact> data) {
        this.data = data;
    }

    public List<Contact> getData() {
        return data;
    }
}
