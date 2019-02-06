package com.example.hiren.telstraassignment.model

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.hiren.telstraassignment.R
import com.squareup.picasso.Picasso

import java.util.ArrayList

class FactsAdaper(private val mInflater: LayoutInflater) : RecyclerView.Adapter<FactsAdaper.Holder>() {
    private var mFactsResponseList: ArrayList<FactsResponse>? = null

    init {
        mFactsResponseList = ArrayList()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Holder {
        return Holder(mInflater.inflate(R.layout.item_fact, viewGroup, false))
    }

    override fun onBindViewHolder(holder: Holder, i: Int) {
        val (title, description, imageHref) = mFactsResponseList!![i]

        if (title != null) {
            holder.mTitle.text = title
        } else {
            holder.mTitle.setText(R.string.no_title)
        }

        if (description != null)
            holder.mDesc.text = description
        else
            holder.mDesc.setText(R.string.no_description)

        //used picasso library to download image lazy
        Picasso.get().load(imageHref)
            .fit()
            .placeholder(R.drawable.telstra_logo)
            .error(R.drawable.telstra_logo)
            .into(holder.mPhoto)

    }

    override fun getItemCount(): Int {
        return mFactsResponseList!!.size
    }

    fun addFactsList(facts: ArrayList<FactsResponse>) {
        mFactsResponseList = facts
        notifyDataSetChanged()
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         val mTitle: TextView
         val mDesc: TextView
         val mPhoto: ImageView

        init {
            mTitle = itemView.findViewById(R.id.tv_title)
            mDesc = itemView.findViewById(R.id.tv_desc)
            mPhoto = itemView.findViewById(R.id.img_photo)
        }
    }
}
