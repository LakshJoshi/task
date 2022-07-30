package com.practicaltask.data.entity.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class ShopByBrand(
    @SerializedName("brandId")
    var brandId : String,
    @SerializedName("label")
    var label : String,
    @SerializedName("url_key")
    var url_key : String,
    @SerializedName("option_id")
    var option_id : String,
    @SerializedName("image")
    var image : String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""
    )

    constructor() : this("","","","","")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(brandId)
        parcel.writeString(label)
        parcel.writeString(url_key)
        parcel.writeString(option_id)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShopByBrand> {
        override fun createFromParcel(parcel: Parcel): ShopByBrand {
            return ShopByBrand(parcel)
        }

        override fun newArray(size: Int): Array<ShopByBrand?> {
            return arrayOfNulls(size)
        }
    }

}
