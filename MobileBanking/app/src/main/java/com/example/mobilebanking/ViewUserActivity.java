package com.example.mobilebanking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import static com.example.mobilebanking.MainActivity.arrayList;

public class ViewUserActivity extends AppCompatActivity
{
    TextView viewUserName,viewUserEmail,viewUserCurrentbalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        viewUserName = (TextView)findViewById(R.id.ViewuserName);
        viewUserEmail = (TextView)findViewById(R.id.ViewuserEmail);
        viewUserCurrentbalance = (TextView)findViewById(R.id.ViewuserCurrentBalance);

        Intent intent = getIntent();
        viewUserName.setText(intent.getStringExtra("Name"));
        viewUserEmail.setText(intent.getStringExtra("Email"));
        viewUserCurrentbalance.setText(intent.getStringExtra("CurrentBalance"));



    }
}