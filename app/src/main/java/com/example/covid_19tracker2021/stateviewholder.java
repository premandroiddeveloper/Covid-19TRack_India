package com.example.covid_19tracker2021;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class stateviewholder extends RecyclerView.ViewHolder {
    TextView txt_source,txt_title,txt_date,txt_readmore;
    ImageView img1;
    public stateviewholder(@NonNull View itemView) {
        super(itemView);
        txt_source=(TextView)itemView.findViewById(R.id.news_source_item);
        txt_title=(TextView)itemView.findViewById(R.id.news_title_item);
        txt_date=(TextView)itemView.findViewById(R.id.news_date_item);
        txt_readmore=(TextView)itemView.findViewById(R.id.textView5);
        img1=(ImageView)itemView.findViewById(R.id.news_image_item);
    }
}
