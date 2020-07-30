package com.masuwes.githubusersecond.ui

import android.view.View
import com.masuwes.githubusersecond.model.UserItem

interface RecyclerViewClickListener {
    fun onItemClicked(view: View, user: UserItem)
}