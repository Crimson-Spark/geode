package org.apache.geode.internal.cache.tier.sockets;

public class CCUStats {
  /**
   * Stats for a CacheClientUpdater. Currently the only thing measured are incoming bytes on the
   * wire
   *
   * @since GemFire 5.7
   */
  public static class CCUStats implements MessageStats {

    private static final StatisticsType type;
    private static final int messagesBeingReceivedId;
    private static final int messageBytesBeingReceivedId;
    private static final int receivedBytesId;

    static {
      StatisticsTypeFactory f = StatisticsTypeFactoryImpl.singleton();
      type = f.createType("CacheClientUpdaterStats", "Statistics about incoming subscription data",
          new StatisticDescriptor[] {
              f.createLongCounter("receivedBytes",
                  "Total number of bytes received from the server.", "bytes"),
              f.createIntGauge("messagesBeingReceived",
                  "Current number of message being received off the network or being processed after reception.",
                  "messages"),
              f.createLongGauge("messageBytesBeingReceived",
                  "Current number of bytes consumed by messages being received or processed.",
                  "bytes"),});
      receivedBytesId = type.nameToId("receivedBytes");
      messagesBeingReceivedId = type.nameToId("messagesBeingReceived");
      messageBytesBeingReceivedId = type.nameToId("messageBytesBeingReceived");
    }

    // instance fields
    private final Statistics stats;

    CCUStats(DistributedSystem distributedSystem, ServerLocation location) {
      // no need for atomic since only a single thread will be writing these
      this.stats = distributedSystem.getStatisticsFactory().createStatistics(type,
          "CacheClientUpdater-" + location);
    }

    public void close() {
      this.stats.close();
    }

    @Override
    public void incReceivedBytes(long v) {
      this.stats.incLong(receivedBytesId, v);
    }

    @Override
    public void incSentBytes(long v) {
      // noop since we never send messages
    }

    @Override
    public void incMessagesBeingReceived(int bytes) {
      this.stats.incInt(messagesBeingReceivedId, 1);
      if (bytes > 0) {
        this.stats.incLong(messageBytesBeingReceivedId, bytes);
      }
    }

    @Override
    public void decMessagesBeingReceived(int bytes) {
      this.stats.incInt(messagesBeingReceivedId, -1);
      if (bytes > 0) {
        this.stats.incLong(messageBytesBeingReceivedId, -bytes);
      }
    }

    /**
     * Returns the current time (ns).
     *
     * @return the current time (ns)
     */
    public long startTime() {
      return DistributionStats.getStatTime();
    }
  }
}
