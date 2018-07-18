/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.geode.statistics.distributed

/**
 * Defines the interface used to access and modify DM statistics.
 *
 *
 */
interface DMStats {

    /**
     * Returns the total number of messages sent by the distribution manager
     */
    val sentMessages: Long

    /**
     * Returns the total number of transaction commit messages sent by the distribution manager
     */
    val sentCommitMessages: Long

    /**
     * Returns the total number commits that had to wait for an ack before completion.
     */
    val commitWaits: Long

    /**
     * Returns the total number of nanoseconds spent sending messages.
     */
    val sentMessagesTime: Long

    /**
     * Returns the total number of messages broadcast by the distribution manager
     */
    val broadcastMessages: Long

    /**
     * Returns the total number of nanoseconds spent sending messages.
     */
    val broadcastMessagesTime: Long

    /**
     * Returns the total number of messages received by the distribution manager
     */
    val receivedMessages: Long

    /**
     * Returns the total number of message bytes received by the distribution manager
     */
    val receivedBytes: Long

    /**
     * Returns the total number of messages processed by the distribution manager
     */
    val processedMessages: Long

    /**
     * Returns the total number of nanoseconds spent processing messages.
     */
    val processedMessagesTime: Long

    /**
     * Returns the total number of nanoseconds spent scheduling messages to be processed.
     */
    val messageProcessingScheduleTime: Long

    val overflowQueueSize: Int

    val numProcessingThreads: Int

    val numSerialThreads: Int

    val udpDispatchRequestTime: Long

    val replyMessageTime: Long

    val distributeMessageTime: Long

    /**
     * returns the current value of the mcastWrites statistic
     */
    val mcastWrites: Int

    val mcastReads: Int

    val udpMsgEncryptionTiime: Long

    val udpMsgDecryptionTime: Long

    var nodes: Int

    val replyWaitsInProgress: Int

    val replyWaitsCompleted: Int

    val replyWaitTime: Long

    /**
     * Returns the number of message replies that have timed out
     *
     * @since GemFire 3.5
     */
    val replyTimeouts: Long

    /**
     * @since GemFire 4.1
     */
    val sendersSU: Int

    /**
     * returns the current number of multicast retransmission requests processed
     */
    val mcastRetransmits: Int

    /**
     * @since GemFire 4.2.2
     */
    val asyncSocketWritesInProgress: Int

    /**
     * @since GemFire 4.2.2
     */
    val asyncSocketWrites: Int

    /**
     * @since GemFire 4.2.2
     */
    val asyncSocketWriteRetries: Int

    /**
     * @since GemFire 4.2.2
     */
    val asyncSocketWriteBytes: Long

    /**
     * @since GemFire 4.2.2
     */
    val asyncSocketWriteTime: Long

    /**
     * @since GemFire 4.2.2
     */
    val asyncQueues: Int

    /**
     * @since GemFire 4.2.2
     */
    val asyncQueueFlushesInProgress: Int

    /**
     * @since GemFire 4.2.2
     */
    val asyncQueueFlushesCompleted: Int

    /**
     * @since GemFire 4.2.2
     */
    val asyncQueueFlushTime: Long

    /**
     * @since GemFire 4.2.2
     */
    val asyncQueueTimeouts: Int

    /**
     * @since GemFire 4.2.2
     */
    val asyncQueueSizeExceeded: Int

    /**
     * @since GemFire 4.2.2
     */
    val asyncDistributionTimeoutExceeded: Int

    /**
     * @since GemFire 4.2.2
     */
    val asyncQueueSize: Long

    /**
     * @since GemFire 4.2.2
     */
    val asyncQueuedMsgs: Long

    /**
     * @since GemFire 4.2.2
     */
    val asyncDequeuedMsgs: Long

    /**
     * @since GemFire 4.2.2
     */
    val asyncConflatedMsgs: Long

    /**
     * @since GemFire 4.2.2
     */
    val asyncThreads: Int

    /**
     * @since GemFire 4.2.2
     */
    val asyncThreadInProgress: Int

    /**
     * @since GemFire 4.2.2
     */
    val asyncThreadCompleted: Int

    /**
     * @since GemFire 4.2.2
     */
    val asyncThreadTime: Long

    /**
     * @since GemFire 4.2.2
     */
    val asyncQueueAddTime: Long

    /**
     * @since GemFire 4.2.2
     */
    val asyncQueueRemoveTime: Long

    /**
     * Returns 1 if the system elder is this member, else returns 0.
     *
     * @return 1 if the system elder is this member, else returns 0
     */
    val elders: Int

    /**
     * Returns the number of initial image reply messages sent from this member which have not yet
     * been acked.
     */
    val initialImageMessagesInFlight: Int

    /**
     * Returns the number of initial images this member is currently requesting.
     */
    val initialImageRequestsInProgress: Int

    // Stats for GMSHealthMonitor
    val heartbeatRequestsSent: Long

    val heartbeatRequestsReceived: Long

    val heartbeatsSent: Long

    val heartbeatsReceived: Long


    val suspectsSent: Long

    val suspectsReceived: Long


    val finalCheckRequestsSent: Long

