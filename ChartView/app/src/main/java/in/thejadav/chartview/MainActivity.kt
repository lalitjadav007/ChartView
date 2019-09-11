package `in`.thejadav.chartview

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
        list.add(ChartEntry(30, color = ContextCompat.getColor(this, R.color.colorAccent)))
        list.add(ChartEntry(55))
        list.add(ChartEntry(150))
        list.add(ChartEntry(265))
        chartView.addChartEntries(list)

    }
}
