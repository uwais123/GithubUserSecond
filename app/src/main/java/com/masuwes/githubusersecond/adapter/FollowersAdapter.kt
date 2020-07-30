package com.masuwes.githubusersecond.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masuwes.githubusersecond.R
import com.masuwes.githubusersecond.model.UserItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class FollowersAdapter : RecyclerView.Adapter<FollowersAdapter.ViewHolder>() {

    private val followersData = ArrayList<UserItem>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userItem: UserItem) {
            with(itemView) {
                title.text = userItem.usrUsername
                Picasso.get().load(Uri.parse(userItem.usrAvatar)).into(image_avatar)
            }
        }
    }

    fun setData(items: ArrayList<UserItem>) {
        followersData.clear()
        followersData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = followersData.size

    override fun onBindViewHolder(holder: FollowersAdapter.ViewHolder, position: Int) {
        holder.bind(followersData[position])
    }

}