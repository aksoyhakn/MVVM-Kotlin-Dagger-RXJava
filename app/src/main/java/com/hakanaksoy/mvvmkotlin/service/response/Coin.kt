package com.hakanaksoy.mvvmkotlin.service.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Coin(
    @SerializedName("id") @Expose var id: String? = null,
    @SerializedName("symbol") @Expose var symbol: String? = null,
    @SerializedName("name") @Expose var name: String? = null,
    @SerializedName("image") @Expose var image: String? = null,
    @SerializedName("last_updated") @Expose var lastUpdated: String? = null
) : Parcelable