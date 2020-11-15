package com.example.mobilebanking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    static ArrayList<DataSet> arrayList = new ArrayList<DataSet>();
    static ArrayList<SetDataTransaction>arrayList2 = new ArrayList<SetDataTransaction>();

    DatabaseHelper mydb;
    TransactionDatabase mytb;
    Button transferMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);
        mytb  = new TransactionDatabase(this);

        transferMoney = (Button)findViewById(R.id.transferMoney);
        moneyTransfer();

        Cursor cursor = mydb.readAllData();
        if(cursor.getCount() == 0) {
            intialiseDatabase();
        }
        viewAll();
        getRow();

        if(arrayList2.size()<1) {
            getTransactionRow();
        }
        viewAlltransactionData();
    }
    public void intialiseDatabase()
    {
        boolean isInserted=false;
        for(int i=0;i<10;i++)
        {
            isInserted = mydb.insertData(arrayList.get(i)) ;
        }
    }

    //here we are viewing all userData.
    public void viewAll()
    {
        Cursor cursor = mydb.readAllData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this,"No data could be retrieved",Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext())
        {
            buffer.append("Id: "+cursor.getString(0)+"\n");
            buffer.append("Name: "+cursor.getString(1)+"\n").toString();
            buffer.append("Email: "+cursor.getString(2)+"\n").toString();
            buffer.append("CurrentBalance: "+cursor.getString(3)+"\n").toString();
        }
        Log.e("data",buffer.toString());
    }
    //here we are updating alluser data array.
    public void getRow()
    {
        for(int i = 0;i<10;i++)
        {
            Cursor cursor = mydb.getRow(""+(i+1));
            if (cursor.getCount() == 0)
            {
                Toast.makeText(MainActivity.this, "No data can be retrieve", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer = new StringBuffer();
            String Name = "", Email = "";
            int CurrentBalance = 0;
            if (cursor.moveToNext())
            {
                Name = cursor.getString(0);
                Email = cursor.getString(1);
                CurrentBalance = cursor.getInt(2);
            }
            arrayList.add( new DataSet(Name,Email,CurrentBalance));
            Log.e("DATABASE", Name + " " + Email + " " + CurrentBalance);
        }
    }
    //here we viewing all transation data
    public void viewAlltransactionData()
    {
        Cursor cursor = mytb.readTransactionData();
        Log.e("cursor",cursor.toString());

        if(cursor.getCount() == 0)
        {
            Toast.makeText(this,"Error Nothing found",Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext())
        {
            buffer.append("ID: "+cursor.getString(0)+"\n");
            buffer.append("SenderName: "+cursor.getString(1)+"\n").toString();
            buffer.append("ReciverName: "+cursor.getString(2)+"\n").toString();
            buffer.append("Balance: "+cursor.getString(3)+"\n").toString();
        }
        Log.e("Transaction_Database",buffer.toString());
    }
    //here we are updating transaction array.
    public void getTransactionRow()
    {
        Cursor c  = mytb.readTransactionData();
        int count = c.getCount();
        for(int i = 0;i<count;i++)
        {
            Cursor cursor = mytb.getTransactionDataRow(""+(i+1));
            if(cursor.getCount() == 0)
            {
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer = new StringBuffer();
            String  SenderName = " ",ReciverName = " ";
            int Balance = 0;
            while(cursor.moveToNext())
            {
                SenderName = cursor.getString(0);
                ReciverName = cursor.getString(1);
                Balance = cursor.getInt(2);
            }
            arrayList2.add(new SetDataTransaction(SenderName,ReciverName,Balance));
            Log.e("TRANSACTION_DATABASE", SenderName + " " + ReciverName + " " + Balance);
        }

    }

    public void moneyTransfer()
    {
        transferMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(),moneyTransferActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addUser()
    {
        arrayList.add(new DataSet("Raushan","raushantiwari146@gmail.com",100000));
        arrayList.add(new DataSet("Golu","golutiwari1246@gmail.com",90000));
        arrayList.add(new DataSet("Pawan kumar","pawanKumar@gmail.com",200000));
        arrayList.add(new DataSet("Deepak kumar","kumarDeepak@gmail.com",50000));
        arrayList.add(new DataSet("Deepesh kumar","deepeshKumar@gmail.com",70000));
        arrayList.add(new DataSet("Shivam tiwari","shivamtiwari146@gmail.com",100000));
        arrayList.add(new DataSet("Shivani","shivanitiwari146@gmail.com",40000));
        arrayList.add(new DataSet("Pooja","pooja@gmail.com",80000));
        arrayList.add(new DataSet("Munna Tripathi","tripathimirajapur@gmail.com",4000));
        arrayList.add(new DataSet("Mk Das" ,"mkdasiic@gmail.com",900000));

    }


    // here i am crerating vertical three dot by using menu item
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_list,menu);
        return true;
    }
    //whatever the item created inside the menu ,here we set option seclectd listiner
    // and also set an activity to move next activity
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.menuid:
                Intent intent = new Intent(getApplicationContext(),ViewTransactionActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }


}