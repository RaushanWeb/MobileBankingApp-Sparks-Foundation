package com.example.mobilebanking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import static com.example.mobilebanking.MainActivity.arrayList;

public class ViewUserButtonActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_button);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerViewButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ViewUserBottonAdapter(this,arrayList));

    }
}