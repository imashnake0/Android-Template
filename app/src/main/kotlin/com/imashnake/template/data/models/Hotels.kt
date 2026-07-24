package com.imashnake.template.data.models

data class UiHotels(
    val status: String,

    val numResults: Long,

    val location: String,
    val hotels: List<UiHotel>
)

data class UiHotel(
    val hotel: String,
    val rooms: Long,
    val rating: String,

    val checkIn: String,

    val pricePerNight: String
)

data class Hotels (
    val status: String,

    val numResults: Long,

    val location: String,
    val hotels: List<Hotel>
)

data class Hotel (
    val hotel: String,
    val rooms: Long,
    val rating: String,

    val checkIn: String,

    val pricePerNight: Long
)