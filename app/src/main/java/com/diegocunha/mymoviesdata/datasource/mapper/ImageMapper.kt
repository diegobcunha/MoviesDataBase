package com.diegocunha.mymoviesdata.datasource.mapper

class ImageMapper {

    operator fun invoke(imagePath: String): String {
        return "https://image.tmdb.org/t/p/w500/$imagePath"
    }
}