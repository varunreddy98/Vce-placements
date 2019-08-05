package com.example.varunsai.vce;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.DbUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;

public class inform extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    Button b1;
    Spinner spec;
    RadioButton r1,r2;
    int sp;
    String name;
    RadioGroup gp;
    int c=0;
    static String fn2,ln2,spec2,phn2;
    String gender="",spec1=""; // spec for choosing departments
    DatabaseReference db;
    String previously,dt;
    EditText txt,edt,idf;
    String des[]={"CSE","IT","ECE","EEE","Civil","Mech"};
    private int mYear, mMonth, mDay ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        edt=(EditText)findViewById(R.id.fname);
        idf=(EditText)findViewById(R.id.id);
        spec=(Spinner)findViewById(R.id.spec);
        gp=(RadioGroup)findViewById(R.id.rd);
        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        previously=prefs1.getString("key","null" );
        db= FirebaseDatabase.getInstance().getReference(Login.logintype.toString()).child(previously);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,des);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spec.setAdapter(aa);
        spec.setOnItemSelectedListener(this);
        b1=(Button)findViewById(R.id.next1);
        b1.setOnClickListener(this);
        r1=(RadioButton)findViewById(R.id.radio1);
        r2=(RadioButton)findViewById(R.id.radio2);
        Button dt=(Button)findViewById(R.id.date);
        txt=(EditText)findViewById(R.id.textdate);
        dt.setOnClickListener(this);
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio1:
                if (checked)
                gender="male";
                Log.d("name", "hhh");
                break;
            case R.id.radio2:
                if (checked)
                    gender="female";
                Log.d("ff", "ggggg");
                break;


        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.date:
                final java.util.Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dt=dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
                                txt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;

            case R.id.next1:
                sp=0;
                String fname= String.valueOf(((EditText)findViewById(R.id.fname)).getText());
                fn2=fname;
                if(fname.equals(""))
                {
                    EditText editText3= (EditText) findViewById(R.id.fname);
                    editText3.setError("This field is necessary");
                    sp++;
                }
                String idfaculty=  String.valueOf(((EditText)findViewById(R.id.id)).getText());
                ln2=idfaculty;
                if(idfaculty.equals(""))
                {
                    EditText editText4 = (EditText) findViewById(R.id.id);
                    editText4.setError("This field is necessary");
                    sp++;
                }
                // String  date=String.valueOf(((EditText)findViewById(R.id.textdate)).getText());
                if(dt.equals(""))
                {
                    EditText e = (EditText) findViewById(R.id.textdate);
                    e.setError("This field is necessary");
                    sp++;
                }

                if(spec1.equals(""))
                {   spec2=spec1;
                    Toast.makeText(this, "select department", Toast.LENGTH_LONG).show();
                    sp++;
                }
                String  phn=String.valueOf(((EditText)findViewById(R.id.phn)).getText());
                if(phn.length()!=10)
                {    phn2=phn;
                    EditText editText1 = (EditText) findViewById(R.id.phn);
                    editText1.setError("not a valid phone number");
                    sp++;
                }


                if(sp==0) {
                    db.child("fname").setValue(fname);
                    db.child("collegeid").setValue(idfaculty);
                    db.child("gender").setValue(gender);
                    db.child("dob").setValue(dt);
                    db.child("department").setValue(spec1);
                    db.child("phn").setValue(phn);
                    //   Toast.makeText(inform.this,"map" ,Toast.LENGTH_LONG ).show();
                    Intent intent = new Intent(getApplicationContext(), inform2.class);
                    startActivity(intent);
                }
                break;
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),des[i] , Toast.LENGTH_LONG).show();
        spec1=des[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}


