package com.example.mobilebanking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>
{
    Context context;
    ArrayList<SetDataTransaction> arrayList2 = new ArrayList<SetDataTransaction>();

    public TransactionAdapter(Context context, ArrayList<SetDataTransaction> arrayList2) {
        this.context = context;
        this.arrayList2 = arrayList2;
    }

    @NonNull
    @Override
    public TransactionAdapter.TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_list,parent,false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.TransactionViewHolder holder, int position)
    {
        holder.senderName.setText(arrayList2.get(position).getSenderName());
        holder.reciverName.setText(arrayList2.get(position).getReciverName());
        holder.balance.setText(""+arrayList2.get(position).getBalance());
    }

    @Override
    public int getItemCount() {
        return arrayList2.size();
    }
    public class TransactionViewHolder extends RecyclerView.ViewHolder
    {
        TextView senderName,reciverName,balance;

        public TransactionViewHolder(@NonNull View itemView)
        {
            super(itemView);
            senderName = (TextView)itemView.findViewById(R.id.senderNameTrans);
            reciverName = (TextView)itemView.findViewById(R.id.reciverNameTrans);
            balance = (TextView)itemView.findViewById(R.id.balanceTrans);
        }
    }

}
