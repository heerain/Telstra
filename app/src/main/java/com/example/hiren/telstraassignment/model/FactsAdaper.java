package com.example.hiren.telstraassignment.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.hiren.telstraassignment.R;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FactsAdaper extends RecyclerView.Adapter<FactsAdaper.Holder> {

    private final LayoutInflater mInflater;
    private ArrayList<FactsResponse> mFactsResponseList;

    public FactsAdaper(LayoutInflater layoutInflater) {
        mInflater = layoutInflater;
        mFactsResponseList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(mInflater.inflate(R.layout.item_fact, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        FactsResponse factsResponse = mFactsResponseList.get(i);

        if (factsResponse.getTitle() != null) {
            holder.mTitle.setText(factsResponse.getTitle());
        } else {
            holder.mTitle.setText(R.string.no_title);
        }

        if (factsResponse.getDescription() != null)
            holder.mDesc.setText(factsResponse.getDescription());
        else
            holder.mDesc.setText(R.string.no_description);

        //used picasso library to download image lazy
        Picasso.get().
                load(factsResponse.getImageHref())
                .fit()
                .placeholder(R.drawable.telstra_logo)
                .error(R.drawable.telstra_logo)
                .into(holder.mPhoto);

    }

    @Override
    public int getItemCount() {
        return mFactsResponseList.size();
    }

    public void addFactsList(@NotNull ArrayList<FactsResponse> facts) {
        mFactsResponseList = facts;
        notifyDataSetChanged();
    }


    public class Holder extends RecyclerView.ViewHolder {

        private TextView mTitle, mDesc;
        private ImageView mPhoto;

        public Holder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_title);
            mDesc = itemView.findViewById(R.id.tv_desc);
            mPhoto = itemView.findViewById(R.id.img_photo);
        }
    }
}
