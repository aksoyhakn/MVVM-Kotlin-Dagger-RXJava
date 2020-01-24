package com.hakanaksoy.mvvmkotlin.service.response


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Personel(
    @SerializedName("srpersonel") @Expose var srpersonel: List<SrPersonel?>? = null,
    @SerializedName("jrpersonel") @Expose var jrpersonel: List<JrPersonel?>? = null
) : Parcelable