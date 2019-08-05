package com.example.varunsai.vce;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import  java.util.*;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.client.result.BookmarkDoCoMoResultParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class MainActivity extends AppCompatActivity{
    ProgressDialog progressDialog;
    private Button button;
    RecyclerView.Adapter adapter;
    static ArrayList<Details_companies> map1=new ArrayList<>();
    static  ArrayList<String> companyids=new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        setContentView(R.layout.activity_companies);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent1=new Intent(MainActivity.this,addcompany1.class);
               startActivity(intent1);

            }
        });
        starter();
    }

    private void starter() {
        // intent.putExtra("doc", mItem.name);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
            final SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            String previously=prefs1.getString("key","null" );
        FirebaseDatabase.getInstance().getReference("companies").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Toast.makeText(MainActivity.this, "sdfff", Toast.LENGTH_LONG).show();
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
                        companyids.add(mmt);

                    }

                    adapter=new RecyclerViewAdapter(MainActivity.this,map1);
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

