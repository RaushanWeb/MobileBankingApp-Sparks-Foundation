package com.example.mobilebanking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutUserActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    DataSet dataSet;
    TextView userName,userEmail,userCurrentBalance;
    Button transferButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_user);
        mydb = new DatabaseHelper(this);

        userName = (TextView)findViewById(R.id.userName);
        userEmail = (TextView)findViewById(R.id.userEmail);
        userCurrentBalance = (TextView)findViewById(R.id.userCurrentBalance);
        Intent intent = getIntent();
        userName.setText(intent.getStringExtra("Name"));
        userEmail.setText(intent.getStringExtra("Email"));
        userCurrentBalance.setText(intent.getStringExtra("CurrentBalance"));

        transferButton = (Button)findViewById(R.id.transferButton);

        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText(AboutuserActivity.this,intent.getStringExtra("Name"),Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(AboutUserActivity.this,TransferActivity.class);
                intent1.putExtra("Name",intent.getStringExtra("Name"));
                startActivity(intent1);

            }
        });


    }

}