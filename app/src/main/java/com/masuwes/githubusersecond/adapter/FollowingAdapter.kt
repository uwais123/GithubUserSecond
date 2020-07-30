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

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {

    private val followingData = ArrayList<UserItem>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userItem: UserItem) {
            with(itemView) {
                title.text = userItem.usrUsername
                Picasso.get().load(Uri.parse(userItem.usrAvatar)).into(image_avatar)
            }
        }
    }

    fun setData(items: ArrayList<UserItem>) {
        followingData.clear()
        followingData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = followingData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(followingData[position])
    }
}