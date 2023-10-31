package com.example.laredoutebetatest.ui.fragments


import TextInput
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.laredoutebetatest.adapter.ViewPagerAdapter
import com.example.laredoutebetatest.data.model.DataReceiver
import com.example.laredoutebetatest.data.model.ReviewFormResponse


class FragmentFactory(private val activity: FragmentActivity) {
    private val fragments = ArrayList<Fragment>()

    fun setupViewPager(viewPager: ViewPager2) {
        val viewPagerAdapter = ViewPagerAdapter(fragments, activity.supportFragmentManager, activity.lifecycle)
        viewPager.adapter = viewPagerAdapter
    }

    fun createFragments(data: ReviewFormResponse?) {
        data?.fields?.forEach { field ->
            val fragment = when (field.type) {
                "BooleanInput" -> BooleanInput()
                "ImageRatingInput" -> ImageRatingInput()
                "IntegerInput" -> IntegerInput()
                "SelectInput" -> SelectInput()
                "TextInput" -> TextInput()
                else -> EndForm()
            }

            if (fragment is DataReceiver) {
                fragment.receiveData(field)
            }

            fragments.add(fragment)
        }
    }
}

