package app.interactive.academy.utils

import androidx.core.content.ContextCompat
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.res.ResourcesCompat
import app.interactive.academy.R


/**
 * Created by nizamalfian on 27/07/2019.
 */
class MyButton : AppCompatButton {

    private var enabledBackground: Drawable? = null
    private var disabledBackground: Drawable? = null
    private var textColor_: Int = 0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    protected override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        background = if (isEnabled) enabledBackground else disabledBackground
        setTextColor(textColor_)
        textSize = 12f
        gravity = Gravity.CENTER
    }

    private fun init() {
        val resources = resources
        enabledBackground = ResourcesCompat.getDrawable(resources,R.drawable.bg_button,null)
        disabledBackground = ResourcesCompat.getDrawable(resources,R.drawable.bg_button_disable,null)
        textColor_ = ContextCompat.getColor(context, android.R.color.background_light)
    }
}