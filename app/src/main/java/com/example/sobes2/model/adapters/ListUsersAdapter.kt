package com.example.sobes2.model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.mvp.view.image.GlideImageLoader
import com.example.sobes2.R
import com.example.sobes2.model.retrofit.entity.Datum

class ListUsersAdapter(private val data: List<Datum>, private val onClickListener: MyOnClickListener) : RecyclerView.Adapter<ListUsersAdapter.MyViewHolder>() {

    private val imageLoader = GlideImageLoader()

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstName: TextView = view.findViewById<View>(R.id.iv_first_name) as TextView
        val lastName: TextView = view.findViewById(R.id.iv_last_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data1 = data[position].firstName
        holder.firstName.text = data1
        holder.lastName.text = data[position].lastName

        holder.itemView.setOnClickListener{
            onClickListener.onClicked(data[position])
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

}