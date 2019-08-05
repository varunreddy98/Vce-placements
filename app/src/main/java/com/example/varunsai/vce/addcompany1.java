package com.example.varunsai.vce;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.circularreveal.CircularRevealFrameLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class addcompany1 extends AppCompatActivity implements View.OnClickListener{
       EditText txt,name,depts,location,post,language;
       static String cref="";
       DatabaseReference db;
    static String previously,dt;
    static  String name1,depts1,location1,post1,language1;
    int sp = 0;
    private int mYear, mMonth, mDay ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db= FirebaseDatabase.getInstance().getReference("companies");
        cref=db.push().getKey();
        db= FirebaseDatabase.getInstance().getReference("companies").child(cref);
        setContentView(R.layout.activity_addcompany1);
        name=(EditText)findViewById(R.id.name);
        depts=(EditText)findViewById(R.id.depts);
        location=(EditText)findViewById(R.id.location);
        post=(EditText)findViewById(R.id.post);
        language=(EditText)findViewById(R.id.language);
        Button dt=(Button)findViewById(R.id.date);
        txt=(EditText)findViewById(R.id.textdate);
        dt.setOnClickListener(this);
        Button btn=(Button)findViewById(R.id.next1);
        btn.setOnClickListener(this);
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
                String fname= String.valueOf(((EditText)findViewById(R.id.name)).getText());
                name1=fname;
                if(fname.equals(""))
                {
                    EditText editText3= (EditText) findViewById(R.id.name);
                    editText3.setError("This field is necessary");
                    sp++;
                }
                String depts2=  String.valueOf(((EditText)findViewById(R.id.depts)).getText());
                depts1=depts2;
                if(depts1.equals(""))
                {
                    EditText editText4 = (EditText) findViewById(R.id.depts);
                    editText4.setError("This field is necessary");
                    sp++;
                }
                String location2=  String.valueOf(((EditText)findViewById(R.id.location)).getText());
                location1=location2;
                if(location1.equals(""))
                {
                    EditText editText4 = (EditText) findViewById(R.id.location);
                    editText4.setError("This field is necessary");
                    sp++;
                }
                String post2=  String.valueOf(((EditText)findViewById(R.id.post)).getText());
                post1=post2;
                if(post1.equals(""))
                {
                    EditText editText4 = (EditText) findViewById(R.id.post);
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

                String  language2=String.valueOf(((EditText)findViewById(R.id.language)).getText());
                language1=language2;
                if(language1.equals(""))
                {
                    EditText editText1 = (EditText) findViewById(R.id.language);
                    editText1.setError("not valid");
                    sp++;
                }


                if(sp==0) {
                    db.child("name").setValue(name1);
                    db.child("depts").setValue(depts1);
                    db.child("location").setValue(location1);
                    db.child("post").setValue(post1);
                    db.child("date").setValue(dt);
                    db.child("language").setValue(language1);
                    db.child("selected_candidates").setValue("");
                    db.child("applied_candidates").setValue("");
                    //   Toast.makeText(inform.this,"map" ,Toast.LENGTH_LONG ).show();
                    Intent intent = new Intent(getApplicationContext(), addcompany2.class);
                    startActivity(intent);
                }
                break;
        }

    }
}
