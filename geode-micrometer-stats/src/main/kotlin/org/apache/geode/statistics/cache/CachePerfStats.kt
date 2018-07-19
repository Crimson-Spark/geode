package org.apache.geode.statistics.cache

import org.apache.geode.distributed.internal.PoolStatHelper
import org.apache.geode.distributed.internal.QueueStatHelper
import org.apache.geode.statistics.internal.micrometer.CounterStatisticMeter
import org.apache.geode.statistics.internal.micrometer.GaugeStatisticMeter
import org.apache.geode.statistics.internal.micrometer.MicrometerMeterGroup
import org.apache.geode.statistics.internal.micrometer.TimerStatisticMeter

class CachePerfStats : MicrometerMeterGroup("CachePerfStats") {

    val cacheListenerCallsInProgressDesc =
            "Current number of threads doing a cache listener call."
    val cacheListenerCallsCompletedDesc =
            "Total number of times a cache listener call has completed."
    val cacheListenerCallTimeDesc = "Total time spent doing cache listener calls."
    val getInitialImagesInProgressDesc =
            "Current number of getInitialImage operations currently in progress."
    val getInitialImagesCompletedDesc =
            "Total number of times getInitialImages (both delta and full GII) initiated by this cache have completed."
    val deltaGetInitialImagesCompletedDesc =
            "Total number of times delta getInitialImages initiated by this cache have completed."
    val getInitialImageTimeDesc =
            "Total time spent doing getInitialImages for region creation."
    val getInitialImageKeysReceivedDesc =
            "Total number of keys received while doing getInitialImage operations."
    val regionsDesc = "The current number of regions in the cache."
    val partitionedRegionsDesc = "The current number of partitioned regions in the cache."
    val destroysDesc =
            "The total number of times a cache object entry has been destroyed in this cache."
    val updatesDesc =
            "The total number of updates originating remotely that have been applied to this cache."
    val updateTimeDesc = "Total time spent performing an update."
    val invalidatesDesc =
            "The total number of times an existing cache object entry value in this cache has been invalidated"
    val getsDesc =
            "The total number of times a successful get has been done on this cache."
    val createsDesc = "The total number of times an entry is added to this cache."
    val putsDesc =
            "The total number of times an entry is added or replaced in this cache as a result of a local operation (put() create() or get() which results in load, netsearch, or netloading a value). Note that this only counts puts done explicitly on this cache. It does not count updates pushed from other caches."
    val putTimeDesc =
            "Total time spent adding or replacing an entry in this cache as a result of a local operation.  This includes synchronizing on the map, invoking cache callbacks, sending messages to other caches and waiting for responses (if required)."
    val putallsDesc =
            "The total number of times a map is added or replaced in this cache as a result of a local operation. Note that this only counts putAlls done explicitly on this cache. It does not count updates pushed from other caches."
    val putallTimeDesc =
            "Total time spent replacing a map in this cache as a result of a local operation.  This includes synchronizing on the map, invoking cache callbacks, sending messages to other caches and waiting for responses (if required)."
    val removeAllsDesc =
            "The total number of removeAll operations that originated in this cache. Note that this only counts removeAlls done explicitly on this cache. It does not count removes pushed from other caches."
    val removeAllTimeDesc =
            "Total time spent performing removeAlls that originated in this cache. This includes time spent waiting for the removeAll to be done in remote caches (if required)."
    val getTimeDesc =
            "Total time spent doing get operations from this cache (including netsearch and netload)"
    val eventQueueSizeDesc = "The number of cache events waiting to be processed."
    val eventQueueThrottleTimeDesc =
            "The total amount of time, in nanoseconds, spent delayed by the event queue throttle."
    val eventQueueThrottleCountDesc =
            "The total number of times a thread was delayed in adding an event to the event queue."
    val eventThreadsDesc = "The number of threads currently processing events."
    val missesDesc =
            "Total number of times a get on the cache did not find a value already in local memory. The number of hits (i.e. gets that did not miss) can be calculated by subtracting misses from gets."
    val queryExecutionsDesc = "Total number of times some query has been executed"
    val queryExecutionTimeDesc = "Total time spent executing queries"
    val queryResultsHashCollisionsDesc =
            "Total number of times an hash code collision occurred when inserting an object into an OQL result set or rehashing it"
    val queryResultsHashCollisionProbeTimeDesc =
            "Total time spent probing the hashtable in an OQL result set due to hash code collisions, includes reads, writes, and rehashes"
    val partitionedRegionOQLQueryRetriesDesc =
            "Total number of times an OQL Query on a Partitioned Region had to be retried"
    val txSuccessLifeTimeDesc =
            "The total amount of time, in nanoseconds, spent in a transaction before a successful commit. The time measured starts at transaction begin and ends when commit is called."
    val txFailedLifeTimeDesc =
            "The total amount of time, in nanoseconds, spent in a transaction before a failed commit. The time measured starts at transaction begin and ends when commit is called."
    val txRollbackLifeTimeDesc =
            "The total amount of time, in nanoseconds, spent in a transaction before an explicit rollback. The time measured starts at transaction begin and ends when rollback is called."
    val txCommitsDesc = "Total number times a transaction commit has succeeded."
    val txFailuresDesc = "Total number times a transaction commit has failed."
    val txRollbacksDesc =
            "Total number times a transaction has been explicitly rolled back."
    val txCommitTimeDesc =
            "The total amount of time, in nanoseconds, spent doing successful transaction commits."
    val txFailureTimeDesc =
            "The total amount of time, in nanoseconds, spent doing failed transaction commits."
    val txRollbackTimeDesc =
            "The total amount of time, in nanoseconds, spent doing explicit transaction rollbacks."
    val txCommitChangesDesc = "Total number of changes made by committed transactions."
    val txFailureChangesDesc = "Total number of changes lost by failed transactions."
    val txRollbackChangesDesc =
            "Total number of changes lost by explicit transaction rollbacks."
    val txConflictCheckTimeDesc =
            "The total amount of time, in nanoseconds, spent doing conflict checks during transaction commit"
    val reliableQueuedOpsDesc =
            "Current number of cache operations queued for distribution to required roles."
    val reliableQueueSizeDesc =
            "Current size in megabytes of disk used to queue for distribution to required roles."
    val reliableQueueMaxDesc =
            "Maximum size in megabytes allotted for disk usage to queue for distribution to required roles."
    val reliableRegionsDesc = "Current number of regions configured for reliability."
    val reliableRegionsMissingDesc =
            "Current number regions configured for reliability that are missing required roles."
    val reliableRegionsQueuingDesc =
            "Current number regions configured for reliability that are queuing for required roles."
    val reliableRegionsMissingFullAccessDesc =
            "Current number of regions configured for reliablity that are missing require roles with full access"
    val reliableRegionsMissingLimitedAccessDesc =
            "Current number of regions configured for reliablity that are missing required roles with Limited access"
    val reliableRegionsMissingNoAccessDesc =
            "Current number of regions configured for reliablity that are missing required roles with No access"
    val clearsDesc = "The total number of times a clear has been done on this cache."
    val nonSingleHopsDesc =
            "Total number of times client request observed more than one hop during operation."
    val metaDataRefreshCountDesc =
            "Total number of times the meta data is refreshed due to hopping observed."
    val conflatedEventsDesc =
            "Number of events not delivered due to conflation.  Typically this means that the event arrived after a later event was already applied to the cache."
    val tombstoneCountDesc =
            "Number of destroyed entries that are retained for concurrent modification detection"
    val tombstoneGCCountDesc =
            "Number of garbage-collections performed on destroyed entries"
    val tombstoneOverhead1Desc =
            "Amount of memory consumed by destroyed entries in replicated or partitioned regions"
    val tombstoneOverhead2Desc =
            "Amount of memory consumed by destroyed entries in non-replicated regions"
    val clearTimeoutsDesc =
            "Number of timeouts waiting for events concurrent to a clear() operation to be received and applied before performing the clear()"
    val deltaUpdatesDesc =
            "The total number of times entries in this cache are updated through delta bytes."
    val deltaUpdatesTimeDesc =
            "Total time spent applying the received delta bytes to entries in this cache."
    val deltaFailedUpdatesDesc = "The total number of times entries in this cache failed to be updated through delta bytes."
    val deltasPreparedDesc = "The total number of times delta was prepared in this cache."
    val deltasPreparedTimeDesc = "Total time spent preparing delta bytes in this cache."
    val deltasSentDesc = "The total number of times delta was sent to remote caches. This excludes deltas sent from server to client."
    val deltaFullValuesSentDesc = "The total number of times a full value was sent to a remote cache."
    val deltaFullValuesRequestedDesc = "The total number of times a full value was requested by this cache."
    val importedEntriesCountDesc = "The total number of entries imported from a snapshot file."
    val importTimeDesc = "The total time spent importing entries from a snapshot file."
    val exportedEntriesCountDesc = "The total number of entries exported into a snapshot file."
    val exportTimeDesc = "The total time spent exporting entries into a snapshot file."
    val compressionCompressTimeDesc = "The total time spent compressing data."
    val compressionDecompressTimeDesc = "The total time spent decompressing data."
    val compressionCompressionsDesc = "The total number of compression operations."
    val compressionDecompressionsDesc = "The total number of decompression operations."
    val compressionPreCompresssedBytesDesc = "The total number of bytes before compressing."
    val compressionPostCompressedBytesDesc = "The total number of bytes after compressing."
    val evictByCriteria_evictionsDesc = "The total number of entries evicted"
    val evictByCriteria_evictionTimeDesc = "Time taken for eviction process"
    val evictByCriteria_evictionsInProgressDesc = "Total number of evictions in progress"
    val evictByCriteria_evaluationsDesc = "Total number of evaluations for eviction"
    val evictByCriteria_evaluationTimeDesc = "Total time taken for evaluation of user expression during eviction"

