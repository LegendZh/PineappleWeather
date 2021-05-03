package com.pinappleweather.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pinappleweather.android.logic.Repository
import com.pinappleweather.android.logic.model.Place

//定义ViewModel层
class PlaceViewModel:ViewModel() {
    private val searchLiveData= MutableLiveData<String>()
    val placeList=ArrayList<Place>()
    val placeLiveData= Transformations.switchMap(searchLiveData){query->
        Repository.searchPlaces(query)
    }
    fun searchPlaces(query:String){
        searchLiveData.value=query
    }
}