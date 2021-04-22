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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class listadapter extends ArrayAdapter<Modelclass> {
    private Context contex;
    private List<Modelclass> countryModelsList;
    private List<Modelclass> countryModelsListfiltered;

    public listadapter(@NonNull Context context, List<Modelclass> countryModelsList) {
        super(context, R.layout.states_item,countryModelsList);
        this.contex=context;
        this.countryModelsList=countryModelsList;

        this.countryModelsListfiltered=countryModelsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.states_item,null,false);
        TextView t1=(TextView)view.findViewById(R.id.common);
        t1.setText(countryModelsListfiltered.get(position).getDistrictname());
        return view;
    }

    @Override
    public int getCount() {
   return countryModelsListfiltered.size();
    }

    @Nullable
    @Override
    public Modelclass getItem(int position) {
        return countryModelsListfiltered.get(position);
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
                    filterResults.count = countryModelsList.size();
                    filterResults.values = countryModelsList;

                }else{
                    List<Modelclass> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(Modelclass itemsModel:countryModelsList){
                        if(itemsModel.getDistrictname().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                countryModelsListfiltered = (List<Modelclass>) results.values;
               districtActivity.ml1 = (List<Modelclass>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