    val loadsInProgressMeter = GaugeStatisticMeter("loads.inprogress", "Current number of threads in this cache doing a cache load.")
    val loadsCompletedMeter = CounterStatisticMeter("loads.completed", "Total number of times a load on this cache has completed (as a result of either a local get() or a remote netload).")
    val loadsTimer = TimerStatisticMeter("load.time", "Total time spent invoking loaders on this cache.", unit = "nanoseconds")
    val netLoadInProgressMeter = GaugeStatisticMeter("netload.inprogress", "Current number of threads doing a network load initiated by a get() in this cache.")
    val netLoadCompletedMeter = CounterStatisticMeter("netload.completed", "Total number of times a network load initiated on this cache has completed.")
    val netLoadTimer = TimerStatisticMeter("netloadTime", "Total time spent doing network loads on this cache.", unit = "nanoseconds")
    val netSearchInProgressMeter = GaugeStatisticMeter("netsearch.inprogress", "Current number of threads doing a network search initiated by a get() in this cache.")
    val netSearchCompletedMeter = CounterStatisticMeter("netsearch.completed", "Total number of times network searches initiated by this cache have completed.")
    val netSearchTimer = TimerStatisticMeter("netsearch.time", "Total time spent doing network searches for cache values.", unit = "nanoseconds")
    val cacheWriterInProgressMeter = GaugeStatisticMeter("cachewriter.inprogress", "Current number of threads doing a cache writer call.")
    val cacheWriterCompletedMeter = CounterStatisticMeter("cachewriter.completed", "Total number of times a cache writer call has completed.")
    val cacheWriteTimer = TimerStatisticMeter("cachewriter.time", "Total time spent doing cache writer calls.", unit="nanoseconds")
    GaugeStatisticMeter("cacheListenerCallsInProgress", cacheListenerCallsInProgressDesc,"operations")
    CounterStatisticMeter("cacheListenerCallsCompleted", cacheListenerCallsCompletedDesc,"operations")
    CounterStatisticMeter("cacheListenerCallTime", cacheListenerCallTimeDesc, "nanoseconds")
    GaugeStatisticMeter("indexUpdateInProgress", "Current number of ops in progress","operations")
    CounterStatisticMeter("indexUpdateCompleted", "Total number of ops that haves completed","operations")
    CounterStatisticMeter("indexUpdateTime", "Total amount of time spent doing this op","nanoseconds")
    GaugeStatisticMeter("indexInitializationInProgress","Current number of index initializations in progress", "operations")
    CounterStatisticMeter("indexInitializationCompleted","Total number of index initializations that have completed", "operations")
    CounterStatisticMeter("indexInitializationTime","Total amount of time spent initializing indexes", "nanoseconds")
    GaugeStatisticMeter("getInitialImagesInProgress", getInitialImagesInProgressDesc,"operations")
    CounterStatisticMeter("getInitialImagesCompleted", getInitialImagesCompletedDesc,"operations")
    CounterStatisticMeter("deltaGetInitialImagesCompleted", deltaGetInitialImagesCompletedDesc,"operations")
    CounterStatisticMeter("getInitialImageTime", getInitialImageTimeDesc, "nanoseconds")
    CounterStatisticMeter("getInitialImageKeysReceived", getInitialImageKeysReceivedDesc,"keys")
    GaugeStatisticMeter("regions", regionsDesc, "regions")
    GaugeStatisticMeter("partitionedRegions", partitionedRegionsDesc, "partitionedRegions")
    CounterStatisticMeter("destroys", destroysDesc, "operations")
    CounterStatisticMeter("updates", updatesDesc, "operations")
    CounterStatisticMeter("updateTime", updateTimeDesc, "nanoseconds")
    CounterStatisticMeter("invalidates", invalidatesDesc, "operations")
    CounterStatisticMeter("gets", getsDesc, "operations")
    CounterStatisticMeter("misses", missesDesc, "operations")
    CounterStatisticMeter("creates", createsDesc, "operations")
    CounterStatisticMeter("puts", putsDesc, "operations")
    CounterStatisticMeter("putTime", putTimeDesc, "nanoseconds", false)
    CounterStatisticMeter("putalls", putallsDesc, "operations")
    CounterStatisticMeter("putallTime", putallTimeDesc, "nanoseconds", false)
    CounterStatisticMeter("removeAlls", removeAllsDesc, "operations")
    CounterStatisticMeter("removeAllTime", removeAllTimeDesc, "nanoseconds", false)
    CounterStatisticMeter("getTime", getTimeDesc, "nanoseconds", false)
    GaugeStatisticMeter("eventQueueSize", eventQueueSizeDesc, "messages")
    GaugeStatisticMeter("eventQueueThrottleCount", eventQueueThrottleCountDesc, "delays")
    CounterStatisticMeter("eventQueueThrottleTime", eventQueueThrottleTimeDesc, "nanoseconds",false)
    GaugeStatisticMeter("eventThreads", eventThreadsDesc, "threads")
    CounterStatisticMeter("queryExecutions", queryExecutionsDesc, "operations")
    CounterStatisticMeter("queryExecutionTime", queryExecutionTimeDesc, "nanoseconds")
    CounterStatisticMeter("queryResultsHashCollisions", queryResultsHashCollisionsDesc,"operations")
    CounterStatisticMeter("queryResultsHashCollisionProbeTime",queryResultsHashCollisionProbeTimeDesc, "nanoseconds")
    CounterStatisticMeter("partitionedRegionQueryRetries",partitionedRegionOQLQueryRetriesDesc, "retries")
    CounterStatisticMeter("txCommits", txCommitsDesc, "commits")
    CounterStatisticMeter("txCommitChanges", txCommitChangesDesc, "changes")
    CounterStatisticMeter("txCommitTime", txCommitTimeDesc, "nanoseconds", false)
    CounterStatisticMeter("txSuccessLifeTime", txSuccessLifeTimeDesc, "nanoseconds", false)
    CounterStatisticMeter("txFailures", txFailuresDesc, "failures")
    CounterStatisticMeter("txFailureChanges", txFailureChangesDesc, "changes")
    CounterStatisticMeter("txFailureTime", txFailureTimeDesc, "nanoseconds", false)
    CounterStatisticMeter("txFailedLifeTime", txFailedLifeTimeDesc, "nanoseconds", false)
    CounterStatisticMeter("txRollbacks", txRollbacksDesc, "rollbacks")
    CounterStatisticMeter("txRollbackChanges", txRollbackChangesDesc, "changes")
    CounterStatisticMeter("txRollbackTime", txRollbackTimeDesc, "nanoseconds", false)
    CounterStatisticMeter("txRollbackLifeTime", txRollbackLifeTimeDesc, "nanoseconds", false)
    CounterStatisticMeter("txConflictCheckTime", txConflictCheckTimeDesc, "nanoseconds",false)
    GaugeStatisticMeter("reliableQueuedOps", reliableQueuedOpsDesc, "operations")
    GaugeStatisticMeter("reliableQueueSize", reliableQueueSizeDesc, "megabytes")
    GaugeStatisticMeter("reliableQueueMax", reliableQueueMaxDesc, "megabytes")
    GaugeStatisticMeter("reliableRegions", reliableRegionsDesc, "regions")
    GaugeStatisticMeter("reliableRegionsMissing", reliableRegionsMissingDesc, "regions")
    GaugeStatisticMeter("reliableRegionsQueuing", reliableRegionsQueuingDesc, "regions")
    GaugeStatisticMeter("reliableRegionsMissingFullAccess",reliableRegionsMissingFullAccessDesc, "regions")
    GaugeStatisticMeter("reliableRegionsMissingLimitedAccess",reliableRegionsMissingLimitedAccessDesc, "regions")
    GaugeStatisticMeter("reliableRegionsMissingNoAccess", reliableRegionsMissingNoAccessDesc,"regions")
    GaugeStatisticMeter("entries","Current number of entries in the cache. This does not include any entries that are tombstones. See tombstoneCount.","entries")
    CounterStatisticMeter("eventsQueued","Number of events attached to " + "other events for callback invocation", "events")
    CounterStatisticMeter("retries","Number of times a concurrent destroy followed by a create has caused an entry operation to need to retry.","operations")
    CounterStatisticMeter("clears", clearsDesc, "operations")
    GaugeStatisticMeter("diskTasksWaiting","Current number of disk tasks (oplog compactions, asynchronous recoveries, etc) that are waiting for a thread to run the operation","operations")
    CounterStatisticMeter("conflatedEvents", conflatedEventsDesc, "operations")
    GaugeStatisticMeter("tombstones", tombstoneCountDesc, "entries")
    CounterStatisticMeter("tombstoneGCs", tombstoneGCCountDesc, "operations")
    GaugeStatisticMeter("replicatedTombstonesSize", tombstoneOverhead1Desc, "bytes")
    GaugeStatisticMeter("nonReplicatedTombstonesSize", tombstoneOverhead2Desc, "bytes")
    CounterStatisticMeter("clearTimeouts", clearTimeoutsDesc, "timeouts")
    GaugeStatisticMeter("evictorJobsStarted", "Number of evictor jobs started", "jobs")
    GaugeStatisticMeter("evictorJobsCompleted", "Number of evictor jobs completed", "jobs")
    GaugeStatisticMeter("evictorQueueSize","Number of jobs waiting to be picked up by evictor threads", "jobs")
    CounterStatisticMeter("evictWorkTime","Total time spent doing eviction work in background threads", "nanoseconds", false)
    CounterStatisticMeter("nonSingleHopsCount", nonSingleHopsDesc,"Total number of times client request observed more than one hop during operation.",false)
    CounterStatisticMeter("metaDataRefreshCount", metaDataRefreshCountDesc,"Total number of times the meta data is refreshed due to hopping.", false)
    CounterStatisticMeter("deltaUpdates", deltaUpdatesDesc, "operations")
    CounterStatisticMeter("deltaUpdatesTime", deltaUpdatesTimeDesc, "nanoseconds", false)
    CounterStatisticMeter("deltaFailedUpdates", deltaFailedUpdatesDesc, "operations")
    CounterStatisticMeter("deltasPrepared", deltasPreparedDesc, "operations")
    CounterStatisticMeter("deltasPreparedTime", deltasPreparedTimeDesc, "nanoseconds", false)
    CounterStatisticMeter("deltasSent", deltasSentDesc, "operations")
    CounterStatisticMeter("deltaFullValuesSent", deltaFullValuesSentDesc, "operations")
    CounterStatisticMeter("deltaFullValuesRequested", deltaFullValuesRequestedDesc,"operations")
    CounterStatisticMeter("importedEntries", importedEntriesCountDesc, "entries")
    CounterStatisticMeter("importTime", importTimeDesc, "nanoseconds")
    CounterStatisticMeter("exportedEntries", exportedEntriesCountDesc, "entries")
    CounterStatisticMeter("exportTime", exportTimeDesc, "nanoseconds")
    CounterStatisticMeter("compressTime", compressionCompressTimeDesc, "nanoseconds")
    CounterStatisticMeter("decompressTime", compressionDecompressTimeDesc, "nanoseconds")
    CounterStatisticMeter("compressions", compressionCompressionsDesc, "operations")
    CounterStatisticMeter("decompressions", compressionDecompressionsDesc, "operations")
    CounterStatisticMeter("preCompressedBytes", compressionPreCompresssedBytesDesc, "bytes")
    CounterStatisticMeter("postCompressedBytes", compressionPostCompressedBytesDesc, "bytes")
    CounterStatisticMeter("evictByCriteria_evictions", evictByCriteria_evictionsDesc,"operations")
    CounterStatisticMeter("evictByCriteria_evictionTime", evictByCriteria_evictionTimeDesc,"nanoseconds")
    CounterStatisticMeter("evictByCriteria_evictionsInProgress",evictByCriteria_evictionsInProgressDesc, "operations")
    CounterStatisticMeter("evictByCriteria_evaluations", evictByCriteria_evaluationsDesc,"operations")
    CounterStatisticMeter("evictByCriteria_evaluationTime",evictByCriteria_evaluationTimeDesc, "nanoseconds")

