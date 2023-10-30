package com.example.laredoutebetatest.ui.theme.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2


class VPadapter(
    private val fragments: List<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

//    fun moveToNextFragment(currentPosition: Int, viewPager: ViewPager2) {
//        if (currentPosition < itemCount - 1) {
//            // Increment the currentPosition
//            val nextPosition = currentPosition + 1
//
//            // Set the ViewPager2 to the next position
//            viewPager.currentItem = nextPosition
//        }
//    }

}



