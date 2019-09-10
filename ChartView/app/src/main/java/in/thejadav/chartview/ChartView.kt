package `in`.thejadav.chartview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class ChartView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private var partCount = 0
    private val paint: Paint = Paint()
    private val rect: RectF = RectF()
    private var colors: ArrayList<Int> = ArrayList()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width
        val height = height
        val radius: Float

        radius = if (width > height) {
            height.toFloat() / 2
        } else {
            width.toFloat() / 2
        }
        paint.style = Paint.Style.FILL

        val center_x: Float
        val center_y: Float
//        paint.setStyle(Paint.Style.STROKE)

        center_x = width / 2f
        center_y = height / 2f

        rect.set(
            center_x - radius,
            center_y - radius,
            center_x + radius,
            center_y + radius
        )

        if(partCount <= 0){
            return
        }

        val partSpace = 360 / partCount

        for (i in 0..partCount){
            val color = if(i < colors.size){
                colors[i]
            } else {
                Color.parseColor(ChartHelper.generateColor())
            }
            paint.color = color
            canvas.drawArc(rect, i * partSpace.toFloat(), partSpace.toFloat(), true, paint)
        }
    }

    fun setPartCount(parts: Int) {
        this.partCount = parts
        invalidate()
    }

    fun setPartColors(colors: Array<Int>) {
        this.colors.clear()
        this.colors.addAll(colors)
    }
}