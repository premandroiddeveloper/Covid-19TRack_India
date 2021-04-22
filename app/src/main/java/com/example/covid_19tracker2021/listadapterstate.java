package com.example.covid_19tracker2021;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class listadapterstate extends ArrayAdapter<statemodelclass> {
    private Context contex;
    private List<statemodelclass> statemodelclasses;
    private List<statemodelclass> statemodelclassesfiltered;

    public listadapterstate(@NonNull Context context, List<statemodelclass> stateModelsList) {
        super(context, R.layout.states_item,stateModelsList);
        this.contex=context;
        this.statemodelclasses=stateModelsList;
        this.statemodelclassesfiltered=stateModelsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.states_item,null,false);
        TextView t1=(TextView)view.findViewById(R.id.common);
        t1.setText(statemodelclassesfiltered.get(position).getStates());
        return view;
    }

    @Override
    public int getCount() {
        return statemodelclassesfiltered.size();
    }

    @Nullable
    @Override
    public statemodelclass getItem(int position) {
        return statemodelclassesfiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count =statemodelclasses.size();
                    filterResults.values = statemodelclasses;

                }else{
                    List<statemodelclass> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(statemodelclass itemsModel:statemodelclasses){
                        if(itemsModel.getStates().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
    }
            protected void publishResults(CharSequence constraint, FilterResults results) {

                statemodelclassesfiltered = (List<statemodelclass>) results.values;
               GUJARATCASES.ls1 = (List<statemodelclass>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
}}