    val finalCheckRequestsReceived: Long

    val finalCheckResponsesSent: Long

    val finalCheckResponsesReceived: Long


    val tcpFinalCheckRequestsSent: Long

    val tcpFinalCheckRequestsReceived: Long

    val tcpFinalCheckResponsesSent: Long

    val tcpFinalCheckResponsesReceived: Long


    val udpFinalCheckRequestsSent: Long

    // UDP final check is implemented using HeartbeatRequestMessage and HeartbeatMessage
    // So the following code is commented out.

    // public long getUdpFinalCheckRequestsReceived();
    //
    // public void incUdpFinalCheckRequestsReceived();
    //
    // public long getUdpFinalCheckResponsesSent();
    //
    // public void incUdpFinalCheckResponsesSent();

    val udpFinalCheckResponsesReceived: Long

    /**
     * Increments the total number of messages sent by the distribution manager
     */
    fun incSentMessages(messages: Long)

    fun incTOSentMsg()

    /**
     * Increments the total number of transaction commit messages sent by the distribution manager
     */
    fun incSentCommitMessages(messages: Long)

    /**
     * Increments the number of commit waits by one.
     */
    fun incCommitWaits()

    /**
     * Increments the total number of nanoseconds spent sending messages.
     */
    fun incSentMessagesTime(nanos: Long)

    /**
     * Increments the total number of messages broadcast by the distribution manager
     */
    fun incBroadcastMessages(messages: Long)

    /**
     * Increments the total number of nanoseconds spend sending messages.
     */
    fun incBroadcastMessagesTime(nanos: Long)

    /**
     * Increments the total number of messages received by the distribution manager
     */
    fun incReceivedMessages(messages: Long)

    /**
     * Increments the total number of message bytes received by the distribution manager
     */
    fun incReceivedBytes(bytes: Long)

    /**
     * Increments the total number of message bytes sent by the distribution manager
     */
    fun incSentBytes(bytes: Long)

    /**
     * Increments the total number of messages processed by the distribution manager
     */
    fun incProcessedMessages(messages: Long)

    /**
     * Increments the total number of nanoseconds spent processing messages.
     */
    fun incProcessedMessagesTime(nanos: Long)

    fun incBatchSendTime(start: Long)

    fun incBatchCopyTime(start: Long)

    fun incBatchWaitTime(start: Long)

    fun incBatchFlushTime(start: Long)

    /**
     * Increments the total number of nanoseconds spent scheduling messages to be processed.
     */
    fun incMessageProcessingScheduleTime(nanos: Long)

    fun incOverflowQueueSize(messages: Int)

    fun incNumProcessingThreads(threads: Int)

    fun incNumSerialThreads(threads: Int)

    fun incMessageChannelTime(`val`: Long)

    fun incUDPDispatchRequestTime(`val`: Long)

    fun incReplyMessageTime(`val`: Long)

    fun incDistributeMessageTime(`val`: Long)

    fun startSocketWrite(sync: Boolean): Long

    fun endSocketWrite(sync: Boolean, start: Long, bytesWritten: Int, retries: Int)

    /**
     * increments the number of unicast writes performed and the number of bytes written
     *
     * @since GemFire 5.0
     */
    fun incUcastWriteBytes(bytesWritten: Int)

    /**
     * increment the number of unicast datagram payload bytes received and the number of unicast reads
     * performed
     */
    fun incUcastReadBytes(amount: Int)

    /**
     * increment the number of multicast datagrams sent and the number of multicast bytes transmitted
     */
    fun incMcastWriteBytes(bytesWritten: Int)

    /**
     * increment the number of multicast datagram payload bytes received, and the number of mcast
     * messages read
     */
    fun incMcastReadBytes(amount: Int)

    fun startSerialization(): Long

    fun endSerialization(start: Long, bytes: Int)

    fun startDeserialization(): Long

    fun endDeserialization(start: Long, bytes: Int)

    fun startMsgSerialization(): Long

    fun endMsgSerialization(start: Long)

    fun startUDPMsgEncryption(): Long

    fun endUDPMsgEncryption(start: Long)

    fun startUDPMsgDecryption(): Long

    fun endUDPMsgDecryption(start: Long)

    fun startMsgDeserialization(): Long

    fun endMsgDeserialization(start: Long)

    fun incNodes(`val`: Int)

    /**
     * @return the timestamp that marks the start of the operation
     */
    fun startReplyWait(): Long

    /**
     * @param startNanos the timestamp taken when the operation started
     * @param initTime the time the operation begain (before msg transmission)
     */
    fun endReplyWait(startNanos: Long, initTime: Long)

    /**
     * Increments the number of message replies that have timed out
     *
     * @since GemFire 3.5
     */
    fun incReplyTimeouts()

    /**
     * @since GemFire 4.1
     */
    fun incReceivers()

    /**
     * @since GemFire 4.1
     */
    fun decReceivers()

    /**
     * @since GemFire 4.1
     */
    fun incFailedAccept()

    /**
     * @since GemFire 4.1
     */
    fun incFailedConnect()

    /**
     * @since GemFire 4.1.1
     */
    fun incReconnectAttempts()

