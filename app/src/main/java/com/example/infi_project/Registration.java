package com.example.infi_project;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;


public class Registration extends AppCompatActivity {

    private EditText userName, userPassword, userEmail, mobile, bdate, roll;
    private Button reg;
    private TextView InfoLog;
    private FirebaseAuth firebaseAuth;
    public TextView contactUsReg, terms;
    private CheckBox acceptTnC;
    String mdate="";
    public  String userid;
    String name;
    String password;
    String email;
    String rol;
    String mobilee;
    private CheckBox tnC;



    private Button temp;// temporary code

    DatabaseReference databaseuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupID();

        if (acceptTnC.isChecked()==false) {
            reg.setEnabled(false);
        }
        acceptTnC.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(acceptTnC.isChecked()){
                            reg.setEnabled(true);

                        }
                        else{
                            reg.setEnabled(false);
                        }
                    }
                }
        );




        firebaseAuth=FirebaseAuth.getInstance();
        databaseuser= FirebaseDatabase.getInstance().getReference("Users");

        bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                class DatePickerFragment extends DialogFragment
                        implements DatePickerDialog.OnDateSetListener {

                    @Override
                    public Dialog onCreateDialog(Bundle savedInstanceState) {
                        // Use the current date as the default date in the picker
                        final Calendar c = Calendar.getInstance();
                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);

                        // Create a new instance of DatePickerDialog and return it
                        return new DatePickerDialog(getActivity(), this, year, month, day);
                    }

                    public void onDateSet(DatePicker view, int year, int month, int day) {
                         mdate=day+"-"+month+"-"+year;
                        bdate.setText(mdate);
                    }
                }




            }
        });

        mdate=bdate.getText().toString();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
                if(validate()){

                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                userid = user.getUid();
                                HashMap<String, Object> profileMap = new HashMap<>();
                                profileMap.put("uid", userid);
                                profileMap.put("name", name);
                                profileMap.put("email", email);
                                profileMap.put("iitb roll no", roll);
                                profileMap.put("phone no ", mobilee);
                                profileMap.put("date of birth", mdate);

                                databaseuser.child("Users").child(userid).updateChildren(profileMap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()) {
                                                    databaseuser.child("PhoneNumber").setValue(mobilee);

                                                    Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                                    startActivity(new Intent(Registration.this, MainActivity.class));
                                                    finish();

                                                } else {
                                                    String message = task.getException().toString();
                                                    Toast.makeText(Registration.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                                                }


                                            }
                                        });
                            } else {
                                Toast.makeText(Registration.this, "Registration Failed", Toast.LENGTH_SHORT).show();


                            }
                        }
                    });

                }
            }
        });
        InfoLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this,MainActivity.class));
            }
        });

        contactUsReg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent contactUs_intent=new Intent (Registration.this, Contact_Us.class);
                        startActivity(contactUs_intent);
                    }
                }
        );

        tempOnclickListener();//temporary code

    }


    private void setupID(){
        userName = (EditText) findViewById(R.id.etn);
        userPassword = (EditText) findViewById(R.id.etp);
        userEmail = (EditText) findViewById(R.id.ete);
        mobile=(EditText) findViewById(R.id.mobileNo);
        roll=(EditText) findViewById(R.id.iitbroll) ;
        reg=(Button)findViewById(R.id.ets);
        contactUsReg=(TextView) findViewById(R.id.contactReg);
        acceptTnC=(CheckBox) findViewById(R.id.checkBoxTnC);
        bdate=(EditText) findViewById(R.id.dob);

        InfoLog = (TextView) findViewById(R.id.eti);
    }

    public Boolean validate() {
        Boolean ans = false;
        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String email = userEmail.getText().toString();
        String rol=roll.getText().toString();
        String mobilee=mobile.getText().toString();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty()|| rol.isEmpty()||mobilee.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();

        } else {
            mobilee="+91"+mobilee;
            ans = true;
        }

        return ans;
    }
    // temporary code
    private void tempOnclickListener(){
        temp=(Button)findViewById(R.id.temporary_button);
        temp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent interest_intent= new Intent(Registration.this, Interest_Part.class);
                        startActivity(interest_intent);
                        finish();
                    }
                }
        );
    }
    //temporary code ends

}
