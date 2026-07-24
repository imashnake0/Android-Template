package com.imashnake.template.data

import com.imashnake.template.data.models.Hotel
import com.imashnake.template.data.models.Hotels
import com.imashnake.template.data.models.UiHotel
import com.imashnake.template.data.models.UiHotels
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

interface HotelsRepository {
    fun getHotels(): Flow<Resource<UiHotels>>
}

class HotelsRepositoryImpl : HotelsRepository {
    override fun getHotels() = flow<Resource<UiHotels>> {
        emit(Resource.Success(mockKtData.toUi()))
    }.catch {
        emit(Resource.Error(message = it.message))
    }
}

fun Hotels.toUi(): UiHotels {
    return UiHotels(
        status = this.status,
        numResults = this.numResults,
        location = this.location,
        hotels = this.hotels.sortedBy { it.pricePerNight }.map {
            UiHotel(
                hotel = it.hotel,
                rooms = it.rooms,
                rating = it.rating,
                checkIn = it.checkIn,
                pricePerNight = $$"$$${it.pricePerNight}/night"
            )
        }
    )
}

sealed class Resource<T> private constructor(
    open val data: T?,
    open val message: String? = null
) {
    data class Success<T>(override val data: T) : Resource<T>(data)
    class Loading<T> : Resource<T>(data = null)
    data class Error<T>(override val message: String?) : Resource<T>(data = null, message = message)
}

val mockKtData = Hotels(
    status = "OK",
    numResults = 3,
    location = "New York",
    hotels = List(150) {
        Hotel(
            hotel = "Hotel ${it + 1}",
            rooms = it + 5L,
            rating = "${it + 2}",
            checkIn = "2026-10-2$it",
            pricePerNight = 1500L - 10 * it
        )
    }
)

val mockData = """
    {
      "status": "OK",
      "num_results": 8,
      "location": "New York",
      "hotels": [
        {
          "hotel": "Hilton Midtown",
          "rooms": 1,
          "rating": "4.5",
          "check_in": "2026-01-24",
          "price_per_night": 320
        },
        {
          "hotel": "Marriott Marquis",
          "rooms": 2,
          "rating": "4.6",
          "check_in": "2026-01-24",
          "price_per_night": 350
        },
        {
          "hotel": "Hyatt Grand Central",
          "rooms": 1,
          "rating": "4.3",
          "check_in": "2026-01-24",
          "price_per_night": 280
        },
        {
          "hotel": "The Plaza Hotel",
          "rooms": 1,
          "rating": "4.8",
          "check_in": "2026-01-24",
          "price_per_night": 620
        },
        {
          "hotel": "Sheraton Times Square",
          "rooms": 3,
          "rating": "4.1",
          "check_in": "2026-01-24",
          "price_per_night": 260
        },
        {
          "hotel": "InterContinental Barclay",
          "rooms": 2,
          "rating": "4.7",
          "check_in": "2026-01-24",
          "price_per_night": 340
        },
        {
          "hotel": "Pod Times Square",
          "rooms": 1,
          "rating": "4.0",
          "check_in": "2026-01-24",
          "price_per_night": 180
        },
        {
          "hotel": "Arlo SoHo",
          "rooms": 2,
          "rating": "4.2",
          "check_in": "2026-01-24",
          "price_per_night": 230
        }
      ]
    }
""".trimIndent()
