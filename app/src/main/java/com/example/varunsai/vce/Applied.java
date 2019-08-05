package com.example.varunsai.vce;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.annotations.Nullable;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Applied extends AppCompatActivity {
    ListView student_list;
    static ArrayList<String> student_array=new ArrayList<>();
    static ArrayAdapter student_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ProgressDialog pd=new ProgressDialog(Applied.this);
        pd.setMessage("Fetching data");
        //FirebaseAuth mAuth=new FirebaseAuth()
        pd.show();

        student_list=(ListView)findViewById(R.id.studentlist);
        student_adapter=new ArrayAdapter(this,R.layout.list_view_student,R.id.studentdetails,student_array);
        student_list.setAdapter(student_adapter);
        student_array.add("hellothisis\nlongtext\ntothethe\nfunctionlaity\n");
        student_array.add("hi");
        student_adapter.notifyDataSetChanged();
        TextView tc=(TextView)findViewById(R.id.text1);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("companies").child(Company.tp);
        //DatabaseReference myRef = database.getReference(user);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null) {
                    for(DataSnapshot dd:dataSnapshot.getChildren())
                    {
                        String name=(String)dd.child("name").getValue();
                        String rno=(String)dd.child("rno").getValue();
                        student_array.add(name+"\n"+rno);
                        student_adapter.notifyDataSetChanged();
                    }

                    pd.cancel();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pd.cancel();

            }
        });
    }

}