    /**
     * Creates a new `CachePerfStats` and registers itself with the given statistics
     * factory.
     */
    fun CachePerfStats(factory: StatisticsFactory, name: String): ??? {
        stats = factory.createAtomicStatistics(type, "RegionStats-$name")
    }

    ////////////////////// Accessing Stats //////////////////////
    fun incReliableQueuedOps(inc: Int) {
        stats!!.incInt(reliableQueuedOpsId, inc)
    }

    fun incReliableQueueSize(inc: Int) {
        stats!!.incInt(reliableQueueSizeId, inc)
    }

    fun incReliableQueueMax(inc: Int) {
        stats!!.incInt(reliableQueueMaxId, inc)
    }


    fun incReliableRegions(inc: Int) {
        stats!!.incInt(reliableRegionsId, inc)
    }

    fun incReliableRegionsMissing(inc: Int) {
        stats!!.incInt(reliableRegionsMissingId, inc)
    }

    fun incReliableRegionsQueuing(inc: Int) {
        stats!!.incInt(reliableRegionsQueuingId, inc)
    }

    fun incReliableRegionsMissingFullAccess(inc: Int) {
        stats!!.incInt(reliableRegionsMissingFullAccessId, inc)
    }

    fun incReliableRegionsMissingLimitedAccess(inc: Int) {
        stats!!.incInt(reliableRegionsMissingLimitedAccessId, inc)
    }

