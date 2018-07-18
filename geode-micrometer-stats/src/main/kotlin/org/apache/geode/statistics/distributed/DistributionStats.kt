package org.apache.geode.statistics.distributed

import org.apache.geode.statistics.internal.micrometer.CounterStatisticMeter
import org.apache.geode.statistics.internal.micrometer.GaugeStatisticMeter
import org.apache.geode.statistics.internal.micrometer.MicrometerMeterGroup
import org.apache.geode.statistics.internal.micrometer.TimerStatisticMeter

class DistributionStats : MicrometerMeterGroup("DistributionStats"), DMStats {

    /**
     * Returns a helper object so that the overflow queue can record its stats to the proper
     * distribution stats.
     *
     * @since GemFire 3.5
     */
    val overflowQueueHelper: ThrottledQueueStatHelper
        get() = object : ThrottledQueueStatHelper {
            override fun incThrottleCount() {
                incOverflowQueueThrottleCount(1)
            }

            override fun throttleTime(nanos: Long) {
                incOverflowQueueThrottleTime(nanos)
            }

            override fun add() {
                incOverflowQueueSize(1)
            }

            override fun remove() {
                incOverflowQueueSize(-1)
            }

            override fun remove(count: Int) {
                incOverflowQueueSize(-count)
            }
        }

    /**
     * Returns a helper object so that the waiting queue can record its stats to the proper
     * distribution stats.
     *
     * @since GemFire 3.5
     */
    val waitingQueueHelper: QueueStatHelper
        get() = object : QueueStatHelper {
            override fun add() {
                incWaitingQueueSize(1)
            }

            override fun remove() {
                incWaitingQueueSize(-1)
            }

            override fun remove(count: Int) {
                incWaitingQueueSize(-count)
            }
        }

    /**
     * Returns a helper object so that the high priority queue can record its stats to the proper
     * distribution stats.
     *
     * @since GemFire 3.5
     */
    val highPriorityQueueHelper: ThrottledQueueStatHelper
        get() = object : ThrottledQueueStatHelper {
            override fun incThrottleCount() {
                incHighPriorityQueueThrottleCount(1)
            }

            override fun throttleTime(nanos: Long) {
                incHighPriorityQueueThrottleTime(nanos)
            }

            override fun add() {
                incHighPriorityQueueSize(1)
            }

            override fun remove() {
                incHighPriorityQueueSize(-1)
            }

            override fun remove(count: Int) {
                incHighPriorityQueueSize(-count)
            }
        }

    /**
     * Returns a helper object so that the partitioned region queue can record its stats to the proper
     * distribution stats.
     *
     * @since GemFire 5.0
     */
    val partitionedRegionQueueHelper: ThrottledQueueStatHelper
        get() = object : ThrottledQueueStatHelper {
            override fun incThrottleCount() {
                incPartitionedRegionQueueThrottleCount(1)
            }

            override fun throttleTime(nanos: Long) {
                incPartitionedRegionQueueThrottleTime(nanos)
            }

            override fun add() {
                incPartitionedRegionQueueSize(1)
            }

            override fun remove() {
                incPartitionedRegionQueueSize(-1)
            }

            override fun remove(count: Int) {
                incPartitionedRegionQueueSize(-count)
            }
        }

    /**
     * Returns a helper object so that the partitioned region pool can record its stats to the proper
     * distribution stats.
     *
     * @since GemFire 5.0.2
     */
    val partitionedRegionPoolHelper: PoolStatHelper
        get() = object : PoolStatHelper {
            override fun startJob() {
                incPartitionedRegionThreadJobs(1)
            }

            override fun endJob() {
                incPartitionedRegionThreadJobs(-1)
            }
        }

    /**
     * Returns a helper object so that the function execution queue can record its stats to the proper
     * distribution stats.
     *
     * @since GemFire 6.0
     */
    val functionExecutionQueueHelper: ThrottledQueueStatHelper
        get() = object : ThrottledQueueStatHelper {
            override fun incThrottleCount() {
                incFunctionExecutionQueueThrottleCount(1)
            }

            override fun throttleTime(nanos: Long) {
                incFunctionExecutionQueueThrottleTime(nanos)
            }

            override fun add() {
                incFunctionExecutionQueueSize(1)
            }

            override fun remove() {
                incFunctionExecutionQueueSize(-1)
            }

            override fun remove(count: Int) {
                incFunctionExecutionQueueSize(-count)
            }
        }

    /**
     * Returns a helper object so that the function execution pool can record its stats to the proper
     * distribution stats.
     *
     * @since GemFire 6.0
     */
    val functionExecutionPoolHelper: PoolStatHelper
        get() = object : PoolStatHelper {
            override fun startJob() {
                incFunctionExecutionThreadJobs(1)
            }

            override fun endJob() {
                incFunctionExecutionThreadJobs(-1)
            }
        }

    /**
     * Returns a helper object so that the serial queue can record its stats to the proper
     * distribution stats.
     *
     * @since GemFire 3.5
     */
    val serialQueueHelper: ThrottledMemQueueStatHelper
        get() = object : ThrottledMemQueueStatHelper {
            override fun incThrottleCount() {
                incSerialQueueThrottleCount(1)
            }

            override fun throttleTime(nanos: Long) {
                incSerialQueueThrottleTime(nanos)
            }

            override fun add() {
                incSerialQueueSize(1)
            }

            override fun remove() {
                incSerialQueueSize(-1)
            }

            override fun remove(count: Int) {
                incSerialQueueSize(-count)
            }

            override fun addMem(amount: Int) {
                incSerialQueueBytes(amount)
            }

            override fun removeMem(amount: Int) {
                incSerialQueueBytes(amount * -1)
            }
        }

    /**
     * Returns a helper object so that the normal pool can record its stats to the proper distribution
     * stats.
     *
     * @since GemFire 3.5
     */
    val normalPoolHelper: PoolStatHelper
        get() = object : PoolStatHelper {
            override fun startJob() {
                incNormalPoolThreadJobs(1)
            }

            override fun endJob() {
                incNormalPoolThreadJobs(-1)
            }
        }

    /**
     * Returns a helper object so that the waiting pool can record its stats to the proper
     * distribution stats.
     *
     * @since GemFire 3.5
     */
    val waitingPoolHelper: PoolStatHelper
        get() = object : PoolStatHelper {
            override fun startJob() {
                incWaitingPoolThreadJobs(1)
            }

            override fun endJob() {
                incWaitingPoolThreadJobs(-1)
            }
        }

    /**
     * Returns a helper object so that the highPriority pool can record its stats to the proper
     * distribution stats.
     *
     * @since GemFire 3.5
     */
    val highPriorityPoolHelper: PoolStatHelper
        get() = object : PoolStatHelper {
            override fun startJob() {
                incHighPriorityThreadJobs(1)
            }

            override fun endJob() {
                incHighPriorityThreadJobs(-1)
            }
        }

    val serialProcessorHelper: PoolStatHelper
        get() = object : PoolStatHelper {
            override fun startJob() {
                incNumSerialThreadJobs(1)
                if (logger.isTraceEnabled()) {
                    logger.trace("[DM.SerialQueuedExecutor.execute] numSerialThreads={}",
                            getNumSerialThreads())
                }
            }

            override fun endJob() {
                incNumSerialThreadJobs(-1)
            }
        }

    val viewProcessorHelper: PoolStatHelper
        get() = object : PoolStatHelper {
            override fun startJob() {
                incViewProcessorThreadJobs(1)
                if (logger.isTraceEnabled()) {
                    logger.trace("[DM.SerialQueuedExecutor.execute] numViewThreads={}", numViewThreads)
                }
            }

            override fun endJob() {
                incViewProcessorThreadJobs(-1)
            }
        }


    val serialPooledProcessorHelper: PoolStatHelper
        get() = object : PoolStatHelper {
            override fun startJob() {
                incSerialPooledProcessorThreadJobs(1)
            }

            override fun endJob() {
                incSerialPooledProcessorThreadJobs(-1)
            }
        }

    // private final HistogramStats replyHandoffHistogram;
    // private final HistogramStats replyWaitHistogram;

    //////////////////////// Constructors ////////////////////////

    /**
     * Creates a new `DistributionStats` and registers itself with the given statistics
     * factory.
     */
    constructor(f: StatisticsFactory, statId: Long) {
        this.stats = f.createAtomicStatistics(type, "distributionStats", statId)
        // this.replyHandoffHistogram = new HistogramStats("ReplyHandOff",unit="nanoseconds", f,
        // new long[] {100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000},
        // false);
        // this.replyWaitHistogram = new HistogramStats("ReplyWait",unit="nanoseconds", f,
        // new long[] {100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000},
        // false);
        Buffers.initBufferStats(this)
    }

    /**
     * Used by tests to create an instance given its already existings stats.
     */
    constructor(stats: Statistics) {
        this.stats = stats
        // this.replyHandoffHistogram = null;
        // this.replyWaitHistogram = null;
    }

    ////////////////////// Instance Methods //////////////////////

    fun close() {
        this.stats.close()
    }

    /**
     * Returns the total number of messages sent by the distribution manager
     */
    override fun getSentMessages(): Long {
        return this.stats.getLong(sentMessagesId)
    }

    override fun incTOSentMsg() {
        this.stats.incLong(TOSentMsgId, 1)
    }

