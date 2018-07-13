package org.apache.geode.statistics.micrometer

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.binder.MeterBinder
import org.apache.geode.statistics.StatisticsMeterGroup
import org.apache.geode.statistics.StatisticsMeter

abstract class MicrometerMeterGroup(private val groupName: String) : StatisticsMeterGroup, MeterBinder {
    private val registeredMeters = mutableListOf<MicrometerStatisticMeter>()

    abstract fun initializeStaticMeters()

    override fun getMeterGroupName(): String = groupName
    override fun bindTo(registry: MeterRegistry) {
        registeredMeters.forEach { it.register(registry) }
    }

    protected fun registerMeter(meter: MicrometerStatisticMeter) {
        registeredMeters.add(meter)
    }
}