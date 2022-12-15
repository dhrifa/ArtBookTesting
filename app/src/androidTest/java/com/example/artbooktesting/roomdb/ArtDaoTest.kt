package com.example.artbooktesting.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.artbooktesting.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
class ArtDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dataBase: ArtDataBase
    private lateinit var dao: ArtDao

    @Before
    fun setup(){
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), ArtDataBase::class.java
        ).allowMainThreadQueries().build()

        dao = dataBase.artDao()
    }

    @After
    fun tearDown(){
        dataBase.close()
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