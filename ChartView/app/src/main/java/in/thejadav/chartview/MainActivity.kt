package `in`.thejadav.chartview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chartView.setSizeBasedOnWeight(true)
        val list: ArrayList<ChartEntry> = ArrayList()
        list.add(ChartEntry(30, color = Color.parseColor("#7285A5")))
        list.add(ChartEntry(55, color = Color.parseColor("#C3B091")))
        list.add(ChartEntry(150, color = Color.parseColor("#800000")))
        list.add(ChartEntry(265, color = Color.parseColor("#50C878")))
        chartView.addChartEntries(list)

    }
}