    fun incReliableRegionsMissingNoAccess(inc: Int) {
        stats!!.incInt(reliableRegionsMissingNoAccessId, inc)
    }

    fun incQueuedEvents(inc: Int) {
        this.stats!!.incLong(eventsQueuedId, inc)
    }


////////////////////// Updating Stats //////////////////////

    fun startCompression(): Long {
        stats!!.incLong(compressionCompressionsId, 1)
        return getStatTime()
    }

    fun endCompression(startTime: Long, startSize: Long, endSize: Long) {
        if (enableClockStats) {
            stats!!.incLong(compressionCompressTimeId, getStatTime() - startTime)
        }
        stats!!.incLong(compressionPreCompressedBytesId, startSize)
        stats!!.incLong(compressionPostCompressedBytesId, endSize)
    }

    fun startDecompression(): Long {
        stats!!.incLong(compressionDecompressionsId, 1)
        return getStatTime()
    }

    fun endDecompression(startTime: Long) {
        if (enableClockStats) {
            stats!!.incLong(compressionDecompressTimeId, getStatTime() - startTime)
        }
    }

    /**
     * @return the timestamp that marks the start of the operation
     */
    fun startLoad(): Long {
        stats!!.incInt(loadsInProgressId, 1)
        return NanoTimer.getTime() // don't use getStatTime so always enabled
    }

