package org.apache.geode.statistics

import java.time.Duration
import java.util.concurrent.TimeUnit

interface StatisticsMeter {
    fun getMetricName(): String
    fun getBaseUnit(): String
}

interface ScalarStatisticsMeter : StatisticsMeter {
    fun increment(value: Double = 1.0)
    fun decrement(value: Double = -1.0)
}

interface TimedStatisticsMeter : StatisticsMeter {
    fun recordValue(amount: Long, timeUnit: TimeUnit = TimeUnit.NANOSECONDS)
    fun recordValue(duration: Duration)
}