    override fun getSentCommitMessages(): Long {
        return this.stats.getLong(sentCommitMessagesId)
    }

    override fun getCommitWaits(): Long {
        return this.stats.getLong(commitWaitsId)
    }

    /**
     * Increments the total number of messages sent by the distribution manager
     */
    override fun incSentMessages(messages: Long) {
        this.stats.incLong(sentMessagesId, messages)
    }

    /**
     * Increments the total number of transactino commit messages sent by the distribution manager
     */
    override fun incSentCommitMessages(messages: Long) {
        this.stats.incLong(sentCommitMessagesId, messages)
    }

    override fun incCommitWaits() {
        this.stats.incLong(commitWaitsId, 1)
    }

    /**
     * Returns the total number of nanoseconds spent sending messages.
     */
    override fun getSentMessagesTime(): Long {
        return this.stats.getLong(sentMessagesTimeId)
    }

    /**
     * Increments the total number of nanoseconds spend sending messages.
     *
     *
     * This also sets the sentMessagesMaxTime, if appropriate
     */
    override fun incSentMessagesTime(nanos: Long) {
        if (enableClockStats) {
            this.stats.incLong(sentMessagesTimeId, nanos)
            val millis = nanos / 1000000
            if (sentMessagesMaxTime < millis) {
                this.stats.setLong(sentMessagesMaxTimeId, millis)
            }
        }
    }


    /**
     * Returns the total number of messages broadcast by the distribution manager
     */
    override fun getBroadcastMessages(): Long {
        return this.stats.getLong(broadcastMessagesId)
    }

    /**
     * Increments the total number of messages broadcast by the distribution manager
     */
    override fun incBroadcastMessages(messages: Long) {
        this.stats.incLong(broadcastMessagesId, messages)
    }

    /**
     * Returns the total number of nanoseconds spent sending messages.
     */
    override fun getBroadcastMessagesTime(): Long {
        return this.stats.getLong(broadcastMessagesTimeId)
    }

    /**
     * Increments the total number of nanoseconds spend sending messages.
     */
    override fun incBroadcastMessagesTime(nanos: Long) {
        if (enableClockStats) {
            this.stats.incLong(broadcastMessagesTimeId, nanos)
        }
    }

    /**
     * Returns the total number of messages received by the distribution manager
     */
    override fun getReceivedMessages(): Long {
        return this.stats.getLong(receivedMessagesId)
    }

    /**
     * Increments the total number of messages received by the distribution manager
     */
    override fun incReceivedMessages(messages: Long) {
        this.stats.incLong(receivedMessagesId, messages)
    }

    /**
     * Returns the total number of bytes received by the distribution manager
     */
    override fun getReceivedBytes(): Long {
        return this.stats.getLong(receivedBytesId)
    }

    override fun incReceivedBytes(bytes: Long) {
        this.stats.incLong(receivedBytesId, bytes)
    }

    override fun incSentBytes(bytes: Long) {
        this.stats.incLong(sentBytesId, bytes)
    }

    /**
     * Returns the total number of messages processed by the distribution manager
     */
    override fun getProcessedMessages(): Long {
        return this.stats.getLong(processedMessagesId)
    }

    /**
     * Increments the total number of messages processed by the distribution manager
     */
    override fun incProcessedMessages(messages: Long) {
        this.stats.incLong(processedMessagesId, messages)
    }

    /**
     * Returns the total number of nanoseconds spent processing messages.
     */
    override fun getProcessedMessagesTime(): Long {
        return this.stats.getLong(processedMessagesTimeId)
    }

    /**
     * Increments the total number of nanoseconds spend processing messages.
     */
    override fun incProcessedMessagesTime(start: Long) {
        if (enableClockStats) {
            this.stats.incLong(processedMessagesTimeId, statTime - start)
        }
    }

    /**
     * Returns the total number of nanoseconds spent scheduling messages to be processed.
     */
    override fun getMessageProcessingScheduleTime(): Long {
        return this.stats.getLong(messageProcessingScheduleTimeId)
    }

    /**
     * Increments the total number of nanoseconds spent scheduling messages to be processed.
     */
    override fun incMessageProcessingScheduleTime(elapsed: Long) {
        if (enableClockStats) {
            this.stats.incLong(messageProcessingScheduleTimeId, elapsed)
        }
    }

    override fun getOverflowQueueSize(): Int {
        return this.stats.getInt(overflowQueueSizeId)
    }

    override fun incOverflowQueueSize(messages: Int) {
        this.stats.incInt(overflowQueueSizeId, messages)
    }

    protected fun incWaitingQueueSize(messages: Int) {
        this.stats.incInt(waitingQueueSizeId, messages)
    }

    protected fun incOverflowQueueThrottleCount(delays: Int) {
        this.stats.incInt(overflowQueueThrottleCountId, delays)
    }

    protected fun incOverflowQueueThrottleTime(nanos: Long) {
        if (enableClockStats) {
            this.stats.incLong(overflowQueueThrottleTimeId, nanos)
        }
    }

    protected fun incHighPriorityQueueSize(messages: Int) {
        this.stats.incInt(highPriorityQueueSizeId, messages)
    }

    protected fun incHighPriorityQueueThrottleCount(delays: Int) {
        this.stats.incInt(highPriorityQueueThrottleCountId, delays)
    }

    protected fun incHighPriorityQueueThrottleTime(nanos: Long) {
        if (enableClockStats) {
            this.stats.incLong(highPriorityQueueThrottleTimeId, nanos)
        }
    }

    protected fun incPartitionedRegionQueueSize(messages: Int) {
        this.stats.incInt(partitionedRegionQueueSizeId, messages)
    }

    protected fun incPartitionedRegionQueueThrottleCount(delays: Int) {
        this.stats.incInt(partitionedRegionQueueThrottleCountId, delays)
    }

    protected fun incPartitionedRegionQueueThrottleTime(nanos: Long) {
        if (enableClockStats) {
            this.stats.incLong(partitionedRegionQueueThrottleTimeId, nanos)
        }
    }

    protected fun incFunctionExecutionQueueSize(messages: Int) {
        this.stats.incInt(functionExecutionQueueSizeId, messages)
    }

    protected fun incFunctionExecutionQueueThrottleCount(delays: Int) {
        this.stats.incInt(functionExecutionQueueThrottleCountId, delays)
    }

    protected fun incFunctionExecutionQueueThrottleTime(nanos: Long) {
        if (enableClockStats) {
            this.stats.incLong(functionExecutionQueueThrottleTimeId, nanos)
        }
    }

    protected fun incSerialQueueSize(messages: Int) {
        this.stats.incInt(serialQueueSizeId, messages)
    }

    protected fun incSerialQueueBytes(amount: Int) {
        this.stats.incInt(serialQueueBytesId, amount)
    }

    protected fun incSerialPooledThread() {
        this.stats.incInt(serialPooledThreadId, 1)
    }

    protected fun incSerialQueueThrottleCount(delays: Int) {
        this.stats.incInt(serialQueueThrottleCountId, delays)
    }

    protected fun incSerialQueueThrottleTime(nanos: Long) {
        if (enableClockStats) {
            this.stats.incLong(serialQueueThrottleTimeId, nanos)
        }
    }

    override fun getNumProcessingThreads(): Int {
        return this.stats.getInt(processingThreadsId)
    }

    override fun incNumProcessingThreads(threads: Int) {
        this.stats.incInt(processingThreadsId, threads)
    }

    override fun getNumSerialThreads(): Int {
        return this.stats.getInt(serialThreadsId)
    }

    override fun incNumSerialThreads(threads: Int) {
        this.stats.incInt(serialThreadsId, threads)
    }

    protected fun incWaitingThreads(threads: Int) {
        this.stats.incInt(waitingThreadsId, threads)
    }

    protected fun incHighPriorityThreads(threads: Int) {
        this.stats.incInt(highPriorityThreadsId, threads)
    }

    protected fun incPartitionedRegionThreads(threads: Int) {
        this.stats.incInt(partitionedRegionThreadsId, threads)
    }

    protected fun incFunctionExecutionThreads(threads: Int) {
        this.stats.incInt(functionExecutionThreadsId, threads)
    }

    override fun incMessageChannelTime(delta: Long) {
        if (enableClockStats) {
            this.stats.incLong(messageChannelTimeId, delta)
        }
    }

    override fun incUDPDispatchRequestTime(delta: Long) {
        if (enableClockStats) {
            this.stats.incLong(udpDispatchRequestTimeId, delta)
        }
    }

    override fun getUDPDispatchRequestTime(): Long {
        return this.stats.getLong(udpDispatchRequestTimeId)
    }

    override fun getReplyMessageTime(): Long {
        return this.stats.getLong(replyMessageTimeId)
    }

    override fun incReplyMessageTime(`val`: Long) {
        if (enableClockStats) {
            this.stats.incLong(replyMessageTimeId, `val`)
        }
    }

    override fun getDistributeMessageTime(): Long {
        return this.stats.getLong(distributeMessageTimeId)
    }

    override fun incDistributeMessageTime(`val`: Long) {
        if (enableClockStats) {
            this.stats.incLong(distributeMessageTimeId, `val`)
        }
    }

    override fun getNodes(): Int {
        return this.stats.getInt(nodesId)
    }

