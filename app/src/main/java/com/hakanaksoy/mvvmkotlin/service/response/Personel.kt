package com.hakanaksoy.mvvmkotlin.service.response


import com.google.gson.annotations.SerializedName

data class Personel(

        @field:SerializedName("srpersonel")
        val srpersonel: List<SrPersonel?>? = null,

        @field:SerializedName("jrpersonel")
        val jrpersonel: List<JrPersonel?>? = null

)