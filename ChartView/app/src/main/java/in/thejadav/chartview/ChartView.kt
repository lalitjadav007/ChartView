package `in`.thejadav.chartview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat


class ChartView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private val paint: Paint = Paint()
    private val rect: RectF = RectF()
    private var changeSizeWithWeight: Boolean = false
    private var chartEntries: ArrayList<ChartEntry> = ArrayList()
    private var totalSum: Float = 0f
    private var totalWeight: Float = 0f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        synchronized(this) {
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

            if(chartEntries.isEmpty()){
                return
            }

            val partSpace = 360 / chartEntries.size

            for ((i, chartEntry) in chartEntries.withIndex()){
                if(chartEntry.color != 0){
                    paint.color = chartEntry.color
                } else {
                    paint.color = Color.parseColor(ChartHelper.generateColor())
                }

                if(changeSizeWithWeight){
                    rect.set(center_x - (radius - totalSum * (totalWeight - chartEntry.weight)),
                        center_y - (radius - totalSum * (totalWeight - chartEntry.weight)),
                        center_x + (radius - totalSum * (totalWeight - chartEntry.weight)),
                        center_y + (radius - totalSum * (totalWeight - chartEntry.weight)))
                }
                canvas.drawArc(rect, i * partSpace.toFloat(), partSpace.toFloat(), true, paint)


                rect.set(center_x - (radius/3),
                    center_y - (radius/3),
                    center_x + (radius/3),
                    center_y + (radius/3))
                paint.color = ContextCompat.getColor(context, R.color.semiTransparent)
                canvas.drawArc(rect, i * partSpace.toFloat(), partSpace.toFloat(), true, paint)

//                if(i == 0){
//                    rect.set(center_x - (150),
//                        center_y - (150),
//                        center_x + (150),
//                        center_y + (150))
//                    paint.color = ContextCompat.getColor(context, R.color.semiTransparent)
//                    canvas.drawArc(rect, i * partSpace.toFloat(), partSpace.toFloat(), true, paint)
//                }
//
//                if(i == 1){
//                    rect.set(center_x - (170),
//                        center_y - (170),
//                        center_x + (170),
//                        center_y + (170))
//                    paint.color = ContextCompat.getColor(context, R.color.semiTransparent)
//                    canvas.drawArc(rect, i * partSpace.toFloat(), partSpace.toFloat(), true, paint)
//                }
            }


            //Draw cneter text
            rect.set(center_x - (radius/4),
                center_y - (radius/4),
                center_x + (radius/4),
                center_y + (radius/4))
            paint.color = Color.WHITE
            canvas.drawArc(rect, 0f, 360f, true, paint)

        }
    }

    fun setSizeBasedOnWeight(changeSizeWithWeight: Boolean) {
        this.changeSizeWithWeight = changeSizeWithWeight
    }

    fun addChartEntries(entries: Array<ChartEntry>) {
        chartEntries.clear()
        chartEntries.addAll(entries)
        calculateEntriesWeight()
    }

    private fun calculateEntriesWeight() {
//        totalWeight = chartEntries.sumBy { it.weight }.toFloat()
        totalWeight = chartEntries.maxBy { it.weight }!!.weight.toFloat()
        totalSum = 100 / totalWeight
    }

    fun addChartEntries(entries: ArrayList<ChartEntry>) {
        chartEntries.clear()
        chartEntries.addAll(entries)
        calculateEntriesWeight()
    }
}