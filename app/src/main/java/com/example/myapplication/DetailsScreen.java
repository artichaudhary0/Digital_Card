package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsScreen extends AppCompatActivity {

    FloatingActionButton editScreenBox,saveDocButton;
    RelativeLayout headerBox, contentBox, mainView;

    TextView fullNameTextView, designationTextView,userCompanyTextView,userAboutMeTextView,userWhatsAppTextView,userContactTextView,userEmailTextView,userAddressTextView,userServicesTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details_screen);
        mainView = findViewById(R.id.mainRelative);


        saveDocButton = findViewById(R.id.saveDoc);
        getData();

//        editScreenBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                customEditBox();
//            }
//        });

        saveDocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage();
            }
        });
    }

    String getFilePath(){
        File f = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String path = f.getPath() + "/MyCard";
        File file = new File(path);
        if(!file.exists())
        {
            file.mkdir();
        }
        return  path;
    }

    void saveImage(){
        mainView.setDrawingCacheEnabled(true);
        Bitmap bitmap = mainView.getDrawingCache();
        String path = getFilePath();
        String filePath = path + "/"+"card.jpg";
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
            fileOutputStream.close();
            mainView.invalidate();
        }catch (FileNotFoundException e)
        {
            throw  new RuntimeException(e);
        }catch (IOException e){
            throw  new RuntimeException(e);
        }finally {
            mainView.setDrawingCacheEnabled(false);
        }
    }

    void getData(){
        Intent intent = getIntent();

        if(intent != null)
        {
            byte[] byteArray = intent.getByteArrayExtra("profilePicture");

            if(byteArray != null)
            {
                Bitmap profilePicture = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
                CircleImageView profileImageView = findViewById(R.id.profile_picture);
                profileImageView.setImageBitmap(profilePicture);
            }else {
                Toast.makeText(DetailsScreen.this,"error",Toast.LENGTH_SHORT).show();
            }

            String fullName = intent.getStringExtra("fullName");
            String designation = intent.getStringExtra("designation");
            String company = intent.getStringExtra("company");
            String aboutMe = intent.getStringExtra("aboutMe");
            String contact = intent.getStringExtra("contactNumber");
            String selected = intent.getStringExtra("selected");
            String email = intent.getStringExtra("email");
            String address = intent.getStringExtra("address");
            String services = intent.getStringExtra("serviceInfo");

            editScreenBox = findViewById(R.id.editContent);

            mainView = findViewById(R.id.mainRelative);
            saveDocButton = findViewById(R.id.saveDoc);
            headerBox = findViewById(R.id.cRelative);
            contentBox = findViewById(R.id.contentRelative);
            fullNameTextView = findViewById(R.id.userName);
            designationTextView = findViewById(R.id.userDesignation);

            userCompanyTextView = findViewById(R.id.userCompany);
            userAboutMeTextView = findViewById(R.id.userAboutMeText);
            userContactTextView = findViewById(R.id.userContactText);

            userWhatsAppTextView = findViewById(R.id.userWhatsAppText);
            userEmailTextView = findViewById(R.id.userEmailText);
            userAddressTextView = findViewById(R.id.userAddressText);
            userServicesTextView = findViewById(R.id.userServiceInfoText);


            fullNameTextView.setText(fullName);
            designationTextView.setText(designation);
            userCompanyTextView.setText(company);
            userAboutMeTextView.setText(aboutMe);
            userContactTextView.setText(contact);
            userEmailTextView.setText(email);
            userAddressTextView.setText(address);
            userServicesTextView.setText(services);
            userWhatsAppTextView.setText(selected);
        }
    }


    void customEditBox(){
        Dialog dialog = new Dialog(this);
//        dialog.setContentView();



    }

}