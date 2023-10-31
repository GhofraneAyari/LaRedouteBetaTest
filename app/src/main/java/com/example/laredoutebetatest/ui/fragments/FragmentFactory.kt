package com.example.laredoutebetatest.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.laredoutebetatest.adapter.ViewPagerAdapter
import com.example.laredoutebetatest.data.model.DataReceiver
import com.example.laredoutebetatest.data.model.ReviewFormResponse



class FragmentFactory(private val activity: FragmentActivity) {
    val fragments = ArrayList<Fragment>()
    private val processedFieldIds = mutableSetOf<String>()

    fun setupViewPager(viewPager: ViewPager2) {
        val viewPagerAdapter = ViewPagerAdapter(fragments, activity.supportFragmentManager, activity.lifecycle)
        viewPager.adapter = viewPagerAdapter
    }

    fun createFragments(data: ReviewFormResponse?) {
        data?.fields?.forEach { field ->
            // Check if the field ID has been processed
            if (!processedFieldIds.contains(field.id)) {
                val fragment = when (field.type) {
                    "BooleanInput" -> BooleanInput()
                    "ImageRatingInput" -> ImageRatingInput()
                    "IntegerInput" -> {
                        val integerInputFragment = IntegerInput()
                        val args = Bundle()
                        args.putString("fieldId", field.id)
                        integerInputFragment.arguments = args
                        integerInputFragment
                    }
                    "SelectInput" -> {
                        val selectInputFragment = SelectInput()
                        val args = Bundle()
                        args.putString("fieldId", field.id)
                        selectInputFragment.arguments = args
                        selectInputFragment
                    }
                    "TextInput" -> TextInput()
                    "TextAreaInput" -> EndForm()
                    else -> EndForm()
                }

                if (fragment is DataReceiver) {
                    fragment.receiveData(field)
                }

                fragments.add(fragment)
                field.id?.let { processedFieldIds.add(it) }
            }
        }
    }
}





