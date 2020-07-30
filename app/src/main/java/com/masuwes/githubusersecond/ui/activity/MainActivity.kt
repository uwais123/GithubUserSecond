package com.masuwes.githubusersecond.ui.activity


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.masuwes.githubusersecond.R
import com.masuwes.githubusersecond.adapter.MainAdapter
import com.masuwes.githubusersecond.model.UserItem
import com.masuwes.githubusersecond.ui.RecyclerViewClickListener
import com.masuwes.githubusersecond.ui.activity.DetailActivity.Companion.USER_DATA
import com.masuwes.githubusersecond.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    RecyclerViewClickListener {

    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Github FindUser"

        adapter = MainAdapter()
        adapter.notifyDataSetChanged()

        // tampilkan recyclerview
        user_list.layoutManager = LinearLayoutManager(this)
        user_list.adapter = adapter
        user_list.setHasFixedSize(true)

        viewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        viewModel.getUser().observe(this, Observer { user ->
            if (user != null) {
                adapter.setData(user)
                showLoading(false)
                no_result_layout.visibility = View.GONE
            }
        })
        adapter.listener = this
    }

    // gatau kenapa gua pen make gnian, populer aja gtu
    private fun showLoading(state: Boolean) {
        if (state) {
            progress_circular.visibility = View.VISIBLE
        } else {
            progress_circular.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        // import androidx.appcompat.widget.SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                showLoading(true)
                no_result_layout.visibility = View.GONE
                viewModel.setUser(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu1) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(view: View, user: UserItem) {
        val detailIntent = Intent(this, DetailActivity::class.java)
            .putExtra(USER_DATA, user)
        startActivity(detailIntent)
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, resources.getString(R.string.twiceback), Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}











// end