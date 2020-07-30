package com.masuwes.githubusersecond.ui.activity

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.masuwes.githubusersecond.R
import com.masuwes.githubusersecond.adapter.SectionPagerAdapter
import com.masuwes.githubusersecond.model.UserItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val USER_DATA = "USER_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.apply {
            title = "Profile"
            setDisplayHomeAsUpEnabled(true)
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f

        // nangkep parcel dari main
        val userData = intent.getParcelableExtra<UserItem>(USER_DATA)

        // kalau userData ada isinya
        userData?.let {
            Picasso.get().load(Uri.parse(it.usrAvatar)).into(detail_avatar)
            detail_name.text = it.usrName
            detail_username.text = it.usrUsername
            detail_company.text = it.usrCompany
            detail_location.text = it.usrLocation

            sectionPagerAdapter.username = it.usrUsername

            val txtFollower = resources.getString(R.string.tab_text1)
            val txtFollowing = resources.getString(R.string.tab_text2)
            tabs.getTabAt(0)?.text = "$txtFollower : ${it.usrFollower}"
            tabs.getTabAt(0)?.setIcon(R.drawable.followers)
            tabs.getTabAt(1)?.text = "$txtFollowing : ${it.usrFollowing}"
            tabs.getTabAt(1)?.setIcon(R.drawable.following)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}