package `in`.thejadav.chartview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class ChartView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {


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

        val paint = Paint()
        paint.color = Color.CYAN
        paint.style = Paint.Style.FILL

        val center_x: Float
        val center_y: Float
        val oval = RectF()
//        paint.setStyle(Paint.Style.STROKE)

        center_x = width / 2f
        center_y = height / 2f

        oval.set(
            center_x - radius,
            center_y - radius,
            center_x + radius,
            center_y + radius
        )
        canvas.drawArc(oval, 0f, 90f, true, paint)
        paint.color = Color.BLUE
        canvas.drawArc(oval, 90f, 90f, true, paint)
        paint.color = Color.GREEN
        canvas.drawArc(oval, 180f, 90f, true, paint)
        paint.color = Color.YELLOW
        canvas.drawArc(oval, 270f, 90f, true, paint)
    }
}