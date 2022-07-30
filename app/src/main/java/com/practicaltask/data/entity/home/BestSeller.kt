package com.practicaltask.data.entity.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class BestSeller(
    @SerializedName("id")
    var id : String,
    @SerializedName("bestseller_list")
    var bestSellerList : ArrayList<BestSellerList>,
) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.createTypedArrayList(BestSellerList)?: ArrayList()
    )

    constructor() : this("", arrayListOf())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeTypedList(bestSellerList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BestSeller> {
        override fun createFromParcel(parcel: Parcel): BestSeller {
            return BestSeller(parcel)
        }

        override fun newArray(size: Int): Array<BestSeller?> {
            return arrayOfNulls(size)
        }
    }

}
