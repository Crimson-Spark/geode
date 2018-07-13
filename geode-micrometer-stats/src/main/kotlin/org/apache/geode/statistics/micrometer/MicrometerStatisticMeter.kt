package org.apache.geode.statistics.micrometer

import io.micrometer.core.instrument.*
import org.apache.geode.statistics.ScalarStatisticsMeter
import org.apache.geode.statistics.TimedStatisticsMeter
import java.time.Duration
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.LongAdder

interface MicrometerStatisticMeter {
    fun register(meterRegistry: MeterRegistry,tags: Iterable<Tag> = emptyList())
}

data class GaugeStatisticMeter(val meterName: String,
                               val description: String,
                               private val unit: String = "") : ScalarStatisticsMeter, MicrometerStatisticMeter {

    private lateinit var meter: Gauge
    private val backingValue: LongAdder = LongAdder()

    override fun getBaseUnit(): String = unit
    override fun getMetricName(): String = meterName

    override fun register(registry: MeterRegistry,tags: Iterable<Tag>) {
        meter = Gauge.builder(meterName, backingValue) { backingValue.toDouble() }
                .description(description).baseUnit(unit).tags(tags).register(registry)
    }

    override fun increment(value: Double) {
        backingValue.add(value.toLong())
    }

    override fun decrement(value: Double) {
        backingValue.add(value.toLong())
    }

    fun overrideValue(value: Double) {
        backingValue.reset()
        backingValue.add(value.toLong())
    }
}

data class CounterStatisticMeter(val meterName: String,
                                 val description: String,
                                 private val unit: String = "") : ScalarStatisticsMeter, MicrometerStatisticMeter {

    private lateinit var meter: Counter

    override fun getBaseUnit(): String = unit
    override fun getMetricName(): String = meterName

    override fun register(registry: MeterRegistry,tags: Iterable<Tag>) {
        meter = Counter.builder(meterName)
                .description(description).tags(tags).baseUnit(unit).register(registry)
    }

    override fun increment(double: Double) {
        meter.increment(double)
    }

    override fun decrement(double: Double) {
        meter.increment(double)
    }
}

data class TimerStatisticMeter(val meterName: String,
                               val description: String,
                               private val unit: String = "") : TimedStatisticsMeter, MicrometerStatisticMeter {

    private lateinit var meter: Timer

    override fun getBaseUnit(): String = unit
    override fun getMetricName(): String = meterName

    override fun register(registry: MeterRegistry,tags: Iterable<Tag>) {
        meter = Timer.builder(meterName)
                .description(description).tags(tags).register(registry)
    }

    override fun recordValue(amount: Long, timeUnit: TimeUnit) {
        meter.record(amount, timeUnit)
    }

    override fun recordValue(duration: Duration) {
        meter.record(duration)
    }
}