    /**
     * @param start the timestamp taken when the operation started
     */
    fun endLoad(start: Long) {
        // note that load times are used in health checks and
        // should not be disabled by enableClockStats==false
        val ts = NanoTimer.getTime() // don't use getStatTime so always enabled
        stats!!.incLong(loadTimeId, ts - start)
        stats!!.incInt(loadsInProgressId, -1)
        stats!!.incInt(loadsCompletedId, 1)
    }

    /**
     * @return the timestamp that marks the start of the operation
     */
    fun startNetload(): Long {
        stats!!.incInt(netloadsInProgressId, 1)
        return getStatTime()
    }

    /**
     * @param start the timestamp taken when the operation started
     */
    fun endNetload(start: Long) {
        if (enableClockStats) {
            stats!!.incLong(netloadTimeId, getStatTime() - start)
        }
        stats!!.incInt(netloadsInProgressId, -1)
        stats!!.incInt(netloadsCompletedId, 1)
    }

    /**
     * @return the timestamp that marks the start of the operation
     */
    fun startNetsearch(): Long {
        stats!!.incInt(netsearchesInProgressId, 1)
        return NanoTimer.getTime() // don't use getStatTime so always enabled
    }

    /**
     * @param start the timestamp taken when the operation started
     */
    fun endNetsearch(start: Long) {
        // note that netsearch is used in health checks and timings should
        // not be disabled by enableClockStats==false
        val ts = NanoTimer.getTime() // don't use getStatTime so always enabled
        stats!!.incLong(netsearchTimeId, ts - start)
        stats!!.incInt(netsearchesInProgressId, -1)
        stats!!.incInt(netsearchesCompletedId, 1)
    }

