package com.example.infi_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Contact_Us extends AppCompatActivity {

    public ImageView whatsappLogo, gmailLogo,phoneLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);

        whatsappLogo=(ImageView)findViewById(R.id.whatsapp);
        whatsappLogo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent whatsappIntent= new Intent (Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=+919828919061"));
                        if (whatsappIntent.resolveActivity(getPackageManager())!=null){
                            startActivity(whatsappIntent);

                        }
                        else{
                            Toast.makeText(Contact_Us.this, "No App support this function", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );

        gmailLogo=(ImageView)findViewById(R.id.gmail);
        gmailLogo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"starup.apps.101@gmail.com"});
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "App Issue");
                        if (emailIntent.resolveActivity(getPackageManager())!=null){
                            startActivity(emailIntent);
                        }
                        else {
                            Toast.makeText(Contact_Us.this, "No App support this function", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        phoneLogo=(ImageView)findViewById(R.id.phone);
        phoneLogo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri number = Uri.parse("tel:9511958591");
                        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                        if (callIntent.resolveActivity(getPackageManager())!=null){
                            startActivity(callIntent);
                        }
                        else{
                            Toast.makeText(Contact_Us.this, "No App support this function", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
        );
    }
}
