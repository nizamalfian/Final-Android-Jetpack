package app.interactive.academy.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


/**
 * Created by nizamalfian on 13/07/2019.
 */
class LiveDataTestUtil {
    companion object{
        fun <T:Any> getValue(liveData:LiveData<T>):T{
            val data= arrayOf<Any>(1)

            val latch:CountDownLatch = CountDownLatch(1).also{
                val observer = object : Observer<T> {
                    override fun onChanged(o: T) {
                        data[0] = o
                        it.countDown()
                        liveData.removeObserver(this)
                    }
                }
                liveData.observeForever(observer)
            }
            try{
                latch.await(2,TimeUnit.SECONDS)
            }catch(e:InterruptedException){
                e.printStackTrace()
            }

            return data[0] as T
        }
    }
}