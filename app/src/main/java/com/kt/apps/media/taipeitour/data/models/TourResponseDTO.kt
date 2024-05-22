package com.kt.apps.media.taipeitour.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TourResponseDTO(
    @SerializedName("data")
    val tourList: List<TourDTO>,
    @SerializedName("total")
    val total: Int
) : Parcelable

@Parcelize
data class TourDTO(
    val address: String,
    val category: List<Category>,
    val distric: String,
    val elong: Double,
    val email: String,
    val facebook: String,
    val fax: String,
    val files: List<File>,
    val friendly: List<Friendly>,
    val id: Int,
    val images: List<Image>,
    val introduction: String,
    val links: List<Link>,
    val modified: String,
    val months: String,
    val name: String,
    val name_zh: String? = null,
    val nlat: Double,
    val official_site: String,
    val open_status: Int,
    val open_time: String,
    val remind: String,
    val service: List<Service>,
    val staytime: String,
    val target: List<Target>,
    val tel: String,
    val ticket: String,
    val url: String,
    val zipcode: String
) : Parcelable

@Parcelize
data class Category(
    val id: Int,
    val name: String
) : Parcelable

@Parcelize
data class Image(
    val ext: String,
    val src: String,
    val subject: String
) : Parcelable

@Parcelize
data class Link(
    val src: String,
    val subject: String
) : Parcelable

@Parcelize
data class Service(
    val id: Int,
    val name: String
) : Parcelable

@Parcelize
data class Target(
    val id: Int,
    val name: String
) : Parcelable

@Parcelize
data class Friendly(
    val id: Int,
    val name: String
) : Parcelable


@Parcelize
data class File(
    val ext: String,
    val src: String,
    val subject: String,
    val description: String
) : Parcelable
