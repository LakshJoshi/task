package com.practicaltask.data.entity.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.practicaltask.data.entity.banner.Banner


data class Home(
    @SerializedName("banner_slider")
    var bannerArrayList : ArrayList<Banner>,
    @SerializedName("best_seller")
    var bestSeller : BestSeller,
    @SerializedName("shop_by_brand")
    var shopByBrandList: ArrayList<ShopByBrand>
) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Banner)?: ArrayList(),
        parcel.readParcelable(BestSeller::class.java.classLoader) ?: BestSeller(),
        parcel.createTypedArrayList(ShopByBrand)?: ArrayList(),
    )

    constructor() : this(arrayListOf(),BestSeller(), arrayListOf())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(bannerArrayList)
        parcel.writeParcelable(bestSeller, flags)
        parcel.writeTypedList(shopByBrandList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Home> {
        override fun createFromParcel(parcel: Parcel): Home {
            return Home(parcel)
        }

        override fun newArray(size: Int): Array<Home?> {
            return arrayOfNulls(size)
        }
    }

}