    /**
     * @return the timestamp that marks the start of the operation
     */
    fun startCacheWriterCall(): Long {
        stats!!.incInt(cacheWriterCallsInProgressId, 1)
        return getStatTime()
    }

    /**
     * @param start the timestamp taken when the operation started
     */
    fun endCacheWriterCall(start: Long) {
        if (enableClockStats) {
            stats!!.incLong(cacheWriterCallTimeId, getStatTime() - start)
        }
        stats!!.incInt(cacheWriterCallsInProgressId, -1)
        stats!!.incInt(cacheWriterCallsCompletedId, 1)
    }

    /**
     * @return the timestamp that marks the start of the operation
     * @since GemFire 3.5
     */
    fun startCacheListenerCall(): Long {
        stats!!.incInt(cacheListenerCallsInProgressId, 1)
        return getStatTime()
    }

    /**
     * @param start the timestamp taken when the operation started
     * @since GemFire 3.5
     */
    fun endCacheListenerCall(start: Long) {
        if (enableClockStats) {
            stats!!.incLong(cacheListenerCallTimeId, getStatTime() - start)
        }
        stats!!.incInt(cacheListenerCallsInProgressId, -1)
        stats!!.incInt(cacheListenerCallsCompletedId, 1)
    }

    /**
     * @return the timestamp that marks the start of the operation
     */
    fun startGetInitialImage(): Long {
        stats!!.incInt(getInitialImagesInProgressId, 1)
        return getStatTime()
    }

    /**
     * @param start the timestamp taken when the operation started
     */
    fun endGetInitialImage(start: Long) {
        if (enableClockStats) {
            stats!!.incLong(getInitialImageTimeId, getStatTime() - start)
        }
        stats!!.incInt(getInitialImagesInProgressId, -1)
        stats!!.incInt(getInitialImagesCompletedId, 1)
    }

    /**
     * @param start the timestamp taken when the operation started
     */
    fun endNoGIIDone(start: Long) {
        if (enableClockStats) {
            stats!!.incLong(getInitialImageTimeId, getStatTime() - start)
        }
        stats!!.incInt(getInitialImagesInProgressId, -1)
    }

