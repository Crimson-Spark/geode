package org.apache.geode.distributed.internal;

public class DummyDMStats {
  public static class DummyDMStats implements DMStats {
    @Override
    public long getSentMessages() {
      return 0;
    }

    @Override
    public void incSentMessages(long messages) {}

    @Override
    public void incTOSentMsg() {}

    @Override
    public long getSentCommitMessages() {
      return 0;
    }

    @Override
    public void incSentCommitMessages(long messages) {}

    @Override
    public long getCommitWaits() {
      return 0;
    }

    @Override
    public void incCommitWaits() {}

    @Override
    public long getSentMessagesTime() {
      return 0;
    }

    @Override
    public void incSentMessagesTime(long nanos) {}

    @Override
    public long getBroadcastMessages() {
      return 0;
    }

    @Override
    public void incBroadcastMessages(long messages) {}

    @Override
    public long getBroadcastMessagesTime() {
      return 0;
    }

    @Override
    public void incBroadcastMessagesTime(long nanos) {}

    @Override
    public long getReceivedMessages() {
      return 0;
    }

    @Override
    public void incReceivedMessages(long messages) {}

    @Override
    public long getReceivedBytes() {
      return 0;
    }

    @Override
    public void incReceivedBytes(long bytes) {}

    @Override
    public void incSentBytes(long bytes) {}

    @Override
    public long getProcessedMessages() {
      return 0;
    }

    @Override
    public void incProcessedMessages(long messages) {}

    @Override
    public long getProcessedMessagesTime() {
      return 0;
    }

    @Override
    public void incProcessedMessagesTime(long nanos) {}

    @Override
    public long getMessageProcessingScheduleTime() {
      return 0;
    }

    @Override
    public void incMessageProcessingScheduleTime(long nanos) {}

    @Override
    public int getOverflowQueueSize() {
      return 0;
    }

    @Override
    public void incOverflowQueueSize(int messages) {}

    @Override
    public int getNumProcessingThreads() {
      return 0;
    }

    @Override
    public void incNumProcessingThreads(int threads) {}

    @Override
    public int getNumSerialThreads() {
      return 0;
    }

    @Override
    public void incNumSerialThreads(int threads) {}

    @Override
    public void incMessageChannelTime(long val) {}

    @Override
    public void incUDPDispatchRequestTime(long val) {};

    @Override
    public long getUDPDispatchRequestTime() {
      return 0;
    };

    @Override
    public long getReplyMessageTime() {
      return 0;
    }

    @Override
    public void incReplyMessageTime(long val) {}

    @Override
    public long getDistributeMessageTime() {
      return 0;
    }

    @Override
    public void incDistributeMessageTime(long val) {}

    @Override
    public int getNodes() {
      return 0;
    }

    @Override
    public void setNodes(int val) {}

    @Override
    public void incNodes(int val) {}

    @Override
    public int getReplyWaitsInProgress() {
      return 0;
    }

    @Override
    public int getReplyWaitsCompleted() {
      return 0;
    }

    @Override
    public long getReplyWaitTime() {
      return 0;
    }

    @Override
    public long startReplyWait() {
      return 0;
    }

    @Override
    public void endReplyWait(long startNanos, long startMillis) {}

    @Override
    public void incReplyTimeouts() {}

    @Override
    public long getReplyTimeouts() {
      return 0;
    }

    @Override
    public void incReceivers() {}

    @Override
    public void decReceivers() {}

    @Override
    public void incFailedAccept() {}

    @Override
    public void incFailedConnect() {}

    @Override
    public void incReconnectAttempts() {}

    @Override
    public void incLostLease() {}

    @Override
    public void incSenders(boolean shared, boolean preserveOrder) {}

    @Override
    public void decSenders(boolean shared, boolean preserveOrder) {}

    @Override
    public int getSendersSU() {
      return 0;
    }

    @Override
    public long startSocketWrite(boolean sync) {
      return 0;
    }

    @Override
    public void endSocketWrite(boolean sync, long start, int bytesWritten, int retries) {}

    @Override
    public long startSerialization() {
      return 0;
    }

    @Override
    public void endSerialization(long start, int bytes) {}

    @Override
    public long startDeserialization() {
      return 0;
    }