    override fun setNodes(`val`: Int) {
        this.stats.setInt(nodesId, `val`)
    }

    override fun incNodes(`val`: Int) {
        this.stats.incInt(nodesId, `val`)
    }

    override fun getReplyWaitsInProgress(): Int {
        return stats.getInt(replyWaitsInProgressId)
    }

    override fun getReplyWaitsCompleted(): Int {
        return stats.getInt(replyWaitsCompletedId)
    }

    override fun getReplyWaitTime(): Long {
        return stats.getLong(replyWaitTimeId)
    }

    override fun startSocketWrite(sync: Boolean): Long {
        if (sync) {
            stats.incInt(syncSocketWritesInProgressId, 1)
        } else {
            stats.incInt(asyncSocketWritesInProgressId, 1)
        }
        return statTime
    }

    override fun endSocketWrite(sync: Boolean, start: Long, bytesWritten: Int, retries: Int) {
        val now = statTime
        if (sync) {
            stats.incInt(syncSocketWritesInProgressId, -1)
            stats.incInt(syncSocketWritesId, 1)
            stats.incLong(syncSocketWriteBytesId, bytesWritten)
            if (enableClockStats) {
                stats.incLong(syncSocketWriteTimeId, now - start)
            }
        } else {
            stats.incInt(asyncSocketWritesInProgressId, -1)
            stats.incInt(asyncSocketWritesId, 1)
            if (retries != 0) {
                stats.incInt(asyncSocketWriteRetriesId, retries)
            }
            stats.incLong(asyncSocketWriteBytesId, bytesWritten)
            if (enableClockStats) {
                stats.incLong(asyncSocketWriteTimeId, now - start)
            }
        }
    }

    override fun startSocketLock(): Long {
        stats.incInt(socketLocksInProgressId, 1)
        return statTime
    }

    override fun endSocketLock(start: Long) {
        val ts = statTime
        stats.incInt(socketLocksInProgressId, -1)
        stats.incInt(socketLocksId, 1)
        stats.incLong(socketLockTimeId, ts - start)
    }

    override fun startBufferAcquire(): Long {
        stats.incInt(bufferAcquiresInProgressId, 1)
        return statTime
    }

    override fun endBufferAcquire(start: Long) {
        val ts = statTime
        stats.incInt(bufferAcquiresInProgressId, -1)
        stats.incInt(bufferAcquiresId, 1)
        stats.incLong(bufferAcquireTimeId, ts - start)
    }

    override fun incUcastWriteBytes(bytesWritten: Int) {
        stats.incInt(ucastWritesId, 1)
        stats.incLong(ucastWriteBytesId, bytesWritten)
    }

    override fun incMcastWriteBytes(bytesWritten: Int) {
        stats.incInt(mcastWritesId, 1)
        stats.incLong(mcastWriteBytesId, bytesWritten)
    }

    override fun getMcastWrites(): Int {
        return stats.getInt(mcastWritesId)
    }

    override fun getMcastReads(): Int {
        return stats.getInt(mcastReadsId)
    }

    override fun getUDPMsgDecryptionTime(): Long {
        return stats.getLong(udpMsgDecryptionTimeId)
    }

    override fun getUDPMsgEncryptionTiime(): Long {
        return stats.getLong(udpMsgEncryptionTimeId)
    }

    override fun incMcastReadBytes(amount: Int) {
        stats.incInt(mcastReadsId, 1)
        stats.incLong(mcastReadBytesId, amount)
    }

    override fun incUcastReadBytes(amount: Int) {
        stats.incInt(ucastReadsId, 1)
        stats.incLong(ucastReadBytesId, amount)
    }

    override fun startSerialization(): Long {
        return statTime
    }

    override fun endSerialization(start: Long, bytes: Int) {
        if (enableClockStats) {
            stats.incLong(serializationTimeId, statTime - start)
        }
        stats.incInt(serializationsId, 1)
        stats.incLong(serializedBytesId, bytes)
    }

    override fun startPdxInstanceDeserialization(): Long {
        return statTime
    }

    override fun endPdxInstanceDeserialization(start: Long) {
        if (enableClockStats) {
            stats.incLong(pdxInstanceDeserializationTimeId, statTime - start)
        }
        stats.incInt(pdxInstanceDeserializationsId, 1)
    }

    override fun incPdxSerialization(bytes: Int) {
        stats.incInt(pdxSerializationsId, 1)
        stats.incLong(pdxSerializedBytesId, bytes)
    }

    override fun incPdxDeserialization(bytes: Int) {
        stats.incInt(pdxDeserializationsId, 1)
        stats.incLong(pdxDeserializedBytesId, bytes)
    }

    override fun incPdxInstanceCreations() {
        stats.incInt(pdxInstanceCreationsId, 1)
    }

    override fun startDeserialization(): Long {
        return statTime
    }

    override fun endDeserialization(start: Long, bytes: Int) {
        if (enableClockStats) {
            stats.incLong(deserializationTimeId, statTime - start)
        }
        stats.incInt(deserializationsId, 1)
        stats.incLong(deserializedBytesId, bytes)
    }

    override fun startMsgSerialization(): Long {
        return statTime
    }

    override fun endMsgSerialization(start: Long) {
        if (enableClockStats) {
            stats.incLong(msgSerializationTimeId, statTime - start)
        }
    }

    override fun startUDPMsgEncryption(): Long {
        return statTime
    }

    override fun endUDPMsgEncryption(start: Long) {
        if (enableClockStats) {
            stats.incLong(udpMsgEncryptionTimeId, statTime - start)
        }
    }

    override fun startMsgDeserialization(): Long {
        return statTime
    }

    override fun endMsgDeserialization(start: Long) {
        if (enableClockStats) {
            stats.incLong(msgDeserializationTimeId, statTime - start)
        }
    }

    override fun startUDPMsgDecryption(): Long {
        return statTime
    }

    override fun endUDPMsgDecryption(start: Long) {
        if (enableClockStats) {
            stats.incLong(udpMsgDecryptionTimeId, statTime - start)
        }
    }

    /**
     * @return the timestamp that marks the start of the operation
     */
    override fun startReplyWait(): Long {
        stats.incInt(replyWaitsInProgressId, 1)
        return statTime
    }

    override fun endReplyWait(startNanos: Long, initTime: Long) {
        if (enableClockStats) {
            stats.incLong(replyWaitTimeId, statTime - startNanos)
            // this.replyWaitHistogram.endOp(delta);
        }
        if (initTime != 0L) {
            val mswait = System.currentTimeMillis() - initTime
            if (mswait > replyWaitMaxTime) {
                stats.setLong(replyWaitMaxTimeId, mswait)
            }
        }
        stats.incInt(replyWaitsInProgressId, -1)
        stats.incInt(replyWaitsCompletedId, 1)

        Breadcrumbs.setSendSide(null) // clear any recipient breadcrumbs set by the message
        Breadcrumbs.setProblem(null) // clear out reply-wait errors
    }

    override fun incReplyTimeouts() {
        stats.incLong(replyTimeoutsId, 1L)
    }

    override fun getReplyTimeouts(): Long {
        return stats.getLong(replyTimeoutsId)
    }

    override fun incReceivers() {
        stats.incInt(receiverConnectionsId, 1)
    }

    override fun decReceivers() {
        stats.incInt(receiverConnectionsId, -1)
    }

    override fun incFailedAccept() {
        stats.incInt(failedAcceptsId, 1)
    }

    override fun incFailedConnect() {
        stats.incInt(failedConnectsId, 1)
    }

    override fun incReconnectAttempts() {
        stats.incInt(reconnectAttemptsId, 1)
    }

    override fun incLostLease() {
        stats.incInt(lostConnectionLeaseId, 1)
    }

    override fun incSenders(shared: Boolean, preserveOrder: Boolean) {
        if (shared) {
            if (preserveOrder) {
                stats.incInt(sharedOrderedSenderConnectionsId, 1)
            } else {
                stats.incInt(sharedUnorderedSenderConnectionsId, 1)
            }
        } else {
            if (preserveOrder) {
                stats.incInt(threadOrderedSenderConnectionsId, 1)
            } else {
                stats.incInt(threadUnorderedSenderConnectionsId, 1)
            }
        }
    }

    override fun getSendersSU(): Int {
        return stats.getInt(sharedUnorderedSenderConnectionsId)
    }

    override fun decSenders(shared: Boolean, preserveOrder: Boolean) {
        if (shared) {
            if (preserveOrder) {
                stats.incInt(sharedOrderedSenderConnectionsId, -1)
            } else {
                stats.incInt(sharedUnorderedSenderConnectionsId, -1)
            }
        } else {
            if (preserveOrder) {
                stats.incInt(threadOrderedSenderConnectionsId, -1)
            } else {
                stats.incInt(threadUnorderedSenderConnectionsId, -1)
            }
        }
    }

    override fun getAsyncSocketWritesInProgress(): Int {
        return stats.getInt(asyncSocketWritesInProgressId)
    }

    override fun getAsyncSocketWrites(): Int {
        return stats.getInt(asyncSocketWritesId)
    }

    override fun getAsyncSocketWriteRetries(): Int {
        return stats.getInt(asyncSocketWriteRetriesId)
    }

    override fun getAsyncSocketWriteBytes(): Long {
        return stats.getLong(asyncSocketWriteBytesId)
    }

