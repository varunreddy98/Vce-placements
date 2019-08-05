package com.example.varunsai.vce;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Student1 extends AppCompatActivity {
    ListView mainlistview;
    static ArrayList<String> arrlist = new ArrayList<>();
    static ArrayAdapter arradapter;
    String data_to_be_sent;
    // DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student1);
      final TextView name1=(TextView)findViewById(R.id.viewname);
        final TextView rno1=(TextView)findViewById(R.id.viewrno);
        final TextView gmail1=(TextView)findViewById(R.id.viewgmail);
        final TextView phnum1=(TextView)findViewById(R.id.viewphnum);
        final TextView cgpa1=(TextView)findViewById(R.id.viewcgpa);
        final TextView age1=(TextView)findViewById(R.id.viewage);
        final TextView gender1=(TextView)findViewById(R.id.viewgender);
        name1.setText("varun");
        rno1.setText("1602-16-733-057");
        gmail1.setText("bachivarunreddy@gmail.com");
        phnum1.setText("8008447954");
        cgpa1.setText("9.0");
        age1.setText("20");
        gender1.setText("male");
/*       db= FirebaseDatabase.getInstance().getReference(Login.logintype.toString()).child(previously);
        mainlistview = (ListView) findViewById(R.id.listv);
        arradapter = new ArrayAdapter(this,R.layout.list_item,R.id.custid,arrlist);
        mainlistview.setAdapter(arradapter);
        arrlist.add("hiiiii");
        final ProgressDialog pd=new ProgressDialog(Student1.this);

        pd.setMessage("Fetching data");

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("student").child(Activity1.ref);
        //DatabaseReference myRef = database.getReference(user);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String temp=dataSnapshot.child("rno").getValue(String.class);
                data_to_be_sent=temp;
                rno1.setText(temp);
                temp=dataSnapshot.child("name").getValue(String.class);
                name1.setText(temp);
                temp=dataSnapshot.child("gmail").getValue(String.class);
                gmail1.setText(temp);
                temp=dataSnapshot.child("phone").getValue(String.class);
                phnum1.setText(temp);
                temp=dataSnapshot.child("cgpa").getValue(String.class);
                cgpa1.setText(temp);
                temp=dataSnapshot.child("age").getValue(String.class);
                age1.setText(temp);
                temp=dataSnapshot.child("gender").getValue(String.class);
                gender1.setText(temp);
                pd.cancel();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pd.cancel();

            }
        });    */

    }
    public void clicktodownload(View v1)
    {
        //Intent intent1=new Intent(Student.this,Act_round1.class);
        // startActivity(intent1);
    }
    public void clicktoselect(View v2)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(Student1.this);
        alert.setTitle("Confirm");
        alert.setMessage("Are you sure to select?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {

                //   db.child("rno").setValue(data_to_be_sent);


                Intent intent2=new Intent(Student1.this,Activity1.class);
                //intent2.putExtra("rno",data_to_be_sent);

                startActivity(intent2);
                dialog.dismiss();


            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        alert.show();
    }

}