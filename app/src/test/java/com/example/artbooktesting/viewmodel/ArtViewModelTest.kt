package com.example.artbooktesting.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.artbooktesting.MainCoroutineRule
import com.example.artbooktesting.getOrAwaitValueTest
import com.example.artbooktesting.repo.FakeArtRepository
import com.example.artbooktesting.util.Status
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup(){
        //test doubles

        viewModel = ArtViewModel(FakeArtRepository())
    }

    @Test
    fun `insert art without year returns error`(){
        //given
        viewModel.makeArt("Mona Liza", "Da Vinci", "")

        //when
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()

        //then
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `insert art without name returns error`(){
        //given
        viewModel.makeArt("", "Da Vinci", "1900")

        //when
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()

        //then
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `insert art without artist name returns error`(){
        //given
        viewModel.makeArt("Mona Liza", "", "1900")

        //when
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()

        //then
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}