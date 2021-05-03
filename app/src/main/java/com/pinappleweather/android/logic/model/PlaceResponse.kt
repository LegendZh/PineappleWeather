package com.pinappleweather.android.logic.model

import com.google.gson.annotations.SerializedName

// 定义获得的数据(Model)
data class PlaceResponse(val status:String,val places:List<Place>)

//定义的目的是为了建立映射联系
data class Place(val name:String,val location:Location,@SerializedName("formatted_address") val address:String)

data class Location(val lng:String,val lat:String)