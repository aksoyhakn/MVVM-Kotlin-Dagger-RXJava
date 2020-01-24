package com.hakanaksoy.mvvmkotlin.service.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JrPersonel(
    @SerializedName("id") @Expose var id: Int? = null,
    @SerializedName("name") @Expose var name: String? = null,
    @SerializedName("image") @Expose var image: String? = null
) : Parcelable