package com.masuwes.githubusersecond.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masuwes.githubusersecond.R
import com.masuwes.githubusersecond.model.UserItem
import com.masuwes.githubusersecond.ui.RecyclerViewClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val dataUser = ArrayList<UserItem>()

    var listener: RecyclerViewClickListener? = null

    fun setData(items: ArrayList<UserItem>) {
        dataUser.clear()
        dataUser.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataUser.size

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bind(dataUser[position])
        holder.itemView.setOnClickListener {
            listener?.onItemClicked(it, dataUser[position])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userItem: UserItem) {
            with(itemView) {
                title.text = userItem.usrUsername
                Picasso.get().load(Uri.parse(userItem.usrAvatar)).into(image_avatar)
                subtitle.text = userItem.usrName
            }
        }
    }

}