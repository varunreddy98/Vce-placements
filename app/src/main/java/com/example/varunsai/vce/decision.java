package com.example.varunsai.vce;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.example.varunsai.vce.Details_companies;
import com.example.varunsai.vce.Login;
import com.example.varunsai.vce.R;
import com.example.varunsai.vce.RecyclerViewAdapter;
import com.example.varunsai.vce.student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class decision extends AppCompatActivity {
    ProgressDialog progressDialog;
    private Button button;
    RecyclerView.Adapter adapter;
    static List<Details_companies> map1=new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        setContentView(R.layout.activity_main);
        starter();
    }

    private void starter() {
        setContentView(R.layout.activity_companies);
        // intent.putExtra("doc", mItem.name);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(decision.this));
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        final SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String previously=prefs1.getString("key","null" );
        FirebaseDatabase.getInstance().getReference(Login.logintype.toString()).child(previously).child("companies").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
              //  Toast.makeText(com.example.varunsai.vce.MainActivi.this, "sdfff", Toast.LENGTH_LONG).show();
                if (snapshot == null) {
                    //Toast.makeText(start1.this, "tipi",Toast.LENGTH_LONG ).show();
                } else {
                    for (DataSnapshot d : snapshot.getChildren()) {
                        ArrayList<student> arrayList2=new ArrayList<>();
                        ArrayList<student> arrayList3=new ArrayList<>();
                        String mmt = (String) d.getKey();
                        String name = (String) d.child("name").getValue();
                        String depts = (String) d.child("depts").getValue();
                        String post = (String) d.child("post").getValue();
                        String date = (String) d.child("date").getValue();
                        String language = (String) d.child("language").getValue();
                        String details_rounds = (String) d.child("details_rounds").getValue();
                        String other_details = (String) d.child("other_details").getValue();
                        String package1 = (String) d.child("package1").getValue();
                        String cgpa = (String) d.child("cgpa").getValue();
                        String Location=(String )d.child("location").getValue();
                        for (DataSnapshot f : d.child("applied_candidates").getChildren()) {

                            String rno=(String)f.child("rno").getValue();
                            String name2=(String)f.child("name").getValue();
                            student ss=new student(name2,rno);
                            arrayList2.add(ss);


                        }

                        for (DataSnapshot f : d.child("selected_candidates").getChildren()) {

                            String rno=(String)f.child("rno").getValue();
                            String name2=(String)f.child("name").getValue();
                            student ss=new student(name2,rno);
                            arrayList3.add(ss);


                        }

                        Details_companies details_companies =new Details_companies(mmt,name,depts, Location,post,date,language,details_rounds,other_details,package1,cgpa,arrayList2,arrayList3);
                        map1.add(details_companies);

                    }

                    adapter=new RecyclerViewAdapter(decision.this,map1);
                    recyclerView.setAdapter(adapter);
                    progressDialog.dismiss();

                    // Toast.makeText(start1.this,"sdt" ,Toast.LENGTH_LONG ).show();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });
        // Toast.makeText(LoginActivity.this, "msg3",Toast.LENGTH_LONG).show();


    }

}
