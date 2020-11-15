package com.example.mobilebanking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.mobilebanking.MainActivity.arrayList;//users
import static com.example.mobilebanking.MainActivity.arrayList2;//transaction
import static com.example.mobilebanking.SpinnerActivity.sReciverName;

public class TransferActivity extends AppCompatActivity
{
    DatabaseHelper mydb;
    TransactionDatabase mytb;
    TableLayout keybordHide;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        String userName;
        if (savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if(extras == null)
            {
                userName= null;
            }
            else
            {
                userName= extras.getString("Name");
            }
        }
        else
        {
            userName= (String) savedInstanceState.getSerializable("Name");
        }


        DataSet sender = getUser(userName);


        mydb = new DatabaseHelper(this);
        mytb = new TransactionDatabase(this);

        /*1*/ TextView name = (TextView)findViewById(R.id.userTransferName);
        Intent intent2 = getIntent();
        name.setText(intent2.getStringExtra("Name"));

        /*2*/ final EditText EnterBalance = (EditText)findViewById(R.id.editText);

        /*3*/  final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.recipient_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerActivity());
        /*4*/
        Button confermTransfer = (Button)findViewById(R.id.confermTransferButton);
        confermTransfer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String enterAmount = EnterBalance.getText().toString();
                //  if(enterAmount.equals(" "))
                if (TextUtils.isEmpty(enterAmount))
                {
                    EnterBalance.setError("required filled!");
                    return;
                    // enterAmount = "0";
                }

                DataSet receiverName = getUser(sReciverName);

                int balance = Integer.parseInt(enterAmount);
                if (balance < 1)
                {
                    Toast.makeText(TransferActivity.this, "Please enter valid  amount", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    if (!receiverName.getName().equals(sender.getName()))  //if both pointing to same object ,we should not enter inside the loop
                    {
                        if (sender.isTransactionPossible(balance)) {
                            sender.subBalance(balance);
                            receiverName.addBalaance(balance);
                            //EnterBalance.setText(" ");
                            //userBalance = "" + sender.getCurrentBalance();
                            arrayList2.add(new SetDataTransaction(sender.getName(), receiverName.getName(), balance));

                            intialiseDatabase(new SetDataTransaction(sender.getName(), receiverName.getName(), balance));

                            Toast.makeText(TransferActivity.this, "Transfer Successful", Toast.LENGTH_SHORT).show();
                            updateData(sender);
                            updateData(receiverName);

                        } else {
                            Toast.makeText(TransferActivity.this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TransferActivity.this, "Please Select a Different User", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e){}

                Intent intent = new Intent(TransferActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //here i writing the code to hide the keybord while clicking anywhere on the screen;
        keybordHide = (TableLayout)findViewById(R.id.keyboardhidelayout);
        keybordHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setKeybordHide(v);
            }


        });



    }
    DataSet getUser(String name)
    {
        for(int i = 0;i<arrayList.size();i++)
        {
            if(name.equals(arrayList.get(i).getName())) // here we are performing content comparision
            {
                return arrayList.get(i);
            }
        }
        return  null;
    }
    public void updateData(DataSet user)
    {
        mydb.updateCurrentBalance(user.getName(),user.getCurrentBalance());
    }

    public void intialiseDatabase(SetDataTransaction transaction)
    {
        mytb.transactionInserted(transaction);
    }

    void setKeybordHide(View view)
    {
        InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }


}