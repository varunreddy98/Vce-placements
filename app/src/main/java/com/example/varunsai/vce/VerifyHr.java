package com.example.varunsai.vce;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class VerifyHr extends AppCompatActivity {
    static String companyvercode;
    EditText tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_hr);
        tv=(EditText) findViewById(R.id.companycode);
    }
    public void clicktoverify(View view)
    {
        companyvercode=tv.getText().toString();
        Intent intent=new Intent(getApplicationContext(), Activity1.class);
        startActivity(intent);
    }
}