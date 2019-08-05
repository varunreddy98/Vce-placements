package com.example.varunsai.vce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Activity1 extends AppCompatActivity {
    ListView mainlistview;
    static  String  ref;
    static ArrayList<String> arrlist = new ArrayList<>();
    static ArrayList<String> reflist=new ArrayList<>();
    static ArrayAdapter arradapter;
    Button viewbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        mainlistview = (ListView) findViewById(R.id.listv);
        arrlist.add("1602-16-733-057");
        arrlist.add("1602-16-733-099");
        arrlist.add("1602-16-733-004");
        arrlist.add("1602-16-733-001");
        arradapter = new ArrayAdapter(this, R.layout.list_item, R.id.custid, arrlist);
        mainlistview.setAdapter(arradapter);
       // TextView rno1 = (TextView) findViewById(R.id.viewrno);
       /* final ProgressDialog pd = new ProgressDialog(Activity1.this);

        pd.setMessage("Fetching data");

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("student");
        //DatabaseReference myRef = database.getReference(user);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //String temp=dataSanpshot.getValue(Str
                String temp = dataSnapshot.child("rno").getValue(String.class);
                arrlist.add(temp);
               // reflist.add(dataSnapshot.getKey().toString());
                arradapter.notifyDataSetChanged();


                //String temp=dataSnapshot.child("Medication").getValue(String.class);
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
        });    */

    }



    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {


            return true;
        }
        if (id == R.id.action_final) {
            Intent intent1 = new Intent(Activity1.this, Act_round1.class);
            startActivity(intent1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
         */

    public void clickme(View v) {
        //Toast.makeText(getApplicationContext(),"run",Toast.LENGTH_SHORT).show();
      /*  View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        //ref=reflist.get(position);     */
        Intent intent1 = new Intent(getApplicationContext(), Student1.class);
        startActivity(intent1);
    }

}