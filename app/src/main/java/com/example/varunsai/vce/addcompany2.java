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

public class addcompany2 extends AppCompatActivity implements View.OnClickListener{
    DatabaseReference db;
    static String previously;
    static  String details_rounds,other_details,package1,cgpa;
    int sp = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db= FirebaseDatabase.getInstance().getReference().child("companies").child(addcompany1.cref);
        setContentView(R.layout.addcompany2);
        Button btn=(Button)findViewById(R.id.next1);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.next1:
                sp=0;
                String d1= String.valueOf(((EditText)findViewById(R.id.details_rounds)).getText());
                details_rounds=d1;
                if(details_rounds.equals(""))
                {
                    EditText editText3= (EditText) findViewById(R.id.details_rounds);
                    editText3.setError("This field is necessary");
                    sp++;
                }
                String d2=  String.valueOf(((EditText)findViewById(R.id.other_details)).getText());
                other_details=d2;
                if(other_details.equals(""))
                {
                    EditText editText4 = (EditText) findViewById(R.id.other_details);
                    editText4.setError("This field is necessary");
                    sp++;
                }
                String p1=  String.valueOf(((EditText)findViewById(R.id.package1)).getText());
                package1=p1;
                if(package1.equals(""))
                {
                    EditText editText4 = (EditText) findViewById(R.id.package1);
                    editText4.setError("This field is necessary");
                    sp++;
                }
                String gpa=  String.valueOf(((EditText)findViewById(R.id.cgpa)).getText());
                cgpa=gpa;
                if(cgpa.equals(""))
                {
                    EditText editText4 = (EditText) findViewById(R.id.cgpa);
                    editText4.setError("This field is necessary");
                    sp++;
                }
                // String  date=String.valueOf(((EditText)findViewById(R.id.textdate)).getText());
                if(sp==0) {
                    db.child("details_rounds").setValue(details_rounds);
                    db.child("other_details").setValue(other_details);
                    db.child("package1").setValue(package1);
                    db.child("cgpa").setValue(cgpa);
                    //   Toast.makeText(inform.this,"map" ,Toast.LENGTH_LONG ).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                break;
        }

    }
}
