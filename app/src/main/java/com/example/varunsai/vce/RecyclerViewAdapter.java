package com.example.varunsai.vce;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.zxing.client.result.BookmarkDoCoMoResultParser;

import java.util.List;
import java.util.TreeMap;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    static int count=0;
    static String cid="";
    Context context;
    SupportMapFragment mapFragment;
    FragmentManager manager;
    private GoogleMap mMap;
    List<Details_companies> MainImageUploadInfoList;
    private double lat,lng;
    public RecyclerViewAdapter(Context context, List<Details_companies> TempList) {

        this.MainImageUploadInfoList = TempList;
        // this.manager=manager;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Details_companies details = MainImageUploadInfoList.get(position);
        holder.companyname.setText("Comapny name: "+details.name);
        holder.location.setText("Location: "+details.location);
        holder.date.setText("Date: "+details.date);
        holder.details_rounds.setText("Round details"+details.details_rounds);
        holder.other_details.setText("Other details: "+details.other_details);
        holder.package1.setText("avg package: "+details.package1);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                //holder.itemView.setBackgroundColor(Color.parseColor("#FF5722"));
                Intent intent=new Intent(context, company_selection.class);
                cid=details.ref;
                context.startActivity(intent);
                holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));

            }
        });
     /*  if(mapFragment==null)
        {
            FragmentManager fragmentManager = manager;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            mapFragment=SupportMapFragment.newInstance();
            transaction.replace(R.id.map,mapFragment ).commit();
        }

        mapFragment.getMapAsync(this);
        FragmentManager fragmentManager =manager;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mapFragment=SupportMapFragment.newInstance();
        transaction.replace(R.id.map,mapFragment ).commit();
        mapFragment.getMapAsync(this);
        //  mapFragment = (SupportMapFragment)holderfindFragmentById(R.id.map);   */
        //  holder.mapview.onCreate(null);
        //holder.mapview.getMapAsync(this);

    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }
/*
@Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat,lng);
        mMap.addMarker(new MarkerOptions().position(sydney).title("address"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng),16));
    }
         */

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView companyname,location,date,details_rounds,other_details,package1;
        // MapView mapview;
        public ViewHolder(View itemView) {
            super(itemView);
            companyname = (TextView) itemView.findViewById(R.id.companyname);
            location= (TextView) itemView.findViewById(R.id.location);
            date= (TextView) itemView.findViewById(R.id.date);
            details_rounds=(TextView)itemView.findViewById(R.id.details_rounds);
            other_details=(TextView)itemView.findViewById(R.id.other_details);
            package1=(TextView)itemView.findViewById(R.id.package1);
            // mapview=(MapView)itemView.findViewById(R.id.mapview);
           /*  mapFragment = (SupportMapFragment)
                  .findFragmentById(R.id.map3);
          //  mapFragment = (SupportMapFragment)itemView.fin(R.id.map); */

        }
    }
}
