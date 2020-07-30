package com.masuwes.githubusersecond.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.masuwes.githubusersecond.R
import com.masuwes.githubusersecond.ui.fragment.FollowersFragment
import com.masuwes.githubusersecond.ui.fragment.FollowingFragment

class SectionPagerAdapter(private val context: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    var username: String? = null

    @StringRes
    private val TAB_TITLES = intArrayOf(
        R.string.tab_text1,
        R.string.tab_text2
    )

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment =
                FollowersFragment.newInstance(
                    username
                )
            1 -> fragment =
                FollowingFragment.newInstance(
                    username
                )
        }
        return fragment as Fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
      return 2
    }
}