package com.masuwes.githubusersecond.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.masuwes.githubusersecond.R
import com.masuwes.githubusersecond.adapter.FollowersAdapter
import com.masuwes.githubusersecond.viewmodel.FollowersViewModel
import kotlinx.android.synthetic.main.fragment_followers.*


class FollowersFragment : Fragment() {

    private lateinit var followersAdapter: FollowersAdapter
    private lateinit var followersViewModel: FollowersViewModel

    companion object {

        private const val ARG_USERNAME = "username"

        fun newInstance(username: String?): FollowersFragment {
            val fragment =
                FollowersFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        followersAdapter = FollowersAdapter()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followers_list.run {
            adapter = followersAdapter
            layoutManager = LinearLayoutManager(view.context)
        }

        followersViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowersViewModel::class.java)

        val username = arguments?.getString(ARG_USERNAME)
        if (username != null) {
            followersViewModel.setUser(username)
        }

        followersViewModel.getUser().observe(activity!!, Observer { followers ->
            if (followers != null) {
                followersAdapter.setData(followers)
            }
        })

    }


}


// end