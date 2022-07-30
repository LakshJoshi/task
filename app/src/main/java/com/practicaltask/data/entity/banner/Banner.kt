package com.practicaltask.data.entity.banner

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Banner(
    @SerializedName("slider_id")
    var sliderId : String,
    @SerializedName("mobile_image")
    var image : String,
    @SerializedName("title")
    var title : String,
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""
    ) {

    }
    constructor() : this("","","")


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(sliderId)
        parcel.writeString(image)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Banner> {
        override fun createFromParcel(parcel: Parcel): Banner {
            return Banner(parcel)
        }

        override fun newArray(size: Int): Array<Banner?> {
            return arrayOfNulls(size)
        }
    }
}
