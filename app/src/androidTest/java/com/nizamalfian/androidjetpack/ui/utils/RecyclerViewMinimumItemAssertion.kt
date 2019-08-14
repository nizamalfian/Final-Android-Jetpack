package com.nizamalfian.androidjetpack.ui.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.hamcrest.CoreMatchers
import org.junit.Assert

/**
 * Created by L
 *
 * on 8/13/2019
 */
class RecyclerViewMinimumItemAssertion(private val expectedMinimumItem:Int): ViewAssertion {
    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if(noViewFoundException!=null)
            throw noViewFoundException
        val recyclerView=view as RecyclerView
        val adapter=recyclerView.adapter
        Assert.assertNotNull(adapter)
        adapter?.let{
            Assert.assertThat(true, CoreMatchers.`is`(it.itemCount>=expectedMinimumItem))
        }
    }
}