    /**
     * @since GemFire 4.1
     */
    fun incLostLease()

    /**
     * @since GemFire 4.1
     */
    fun incSenders(shared: Boolean, preserveOrder: Boolean)

    /**
     * @since GemFire 4.1
     */
    fun decSenders(shared: Boolean, preserveOrder: Boolean)

    /**
     * increment the number of unicast UDP retransmission requests received from other processes
     *
     * @since GemFire 5.0
     */
    fun incUcastRetransmits()

    /**
     * increment the number of multicast UDP retransmissions sent to other processes
     *
     * @since GemFire 5.0
     */
    fun incMcastRetransmits()

    /**
     * increment the number of multicast UDP retransmission requests sent to other processes
     *
     * @since GemFire 5.0
     */
    fun incMcastRetransmitRequests()

    /**
     * @since GemFire 4.2.2
     */
    fun incAsyncQueues(inc: Int)

    /**
     * @since GemFire 4.2.2
     */
    fun startAsyncQueueFlush(): Long

    /**
     * @since GemFire 4.2.2
     */
    fun endAsyncQueueFlush(start: Long)

    /**
     * @since GemFire 4.2.2
     */
    fun incAsyncQueueTimeouts(inc: Int)

    /**
     * @since GemFire 4.2.2
     */
    fun incAsyncQueueSizeExceeded(inc: Int)

    /**
     * @since GemFire 4.2.2
     */
    fun incAsyncDistributionTimeoutExceeded()

    /**
     * @since GemFire 4.2.2
     */
    fun incAsyncQueueSize(inc: Long)

    /**
     * @since GemFire 4.2.2
     */
    fun incAsyncQueuedMsgs()

    /**
     * @since GemFire 4.2.2
     */
    fun incAsyncDequeuedMsgs()

    /**
     * @since GemFire 4.2.2
     */
    fun incAsyncConflatedMsgs()

    /**
     * @since GemFire 4.2.2
     */
    fun incAsyncThreads(inc: Int)

    /**
     * @since GemFire 4.2.2
     */
    fun startAsyncThread(): Long

    /**
     * @since GemFire 4.2.2
     */
    fun endAsyncThread(start: Long)

    /**
     * @since GemFire 4.2.2
     */
    fun incAsyncQueueAddTime(inc: Long)

    /**
     * @since GemFire 4.2.2
     */
    fun incAsyncQueueRemoveTime(inc: Long)

    /**
     * @since GemFire 5.0.2.4
     */
    fun incReceiverBufferSize(inc: Int, direct: Boolean)

    /**
     * @since GemFire 5.0.2.4
     */
    fun incSenderBufferSize(inc: Int, direct: Boolean)

    /**
     * @since GemFire 5.0.2.4
     */
    fun startSocketLock(): Long

    /**
     * @since GemFire 5.0.2.4
     */
    fun endSocketLock(start: Long)

    /**
     * @since GemFire 5.0.2.4
     */
    fun startBufferAcquire(): Long

    /**
     * @since GemFire 5.0.2.4
     */
    fun endBufferAcquire(start: Long)

    /**
     * increment/decrement the number of thread-owned receivers with the given domino count
     *
     * @param dominoCount thread-owned connection chain count
     */
    fun incThreadOwnedReceivers(value: Long, dominoCount: Int)

    /**
     * Called when a new message is received.
     *
     * @param newMsg true if a new message being received was detected; false if this is just
     * additional data for a message already detected.
     * @param bytes the number of bytes read, so far, for the message being received.
     * @since GemFire 5.0.2
     */
    fun incMessagesBeingReceived(newMsg: Boolean, bytes: Int)

    /**
     * Called when we finish processing a received message.
     *
     * @param bytes the number of bytes read off the wire for the message we have finished with.
     * @since GemFire 5.0.2
     */
    fun decMessagesBeingReceived(bytes: Int)

    fun incReplyHandOffTime(start: Long)

    fun incElders(`val`: Int)

    fun incInitialImageMessagesInFlight(`val`: Int)

    fun incInitialImageRequestsInProgress(`val`: Int)

    fun incPdxSerialization(bytesWritten: Int)

    fun incPdxDeserialization(i: Int)

    fun startPdxInstanceDeserialization(): Long

    fun endPdxInstanceDeserialization(start: Long)

    fun incPdxInstanceCreations()

    fun incHeartbeatRequestsSent()

    fun incHeartbeatRequestsReceived()

    fun incHeartbeatsSent()

    fun incHeartbeatsReceived()

    fun incSuspectsSent()

    fun incSuspectsReceived()

    fun incFinalCheckRequestsSent()

    fun incFinalCheckRequestsReceived()

    fun incFinalCheckResponsesSent()

    fun incFinalCheckResponsesReceived()

    fun incTcpFinalCheckRequestsSent()

    fun incTcpFinalCheckRequestsReceived()

    fun incTcpFinalCheckResponsesSent()

    fun incTcpFinalCheckResponsesReceived()

    fun incUdpFinalCheckRequestsSent()

    fun incUdpFinalCheckResponsesReceived()
}
