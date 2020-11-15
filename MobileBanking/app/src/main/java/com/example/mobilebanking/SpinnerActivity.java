package com.example.mobilebanking;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener
{
    public static String sReciverName;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        sReciverName = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

