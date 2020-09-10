package com.globomed.gads_2020_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerViewAdapterLearners extends RecyclerView.Adapter<RecyclerViewAdapterLearners.CustomViewHolder> {

    private List<DataModelLearners> dataList;
    private Context context;

    public RecyclerViewAdapterLearners(Context context,List<DataModelLearners> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView name;
        TextView hours_and_country;
         ImageView image;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            name = mView.findViewById(R.id.name);
            hours_and_country=mView.findViewById(R.id.hours_and_country);
            image = mView.findViewById(R.id.image);
        }
    }

    @NotNull
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_learners, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.name.setText(dataList.get(position).getName());
        String hours_country=dataList.get(position).getHours()+" learning hours, "+dataList.get(position).getCountry()+".";
        holder.hours_and_country.setText(hours_country);
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getBadgeUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
