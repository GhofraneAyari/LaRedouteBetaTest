package com.example.laredoutebetatest

import com.example.laredoutebetatest.data.model.NameValue
import com.example.laredoutebetatest.data.model.ReviewFormResponse
import com.example.laredoutebetatest.data.networking.ApiService
import org.junit.Test

import org.junit.Assert.*

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiServiceTest {
    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


@Test
fun testGetFormData() {

    val responseData = ReviewFormResponse(
        "https://cdn.laredoute.com/generic/marketingwarehouse/1/4f8c30711d9eb.png",
        "https://cdn.laredoute.com/products/b/8/0/b804919f0489c6f2bd5609a4404098f2.jpg",
        "Pull encolure v en fine maille cachemire",
        listOf(
            ReviewFormResponse.ReviewField(
                "rating",
                "ImageRatingInput",
                "Quelle note donneriez-vous ?",
                "",
                listOf(
                    NameValue("Très Bon produit 🤩", "5"),
                    NameValue("Bon produit 👍", "4"),
                    NameValue("Moyen 😐", "3"),
                    NameValue("Mauvais 😕", "2"),
                    NameValue("Très mauvais 😟", "1")
                ),
                "Valider"
            ),

            )
    )

    val call = Mockito.mock(Call::class.java) as Call<ReviewFormResponse>
    Mockito.`when`(apiService.getFormData()).thenReturn(call)

    Mockito.`when`(call.execute()).thenReturn(Response.success(responseData))

    val result = apiService.getFormData().execute()

        // Assert the result
        assert(result.isSuccessful)
        assert(result.body() == responseData)
    }

    @Test
    fun testPostUserInputData() {

        val inputData = listOf(
            NameValue("key1", "value1"),
            NameValue("key2", "value2")
        )

        val responseData = listOf(
            NameValue("responseKey1", "responseValue1"),
            NameValue("responseKey2", "responseValue2")
        )

        val call = Mockito.mock(Call::class.java) as Call<List<NameValue>>
        Mockito.`when`(apiService.postUserInputData(inputData)).thenReturn(call)

        Mockito.`when`(call.execute()).thenReturn(Response.success(responseData))

        val result = apiService.postUserInputData(inputData).execute()


        assert(result.isSuccessful)
        assert(result.body() == responseData)
    }
    }
