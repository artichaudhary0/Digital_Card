package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class InformationScreen extends AppCompatActivity {

    CircleImageView circleImageView;
    TextInputLayout nameTextLayout , designationTextLayout , companyTextLayout,aboutMeTextLayout,contactTextLayout,emailTextLayout, addressTextLayout, serviceInfoTextLayout;
    TextInputEditText nameText, designationText,companyText,aboutMeText,contactNumberText,emailText,addressText,serviceInfoText;
    RadioGroup radioGroup;
    RadioButton yesRadio, noRadio;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_information_screen);

        initBinding();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = nameText.getText().toString();
                String designation = designationText.getText().toString();
                String company = companyText.getText().toString();
                String aboutMe = aboutMeText.getText().toString();
                String contactNumber = contactNumberText.getText().toString();
                String email = emailText.getText().toString();
                String address = addressText.getText().toString();
                String serviceInfo = serviceInfoText.getText().toString();
                String selected= "";

                int id = radioGroup.getCheckedRadioButtonId();

                if(id == R.id.yesWhatsApp)
                {
                    selected ="Yes";
                } else if (id == R.id.noWhatsApp) {
                    selected = "No";
                }



                if(fullName.isEmpty())
                {
                    nameText.setError("Please Enter Name");
                    nameTextLayout.setError("Please Enter Name");
                    nameText.requestFocus();
                } else if (designation.isEmpty()) {
                    designationText.setError("Please Enter Name");
                    designationTextLayout.setError("Please Enter Name");
                    designationText.requestFocus();
                }else if (company.isEmpty()) {
                    companyText.setError("Please Enter Name");
                    companyTextLayout.setError("Please Enter Name");
                    companyText.requestFocus();
                }else if (aboutMe.isEmpty()) {
                    aboutMeText.setError("Please Enter Name");
                    aboutMeTextLayout.setError("Please Enter Name");
                    aboutMeText.requestFocus();
                }else if (contactNumber.isEmpty()) {
                    contactNumberText.setError("Please Enter Name");
                    contactTextLayout.setError("Please Enter Name");
                    contactNumberText.requestFocus();
                }else if (email.isEmpty()) {
                    emailText.setError("Please Enter Name");
                    emailTextLayout.setError("Please Enter Name");
                    emailText.requestFocus();
                }else if (address.isEmpty()) {
                    addressText.setError("Please Enter Name");
                    addressTextLayout.setError("Please Enter Name");
                    addressText.requestFocus();
                }else if (serviceInfo.isEmpty()) {
                    serviceInfoText.setError("Please Enter Name");
                    serviceInfoTextLayout.setError("Please Enter Name");
                    serviceInfoText.requestFocus();
                }else{
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap bitmap = ((BitmapDrawable) circleImageView.getDrawable()).getBitmap();

                    bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);

                    byte[] byteArray = stream.toByteArray();
                    Intent intent = new Intent(InformationScreen.this, DetailsScreen.class);

                    intent.putExtra("profilePicture",byteArray);

                    intent.putExtra("fullName",fullName);
                    intent.putExtra("designation",designation);
                    intent.putExtra("company",company);
                    intent.putExtra("aboutMe",aboutMe);
                    intent.putExtra("contactNumber",contactNumber);
                    intent.putExtra("selected",selected);
                    intent.putExtra("email",email);
                    intent.putExtra("address",address);
                    intent.putExtra("serviceInfo",serviceInfo);

                    startActivity(intent);

                    Toast.makeText(InformationScreen.this,"Success",Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

    private  void initBinding(){
        circleImageView = findViewById(R.id.profile_image);
        nameTextLayout = findViewById(R.id.fullName);
        designationTextLayout = findViewById(R.id.designation);
        companyTextLayout = findViewById(R.id.company);
        aboutMeTextLayout = findViewById(R.id.about_me);
        contactTextLayout = findViewById(R.id.contact_number);
        emailTextLayout = findViewById(R.id.email);
        addressTextLayout = findViewById(R.id.address);
        serviceInfoTextLayout = findViewById(R.id.service);

        nameText = findViewById(R.id.fullscreen_content);
        designationText = findViewById(R.id.designation_content);
        companyText = findViewById(R.id.company_content);
        aboutMeText = findViewById(R.id.about_me_content);
        contactNumberText = findViewById(R.id.contact_number_content);
        emailText = findViewById(R.id.email_content);
        addressText = findViewById(R.id.address_content);
        serviceInfoText = findViewById(R.id.service_content);


        radioGroup = findViewById(R.id.radioGroupWhatsApp);
        yesRadio = findViewById(R.id.yesWhatsApp);
        noRadio = findViewById(R.id.noWhatsApp);
        nextButton = findViewById(R.id.nextButton);

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected  void onActivityResult(int requestCode , int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(data!=null)
        {
            Uri uri = data.getData();
            circleImageView.setImageURI(uri);
        } else if (resultCode == 101) {
            if(data!=null)
            {
                Bitmap b1 = (Bitmap) data.getExtras().get("data");
                circleImageView.setImageBitmap(b1);
            }
        }
    };
}