    @Override
    public void endDeserialization(long start, int bytes) {}

    @Override
    public long startMsgSerialization() {
      return 0;
    }

    @Override
    public void endMsgSerialization(long start) {}

    @Override
    public long startMsgDeserialization() {
      return 0;
    }

    @Override
    public void endMsgDeserialization(long start) {}

    @Override
    public void incBatchSendTime(long start) {}

    @Override
    public void incBatchCopyTime(long start) {}

    @Override
    public void incBatchWaitTime(long start) {}

    @Override
    public void incBatchFlushTime(long start) {}

    @Override
    public void incUcastWriteBytes(int bytesWritten) {}

    @Override
    public void incMcastWriteBytes(int bytesWritten) {}

    @Override
    public void incUcastRetransmits() {}

    @Override
    public void incMcastRetransmits() {}

    @Override
    public void incMcastRetransmitRequests() {}

    @Override
    public int getMcastRetransmits() {
      return 0;
    }

    @Override
    public int getMcastWrites() {
      return 0;
    }

    @Override
    public int getMcastReads() {
      return 0;
    }

    @Override
    public void incUcastReadBytes(int amount) {}

    @Override
    public void incMcastReadBytes(int amount) {}

    @Override
    public int getAsyncSocketWritesInProgress() {
      return 0;
    }

    @Override
    public int getAsyncSocketWrites() {
      return 0;
    }

    @Override
    public int getAsyncSocketWriteRetries() {
      return 0;
    }

    @Override
    public long getAsyncSocketWriteBytes() {
      return 0;
    }

    @Override
    public long getAsyncSocketWriteTime() {
      return 0;
    }

    @Override
    public int getAsyncQueues() {
      return 0;
    }

    @Override
    public void incAsyncQueues(int inc) {}

    @Override
    public int getAsyncQueueFlushesInProgress() {
      return 0;
    }

    @Override
    public int getAsyncQueueFlushesCompleted() {
      return 0;
    }

    @Override
    public long getAsyncQueueFlushTime() {
      return 0;
    }

    @Override
    public long startAsyncQueueFlush() {
      return 0;
    }

    @Override
    public void endAsyncQueueFlush(long start) {}

    @Override
    public int getAsyncQueueTimeouts() {
      return 0;
    }

    @Override
    public void incAsyncQueueTimeouts(int inc) {}

    @Override
    public int getAsyncQueueSizeExceeded() {
      return 0;
    }

    @Override
    public void incAsyncQueueSizeExceeded(int inc) {}

    @Override
    public int getAsyncDistributionTimeoutExceeded() {
      return 0;
    }

    @Override
    public void incAsyncDistributionTimeoutExceeded() {}

    @Override
    public long getAsyncQueueSize() {
      return 0;
    }

    @Override
    public void incAsyncQueueSize(long inc) {}

    @Override
    public long getAsyncQueuedMsgs() {
      return 0;
    }

    @Override
    public void incAsyncQueuedMsgs() {}

    @Override
    public long getAsyncDequeuedMsgs() {
      return 0;
    }

    @Override
    public void incAsyncDequeuedMsgs() {}

    @Override
    public long getAsyncConflatedMsgs() {
      return 0;
    }

    @Override
    public void incAsyncConflatedMsgs() {}

    @Override
    public int getAsyncThreads() {
      return 0;
    }

    @Override
    public void incAsyncThreads(int inc) {}

    @Override
    public int getAsyncThreadInProgress() {
      return 0;
    }

    @Override
    public int getAsyncThreadCompleted() {
      return 0;
    }

    @Override
    public long getAsyncThreadTime() {
      return 0;
    }

    @Override
    public long startAsyncThread() {
      return 0;
    }

    @Override
    public void endAsyncThread(long start) {}

    @Override
    public long getAsyncQueueAddTime() {
      return 0;
    }

    @Override
    public void incAsyncQueueAddTime(long inc) {}

    @Override
    public long getAsyncQueueRemoveTime() {
      return 0;
    }

    @Override
    public void incAsyncQueueRemoveTime(long inc) {}

    @Override
    public void incReceiverBufferSize(int inc, boolean direct) {}

    @Override
    public void incSenderBufferSize(int inc, boolean direct) {}

