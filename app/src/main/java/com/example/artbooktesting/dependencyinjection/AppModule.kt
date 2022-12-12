package com.example.artbooktesting.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.example.artbooktesting.api.RetrofitApi
import com.example.artbooktesting.roomdb.ArtDataBase
import com.example.artbooktesting.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponentManager::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, ArtDataBase::class.java, "ArtBookDB"
    ).build()

    @Singleton
    @Provides
    fun injectDao(dataBase: ArtDataBase) = dataBase.artDao()

    @Singleton
    @Provides
    fun injectRetrofitAPI() : RetrofitApi{
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitApi::class.java)
    }
}