    override fun getAsyncSocketWriteTime(): Long {
        return stats.getLong(asyncSocketWriteTimeId)
    }

    override fun getAsyncQueueAddTime(): Long {
        return stats.getLong(asyncQueueAddTimeId)
    }

    override fun incAsyncQueueAddTime(inc: Long) {
        if (enableClockStats) {
            stats.incLong(asyncQueueAddTimeId, inc)
        }
    }

    override fun getAsyncQueueRemoveTime(): Long {
        return stats.getLong(asyncQueueRemoveTimeId)
    }

    override fun incAsyncQueueRemoveTime(inc: Long) {
        if (enableClockStats) {
            stats.incLong(asyncQueueRemoveTimeId, inc)
        }
    }

    override fun getAsyncQueues(): Int {
        return stats.getInt(asyncQueuesId)
    }

    override fun incAsyncQueues(inc: Int) {
        stats.incInt(asyncQueuesId, inc)
    }

    override fun getAsyncQueueFlushesInProgress(): Int {
        return stats.getInt(asyncQueueFlushesInProgressId)
    }

    override fun getAsyncQueueFlushesCompleted(): Int {
        return stats.getInt(asyncQueueFlushesCompletedId)
    }

    override fun getAsyncQueueFlushTime(): Long {
        return stats.getLong(asyncQueueFlushTimeId)
    }

    override fun startAsyncQueueFlush(): Long {
        stats.incInt(asyncQueueFlushesInProgressId, 1)
        return statTime
    }

    override fun endAsyncQueueFlush(start: Long) {
        stats.incInt(asyncQueueFlushesInProgressId, -1)
        stats.incInt(asyncQueueFlushesCompletedId, 1)
        if (enableClockStats) {
            stats.incLong(asyncQueueFlushTimeId, statTime - start)
        }
    }

    override fun getAsyncQueueTimeouts(): Int {
        return stats.getInt(asyncQueueTimeoutExceededId)
    }

    override fun incAsyncQueueTimeouts(inc: Int) {
        stats.incInt(asyncQueueTimeoutExceededId, inc)
    }

    override fun getAsyncQueueSizeExceeded(): Int {
        return stats.getInt(asyncQueueSizeExceededId)
    }

    override fun incAsyncQueueSizeExceeded(inc: Int) {
        stats.incInt(asyncQueueSizeExceededId, inc)
    }

    override fun getAsyncDistributionTimeoutExceeded(): Int {
        return stats.getInt(asyncDistributionTimeoutExceededId)
    }

    override fun incAsyncDistributionTimeoutExceeded() {
        stats.incInt(asyncDistributionTimeoutExceededId, 1)
    }

    override fun getAsyncQueueSize(): Long {
        return stats.getLong(asyncQueueSizeId)
    }

    override fun incAsyncQueueSize(inc: Long) {
        stats.incLong(asyncQueueSizeId, inc)
    }

    override fun getAsyncQueuedMsgs(): Long {
        return stats.getLong(asyncQueuedMsgsId)
    }

    override fun incAsyncQueuedMsgs() {
        stats.incLong(asyncQueuedMsgsId, 1)
    }

    override fun getAsyncDequeuedMsgs(): Long {
        return stats.getLong(asyncDequeuedMsgsId)
    }

    override fun incAsyncDequeuedMsgs() {
        stats.incLong(asyncDequeuedMsgsId, 1)
    }

    override fun getAsyncConflatedMsgs(): Long {
        return stats.getLong(asyncConflatedMsgsId)
    }

    override fun incAsyncConflatedMsgs() {
        stats.incLong(asyncConflatedMsgsId, 1)
    }

    override fun getAsyncThreads(): Int {
        return stats.getInt(asyncThreadsId)
    }

    override fun incAsyncThreads(inc: Int) {
        stats.incInt(asyncThreadsId, inc)
    }

    override fun getAsyncThreadInProgress(): Int {
        return stats.getInt(asyncThreadInProgressId)
    }

    override fun getAsyncThreadCompleted(): Int {
        return stats.getInt(asyncThreadCompletedId)
    }

    override fun getAsyncThreadTime(): Long {
        return stats.getLong(asyncThreadTimeId)
    }

    override fun startAsyncThread(): Long {
        stats.incInt(asyncThreadInProgressId, 1)
        return statTime
    }

    override fun endAsyncThread(start: Long) {
        stats.incInt(asyncThreadInProgressId, -1)
        stats.incInt(asyncThreadCompletedId, 1)
        if (enableClockStats) {
            stats.incLong(asyncThreadTimeId, statTime - start)
        }
    }

    override fun incBatchSendTime(start: Long) {
        if (enableClockStats) {
            stats.incLong(batchSendTimeId, statTime - start)
        }
    }

    override fun incBatchCopyTime(start: Long) {
        if (enableClockStats) {
            stats.incLong(batchCopyTimeId, statTime - start)
        }
    }

    override fun incBatchWaitTime(start: Long) {
        if (enableClockStats) {
            stats.incLong(batchWaitTimeId, statTime - start)
        }
    }

    override fun incBatchFlushTime(start: Long) {
        if (enableClockStats) {
            stats.incLong(batchFlushTimeId, statTime - start)
        }
    }

    override fun incUcastRetransmits() {
        stats.incInt(ucastRetransmitsId, 1)
    }

    override fun incMcastRetransmits() {
        stats.incInt(mcastRetransmitsId, 1)
    }

    override fun incMcastRetransmitRequests() {
        stats.incInt(mcastRetransmitRequestsId, 1)
    }

    override fun getMcastRetransmits(): Int {
        return stats.getInt(mcastRetransmitsId)
    }

    override fun incThreadOwnedReceivers(value: Long, dominoCount: Int) {
        if (dominoCount < 2) {
            stats.incLong(threadOwnedReceiversId, value)
        } else {
            stats.incLong(threadOwnedReceiversId2, value)
        }
    }

    /**
     * @since GemFire 5.0.2.4
     */
    override fun incReceiverBufferSize(inc: Int, direct: Boolean) {
        if (direct) {
            stats.incLong(receiverDirectBufferSizeId, inc)
        } else {
            stats.incLong(receiverHeapBufferSizeId, inc)
        }
    }

    /**
     * @since GemFire 5.0.2.4
     */
    override fun incSenderBufferSize(inc: Int, direct: Boolean) {
        if (direct) {
            stats.incLong(senderDirectBufferSizeId, inc)
        } else {
            stats.incLong(senderHeapBufferSizeId, inc)
        }
    }

    override fun incMessagesBeingReceived(newMsg: Boolean, bytes: Int) {
        if (newMsg) {
            stats.incInt(messagesBeingReceivedId, 1)
        }
        stats.incLong(messageBytesBeingReceivedId, bytes)
    }

    override fun decMessagesBeingReceived(bytes: Int) {
        stats.incInt(messagesBeingReceivedId, -1)
        stats.incLong(messageBytesBeingReceivedId, -bytes)
    }

    fun incSerialThreadStarts() {
        stats.incLong(serialThreadStartsId, 1)
    }

    fun incViewThreadStarts() {
        stats.incLong(viewThreadStartsId, 1)
    }

    fun incProcessingThreadStarts() {
        stats.incLong(processingThreadStartsId, 1)
    }

    fun incHighPriorityThreadStarts() {
        stats.incLong(highPriorityThreadStartsId, 1)
    }

    fun incWaitingThreadStarts() {
        stats.incLong(waitingThreadStartsId, 1)
    }

    fun incPartitionedRegionThreadStarts() {
        stats.incLong(partitionedRegionThreadStartsId, 1)
    }

    fun incFunctionExecutionThreadStarts() {
        stats.incLong(functionExecutionThreadStartsId, 1)
    }

    fun incSerialPooledThreadStarts() {
        stats.incLong(serialPooledThreadStartsId, 1)
    }

    override fun incReplyHandOffTime(start: Long) {
        if (enableClockStats) {
            val delta = statTime - start
            stats.incLong(replyHandoffTimeId, delta)
            // this.replyHandoffHistogram.endOp(delta);
        }
    }

    protected fun incPartitionedRegionThreadJobs(i: Int) {
        this.stats.incInt(partitionedRegionThreadJobsId, i)
    }

    protected fun incFunctionExecutionThreadJobs(i: Int) {
        this.stats.incInt(functionExecutionThreadJobsId, i)
    }

    fun incNumViewThreads(threads: Int) {
        this.stats.incInt(viewThreadsId, threads)
    }

    protected fun incNumSerialThreadJobs(jobs: Int) {
        this.stats.incInt(serialThreadJobsId, jobs)
    }

    protected fun incViewProcessorThreadJobs(jobs: Int) {
        this.stats.incInt(viewProcessorThreadJobsId, jobs)
    }

    protected fun incSerialPooledProcessorThreadJobs(jobs: Int) {
        this.stats.incInt(serialPooledThreadJobsId, jobs)
    }

    protected fun incNormalPoolThreadJobs(jobs: Int) {
        this.stats.incInt(pooledMessageThreadJobsId, jobs)
    }

    protected fun incHighPriorityThreadJobs(jobs: Int) {
        this.stats.incInt(highPriorityThreadJobsId, jobs)
    }

    protected fun incWaitingPoolThreadJobs(jobs: Int) {
        this.stats.incInt(waitingPoolThreadJobsId, jobs)
    }

