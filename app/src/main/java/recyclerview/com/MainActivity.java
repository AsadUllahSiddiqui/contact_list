package recyclerview.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String text1;
    String text2;
    String text3;
    String text4;
    RecyclerView rv;
    list_container list;
    ArrayList<Contact> ls;
    ImageButton button1;
    ImageButton button2;
    MyRvAdapter adapter;
    public static Contact contact;
    private Object View;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv= findViewById(R.id.rv);
        ls=new ArrayList<>();
        adapter=new MyRvAdapter(ls,this);
        RecyclerView.LayoutManager lm =new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        EditText editText=findViewById(R.id.search_editTest);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
                adapter.notifyDataSetChanged();

            }
        });
        configure_Button_Send_message();
        configure_Button_Create_contact();

    }
    private void filter(String string){
        ArrayList<Contact> filteredList=new ArrayList<>();
        for (Contact item:ls){
           if( item.getName().toLowerCase().contains(string.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }
    private void configure_Button_Send_message(){
    button1=(ImageButton) findViewById(R.id.new_message);
    button1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(android.view.View v) {

            startActivity(new Intent(MainActivity.this,new_message.class));
        }
    });
}
    private void configure_Button_Create_contact(){
        button2=(ImageButton) findViewById(R.id.new_contact);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                startActivityForResult(new Intent(MainActivity.this,create_contact.class),1);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==requestCode)
        {
            String getname=data.getStringExtra("keyText1");
            String getphone=data.getStringExtra("keyText2");
            String getemail=data.getStringExtra("keyText3");
            String getaddress=data.getStringExtra("keyText4");
            String ImageUri=data.getStringExtra("asad");
            ls.add(new Contact(ImageUri,getname,getphone,getemail,getaddress));
            list=new list_container(ls);
            adapter.notifyDataSetChanged();
        }
    }

}