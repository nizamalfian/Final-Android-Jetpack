package app.interactive.academy.utils

import androidx.test.espresso.idling.CountingIdlingResource

/**
 * Created by nizamalfian on 13/07/2019.
 */
class EspressoIdlingResource {
    companion object {
        private const val RESOURCE = "GLOBAL"
        private val esspressoIdlingResource = CountingIdlingResource(RESOURCE)

        fun increment() {
            esspressoIdlingResource.increment()
        }

        fun decrement() {
            if (!esspressoIdlingResource.isIdleNow)
                esspressoIdlingResource.decrement()
        }

        fun getEspressoIdlingResourceForMainActivity() =
            esspressoIdlingResource
    }
}