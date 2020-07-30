package com.masuwes.githubusersecond.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserItem(
    var usrUsername : String? = null,
    var usrName : String? = null,
    var usrAvatar : String? = null,
    var usrCompany : String? = null,
    var usrLocation : String? = null,
    var usrRepository : Int? = null,
    var usrFollower : Int? = null,
    var usrFollowing : Int? = null
) : Parcelable