    fun incDeltaGIICompleted() {
        stats!!.incInt(deltaGetInitialImagesCompletedId, 1)
    }

    fun incGetInitialImageKeysReceived() {
        stats!!.incInt(getInitialImageKeysReceivedId, 1)
    }

    fun startIndexUpdate(): Long {
        stats!!.incInt(indexUpdateInProgressId, 1)
        return getStatTime()
    }

    fun endIndexUpdate(start: Long) {
        val ts = getStatTime()
        stats!!.incLong(indexUpdateTimeId, ts - start)
        stats!!.incInt(indexUpdateInProgressId, -1)
        stats!!.incInt(indexUpdateCompletedId, 1)
    }

    fun startIndexInitialization(): Long {
        stats!!.incInt(indexInitializationInProgressId, 1)
        return getStatTime()
    }

    fun endIndexInitialization(start: Long) {
        val ts = getStatTime()
        stats!!.incLong(indexInitializationTimeId, ts - start)
        stats!!.incInt(indexInitializationInProgressId, -1)
        stats!!.incInt(indexInitializationCompletedId, 1)
    }

    fun getIndexInitializationTime(): Long {
        return stats!!.getLong(indexInitializationTimeId)
    }

    fun incRegions(inc: Int) {
        stats!!.incInt(regionsId, inc)
    }

    fun incPartitionedRegions(inc: Int) {
        stats!!.incInt(partitionedRegionsId, inc)
    }

    fun incDestroys() {
        stats!!.incInt(destroysId, 1)
    }

    fun incCreates() {
        stats!!.incInt(createsId, 1)
    }

    fun incInvalidates() {
        stats!!.incInt(invalidatesId, 1)
    }

    /**
     * @return the timestamp that marks the start of the operation
     */
    fun startGet(): Long {
        return getStatTime()
    }

    /**
     * @param start the timestamp taken when the operation started
     */
    fun endGet(start: Long, miss: Boolean) {
        if (enableClockStats) {
            stats!!.incLong(getTimeId, getStatTime() - start)
        }
        stats!!.incInt(getsId, 1)
        if (miss) {
            stats!!.incInt(missesId, 1)
        }
    }

    /**
     * @param start the timestamp taken when the operation started
     * @param isUpdate true if the put was an update (origin remote)
     */
    fun endPut(start: Long, isUpdate: Boolean): Long {
        var total: Long = 0
        if (isUpdate) {
            stats!!.incInt(updatesId, 1)
            if (enableClockStats) {
                total = getStatTime() - start
                stats!!.incLong(updateTimeId, total)
            }
        } else {
            stats!!.incInt(putsId, 1)
            if (enableClockStats) {
                total = getStatTime() - start
                stats!!.incLong(putTimeId, total)
            }
        }
        return total
    }

    fun endPutAll(start: Long) {
        stats!!.incInt(putallsId, 1)
        if (enableClockStats)
            stats!!.incLong(putallTimeId, getStatTime() - start)
    }

    fun endRemoveAll(start: Long) {
        stats!!.incInt(removeAllsId, 1)
        if (enableClockStats)
            stats!!.incLong(removeAllTimeId, getStatTime() - start)
    }

    fun endQueryExecution(executionTime: Long) {
        stats!!.incInt(queryExecutionsId, 1)
        if (enableClockStats) {
            stats!!.incLong(queryExecutionTimeId, executionTime)
        }
    }

    fun endQueryResultsHashCollisionProbe(start: Long) {
        if (enableClockStats) {
            stats!!.incLong(queryResultsHashCollisionProbeTimeId, getStatTime() - start)
        }
    }

    fun incQueryResultsHashCollisions() {
        stats!!.incInt(queryResultsHashCollisionsId, 1)
    }

    fun incTxConflictCheckTime(delta: Long) {
        stats!!.incLong(txConflictCheckTimeId, delta)
    }

    fun txSuccess(opTime: Long, txLifeTime: Long, txChanges: Int) {
        stats!!.incInt(txCommitsId, 1)
        stats!!.incInt(txCommitChangesId, txChanges)
        stats!!.incLong(txCommitTimeId, opTime)
        stats!!.incLong(txSuccessLifeTimeId, txLifeTime)
    }

    fun txFailure(opTime: Long, txLifeTime: Long, txChanges: Int) {
        stats!!.incInt(txFailuresId, 1)
        stats!!.incInt(txFailureChangesId, txChanges)
        stats!!.incLong(txFailureTimeId, opTime)
        stats!!.incLong(txFailedLifeTimeId, txLifeTime)
    }

    fun txRollback(opTime: Long, txLifeTime: Long, txChanges: Int) {
        stats!!.incInt(txRollbacksId, 1)
        stats!!.incInt(txRollbackChangesId, txChanges)
        stats!!.incLong(txRollbackTimeId, opTime)
        stats!!.incLong(txRollbackLifeTimeId, txLifeTime)
    }

