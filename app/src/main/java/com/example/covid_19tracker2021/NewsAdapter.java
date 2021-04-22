package com.example.covid_19tracker2021;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<stateviewholder> {

private Context context;
private List<newsmodelclass> actuall_news_fetcher;

public NewsAdapter(Context context,List<newsmodelclass> actuall_news_fetcher){
    this.context=context;
    this.actuall_news_fetcher=actuall_news_fetcher;
}
    @NonNull
    @Override
    public stateviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.india_news_item,parent,false);

        return new stateviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull stateviewholder holder, int position) {
        holder.txt_title.setText(actuall_news_fetcher.get(position).getNews_title());
        holder.txt_source.setText(actuall_news_fetcher.get(position).getNews_source());
        holder.txt_date.setText(actuall_news_fetcher.get(position).getNews_date());
        Glide.with(context).load(actuall_news_fetcher.get(position).getImage())
                .thumbnail(0.5f)
                .into(holder.img1);

    }

    @Override
    public int getItemCount() {

        return actuall_news_fetcher.size();
    }
}
