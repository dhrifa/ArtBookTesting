package com.example.artbooktesting.repo

import androidx.lifecycle.LiveData
import com.example.artbooktesting.model.ImageResponse
import com.example.artbooktesting.roomdb.Art
import com.example.artbooktesting.util.Resource

interface ArtRepositoryInterface {

//    fun test(a:Array<Int>): Int {
//     return   a.sum()
    //    return if (a in 1..1000 && b in 1..1000) a + b else -1
//    }

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getArt(): LiveData<List<Art>>

    suspend fun searchImage(imageString: String): Resource<ImageResponse>

}