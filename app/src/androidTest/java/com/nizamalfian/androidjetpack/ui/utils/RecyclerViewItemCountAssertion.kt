package com.nizamalfian.androidjetpack.ui.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Assert.assertNotNull as assertNotNull1

/**
 * Created by nizamalfian on 01/07/2019.
 */
class RecyclerViewItemCountAssertion(private val expectedCount:Int):ViewAssertion {
    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if(noViewFoundException!=null)
            throw noViewFoundException
        val recyclerView=view as RecyclerView
        val adapter=recyclerView.adapter
        assertNotNull1(adapter)
        assertThat(adapter?.itemCount,`is`(expectedCount))
    }
}