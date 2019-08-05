package com.example.varunsai.vce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class company_selection extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_selection);
        Button b1=(Button)findViewById(R.id.next1);
        Button b2=(Button)findViewById(R.id.next2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.next1:
                Intent intent=new Intent(company_selection.this,company_students.class);
                intent.putExtra("type","applied_candidates" );
                startActivity(intent);
                break;

            case R.id.next2:
                Intent intent1=new Intent(company_selection.this,company_students.class);
                intent1.putExtra("type","selected_candidates" );
               startActivity(intent1);
                break;

        }

    }
}
