package com.example.varunsai.vce;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;
import com.google.firebase.database.IgnoreExtraProperties;
 import java.io.Serializable;
@IgnoreExtraProperties

public class user implements Serializable {
    public String uid;
    public String fname,phn,spec,lname;
    public String mail;
    public String username;
    public String url;
    //public String firstname,lastname,phonenumber,pincode,address;
    public String longitude,latitude,address;
    public user(String uid,String username, String email,String url) {
        this.username = username;
        this.uid=uid;
        this.mail = email;
        this.url=url;
    }

    public user(String uid,String username, String email,String url,String fname,String lname, String spec,String phn,String exp,String degree,String address) {
        this.username = username;
        this.uid=uid;
        this.mail = email;
        this.url=url;
        this.fname=fname;
        this.lname=lname;
        this.phn=phn;
        this.spec=spec;
        this.address=address;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getUrl() {
        return url;
    }

    public String getMail() {
        return mail;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPhn() {
        return phn;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }


    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
