package com.example.mobilebanking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder>
{
    ArrayList<DataSet> arrayList = new ArrayList<DataSet>();
    Context context;

    public ProgrammingAdapter(Context context,ArrayList<DataSet> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout,parent,false);
        return new ProgrammingViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position)
    {
        String s = arrayList.get(position).getName().trim();
        String s2 = String.valueOf(s.charAt(0));
        for(int i = 1;i<s.length();i++)
        {
            if(s.charAt(i-1) == ' ')
            {
                s2 +=s.charAt(i);
            }
        }
        holder.imageicon.setText(s2);
        holder.nametext.setText(arrayList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // Toast. makeText (  context,arrayList.get(position).getName(), Toast . LENGTH_SHORT ). show ();
                //Toast. makeText (  context,arrayList.get(position).getEmail(), Toast . LENGTH_SHORT ). show ();
                //Toast.makeText(context,""+arrayList.get(position).getCurrentBalance(),Toast.LENGTH_SHORT).show();
                // String s = arrayList.get(position).getCurrentBalance();
                Intent intent = new Intent(context,AboutUserActivity.class);
                intent.putExtra("Name",arrayList.get(position).getName());
                intent.putExtra("Email",arrayList.get(position).getEmail());
                intent.putExtra("CurrentBalance",""+arrayList.get(position).getCurrentBalance());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ProgrammingViewHolder extends RecyclerView.ViewHolder
    {
        TextView imageicon;
        TextView nametext;

        public ProgrammingViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageicon = (TextView)itemView.findViewById(R.id.imageicon);
            nametext = (TextView)itemView.findViewById(R.id.textname);
        }

    }
}

