package com.paysafe.hackathon.thesesame.ui.anychartGraphs

import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Base
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.TooltipDisplayMode
import com.anychart.enums.TooltipPositionMode

class AnyChartGraphsUtils {
    companion object{
        class CustomDataEntry(
            x: String?,
            value: Number = 0,
            value2: Number? = 0,
            value3: Number? = 0,
            value4: Number? = 0,
            value5: Number? = 0,
            stringValue: String = ""
        ) :

            ValueDataEntry(x, value.toInt() ) {
            init {
                setValue("value2", value2)
                setValue("value3", value3)
                setValue("value4", value4)
                setValue("value5", value5)
                setValue("stringValue", stringValue)
            }
        }

        fun setupAndGetBaseCartesian(chartTitle: String): Cartesian {
            val cartesian: Cartesian = AnyChart.cartesian()
            cartesian.autoRedraw(true)
            cartesian.dataArea(true)
            cartesian.animation(true)
            cartesian.crosshair(false)
            cartesian.legend(true)
            cartesian.title(chartTitle)
            return cartesian
        }

        fun com.anychart.core.axes.Linear.setAxisLabelName(unitAbbreviation: String){
            this.labels().format("{%Value} $unitAbbreviation")
        }

        fun colorizeAxesBackground(axis: com.anychart.core.axes.Linear) {
            val xLabelsBackground = axis.labels().background();
            xLabelsBackground.enabled(true);
            xLabelsBackground.stroke("#cecece");
            xLabelsBackground.cornerType("round");
            xLabelsBackground.corners(5);
        }

        fun hideSeriesTooltip(series: Base){
            val tooltip = series.tooltip();
            tooltip.enabled(false)
        }

        fun setSeriesTooltip(series: Base, positionAnchor: Anchor, formatString :String= "", title: String? = null) {
            val tooltip1 = series.tooltip();
            tooltip1.anchor(positionAnchor);
            when {
                formatString != "" -> {
                    tooltip1.useHtml(true);
                    tooltip1.format(formatString)
                }
                title != null -> {
                    tooltip1.title(title)
                }
                else ->
                    tooltip1.title(false)
            };

            tooltip1.separator(false);
        }
        fun setupTooltipAndInteractivity(cartesian: Cartesian) {
            cartesian.tooltip()
                .positionMode(TooltipPositionMode.POINT)
                .displayMode(TooltipDisplayMode.SEPARATED)
            cartesian.interactivity().hoverMode(HoverMode.BY_X).selectionMode()
        }
        fun testChartData(): MutableList<DataEntry> {
            val data: MutableList<DataEntry> = ArrayList()
            data.add(CustomDataEntry("02.12.2000", 24, 23, 5))
            data.add(CustomDataEntry("03.12.2000", 21, 22, 3))
            data.add(CustomDataEntry("04.12.2000", 12, 31, 6))
            data.add(CustomDataEntry("05.12.2000", 23, 1, 2))
            data.add(CustomDataEntry("06.12.2000", 22, 5, 1))
            data.add(CustomDataEntry("07.12.2000", 54, 8, 1))
            data.add(CustomDataEntry("08.12.2000", 45, 21, 1))
            data.add(CustomDataEntry("09.12.2000", 22, 12, 1))
            data.add(CustomDataEntry("10.12.2000", 12, 34, 1))
            data.add(CustomDataEntry("11.12.2000", 10, 44, 1))
            return data
        }
    }
}