package com.example.laredoutebetatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.laredoutebetatest.data.model.ReviewFormResponse
import com.example.laredoutebetatest.ui.theme.fragments.BooleanInputFragment
import me.relex.circleindicator.CircleIndicator
import me.relex.circleindicator.CircleIndicator3


class FeedbackActivity : AppCompatActivity() {
    var reviewFormData: ReviewFormResponse? = null


    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val newFragment: Fragment = BooleanInputFragment()

        val ft = this.supportFragmentManager.beginTransaction()


        if (savedInstanceState == null) {

            ft.add(R.id.fragmentGroup, newFragment).commit()
        }

//        potToList()
//
//        val view_pager2 = findViewById<ViewPager2>(R.id.view_pager2)
//
//        view_pager2.adapter = ViewPagerAdapter(titleList,imagesList,descList)
//        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//
//        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
//        indicator.setViewPager(view_pager2)



//        val viewPager = findViewById<ViewPager>(R.id.viewPager)
//        val questionTextView = findViewById<TextView>(R.id.questionTextView)

//// Assuming you have a list of ReviewFields in your ReviewFormResponse
//        val reviewFields = reviewFormResponse.fields
//
//// Assuming you want to populate the questionTextView with the first ReviewField's title
//        if (reviewFields.isNotEmpty()) {
//            val firstReviewField = reviewFields[0] // You can select any specific ReviewField
//
//            // Set the questionTextView's text to the title of the selected ReviewField
//            questionTextView.text = firstReviewField.title
//
//            // Create a ViewPager adapter and set it for dynamic fragment population
//            val adapter = ReviewFieldPagerAdapter(supportFragmentManager, reviewFields)
//            viewPager.adapter = adapter
//        }






    }

    private fun addToList(title: String, description: String, image: Int) {

        titleList.add(title)
        descList.add(description)
        imagesList.add(image)

    }

    private fun potToList() {
        for(i in 1..2) {
            addToList("Title $i", "Description $i",R.mipmap.ic_launcher_round )
        }
        for(i in 3..10) {
            addToList("Title $i", "Description $i",R.mipmap.ic_launcher_round )
        }
    }

}