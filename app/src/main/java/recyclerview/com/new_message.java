package recyclerview.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class new_message extends AppCompatActivity {
    Button send_btn;

    AutoCompleteTextView AutoName;
    AutoCompleteTextView AutoPhoneNo;
    EditText number;
    EditText message;
    String[] names;
    String[] Phone_numbers;
    String revieverNo;
    String selectedName;
    static int index;
    //list_container list;
    ArrayList<Contact> ls;
    final int SEND_SMS_PERMISSION_REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        revieverNo=new String();
        selectedName=new String();
        ls=new ArrayList<Contact>();
        ls=list_container.list;
        if(list_container.list!=null) {
            names = new String[ls.size()];
            Phone_numbers = new String[ls.size()];
        }
        else{
            ls=new ArrayList<>();
        }
        setstringarray(ls);

        setContentView(R.layout.activity_new_message);
        number=findViewById(R.id.xyzphoneNo);
        message=findViewById(R.id.MessageToSend);

        if(checkPermission(Manifest.permission.SEND_SMS)) {

        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},
                    SEND_SMS_PERMISSION_REQUEST_CODE);
        }


        AutoCompleteTextView act1=findViewById(R.id.xyzname);
        AutoCompleteTextView act2=findViewById(R.id.xyzphoneNo);
        if(list_container.list!=null) {
            ArrayAdapter<String> adt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
            act1.setAdapter(adt);
            selectedName = act1.getText().toString();

            //returnBumber(names,act1.getText().toString());
            //act2.setText(Phone_numbers[index]);
            ArrayAdapter<String> adt1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Phone_numbers);
            act2.setAdapter(adt1);
        }
            configureButton4();

    }
    public void onSend(){
        String phoneNumber=number.getText().toString();
        String smsMessage=message.getText().toString();
        if((phoneNumber!=null ||phoneNumber.length()!=0||smsMessage!=null ||smsMessage.length()!=0)&& android.os.Build.VERSION.SDK_INT>android.os.Build.VERSION_CODES.M) {

            if (checkPermission(Manifest.permission.SEND_SMS)) {
                //SmsManager smsManager = SmsManager.getDefault();
                //smsManager.sendTextMessage(phoneNumber, null, smsMessage, null, null);
                int a=ls.size();
                String s=Integer.toString(index);
                Toast.makeText(new_message.this, "Sent!", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void configureButton4(){
        send_btn=(Button) findViewById(R.id.send_message);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSend();
                finish();
            }
        });
    }

    public boolean checkPermission(String permission){
        int check= ContextCompat.checkSelfPermission(this,permission);
        return (check== PackageManager.PERMISSION_GRANTED);
    }
    public void setstringarray(ArrayList<Contact> lst) {
        for (int counter = 0; counter < lst.size(); counter++) {
            names[counter]=(lst.get(counter).getName());
            Phone_numbers[counter]=(lst.get(counter).getNumber());
        }
    }

    public void returnnumber(String[] array,String selectedName){

        for (int counter = 0; counter < array.length; counter++) {
            if(array[counter].matches(selectedName))
            {
                Toast.makeText(new_message.this, "match", Toast.LENGTH_SHORT).show();
                index+=counter;
            }

        }

    }

}

