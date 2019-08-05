package com.example.varunsai.vce;

import android.os.strictmode.IntentReceiverLeakedViolation;

import java.util.ArrayList;

public class Details_companies {

    String name;
    String depts;
    String location;
    String post;
    String date;
    String language;
    String details_rounds;
    String other_details;
    String package1;
    String cgpa;
    String ref;
    ArrayList<student> arrayList=new ArrayList<student>();
    ArrayList<student> arrayList2=new ArrayList<student>();

    Details_companies(String ref,String name,String depts,String location,String post,String date,String language,String details_rounds,String other_details,String package1,String cgpa,ArrayList<student> arrayList,ArrayList<student> arrayList2)
    {      this.ref=ref;
        this.name=name;
        this.depts=depts;
        this.location=location;
        this.post=post;
        this.date=date;
        this.language=language;
        this.details_rounds=details_rounds;
        this.other_details=other_details;
        this.package1=package1;
        this.cgpa=cgpa;
        this.arrayList=arrayList;
        this.arrayList2=arrayList2;
    }







}