    fun endDeltaUpdate(start: Long) {
        stats!!.incInt(deltaUpdatesId, 1)
        if (enableClockStats) {
            stats!!.incLong(deltaUpdatesTimeId, getStatTime() - start)
        }
    }

    fun incDeltaFailedUpdates() {
        stats!!.incInt(deltaFailedUpdatesId, 1)
    }

    fun endDeltaPrepared(start: Long) {
        stats!!.incInt(deltasPreparedId, 1)
        if (enableClockStats) {
            stats!!.incLong(deltasPreparedTimeId, getStatTime() - start)
        }
    }

    fun incDeltasSent() {
        stats!!.incInt(deltasSentId, 1)
    }

    fun incDeltaFullValuesSent() {
        stats!!.incInt(deltaFullValuesSentId, 1)
    }

    fun incDeltaFullValuesRequested() {
        stats!!.incInt(deltaFullValuesRequestedId, 1)
    }

    fun incEventQueueSize(items: Int) {
        this.stats!!.incInt(eventQueueSizeId, items)
    }

    fun incEventQueueThrottleCount(items: Int) {
        this.stats!!.incInt(eventQueueThrottleCountId, items)
    }

    protected fun incEventQueueThrottleTime(nanos: Long) {
        this.stats!!.incLong(eventQueueThrottleTimeId, nanos)
    }

    protected fun incEventThreads(items: Int) {
        this.stats!!.incInt(eventThreadsId, items)
    }

    fun incEntryCount(delta: Int) {
        this.stats!!.incLong(entryCountId, delta)
    }

    fun incRetries() {
        this.stats!!.incInt(retriesId, 1)
    }

    fun incDiskTasksWaiting() {
        this.stats!!.incInt(diskTasksWaitingId, 1)
    }

    fun decDiskTasksWaiting() {
        this.stats!!.incInt(diskTasksWaitingId, -1)
    }

    fun decDiskTasksWaiting(count: Int) {
        this.stats!!.incInt(diskTasksWaitingId, -count)
    }

    fun incEvictorJobsStarted() {
        this.stats!!.incInt(evictorJobsStartedId, 1)
    }

    fun incEvictorJobsCompleted() {
        this.stats!!.incInt(evictorJobsCompletedId, 1)
    }

    fun incEvictorQueueSize(delta: Int) {
        this.stats!!.incInt(evictorQueueSizeId, delta)
    }

    fun incEvictWorkTime(delta: Long) {
        this.stats!!.incLong(evictWorkTimeId, delta)
    }

    fun getEventPoolHelper(): PoolStatHelper {
        return object : PoolStatHelper {
            override fun startJob() {
                incEventThreads(1)
            }

            override fun endJob() {
                incEventThreads(-1)
            }
        }
    }

    fun incClearCount() {
        this.stats!!.incInt(clearsId, 1)
    }

    fun incConflatedEventsCount() {
        this.stats!!.incLong(conflatedEventsId, 1)
    }

    fun incTombstoneCount(amount: Int) {
        this.stats!!.incInt(tombstoneCountId, amount)
    }

    fun incTombstoneGCCount() {
        this.stats!!.incInt(tombstoneGCCountId, 1)
    }

    fun setReplicatedTombstonesSize(size: Long) {
        this.stats!!.setLong(tombstoneOverhead1Id, size)
    }

    fun setNonReplicatedTombstonesSize(size: Long) {
        this.stats!!.setLong(tombstoneOverhead2Id, size)
    }

    fun incClearTimeouts() {
        this.stats!!.incInt(clearTimeoutsId, 1)
    }

    fun incPRQueryRetries() {
        this.stats!!.incLong(partitionedRegionQueryRetriesId, 1)
    }

    fun getEvictionQueueStatHelper(): QueueStatHelper {
        return object : QueueStatHelper {
            override fun add() {
                incEvictorQueueSize(1)
            }

            override fun remove() {
                incEvictorQueueSize(-1)
            }

            override fun remove(count: Int) {
                incEvictorQueueSize(count * -1)
            }
        }
    }

    fun incMetaDataRefreshCount() {
        this.stats!!.incLong(metaDataRefreshCountId, 1)
    }

    fun endImport(entryCount: Long, start: Long) {
        stats!!.incLong(importedEntriesCountId, entryCount)
        if (enableClockStats) {
            stats!!.incLong(importTimeId, getStatTime() - start)
        }
    }

    fun endExport(entryCount: Long, start: Long) {
        stats!!.incLong(exportedEntriesCountId, entryCount)
        if (enableClockStats) {
            stats!!.incLong(exportTimeId, getStatTime() - start)
        }
    }
