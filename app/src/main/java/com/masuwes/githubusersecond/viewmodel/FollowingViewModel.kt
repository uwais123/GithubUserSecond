package com.masuwes.githubusersecond.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.masuwes.githubusersecond.model.UserItem
import com.masuwes.githubusersecond.ui.activity.MainActivity
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class FollowingViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<UserItem>>()

    fun setUser(username: String) {

        val client = AsyncHttpClient()
        val listItems = ArrayList<UserItem>()

        val url = "https://api.github.com/users/$username/following"

        client.addHeader("Authorization", "token [YOUR_APIKEY]")
        client.addHeader("User-Agent", "request")

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)
                Log.d(MainActivity.TAG + " detail", result)

                try{
                    val jsonArray = JSONArray(result)

                    for (i in 0 until jsonArray.length()) {
                        val item = jsonArray.getJSONObject(i)
                        val user = UserItem()
                        user.usrUsername = item.getString("login")
                        user.usrAvatar = item.getString("avatar_url")

                        listItems.add(user)
                    }
                    listUsers.postValue(listItems)

                }
                catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                Log.d("onFailure", error.message.toString())
            }

        })
    }

    fun getUser(): LiveData<ArrayList<UserItem>> = listUsers

}