    override fun getElders(): Int {
        return this.stats.getInt(eldersId)
    }

    override fun incElders(`val`: Int) {
        this.stats.incInt(eldersId, `val`)
    }

    override fun getInitialImageMessagesInFlight(): Int {
        return this.stats.getInt(initialImageMessagesInFlightId)
    }

    override fun incInitialImageMessagesInFlight(`val`: Int) {
        this.stats.incInt(initialImageMessagesInFlightId, `val`)
    }

    override fun getInitialImageRequestsInProgress(): Int {
        return this.stats.getInt(initialImageRequestsInProgressId)
    }

    override fun incInitialImageRequestsInProgress(`val`: Int) {
        this.stats.incInt(initialImageRequestsInProgressId, `val`)
    }

    // For GMSHealthMonitor
    override fun getHeartbeatRequestsSent(): Long {
        return this.stats.getLong(heartbeatRequestsSentId)
    }

    override fun incHeartbeatRequestsSent() {
        this.stats.incLong(heartbeatRequestsSentId, 1L)
    }

    override fun getHeartbeatRequestsReceived(): Long {
        return this.stats.getLong(heartbeatRequestsReceivedId)
    }

    override fun incHeartbeatRequestsReceived() {
        this.stats.incLong(heartbeatRequestsReceivedId, 1L)
    }

    override fun getHeartbeatsSent(): Long {
        return this.stats.getLong(heartbeatsSentId)
    }

    override fun incHeartbeatsSent() {
        this.stats.incLong(heartbeatsSentId, 1L)
    }

    override fun getHeartbeatsReceived(): Long {
        return this.stats.getLong(heartbeatsReceivedId)
    }

    override fun incHeartbeatsReceived() {
        this.stats.incLong(heartbeatsReceivedId, 1L)
    }

    override fun getSuspectsSent(): Long {
        return this.stats.getLong(suspectsSentId)
    }

    override fun incSuspectsSent() {
        this.stats.incLong(suspectsSentId, 1L)
    }

    override fun getSuspectsReceived(): Long {
        return this.stats.getLong(suspectsReceivedId)
    }

    override fun incSuspectsReceived() {
        this.stats.incLong(suspectsReceivedId, 1L)
    }

    override fun getFinalCheckRequestsSent(): Long {
        return this.stats.getLong(finalCheckRequestsSentId)
    }

    override fun incFinalCheckRequestsSent() {
        this.stats.incLong(finalCheckRequestsSentId, 1L)
    }

    override fun getFinalCheckRequestsReceived(): Long {
        return this.stats.getLong(finalCheckRequestsReceivedId)
    }

    override fun incFinalCheckRequestsReceived() {
        this.stats.incLong(finalCheckRequestsReceivedId, 1L)
    }

    override fun getFinalCheckResponsesSent(): Long {
        return this.stats.getLong(finalCheckResponsesSentId)
    }

    override fun incFinalCheckResponsesSent() {
        this.stats.incLong(finalCheckResponsesSentId, 1L)
    }

    override fun getFinalCheckResponsesReceived(): Long {
        return this.stats.getLong(finalCheckResponsesReceivedId)
    }

    override fun incFinalCheckResponsesReceived() {
        this.stats.incLong(finalCheckResponsesReceivedId, 1L)
    }

    ///
    override fun getTcpFinalCheckRequestsSent(): Long {
        return this.stats.getLong(tcpFinalCheckRequestsSentId)
    }

    override fun incTcpFinalCheckRequestsSent() {
        this.stats.incLong(tcpFinalCheckRequestsSentId, 1L)
    }

    override fun getTcpFinalCheckRequestsReceived(): Long {
        return this.stats.getLong(tcpFinalCheckRequestsReceivedId)
    }

    override fun incTcpFinalCheckRequestsReceived() {
        this.stats.incLong(tcpFinalCheckRequestsReceivedId, 1L)
    }

    override fun getTcpFinalCheckResponsesSent(): Long {
        return this.stats.getLong(tcpFinalCheckResponsesSentId)
    }

    override fun incTcpFinalCheckResponsesSent() {
        this.stats.incLong(tcpFinalCheckResponsesSentId, 1L)
    }

    override fun getTcpFinalCheckResponsesReceived(): Long {
        return this.stats.getLong(tcpFinalCheckResponsesReceivedId)
    }

    override fun incTcpFinalCheckResponsesReceived() {
        this.stats.incLong(tcpFinalCheckResponsesReceivedId, 1L)
    }

    ///
    override fun getUdpFinalCheckRequestsSent(): Long {
        return this.stats.getLong(udpFinalCheckRequestsSentId)
    }

    override fun incUdpFinalCheckRequestsSent() {
        this.stats.incLong(udpFinalCheckRequestsSentId, 1L)
    }

    // UDP final check is implemented using HeartbeatRequestMessage and HeartbeatMessage
    // So the following code is commented out
    // public long getUdpFinalCheckRequestsReceived() {
    // return this.stats.getLong(udpFinalCheckRequestsReceivedId);
    // }
    //
    // public void incUdpFinalCheckRequestsReceived() {
    // this.stats.incLong(udpFinalCheckRequestsReceivedId, 1L);
    // }
    //
    // public long getUdpFinalCheckResponsesSent() {
    // return this.stats.getLong(udpFinalCheckResponsesSentId);
    // }
    //
    // public void incUdpFinalCheckResponsesSent() {
    // this.stats.incLong(udpFinalCheckResponsesSentId, 1L);
    // }

    override fun getUdpFinalCheckResponsesReceived(): Long {
        return this.stats.getLong(udpFinalCheckResponsesReceivedId)
    }

    override fun incUdpFinalCheckResponsesReceived() {
        this.stats.incLong(udpFinalCheckResponsesReceivedId, 1L)
    }

