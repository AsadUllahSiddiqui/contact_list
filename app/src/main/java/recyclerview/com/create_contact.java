package recyclerview.com;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class create_contact extends AppCompatActivity {
    CircleImageView mImageView;

    Button mChooseBtn;
    private static final int IMAGE_PICK_CODE=1000;
    private static final int PERMISSION_CODE=1001;
    private static final int PICK_IMAGE=1;
    Uri img;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        configureButton3();
        configureimage();


    }
    private void configureButton3(){
        button3=(Button) findViewById(R.id.save_contact);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText1= findViewById(R.id.create_name);
                String text1=editText1.getText().toString();
                EditText editText2= findViewById(R.id.create_phone);
                String text2=editText2.getText().toString();
                EditText editText3= findViewById(R.id.create_email);
                String text3=editText3.getText().toString();
                EditText editText4= findViewById(R.id.create_address);
                String text4=editText4.getText().toString();
                String picUri="";
                if(img!=null) {
                    picUri = img.toString();
                }

                Intent intent3=new Intent(create_contact.this,MainActivity.class);
                intent3.putExtra("keyText1",text1);
                intent3.putExtra("keyText2",text2);
                intent3.putExtra("keyText3",text3);
                intent3.putExtra("keyText4",text4);
                intent3.putExtra("asad",picUri);
                setResult(RESULT_OK,intent3);
                finish();
            }
        });
    }
    private void configureimage(){
        mImageView=findViewById(R.id.profile_pic);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==
                            PackageManager.PERMISSION_DENIED){
                        //permission not granted, request it
                        String[] permission={Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show toast
                        requestPermissions(permission,PERMISSION_CODE);

                    }
                    else{
                        //permission already granted
                        pickImageFromGallery();

                    }

                }
                else {
                    //system os is less than marshmallow
                    pickImageFromGallery();

                }
            }
        });

    }
    private void pickImageFromGallery() {
        //intent to pic image
        Intent i=new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //permission was granted
                    pickImageFromGallery();
                }
                else{
                    Toast.makeText(this,"Permission denied..!",Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    //handle result to pick image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==IMAGE_PICK_CODE){
            mImageView.setImageURI(data.getData());
            img= data.getData();
        }
    }


}