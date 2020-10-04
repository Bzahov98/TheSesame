package com.paysafe.hackathon.thesesame.ui.anychartGraphs

import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Line
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.Anchor
import com.anychart.enums.ScaleTypes
import com.paysafe.hackathon.thesesame.R
import com.paysafe.hackathon.thesesame.TheSesameApplication.Companion.getAppString
import com.paysafe.hackathon.thesesame.data.model.PortfolioData
import com.paysafe.hackathon.thesesame.ui.anychartGraphs.AnyChartGraphsUtils.Companion.colorizeAxesBackground
import com.paysafe.hackathon.thesesame.ui.anychartGraphs.AnyChartGraphsUtils.Companion.setAxisLabelName
import com.paysafe.hackathon.thesesame.ui.anychartGraphs.AnyChartGraphsUtils.Companion.setSeriesTooltip
import com.paysafe.hackathon.thesesame.ui.anychartGraphs.AnyChartGraphsUtils.Companion.setupAndGetBaseCartesian
import com.paysafe.hackathon.thesesame.ui.anychartGraphs.AnyChartGraphsUtils.Companion.setupTooltipAndInteractivity
import com.paysafe.hackathon.thesesame.ui.anychartGraphs.AnyChartGraphsUtils.Companion.testChartData

class PortfolioChartUtils {
    companion object {

        const val KEY_PRICE_1 = "value"
        const val KEY_PRICE_2 = "value2"
        const val CHART_TYPE_FINANCE = "FINANCE"

        fun createPriceChart(
            weatherStateData: PortfolioData?,
            chartType: String = CHART_TYPE_FINANCE
        ): Cartesian {
            val xAxisName = getAppString(R.string.graphs_default_axis_x_name)
            val yAxisName = getAppString(R.string.graphs_axis_y_name_speed)
            val series1Name = getAppString(R.string.graphs_profit_series_degree)
            val series2Name = getAppString(R.string.graphs_profit_series_degree)
            val unitAbbreviation =
                getAppString(R.string.graphs_money_unit_abbreviation)
            val chartTitle = getAppString(R.string.graphs_price_title)

            val cartesian: Cartesian = setupAndGetBaseCartesian(chartTitle)

            var data: MutableList<DataEntry> = testChartData() // TODO: Debug

//            if (weatherStateData != null) {
//                //data = extractChartDataFromState(weatherStateData, chartType)
//                // currentStateData.hourInfoItemsList.last().
//                //unitAbbreviation = weatherStateData.getUnitAbbreviation()
//            } else {
//                Log.e(TAG, "createwindChart: weatherStateData is null")
//                cartesian.label("No DATA")
//                return cartesian
//            }

            val xAxis = cartesian.xAxis(0)
            val yAxis = cartesian.yAxis(0)
            //val yAxis1 = cartesian.yAxis(1)

            xAxis
                .title(xAxisName)
                .staggerMode(true)
                .staggerMaxLines(2)
            yAxis.title(yAxisName)
            yAxis.setAxisLabelName(unitAbbreviation)
            //yAxis1.setAxisLabelName("%")


//                val percentYAxis =
//                    yAxis1.title(getAppString(R.string.graphs_precipitation_axis_y_namecloud_humid))
//                        .orientation(Orientation.RIGHT)
//                val lineMarker = cartesian.lineMarker(0)
//                    .axis(percentYAxis)
//                    .value(85)
//                    //.layout("85%")
//                    .stroke("#A5B3B3", 1, "5 2", StrokeLineJoin.ROUND, StrokeLineCap.ROUND)


            val set: Set = Set.instantiate()
            set.data(data)

            val price1: Mapping =
                set.mapAs("{ x: 'x', value: '${KEY_PRICE_1}' }") // wind speed - value
            val price2: Mapping =
                set.mapAs("{ x: 'x', value: '${KEY_PRICE_2}' }") // wind degree, 0 - value2



//            val series3 = cartesian.column(column3Data)
//            series3.name("Any")

//                var line = chart.lineMarker();
//                line.value(0);
//                line.stroke("2 red");


            val line: Line = cartesian.line(price1)
            line.name(getAppString(R.string.graphs_axis_y_name_speed))
            line.labels().enabled(false)
            line.legendItem().disabled()

            val line2: Line = cartesian.line(price2)
            line2.name(getAppString(R.string.graphs_axis_y_name_speed2))
            line2.labels().enabled(false)
            line2.legendItem().disabled()

//            val humidityLine: Line = cartesian.line(windDegreeData)
//            humidityLine.name(getAppString(R.string.graphs_precipitation_line_humidity))
//            humidityLine.labels().enabled()
//            val pressureLine: Line = cartesian.line(pressureLineData)
//            pressureLine.name("Pressure, hPa")

//            val series1 = cartesian.column(price1)
            //val series2 = cartesian.column(windDegreeData)

//            series1.name(series1Name)
//            series2.name(series2Name)


            //cartesian.yAxis(0).scale(ScaleTypes.GANTT)

            //tooltipSettings(cartesian, unitAbbreviation)
            setupTooltipAndInteractivity(cartesian)

   //         setSeriesTooltip(series1, Anchor.RIGHT_BOTTOM,"Wind Speed: {%value}$unitAbbreviation , <br> <b>Wind direction: {%$KEY_WIND_DEGREE}</b>")
            //hideSeriesTooltip(line)
//            setSeriesAnchor(series2, Anchor.LEFT_BOTTO    M)
            //setSeriesAnchor(line, Anchor.CENTER)
//            setSeriesAnchor(humidityLine, Anchor.AUTO)
            //

            val log = cartesian.yScale(ScaleTypes.LINEAR_COLOR)
            val yScale = cartesian.yScale()
            //yScale.softMaximum(100)
//            yScale.ticks().interval(10)
//            yScale.minorTicks().interval(2)
            yScale.softMinimum(0)
            colorizeAxesBackground(xAxis)

            return cartesian
        }
    }
}