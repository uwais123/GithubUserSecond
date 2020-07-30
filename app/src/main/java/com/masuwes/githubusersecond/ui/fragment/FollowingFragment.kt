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
import com.masuwes.githubusersecond.adapter.FollowingAdapter
import com.masuwes.githubusersecond.viewmodel.FollowingViewModel
import kotlinx.android.synthetic.main.fragment_following.*

class FollowingFragment : Fragment() {

    private lateinit var followingAdapter: FollowingAdapter
    private lateinit var followingViewModel: FollowingViewModel

    companion object {

        private const val ARG_USERNAME = "username"

        fun newInstance(username: String?): FollowingFragment {
            val fragment =
                FollowingFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        followingAdapter = FollowingAdapter()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        following_list.run {
            layoutManager = LinearLayoutManager(view.context)
            adapter = followingAdapter
        }

        followingViewModel = activity?.let {
            ViewModelProvider(
                it,
                ViewModelProvider.NewInstanceFactory()
            ).get(FollowingViewModel::class.java)
        }!!

        val username = arguments?.getString(ARG_USERNAME)
        if (username != null) {
            followingViewModel.setUser(username)
        }

        followingViewModel.getUser().observe(activity!!, Observer { following ->
            if (following != null) {
                followingAdapter.setData(following)
            }
        })
    }

}