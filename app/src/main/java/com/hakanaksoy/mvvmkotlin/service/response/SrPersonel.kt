package com.hakanaksoy.mvvmkotlin.service.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class SrPersonel(

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("image")
        val image: String? = null


) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeString(image)
        parcel.writeString(name)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SrPersonel> {
        override fun createFromParcel(parcel: Parcel): SrPersonel {
            return SrPersonel(parcel)
        }

        override fun newArray(size: Int): Array<SrPersonel?> {
            return arrayOfNulls(size)
        }
    }

}