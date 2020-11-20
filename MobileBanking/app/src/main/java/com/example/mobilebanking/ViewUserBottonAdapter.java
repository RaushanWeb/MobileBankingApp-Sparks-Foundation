package com.example.mobilebanking;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewUserBottonAdapter extends RecyclerView.Adapter<ViewUserBottonAdapter.userViewHolder>
{
    Context context;
    List<DataSet> arrayList = new ArrayList<DataSet>();

    public ViewUserBottonAdapter(Context context, ArrayList<DataSet> arrayList) {
        this.context = context;
        this.arrayList = arrayList.subList(0,10);
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_user_item_list,parent,false);
        return new userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position)
    {
        String s = arrayList.get(position).getName();
        String s1 = String.valueOf(s.charAt(0));
        for(int i =1;i<s.length();i++)
        {
            if(s.charAt(i-1) == ' ' )
            {
                s1+=s.charAt(i);
            }
        }
        holder.imageIcon.setText(s1);
        holder.nameText.setText(arrayList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context,ViewUserActivity.class);
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


    public class userViewHolder extends RecyclerView.ViewHolder
    {
        TextView imageIcon;
        TextView nameText;

        public userViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageIcon = (TextView)itemView.findViewById(R.id.imageiconViewUsser);
            nameText = (TextView)itemView.findViewById(R.id.textnameViewUser);
        }
    }
}
