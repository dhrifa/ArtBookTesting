package com.example.artbooktesting.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.artbooktesting.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database: ArtDataBase

    //private lateinit var database: ArtDataBase used before dependency injection
    private lateinit var dao: ArtDao

    @Before
    fun setup(){
        /*
        used before DI
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), ArtDataBase::class.java
        ).allowMainThreadQueries().build()
        */

        hiltRule.inject()

        dao = database.artDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertArtTesting()= runBlocking{
        //given
        val exArt = Art("mona liza", "da vinci", 1900, "url", 1)
       //when
        dao.insertArt(exArt)

        //then
        val list = dao.observeArts().getOrAwaitValue()
        assertThat(list).contains(exArt)

    }

    @Test
    fun deleteArtTesting() = runBlocking{
       //given
        val exArt = Art("mona liza", "da vinci", 1900, "url", 1)
        dao.insertArt(exArt)
        //when
        dao.deleteArt(exArt)
        //then
        val list = dao.observeArts().getOrAwaitValue()
        assertThat(list).doesNotContain(exArt)
    }

}