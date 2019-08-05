package com.example.varunsai.vce;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {
    String gender="male";
    boolean c=true,c1,java,python,dbms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.male:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "male", Toast.LENGTH_SHORT).show();
                    gender="male";
                }   break;
            case R.id.female:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
                    gender="female";
                }   break;
        }
    }
    public void onCheckboxClicked(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.c:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
                    c=true;
                }
                else{
                    c=false;}
                break;
            case R.id.c1:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
                    c1=true;
                }
                else{
                    c1=false;}
                break;
            case R.id.python:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
                    python=true;
                }
                else{
                    python=false;}
                break;
            case R.id.dbms:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
                    dbms=true;
                }
                else{
                    dbms=false;}
                break;
            case R.id.java:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
                    java=true;
                }
                else{
                    java=false;}
                break;
        }
    }
    public void editProfile(View view)
    {

    }

}
