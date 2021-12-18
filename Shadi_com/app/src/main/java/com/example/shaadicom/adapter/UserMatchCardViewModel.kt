package com.example.shaadicom.adapter

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.example.shaadicom.database.UserDetailsEntity

class UserMatchCardViewModel(val matchcard: UserDetailsEntity, val position :Int) : BaseObservable() {
    var FullName = ObservableField<String>().apply {
        set(
            "${matchcard.fullname}"
        )
    }
    var imageurl=ObservableField<String>().apply {
        set(
        matchcard.imageurl
        )
    }
    var location = ObservableField<String>().apply {
        set(
            "${matchcard.location}"
        )
    }
    var age_gender = ObservableField<String>().apply {
        set(
        "${matchcard.ageplusgender}"
    )
    }
    var  accept = ObservableField<String>().apply {
        set(
            "${matchcard.accept}"
        )
    }
    var decline = ObservableField<String>().apply {
        set(
            "${matchcard.decline}"
        )
    }



}