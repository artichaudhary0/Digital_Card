package com.example.myapplication;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsScreen extends AppCompatActivity {

    FloatingActionButton editScreenBox,saveDocButton,callButton,messageButton,locationButton;
    RelativeLayout headerBox, contentBox, mainView;

    TextView fullNameTextView, designationTextView,userCompanyTextView,userAboutMeTextView,userWhatsAppTextView,userContactTextView,userEmailTextView,userAddressTextView,userServicesTextView;

    String fullName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details_screen);
        mainView = findViewById(R.id.mainRelative);

        callButton = findViewById(R.id.call);
        messageButton = findViewById(R.id.message);
        locationButton = findViewById(R.id.location);


        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:47356475845")); // replace with proper no.
                startActivity(callIntent);

            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messageIntent = new Intent(Intent.ACTION_SENDTO);
                messageIntent.setData(Uri.parse("smsto:3546453645"));// replace with proper no.
                messageIntent.putExtra("sms_body","Hello");
                startActivity(messageIntent);
            }
        });


        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geoUri = "geo:0,0?q=Surat,Gujarat,India";
                Intent locationIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(geoUri));
                startActivity(locationIntent);

            }
        });





        saveDocButton = findViewById(R.id.saveDoc);
        getData();

        editScreenBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              customEditBox();
            }
        });

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
            String imagePath = intent.getStringExtra("profilePicturePath");

            if(imagePath != null)
            {
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    Bitmap profilePicture = BitmapFactory.decodeFile(imagePath);
                    CircleImageView profileImageView = findViewById(R.id.profile_picture);
                    profileImageView.setImageBitmap(profilePicture);

//                Bitmap profilePicture = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
//                CircleImageView profileImageView = findViewById(R.id.profile_picture);
//                profileImageView.setImageBitmap(profilePicture);

                }

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
    };


    void customEditBox(){
        Dialog dialog = new Dialog(DetailsScreen.this);
        dialog.setContentView(R.layout.activity_edit_dialogue);


        LinearLayout blackColor = dialog.findViewById(R.id.blackColor);
        LinearLayout tealColor = dialog.findViewById(R.id.tealColor);
        LinearLayout splashColor = dialog.findViewById(R.id.splashColor);
        LinearLayout purpleColor = dialog.findViewById(R.id.purpleColor);



        LinearLayout bg1 = dialog.findViewById(R.id.bg1);
        LinearLayout bg2 = dialog.findViewById(R.id.bg2);
        LinearLayout bg3 = dialog.findViewById(R.id.bg3);
        LinearLayout bg4 = dialog.findViewById(R.id.bg4);
        LinearLayout bg5 = dialog.findViewById(R.id.bg5);

        TextView text1 = dialog.findViewById(R.id.text1);
        TextView text2 = dialog.findViewById(R.id.text2);
        TextView text3 = dialog.findViewById(R.id.text3);
        TextView text4 = dialog.findViewById(R.id.text4);
        TextView text5 = dialog.findViewById(R.id.text5);
        TextView text6 = dialog.findViewById(R.id.text6);
        TextView text7 = dialog.findViewById(R.id.text7);


        Button cancelButton = dialog.findViewById(R.id.cancelButton);
        Button resetButton = dialog.findViewById(R.id.resetButton);

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText(R.font.hindi);
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText(R.font.play);
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText(R.font.playfair);
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText(R.font.playread);
            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText(R.font.playwrite);
            }
        });

        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText(R.font.siliguri);
            }
        });

        text7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText(R.font.sofiasans);
            }
        });


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText(R.font.defaultstyle);

                headerBox.setBackgroundColor(getColor(R.color.teal));
                contentBox.setBackground(getDrawable(R.color.white));

            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        blackColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerBox.setBackgroundColor(getColor(R.color.black));
            }
        });
        splashColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerBox.setBackgroundColor(getColor(R.color.splash));
            }
        });
        tealColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerBox.setBackgroundColor(getColor(R.color.teal));
            }
        });
        purpleColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerBox.setBackgroundColor(getColor(R.color.purple));
            }
        });


        bg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentBox.setBackground(getDrawable(R.drawable.bg1));
            }
        });

        bg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentBox.setBackground(getDrawable(R.drawable.bg2));
            }
        });

        bg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentBox.setBackground(getDrawable(R.drawable.bg3));
            }
        });

        bg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentBox.setBackground(getDrawable(R.drawable.bg4));
            }
        });

        bg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentBox.setBackground(getDrawable(R.drawable.bg5));
            }
        });

        dialog.show();
    }

    void setText(int font){
            fullNameTextView.setTypeface(ResourcesCompat.getFont(DetailsScreen.this, font));
            designationTextView.setTypeface(ResourcesCompat.getFont(DetailsScreen.this, font));
            userCompanyTextView.setTypeface(ResourcesCompat.getFont(DetailsScreen.this, font));


    }

        }