    companion object {
        init {

            // For GMSHealthMonitor

            val distributionMessageMeter = CounterStatisticMeter("distribution.messages", "The number of distribution messages that this GemFire system has sent. This includes broadcastMessages.", arrayOf("direction", "sent"))
            val distributionMessageTransactionCommitMeter = CounterStatisticMeter("distribution.message.created", "The number of transaction commit messages that this GemFire system has created to be sent. " + "Note that it is possible for a commit to only create one message even though it will end up being sent to multiple recipients.", arrayOf("type", "transaction-commit"))
            val distributionMessageTransactionCommitTimer = TimerStatisticMeter("distribution.message.created.time", "The number of transaction commits that had to wait for a response before they could complete.", arrayOf("type", "transaction-commit"))
            val distributionMessageTimer = TimerStatisticMeter("distribution.messages.time", "The total amount of time this distribution manager has spent sending messages. This includes broadcastMessagesTime.", arrayOf("direction", "sent"), unit = "nanoseconds")
            val distributionBroadcastMessageMeter = CounterStatisticMeter("distribution.messages", "The number of distribution messages that this GemFire system has broadcast. A broadcast message is one sent to every other manager in the group.", arrayOf("direction", "sent", "messageType", "broadcast"))
            val distributionBroadcastMessageTimer = TimerStatisticMeter("distribution.messages.time", "The total amount of time this distribution manager has spent broadcasting messages. A broadcast message is one sent to every other manager in the group.", arrayOf("messageType", "broadcast", "direction", "sent"), unit = "nanoseconds")
            val distributionMessageReceivedMeter = CounterStatisticMeter("distribution.messages", "The number of distribution messages that this GemFire system has received.", arrayOf("direction", "received"))
            val distributionMessageReceivedBytesMeter = CounterStatisticMeter("distribution.messages.bytes", "The number of distribution message bytes that this GemFire system has received.", arrayOf("direction", "received"), unit = "bytes")
            val distributionMessageSentBytesMeter = CounterStatisticMeter("distribution.messages.bytes", "The number of distribution message bytes that this GemFire system has sent.", arrayOf("direction", "sent"), unit = "bytes")
            val distributionMessageProcessedMeter = CounterStatisticMeter("distribution.messages", "The number of distribution messages that this GemFire system has processed.", arrayOf("status", "processed"))
            val distributionMessageTimeoutMeter = CounterStatisticMeter("distribution.messages", "Total number of message replies that have timed out.", arrayOf("status", "timeout"))
            val distributionMessageProcessedTimer = TimerStatisticMeter("distribution.messages.time", "The amount of time this distribution manager has spent in message.process().", arrayOf("status", "processed"), unit = "nanoseconds")
            val distributionMessageDispatchTimer = TimerStatisticMeter("distribution.messages.dispatch.time", "The amount of time this distribution manager has spent dispatching message to processor threads.", unit = "nanoseconds")
            val distributionQueueWaitingMeter = GaugeStatisticMeter("distribution.message.queued.waiting", "The number of distribution messages currently waiting for some other resource before they can be processed.")
            val distributionQueueOverflowMeter = GaugeStatisticMeter("distribution.message.queued.overflow", "The number of normal distribution messages currently waiting to be processed.", arrayOf("priority", "normal"))
            val distributionQueueOverflowThrottleMeter = GaugeStatisticMeter("distribution.message.queued.overflow.throttle", "The total number of times a thread was delayed in adding a normal message to the overflow queue.", arrayOf("priority", "normal"))
            val distributionQueueOverflowThrottleTimer = TimerStatisticMeter("distribution.message.queued.overflow.throttle.time", "The total amount of time, in nanoseconds, spent delayed by the overflow queue throttle.", arrayOf("priority", "normal"), unit = "nanoseconds")
            val distributionQueueHighPriorityMeter = GaugeStatisticMeter("distribution.message.queued.overflow", "The number of high priority distribution messages currently waiting to be processed.", arrayOf("priority", "high"))
            val distributionQueueHighPriorityThrottleMeter = GaugeStatisticMeter("distribution.message.queued.overflow.throttle", "The total number of times a thread was delayed in adding a normal message to the high priority queue.", arrayOf("priority", "high"))
            val distributionQueueHighPriorityThrottleTimer = TimerStatisticMeter("distribution.message.queued.overflow.throttle.time", "The total amount of time, in nanoseconds, spent delayed by the high priority queue throttle.", arrayOf("priority", "high"), unit = "nanoseconds")
            val distributionQueuePartitionedMeter = GaugeStatisticMeter("distribution.message.queued.overflow", "The number of high priority distribution messages currently waiting to be processed.", arrayOf("priority", "high", "type", "partitioned"))
            val distributionQueuePartitionedThrottleMeter = GaugeStatisticMeter("distribution.message.queued.overflow.throttle", "The total number of times a thread was delayed in adding a normal message to the high priority queue.", arrayOf("priority", "high", "type", "partitioned"))
            val distributionQueuePartitionedThrottleTimer = TimerStatisticMeter("distribution.message.queued.overflow.throttle.time", "The total amount of time, in nanoseconds, spent delayed by the high priority queue throttle.", arrayOf("priority", "high", "type", "partitioned"), unit = "nanoseconds")
            val distributionQueueFunctionMeter = GaugeStatisticMeter("distribution.message.queued.overflow", "The number of high priority distribution messages currently waiting to be processed.", arrayOf("priority", "high", "type", "function"))
            val distributionQueueFunctionThrottleMeter = GaugeStatisticMeter("distribution.message.queued.overflow.throttle", "The total number of times a thread was delayed in adding a normal message to the high priority queue.", arrayOf("priority", "high", "type", "function"))
            val distributionQueueFunctionThrottleTimer = TimerStatisticMeter("distribution.message.queued.overflow.throttle.time", "The total amount of time, in nanoseconds, spent delayed by the high priority queue throttle.", arrayOf("priority", "high", "type", "function"), unit = "nanoseconds")
            val distributionQueueSerialMeter = GaugeStatisticMeter("distribution.message.queued.overflow", "The number of serial distribution messages currently waiting to be processed.", arrayOf("priority", "high", "type", "serial"))
            val distributionQueueSerialThrottleMeter = GaugeStatisticMeter("distribution.message.queued.overflow.throttle", "The total number of times a thread was delayed in adding a ordered message to the serial queue.", arrayOf("priority", "high", "type", "serial"))
            val distributionQueueSerialThrottleTimer = TimerStatisticMeter("distribution.message.queued.overflow.throttle.time", "The total amount of time, in nanoseconds, spent delayed by the serial queue throttle.", arrayOf("priority", "high", "type", "serial"), unit = "nanoseconds")
            val distributionQueueSerialBytesMeter = GaugeStatisticMeter("distribution.message.queued.overflow.bytes", "The approximate number of bytes consumed by serial distribution messages currently waiting to be processed.", arrayOf("priority", "high", "type", "serial"), unit = "bytes")
            val distributionQueueSerialPoolThreadsMeter = CounterStatisticMeter("distribution.message.threads", "The number of threads created in the SerialQueuedExecutorPool.")
            val distributionThreadSerialMeter = GaugeStatisticMeter("distribution.message.threads", "The number of threads currently processing serial/ordered messages.", arrayOf("priority", "high", "type", "serial"))
            val distributionThreadNormalPriorityMeter = GaugeStatisticMeter("distribution.message.threads", "The number of threads currently processing normal messages.", arrayOf("priority", "normal"))
            val distributionThreadHighPriorityMeter = GaugeStatisticMeter("distribution.message.threads", "The number of threads currently processing high priority messages.", arrayOf("priority", "high"))
            val distributionThreadPartitionedMeter = GaugeStatisticMeter("distribution.message.threads", "The number of threads currently processing partitioned region messages.", arrayOf("priority", "high", "type", "partitioned"))
            val distributionThreadFunctionMeter = GaugeStatisticMeter("distribution.message.threads", "The number of threads currently processing function execution messages.", arrayOf("priority", "high", "type", "function"))
            val distributionThreadWaitingMeter = GaugeStatisticMeter("distribution.message.threads.waiting", "The number of threads currently processing messages that had to wait for a resource.")
            val distributionChannelReceivedTimer = TimerStatisticMeter("distribution.message.channel.time", "The total amount of time received messages spent in the distribution channel", unit = "nanoseconds")
            val distributionChannelUDPTimer = TimerStatisticMeter("distribution.message.channel.udp.time", "The total amount of time spent deserializing and dispatching UDP messages in the message-reader thread.", unit = "nanoseconds")
            val distributionChannelDispatchingTimer = TimerStatisticMeter("distribution.message.reply.time", "The amount of time spent processing reply messages. This includes both processedMessagesTime and messageProcessingScheduleTime.", unit = "nanoseconds")
            val distributionChannelDistributeTimer = TimerStatisticMeter("distribution.message.distribute.time", "The amount of time it takes to prepare a message and send it on the network.  This includes sentMessagesTime.", unit = "nanoseconds")
            val distributionNodesMeter = GaugeStatisticMeter("distribution.nodes", "The current number of nodes in this distributed system.")
            val distributionReplyThreadMeter = GaugeStatisticMeter("distribution.thread.reply", "Current number of threads waiting for a reply.", arrayOf("status", "waiting"))
            val distributionReplyThreadCompletedMeter = CounterStatisticMeter("distribution.thread.reply", "Total number of times waits for a reply have completed.", arrayOf("status", "completed"))
            val distributionReplyThreadWaitingTimer = TimerStatisticMeter("distribution.thread.reply.time", "Total time spent waiting for a reply to a message.", arrayOf("status", "waiting"), unit = "nanoseconds")
            val socketReceiverCountMeter = GaugeStatisticMeter("distribution.sockets", "Current number of sockets dedicated to receiving messages.", arrayOf("direction", "receiving"))
            val socketSenderSharedOrderedCountMeter = GaugeStatisticMeter("distribution.sockets", "Current number of shared sockets dedicated to sending ordered messages.", arrayOf("ordered", "true", "type", "shared", "direction", "sending"))
            val socketSenderSharedUnOrderedCountMeter = GaugeStatisticMeter("distribution.sockets", "Current number of shared sockets dedicated to sending unordered messages.", arrayOf("ordered", "false", "type", "shared", "direction", "sending"))
            val socketSenderThreadOrderedCountMeter = GaugeStatisticMeter("distribution.sockets", "Current number of thread sockets dedicated to sending ordered messages.", arrayOf("ordered", "true", "type", "thread", "direction", "sending"))
            val socketSenderThreadUnOrderedCountMeter = GaugeStatisticMeter("distribution.sockets", "Current number of thread sockets dedicated to sending unordered messages.", arrayOf("ordered", "false", "type", "thread", "direction", "sending"))
            val socketReceiverFailedMeter = CounterStatisticMeter("distribution.socket", "Total number of times an accept (receiver creation) of a connect from some other member has failed", arrayOf("type", "receiver", "status", "failed"))
            val socketSenderFailedMeter = CounterStatisticMeter("distribution.socket", "Total number of times a connect (sender creation) to some other member has failed.", arrayOf("type", "sender", "status", "failed"))
            val socketReconnectMeter = CounterStatisticMeter("distribution.socket", "Total number of times an established connection was lost and a reconnect was attempted.", arrayOf("status", "reconnect"))
            val socketSenderExpiredMeter = CounterStatisticMeter("distribution.socket", "Total number of times an unshared sender socket has remained idle long enough that its lease expired.", arrayOf("type", "sender", "status", "expired"))
            val socketWritesInProgressMeter = GaugeStatisticMeter("distribution.socket.writes.inprogress", "Current number of synchronous/blocking socket write calls in progress.", arrayOf("type", "sync"))
            val socketWritesTimer = TimerStatisticMeter("distribution.socket.writes.time", "Total amount of time, in nanoseconds, spent in synchronous/blocking socket write calls.", arrayOf("type", "sync"), unit = "nanoseconds")
            val socketWritesCompletedMeter = CounterStatisticMeter("distribution.socket.writes.completed", "Total number of completed synchronous/blocking socket write calls.", arrayOf("type", "sync"))
            val socketWritesBytesMeter = CounterStatisticMeter("distribution.socket.writes.bytes", "Total number of bytes sent out in synchronous/blocking mode on sockets.", arrayOf("type", "sync"), unit = "bytes")
            val socketUniCastReadMeter = CounterStatisticMeter("distribution.socket.unicast", "Total number of unicast datagrams received", arrayOf("type", "read"))
            val socketUniCastReadBytesMeter = CounterStatisticMeter("distribution.socket.unicast.bytes", "Total number of bytes received in unicast datagrams", arrayOf("type", "read"), unit = "bytes")
            val socketUniCastWriteMeter = CounterStatisticMeter("distribution.socket.unicast", "Total number of unicast datagram socket write calls.", arrayOf("type", "write"))
            val socketUniCastWriteBytesMeter = CounterStatisticMeter("distribution.socket.unicast.bytes", "Total number of bytes sent out on unicast datagram sockets.", arrayOf("type", "write"), unit = "bytes")
            val socketUniCastRetransmitMeter = CounterStatisticMeter("distribution.socket.unicast", "Total number of unicast datagram socket retransmissions", arrayOf("type", "retransmit"))
            val socketMultiCastReadMeter = CounterStatisticMeter("distribution.socket.multicast", "Total number of multicast datagrams received", arrayOf("type", "read"))
            val socketMultiCastReadBytesMeter = CounterStatisticMeter("mcastReadBytes", "Total number of bytes received in multicast datagrams", arrayOf("type", "read"))
            val socketMultiCastWriteMeter = CounterStatisticMeter("mcastWrites", "Total number of multicast datagram socket write calls.", arrayOf("type", "write"))
            val socketMultiCastWriteBytesMeter = CounterStatisticMeter("mcastWriteBytes", "Total number of bytes sent out on multicast datagram sockets.", arrayOf("type", "write"))
            val socketMultiCastRetransmitMeter = CounterStatisticMeter("mcastRetransmits", "Total number of multicast datagram socket retransmissions", arrayOf("type", "retransmit"))
            val socketMultiCastRetransmitRequestsMeter = CounterStatisticMeter("mcastRetransmitRequests", "Total number of multicast datagram socket retransmission requests sent to other processes", arrayOf("type", "retransmit-request"))
            val serializationTimer = TimerStatisticMeter("serialization.time", "Total amount of time, in nanoseconds, spent serializing objects. This includes pdx serializations.", unit = "nanoseconds")
            val serializationMeter = CounterStatisticMeter("serialization.count", "Total number of object serialization calls. This includes pdx serializations.")
            val serializationBytesMeter = CounterStatisticMeter("serialization.byte", "Total number of bytes produced by object serialization. This includes pdx serializations.", unit = "bytes")
            val serializationPdxMeter = CounterStatisticMeter("serialization.count", "Total number of pdx serializations.", arrayOf("type", "pdx"))
            val serializationPdxBytesMeter = CounterStatisticMeter("serialization.bytes", "Total number of bytes produced by pdx serialization.", arrayOf("type", "pdx"), unit = "bytes")
            val deserializationTimer = TimerStatisticMeter("deserialization.time","Total amount of time, in nanoseconds, spent deserializing objects. This includes deserialization that results in a PdxInstance.", unit = "nanoseconds")
            val deserializationMeter = CounterStatisticMeter("deserialization.count", "Total number of object deserialization calls. This includes deserialization that results in a PdxInstance.")
            val deserializationBytesMeter = CounterStatisticMeter("deserialization.bytes", "Total number of bytes read by object deserialization. This includes deserialization that results in a PdxInstance.", unit="bytes")
            val deserializationPdxMeter = CounterStatisticMeter("deserialization.count", "Total number of pdx deserializations.", arrayOf("type", "pdx"))
            val deserializationPdxBytesMeter = CounterStatisticMeter("deserialization.bytes", "Total number of bytes read by pdx deserialization.", arrayOf("type", "pdx"), unit = "bytes")
            val messageSerializationTimer = TimerStatisticMeter("serialization.message.timer", "Total amount of time, in nanoseconds, spent serializing messages.", unit = "nanoseconds")
            val messageDeserializationTimer = TimerStatisticMeter("deserialization.message.time", "Total amount of time, in nanoseconds, spent deserializing messages.", unit = "nanoseconds")
            val messageUDPEncryptionTimer = TimerStatisticMeter("udp.message.encryption.time", "Total amount of time, in nanoseconds, spent encrypting udp messages.", unit = "nanoseconds")
            val messageUDPDecryptionTimer = TimerStatisticMeter("udp.message.deencryption.time", "Total amount of time, in nanoseconds, spent decrypting udp messages.", unit = "nanoseconds")
            val deserializationPdxObjectMeter = CounterStatisticMeter("pdx.deserialization.object.count", "Total number of times getObject has been called on a PdxInstance.")
            val deserializationPdxObjectTimer = TimerStatisticMeter("pdx.deserialization.object.time", "Total amount of time, in nanoseconds, spent deserializing PdxInstances by calling getObject.", unit = "nanoseconds")
            val pdxObjectCreateMeter = CounterStatisticMeter("pdx.deserialization.object.create", "Total number of times a deserialization created a PdxInstance.")
            val batchSendTime = TimerStatisticMeter("batch.send.time", "Total amount of time, in nanoseconds, spent queueing and flushing message batches", unit = "nanoseconds")
            val batchWaitTime = TimerStatisticMeter("batch.wait.time", "Reserved for future use", arrayOf("type", "wait"), unit = "nanoseconds")
            val batchCopyTime = TimerStatisticMeter("batch.copy.time", "Total amount of time, in nanoseconds, spent copying messages for batched transmission", arrayOf("type", "copy"), unit = "nanoseconds")
            val batchFlushTime = TimerStatisticMeter("batch.flush.time", "Total amount of time, in nanoseconds, spent flushing batched messages to the network", arrayOf("type", "flush"), unit = "nanoseconds")
            val socketAsyncWriteInProgressMeter = GaugeStatisticMeter("distribution.socket.async.inprogress", "Current number of non-blocking socket write calls in progress.", arrayOf("type", "async"))
            val socketAsyncWriteCompletedMeter = CounterStatisticMeter("distribution.socket.async.completed", "Total number of non-blocking socket write calls completed.", arrayOf("type", "async"))
            val socketAsyncWriteRetriesMeter = CounterStatisticMeter("distribution.socket.async.retries", "Total number of retries needed to write a single block of data using non-blocking socket write calls.", arrayOf("type", "async"))
            val socketAsyncWriteTimer = TimerStatisticMeter("distribution.socket.async.time", "Total amount of time, in nanoseconds, spent in non-blocking socket write calls.", arrayOf("type", "async"), unit = "nanoseconds")
            val socketAsyncWriteBytesMeter = CounterStatisticMeter("distribution.socket.async.bytes", "Total number of bytes sent out on non-blocking sockets.", arrayOf("type", "async"), unit = "bytes")
            val asyncQueueAddTimer = TimerStatisticMeter("distribution.queue.async.time", "Total amount of time, in nanoseconds, spent in adding messages to async queue.", arrayOf("type", "async", "operation", "add"), unit = "nanoseconds")
            val asyncQueueRemoveTimer = TimerStatisticMeter("distribution.queue.async.time", "Total amount of time, in nanoseconds, spent in removing messages from async queue.", arrayOf("type", "async", "operation", "remove"), unit = "nanoseconds")
            val asyncQueueCountMeter = GaugeStatisticMeter("distribution.queue.async.count", "The current number of queues for asynchronous messaging.")
            val asyncQueueFlushesInProgressMeter = GaugeStatisticMeter("distribution.queue.async.flush.inprogress", "Current number of asynchronous queues being flushed.", arrayOf("type", "async"))
            val asyncQueueFlushedCompletedMeter = CounterStatisticMeter("distribution.queue.async.flush.completed", "Total number of asynchronous queue flushes completed.", arrayOf("type", "async"))
            val asyncQueueFlushesTimer = TimerStatisticMeter("distribution.queue.async.flush.time", "Total time spent flushing asynchronous queues.", arrayOf("type", "async"), unit = "nanoseconds")
            val asyncQueueTimeOutExceededMeter = CounterStatisticMeter("distribution.queue.async.timeout.exceeded", "Total number of asynchronous queues that have timed out by being blocked for more than async-queue-timeout milliseconds.", arrayOf("type", "async"))
            val asyncQueueSizeExceededMeter = CounterStatisticMeter("distribution.queue.async.size.exceeded", "Total number of asynchronous queues that have exceeded max size.", arrayOf("type", "async"))
            val asyncQueueDistributionTimeOutExceededMeter = CounterStatisticMeter("distribution.queue.async.distribution.timeout.exceeded", "Total number of times the async-distribution-timeout has been exceeded during a socket write.", arrayOf("type", "async"))
            val asyncQueueSizeBytesMeter = GaugeStatisticMeter("distribution.queue.async.bytes", "The current size in bytes used for asynchronous queues.", arrayOf("type", "async"), unit = "bytes")
            val asyncQueueQueuedMessagesMeter = CounterStatisticMeter("distribution.queue.async.queued", "The total number of queued messages used for asynchronous queues.", arrayOf("type", "async"))
            val asyncQueueDequeuedMessagesMeter = CounterStatisticMeter("distribution.queue.async.dequeued", "The total number of queued messages that have been removed from the queue and successfully sent.", arrayOf("type", "async"))
            val asyncQueueConflatedMessagesMeter = CounterStatisticMeter("distribution.queue.async.conflated", "The total number of queued conflated messages used for asynchronous queues.", arrayOf("type", "async"))
            val asyncQueueThreadMeter = GaugeStatisticMeter("distribution.queue.async.thread.max", "Total number of asynchronous message queue threads.", arrayOf("type", "async"))
            val asyncQueueThreadInProgressMeter = GaugeStatisticMeter("distribution.queue.async.thread.inprogress", "Current iterations of work performed by asynchronous message queue threads.", arrayOf("type", "async"))
            val asyncQueueThreadCompletedMeter = CounterStatisticMeter("distribution.queue.async.thread.completed", "Total number of iterations of work performed by asynchronous message queue threads.", arrayOf("type", "async"))
            val asyncQueueThreadTimer = TimerStatisticMeter("distribution.queue.async.thread.time", "Total time spent by asynchronous message queue threads performing iterations.", arrayOf("type", "async"), unit = "nanoseconds")
            val receiverThreadsOwnedByNonReceiverMeter = GaugeStatisticMeter("receivers.tread.owned.nonthread", "Number of receiver threads owned by non-receiver threads in other members.")
            val receiverThreadsOwnedByReceiverMeter = GaugeStatisticMeter("receivers.tread.owned.thread", "Number of receiver threads owned in turn by receiver threads in other members")
            val receiverDirectBufferSizeMeter = GaugeStatisticMeter("receiver.buffer.direct.size", "Current number of bytes allocated from direct memory as buffers for incoming messages.", unit = "bytes")
            val receiverHeapBufferSizeMeter = GaugeStatisticMeter("receiver.buffer.heap.size", "Current number of bytes allocated from Java heap memory as buffers for incoming messages.", unit = "bytes")
            val senderDirectBufferSizeMeter = GaugeStatisticMeter("sender.buffer.direct.size", "Current number of bytes allocated from direct memory as buffers for outgoing messages.", unit = "bytes")
            val senderHeapBufferSizeMeter = GaugeStatisticMeter("sender.buffer.heap.size", "Current number of bytes allocated from Java heap memory as buffers for outoing messages.", unit = "bytes")
            val socketLocksInProgressMeter = GaugeStatisticMeter("socket.locks.inprogress", "Current number of threads waiting to lock a socket")
            val socketLocksMeter = CounterStatisticMeter("socket.locks", "Total number of times a socket has been locked.")
            val socketLockTimer = TimerStatisticMeter("socket.lock.time", "Total amount of time, in nanoseconds, spent locking a socket", unit = "nanoseconds")
            val bufferAcquiresInProgressMeter = GaugeStatisticMeter("buffer.acquires.inprogress", "Current number of threads waiting to acquire a buffer")
            val bufferAcquiresMeter = CounterStatisticMeter("buffer.acquires", "Total number of times a buffer has been acquired.")
            val bufferAcquireTimer = TimerStatisticMeter("buffer.acquire.time", "Total amount of time, in nanoseconds, spent acquiring a socket", unit = "nanoseconds")
            val messageBeingReceivedMeter = GaugeStatisticMeter("messages.being.received", "Current number of message being received off the network or being processed after reception.")
            val messageBeingReceivedBytedMeter = GaugeStatisticMeter("message.being.received.bytes", "Current number of bytes consumed by messages being received or processed.", unit = "bytes")
            val serialThreadStartMeter = CounterStatisticMeter("thread.starts", "Total number of times a thread has been created for the serial message executor.", arrayOf("type", "serial"))
            val viewThreadStartMeter = CounterStatisticMeter("thread.starts", "Total number of times a thread has been created for the view message executor.", arrayOf("type", "view"))
            val processingThreadStartMeter = CounterStatisticMeter("thread.starts", "Total number of times a thread has been created for the pool processing normal messages.", arrayOf("type", "processing"))
            val highPriorityThreadStartMeter = CounterStatisticMeter("thread.startshighPriorityThreadStarts", "Total number of times a thread has been created for the pool handling high priority messages.", arrayOf("type", "highPriority"))
            val waitingThreadStartMeter = CounterStatisticMeter("thread.startswaitingThreadStarts", "Total number of times a thread has been created for the waiting pool.", arrayOf("type", "waiting"))
            val partitionedRegionThreadStartMeter = CounterStatisticMeter("thread.starts", "Total number of times a thread has been created for the pool handling partitioned region messages.", arrayOf("type", "partitionedRegion"))
            val functionExecutionThreadStartMeter = CounterStatisticMeter("thread.starts", "Total number of times a thread has been created for the pool handling function execution messages.", arrayOf("type", "functions"))
            val serialPoolThreadStartMeter = CounterStatisticMeter("thread.starts", "Total number of times a thread has been created for the serial pool(s).", arrayOf("type", "serialPool"))
            val threadOwnedMessagesSentMeter = CounterStatisticMeter("thread.owned.messages.sent", "Total number of messages sent on thread owned senders")
            val replayHandOffTimer = TimerStatisticMeter("reply.handoff.time", "Total number of seconds to switch thread contexts from processing thread to application thread.", unit = "nanoseconds")
            val partitionedRegionThreadJobsMeter = GaugeStatisticMeter("thread.jobs", "The number of messages currently being processed by partitioned region threads", arrayOf("type", "partitionedRegion"))
            val functionThreadJobsMeter = GaugeStatisticMeter("thread.jobs", "The number of messages currently being processed by function execution threads", arrayOf("type", "functions"))
            val threadCountForViewMessageMeter = GaugeStatisticMeter("thread.count", "The number of threads currently processing view messages.", arrayOf("type", "view"))
            val threadJobsForSerialThreadsMeter = GaugeStatisticMeter("thread.jobs", "The number of messages currently being processed by serial threads.", arrayOf("type", "serial"))
            val threadJobsForViewThreadsMeter = GaugeStatisticMeter("thread.jobs", "The number of messages currently being processed by view threads.", arrayOf("type", "view"))
            val threadJobsForSerialPoolThreadsMeter = GaugeStatisticMeter("thread.jobs", "The number of messages currently being processed by pooled serial processor threads.", arrayOf("type", "serialPool"))
            val threadJobsForProcessingThreadsMeter = GaugeStatisticMeter("thread.jobs", "The number of messages currently being processed by pooled message processor threads.", arrayOf("type", "processing"))
            val threadJobsForHighPriorityThreadsMeter = GaugeStatisticMeter("thread.jobs", "The number of messages currently being processed by high priority processor threads.",arrayOf("type","highPriority"))
            val threadJobsForWaitingThreadsMeter = GaugeStatisticMeter("thread.jobs", "The number of messages currently being processed by waiting pooly processor threads.",arrayOf("type","waiting"))
            val elderCountMeter = GaugeStatisticMeter("elder.count", "Current number of system elders hosted in this member.")
            val initialImageMessageInFlightMeter = GaugeStatisticMeter("initial.image.messages.inflight", "The number of messages with initial image data sent from this member that have not yet been acknowledged.")
            val initialImageMessageInProgressMeter = GaugeStatisticMeter("initial.image.messages.inprogress", "The number of initial images this member is currently receiving.")

            // For GMSHealthMonitor
            val heartBeatRequestSentMeter = CounterStatisticMeter("messages.sent", "Heartbeat request messages that this member has sent.",arrayOf("type","heartbeatRequest"))
            val heartBeatRequestReceivedMeter = CounterStatisticMeter("messages.received", "Heartbeat request messages that this member has received.",arrayOf("type","heartbeatRequest"))
            val heartBeatSendMeter = CounterStatisticMeter("messages.sent", "Heartbeat messages that this member has sent.",arrayOf("type","heartbeat"))
            val heartBeatReceivedMeter = CounterStatisticMeter("messages.received", "Heartbeat messages that this member has received.",arrayOf("type","heartbeat"))
            val suspectSentMeter  = CounterStatisticMeter("messages.sent", "Suspect member messages that this member has sent.",arrayOf("type","suspect"))
            val suspectReceivedMeter = CounterStatisticMeter("messages.received", "Suspect member messages that this member has received.",arrayOf("type","suspect"))
            val tcpFinalCheckRequestSentMeter = CounterStatisticMeter("messages.sent", "TCP final check requests that this member has sent.",arrayOf("type","finalCheckRequest","transport","tcp"))
            val tcpFinalCheckRequestReceivedMeter = CounterStatisticMeter("messages.received", "TCP final check requests that this member has received.",arrayOf("type","finalCheckRequest","transport","tcp"))
            val tcpFinalCheckResponseSentMeter = CounterStatisticMeter("messages.sent", "TCP final check responses that this member has sent.",arrayOf("type","finalCheckResponse","transport","tcp"))
            val tcpFinalCheckResponseReceivedMeter = CounterStatisticMeter("messages.received", "TCP final check responses that this member has received.",arrayOf("type","finalCheckResponse","transport","tcp"))
            val udpFinalCheckRequestSentMeter = CounterStatisticMeter("messages.sent", "UDP final check requests that this member has sent.",arrayOf("type","finalCheckRequest","transport","udp"))
            val udpFinalCheckRequestReceivedMeter = CounterStatisticMeter("messages.received", "UDP final check requests that this member has received.",arrayOf("type","finalCheckRequest","transport","udp"))
            val udpFinalCheckResponseSentMeter = CounterStatisticMeter("messages.sent", "UDP final check responses that this member has sent.",arrayOf("type","finalCheckResponse","transport","udp"))
            val udpFinalCheckResponseReceivedMeter = CounterStatisticMeter("messages.received", "UDP final check responses that this member has received.",arrayOf("type","finalCheckResponse","transport","udp"))

        }
    }

}
