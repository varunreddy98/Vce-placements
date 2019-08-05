package com.example.varunsai.vce;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//
//import com.google.android.gms.common.util.DbUtils;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
public class Profile extends AppCompatActivity {
    TextView sucres, sucimg, errmsg;

    DatabaseReference db;
    TextInputEditText name, email, phoneno, cgpa, age, rno;
    boolean m, f;
    boolean c = true, c1, java, dbms, python;
    String gender = "male";
    String name1, email1, phoneno1, cgpa1, age1, previously, rno1;

    //  DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        previously = prefs1.getString("key", "null");
        db = FirebaseDatabase.getInstance().getReference(Login.logintype.toString()).child(previously);
        sucres = (TextView) findViewById(R.id.sucessres);
        sucimg = (TextView) findViewById(R.id.sucessimg);
        sucres.setText("sucess");
        sucimg.setText("success");
        name = (TextInputEditText) findViewById(R.id.name);
      //  email = (TextInputEditText) findViewById(R.id.email);
        phoneno = (TextInputEditText) findViewById(R.id.phone);
        cgpa = (TextInputEditText) findViewById(R.id.gpa);
        age = (TextInputEditText) findViewById(R.id.age);
        errmsg = (TextView) findViewById(R.id.errormsg);
        rno = (TextInputEditText) findViewById(R.id.rno);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.male:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "male", Toast.LENGTH_SHORT).show();
                    gender = "male";
                }
                break;
            case R.id.female:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
                    gender = "female";
                }
                break;
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.c:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
                    c = true;
                } else {
                    c = false;
                }
                break;
            case R.id.c1:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
                    c1 = true;
                } else {
                    c1 = false;
                }
                break;
            case R.id.python:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
                    python = true;
                } else {
                    python = false;
                }
                break;
            case R.id.dbms:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
                    dbms = true;
                } else {
                    dbms = false;
                }
                break;
            case R.id.java:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
                    java = true;
                } else {
                    java = false;
                }
                break;
        }
    }

    public void submitProfile(View view) {

        name1 = name.getText().toString();
        email1 = Login.name;
        phoneno1 = phoneno.getText().toString();
        cgpa1 = cgpa.getText().toString();
        age1 = age.getText().toString();
        rno1 = rno.getText().toString();
        Toast.makeText(getApplicationContext(), name1, Toast.LENGTH_SHORT).show();
        if (name1.equals(""))
            errmsg.setText("name is required");
        else if (rno1.equals(""))
            errmsg.setText("rno is required");
        else if (email1.equals(""))
            errmsg.setText("email is required");
        else if (phoneno1.equals(""))
            errmsg.setText("phoneno is required");
        else if (cgpa1.equals(""))
            errmsg.setText("cgpa is required");
        else if (age1.equals(""))
            errmsg.setText("age is required");
        else if (!c && !c1 && !java && !python && !dbms)
            errmsg.setText("minimun one languague is required");
        else {

            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
            db.child("name").setValue(name1);
            db.child("email").setValue(email1);
            db.child("phone").setValue(phoneno1);
            db.child("cgpa").setValue(cgpa1);
            db.child("age").setValue(age1);
            db.child("rno").setValue(rno1);
            db.child("gender").setValue(gender);
            db.child("c").setValue(c);
            db.child("c++").setValue(c1);
            db.child("java").setValue(java);
            db.child("python").setValue(python);
            db.child("dbms").setValue(dbms);
            // Toast.makeText(inform.this,"map" ,Toast.LENGTH_LONG ).show();
            Intent intent = new Intent(getApplicationContext(), StudentHome.class);
            startActivity(intent);
        }


    }

}