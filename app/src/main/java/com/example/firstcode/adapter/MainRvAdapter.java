package com.example.firstcode.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstcode.R;

import java.util.ArrayList;
import java.util.List;

public class MainRvAdapter extends RecyclerView.Adapter<MainRvAdapter.MyViewHolder> {
    Context context;

    List<String> datalist = new ArrayList<>();

    public MainRvAdapter(Context context,List<String> list) {
        super();
        datalist = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MainRvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rv_item, parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MainRvAdapter.MyViewHolder holder, final int position) {
        holder.textView.setText(datalist.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, holder.textView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.main_rv_item_tv);
        }
    }


}
