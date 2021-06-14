package recyclerview.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class view_contact extends AppCompatActivity {
Button btn;
Context cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        Intent intent=getIntent();
        String text1=intent.getStringExtra(MyRvAdapter.EXTRA_TEXT1);
        String text2=intent.getStringExtra(MyRvAdapter.EXTRA_TEXT2);
        String text3=intent.getStringExtra(MyRvAdapter.EXTRA_TEXT4);
        String text4=intent.getStringExtra(MyRvAdapter.EXTRA_TEXT3);
        String text5=intent.getStringExtra(MyRvAdapter.EXTRA_TEXT5);

        TextView textView1= findViewById(R.id.view_contact_name);
        TextView textView2= findViewById(R.id.view_contact_phone);
        TextView textView3= findViewById(R.id.view_contact_email);
        TextView textView4= findViewById(R.id.view_contact_address);
        CircleImageView c4=findViewById(R.id.view_Profile_image);

        textView1.setText(text1);
        textView2.setText(text2);
        textView3.setText(text3);
        textView4.setText(text4);
        Uri uri=Uri.parse(text5);
        c4.setImageURI(uri);

        configureButton5();

    }
    private void configureButton5(){
        btn=(Button) findViewById(R.id.view_my_contact);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}