    @Override
    public long startSocketLock() {
      return 0;
    }

    @Override
    public void endSocketLock(long start) {}

    @Override
    public long startBufferAcquire() {
      return 0;
    }

    @Override
    public void endBufferAcquire(long start) {}

    @Override
    public void incMessagesBeingReceived(boolean newMsg, int bytes) {}

    @Override
    public void decMessagesBeingReceived(int bytes) {}

    @Override
    public void incReplyHandOffTime(long start) {}

    @Override
    public int getElders() {
      return 0;
    }

    @Override
    public void incElders(int val) {}

    @Override
    public int getInitialImageMessagesInFlight() {
      return 0;
    }

    @Override
    public void incInitialImageMessagesInFlight(int val) {}

    @Override
    public int getInitialImageRequestsInProgress() {
      return 0;
    }

    @Override
    public void incInitialImageRequestsInProgress(int val) {}

    @Override
    public void incPdxSerialization(int bytesWritten) {}

    @Override
    public void incPdxDeserialization(int i) {}

    @Override
    public long startPdxInstanceDeserialization() {
      return 0;
    }

    @Override
    public void endPdxInstanceDeserialization(long start) {}

    @Override
    public void incPdxInstanceCreations() {}

    @Override
    public void incThreadOwnedReceivers(long value, int dominoCount) {}

    @Override
    public long getHeartbeatRequestsSent() {
      return 0;
    }

    @Override
    public void incHeartbeatRequestsSent() {}

    @Override
    public long getHeartbeatRequestsReceived() {
      return 0;
    }

    @Override
    public void incHeartbeatRequestsReceived() {}

    @Override
    public long getHeartbeatsSent() {
      return 0;
    }

    @Override
    public void incHeartbeatsSent() {}

    @Override
    public long getHeartbeatsReceived() {
      return 0;
    }

    @Override
    public void incHeartbeatsReceived() {}

    @Override
    public long getSuspectsSent() {
      return 0;
    }

    @Override
    public void incSuspectsSent() {}

    @Override
    public long getSuspectsReceived() {
      return 0;
    }

    @Override
    public void incSuspectsReceived() {}

    @Override
    public long getFinalCheckRequestsSent() {
      return 0;
    }

    @Override
    public void incFinalCheckRequestsSent() {}

    @Override
    public long getFinalCheckRequestsReceived() {
      return 0;
    }

    @Override
    public void incFinalCheckRequestsReceived() {}

    @Override
    public long getFinalCheckResponsesSent() {
      return 0;
    }

    @Override
    public void incFinalCheckResponsesSent() {}

    @Override
    public long getFinalCheckResponsesReceived() {
      return 0;
    }

    @Override
    public void incFinalCheckResponsesReceived() {}

    @Override
    public long getTcpFinalCheckRequestsSent() {
      return 0;
    }

    @Override
    public void incTcpFinalCheckRequestsSent() {}

    @Override
    public long getTcpFinalCheckRequestsReceived() {
      return 0;
    }

    @Override
    public void incTcpFinalCheckRequestsReceived() {}

    @Override
    public long getTcpFinalCheckResponsesSent() {
      return 0;
    }

    @Override
    public void incTcpFinalCheckResponsesSent() {}

    @Override
    public long getTcpFinalCheckResponsesReceived() {
      return 0;
    }

    @Override
    public void incTcpFinalCheckResponsesReceived() {}

    @Override
    public long getUdpFinalCheckRequestsSent() {
      return 0;
    }

    @Override
    public void incUdpFinalCheckRequestsSent() {}

    @Override
    public long getUdpFinalCheckResponsesReceived() {
      return 0;
    }

    @Override
    public void incUdpFinalCheckResponsesReceived() {}

    @Override
    public long startUDPMsgEncryption() {
      return 0;
    }

    @Override
    public void endUDPMsgEncryption(long start) {}

    @Override
    public long startUDPMsgDecryption() {
      return 0;
    }

    @Override
    public void endUDPMsgDecryption(long start) {}

    @Override
    public long getUDPMsgEncryptionTiime() {
      return 0;
    }

    @Override
    public long getUDPMsgDecryptionTime() {
      return 0;
    }
  }
}
