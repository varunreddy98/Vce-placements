package com.example.varunsai.vce;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Company extends AppCompatActivity {
    static   DatabaseReference personalref;
    ListView company_list;
    String previously;
    String name,rno,phone,age,gender,mail,cgpa;
    static String tp="";
    static  ArrayList<String> tempcomp=new ArrayList<>();
    static ArrayList<String> company_array=new ArrayList<>();
    static ArrayAdapter company_adapter;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        company_list=(ListView)findViewById(R.id.Companies);
        company_adapter=new ArrayAdapter(this,R.layout.list_view_custom,R.id.companydetails,company_array);
        company_list.setAdapter(company_adapter);
        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        previously = prefs1.getString("key", "null");
        db=FirebaseDatabase.getInstance().getReference("student").child(previously);
        final ProgressDialog pd=new ProgressDialog(Company.this);
        pd.setMessage("Fetching data");
        //FirebaseAuth mAuth=new FirebaseAuth()
        pd.show();
        TextView tc=(TextView)findViewById(R.id.text1);

        //DatabaseReference myRef = database.getReference(user).child("Medication");

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("companies");
        //DatabaseReference myRef = database.getReference(user);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //String temp=dataSanpshot.getValue(Str
                tempcomp.add(dataSnapshot.getKey().toString());
                String temp=dataSnapshot.child("name").getValue(String.class);
                temp+="\n"+dataSnapshot.child("depts").getValue(String.class);
                temp+="\n"+dataSnapshot.child("location").getValue(String.class);
                temp+="\n"+dataSnapshot.child("date").getValue(String.class);
                temp+="\n"+dataSnapshot.child("post").getValue(String.class);
                temp+="\n"+dataSnapshot.child("languague").getValue(String.class);
                temp+="\n"+dataSnapshot.child("location").getValue(String.class);
                temp+="\n"+dataSnapshot.child("package").getValue(String.class);
                temp+="\n"+dataSnapshot.child("cgpa").getValue(String.class);





                //String temp=dataSnapshot.child("Medication").getValue(String.class);
                company_array.add(temp);
                company_adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pd.cancel();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void goToEdit(View view)
    {
        Intent intent=new Intent(getApplicationContext(),EditProfile.class);
        startActivity(intent);
    }

    public void applyJob(View v)
    {

//        Button b=(Button)findViewById(R.id.apply);
//        b.setText("Applied");

        //Edit here for applu logic to add children to db
        View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // Toast.makeText(MainActivity.this, "sdfff", Toast.LENGTH_LONG).show();
                if (snapshot == null) {
                    //Toast.makeText(start1.this, "tipi",Toast.LENGTH_LONG ).show();
                } else {
                    name=(String)snapshot.child("name").getValue();
                    rno=(String )snapshot.child("rno").getValue();
                    mail=Login.name;
                    age=(String)snapshot.child("age").getValue();
                    phone=(String)snapshot.child("phone").getValue();
                    gender=(String)snapshot.child("gender").getValue();
                    cgpa=(String)snapshot.child("cgpa").getValue();


                }


                DatabaseReference reference=FirebaseDatabase.getInstance().getReference("companies").child(tempcomp.get(position)).child("applied_candidates");
                String key=reference.push().getKey();
                reference.child(key).child("name").setValue(name);
                reference.child(key).child("rno").setValue(rno);
                reference.child(key).child("gender").setValue(gender);
                reference.child(key).child("phone").setValue(phone);
                reference.child(key).child("cgpa").setValue(cgpa);
                reference.child(key).child("age").setValue(age);
                Toast.makeText(Company.this, "Applied successfully", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });
    }
    public void viewStudents(View v)
    {     View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        Intent i=new Intent(getApplicationContext(),Applied.class);
        tp=tempcomp.get(position);
        startActivity(i);
    }

}