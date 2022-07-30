package com.practicaltask.data.entity.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class BestSellerList(
    @SerializedName("product_id")
    var productId : String,
    @SerializedName("sku")
    var sku : String,
    @SerializedName("mgs_brand")
    var msgBrand : String,
    @SerializedName("image")
    var image : String,
    @SerializedName("currency_code")
    var currencyCode : String,
    @SerializedName("price")
    var price : String,
    @SerializedName("final_price")
    var finalPrice : String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""
    ) {
    }
    constructor() : this("","","","","","","")
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(productId)
        parcel.writeString(sku)
        parcel.writeString(msgBrand)
        parcel.writeString(image)
        parcel.writeString(currencyCode)
        parcel.writeString(price)
        parcel.writeString(finalPrice)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BestSellerList> {
        override fun createFromParcel(parcel: Parcel): BestSellerList {
            return BestSellerList(parcel)
        }

        override fun newArray(size: Int): Array<BestSellerList?> {
            return arrayOfNulls(size)
        }
    }

}