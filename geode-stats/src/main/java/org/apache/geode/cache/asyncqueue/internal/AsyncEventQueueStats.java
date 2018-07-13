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
package org.apache.geode.cache.asyncqueue.internal;

import org.apache.geode.internal.cache.wan.GatewaySenderStats;
import org.apache.geode.internal.statistics.StatisticsTypeFactoryImpl;
import org.apache.geode.statistics.StatisticDescriptor;
import org.apache.geode.statistics.StatisticsFactory;
import org.apache.geode.statistics.StatisticsType;
import org.apache.geode.statistics.StatisticsTypeFactory;

public class AsyncEventQueueStats extends GatewaySenderStats {

  public static final String typeName = "AsyncEventQueueStatistics";

  /** The <code>StatisticsType</code> of the statistics */
  public static final StatisticsType type;


  static {

    StatisticsTypeFactory f = StatisticsTypeFactoryImpl.singleton();

    type = f.createType(typeName, "Stats for activity in the AsyncEventQueue",
        new StatisticDescriptor[] {
            f.createIntCounter(GatewaySenderStats.EVENTS_RECEIVED, "Number of events received by this queue.",
                "operations"),
            f.createIntCounter(GatewaySenderStats.EVENTS_QUEUED, "Number of events added to the event queue.",
                "operations"),
            f.createLongCounter(GatewaySenderStats.EVENT_QUEUE_TIME, "Total time spent queueing events.",
                "nanoseconds"),
            f.createIntGauge(GatewaySenderStats.EVENT_QUEUE_SIZE, "Size of the event queue.", "operations", false),
            f.createIntGauge(GatewaySenderStats.SECONDARY_EVENT_QUEUE_SIZE, "Size of the secondary event queue.",
                "operations", false),
            f.createIntGauge(GatewaySenderStats.EVENTS_PROCESSED_BY_PQRM,
                "Total number of events processed by Parallel Queue Removal Message(PQRM).",
                "operations", false),
            f.createIntGauge(GatewaySenderStats.TMP_EVENT_QUEUE_SIZE, "Size of the temporary events queue.",
                "operations", false),
            f.createIntCounter(GatewaySenderStats.EVENTS_NOT_QUEUED_CONFLATED,
                "Number of events received but not added to the event queue because the queue already contains an event with the event's key.",
                "operations"),
            f.createIntCounter(GatewaySenderStats.EVENTS_CONFLATED_FROM_BATCHES,
                "Number of events conflated from batches.", "operations"),
            f.createIntCounter(GatewaySenderStats.EVENTS_DISTRIBUTED,
                "Number of events removed from the event queue and sent.", "operations"),
            f.createIntCounter(GatewaySenderStats.EVENTS_EXCEEDING_ALERT_THRESHOLD,
                "Number of events exceeding the alert threshold.", "operations", false),
            f.createLongCounter(GatewaySenderStats.BATCH_DISTRIBUTION_TIME,
                "Total time spent distributing batches of events to receivers.", "nanoseconds"),
            f.createIntCounter(GatewaySenderStats.BATCHES_DISTRIBUTED,
                "Number of batches of events removed from the event queue and sent.", "operations"),
            f.createIntCounter(GatewaySenderStats.BATCHES_REDISTRIBUTED,
                "Number of batches of events removed from the event queue and resent.",
                "operations", false),
            f.createIntCounter(GatewaySenderStats.UNPROCESSED_TOKENS_ADDED_BY_PRIMARY,
                "Number of tokens added to the secondary's unprocessed token map by the primary (though a listener).",
                "tokens"),
            f.createIntCounter(GatewaySenderStats.UNPROCESSED_EVENTS_ADDED_BY_SECONDARY,
                "Number of events added to the secondary's unprocessed event map by the secondary.",
                "events"),
            f.createIntCounter(GatewaySenderStats.UNPROCESSED_EVENTS_REMOVED_BY_PRIMARY,
                "Number of events removed from the secondary's unprocessed event map by the primary (though a listener).",
                "events"),
            f.createIntCounter(GatewaySenderStats.UNPROCESSED_TOKENS_REMOVED_BY_SECONDARY,
                "Number of tokens removed from the secondary's unprocessed token map by the secondary.",
                "tokens"),
            f.createIntCounter(GatewaySenderStats.UNPROCESSED_EVENTS_REMOVED_BY_TIMEOUT,
                "Number of events removed from the secondary's unprocessed event map by a timeout.",
                "events"),
            f.createIntCounter(GatewaySenderStats.UNPROCESSED_TOKENS_REMOVED_BY_TIMEOUT,
                "Number of tokens removed from the secondary's unprocessed token map by a timeout.",
                "tokens"),
            f.createIntGauge(GatewaySenderStats.UNPROCESSED_EVENT_MAP_SIZE,
                "Current number of entries in the secondary's unprocessed event map.", "events",
                false),
            f.createIntGauge(GatewaySenderStats.UNPROCESSED_TOKEN_MAP_SIZE,
                "Current number of entries in the secondary's unprocessed token map.", "tokens",
                false),
            f.createIntGauge(GatewaySenderStats.CONFLATION_INDEXES_MAP_SIZE,
                "Current number of entries in the conflation indexes map.", "events"),
            f.createIntCounter(GatewaySenderStats.NOT_QUEUED_EVENTS, "Number of events not added to queue.", "events"),
            f.createIntCounter(GatewaySenderStats.EVENTS_DROPPED_DUE_TO_PRIMARY_SENDER_NOT_RUNNING,
                "Number of events dropped because the primary gateway sender is not running.",
                "events"),
            f.createIntCounter(GatewaySenderStats.EVENTS_FILTERED,
                "Number of events filtered through GatewayEventFilter.", "events"),
            f.createIntCounter(GatewaySenderStats.LOAD_BALANCES_COMPLETED, "Number of load balances completed",
                "operations"),
            f.createIntGauge(GatewaySenderStats.LOAD_BALANCES_IN_PROGRESS, "Number of load balances in progress",
                "operations"),
            f.createLongCounter(GatewaySenderStats.LOAD_BALANCE_TIME, "Total time spent load balancing this sender",
                "nanoseconds"),
            f.createIntCounter(GatewaySenderStats.SYNCHRONIZATION_EVENTS_ENQUEUED,
                "Number of synchronization events added to the event queue.", "operations"),
            f.createIntCounter(GatewaySenderStats.SYNCHRONIZATION_EVENTS_PROVIDED,
                "Number of synchronization events provided to other members.", "operations"),});

    // Initialize id fields
    GatewaySenderStats.eventsReceivedId = type.nameToId(GatewaySenderStats.EVENTS_RECEIVED);
    GatewaySenderStats.eventsQueuedId = type.nameToId(GatewaySenderStats.EVENTS_QUEUED);
    GatewaySenderStats.eventsNotQueuedConflatedId = type.nameToId(
        GatewaySenderStats.EVENTS_NOT_QUEUED_CONFLATED);
    GatewaySenderStats.eventQueueTimeId = type.nameToId(GatewaySenderStats.EVENT_QUEUE_TIME);
    GatewaySenderStats.eventQueueSizeId = type.nameToId(GatewaySenderStats.EVENT_QUEUE_SIZE);
    GatewaySenderStats.secondaryEventQueueSizeId = type.nameToId(
        GatewaySenderStats.SECONDARY_EVENT_QUEUE_SIZE);
    GatewaySenderStats.eventsProcessedByPQRMId = type.nameToId(
        GatewaySenderStats.EVENTS_PROCESSED_BY_PQRM);
    GatewaySenderStats.eventTmpQueueSizeId = type.nameToId(GatewaySenderStats.TMP_EVENT_QUEUE_SIZE);
    GatewaySenderStats.eventsDistributedId = type.nameToId(GatewaySenderStats.EVENTS_DISTRIBUTED);
    GatewaySenderStats.eventsExceedingAlertThresholdId = type.nameToId(
        GatewaySenderStats.EVENTS_EXCEEDING_ALERT_THRESHOLD);
    GatewaySenderStats.batchDistributionTimeId = type.nameToId(
        GatewaySenderStats.BATCH_DISTRIBUTION_TIME);
    GatewaySenderStats.batchesDistributedId = type.nameToId(GatewaySenderStats.BATCHES_DISTRIBUTED);
    GatewaySenderStats.batchesRedistributedId = type.nameToId(
        GatewaySenderStats.BATCHES_REDISTRIBUTED);
    GatewaySenderStats.unprocessedTokensAddedByPrimaryId = type.nameToId(
        GatewaySenderStats.UNPROCESSED_TOKENS_ADDED_BY_PRIMARY);
    GatewaySenderStats.unprocessedEventsAddedBySecondaryId = type.nameToId(
        GatewaySenderStats.UNPROCESSED_EVENTS_ADDED_BY_SECONDARY);
    GatewaySenderStats.unprocessedEventsRemovedByPrimaryId = type.nameToId(
        GatewaySenderStats.UNPROCESSED_EVENTS_REMOVED_BY_PRIMARY);
    GatewaySenderStats.unprocessedTokensRemovedBySecondaryId = type.nameToId(
        GatewaySenderStats.UNPROCESSED_TOKENS_REMOVED_BY_SECONDARY);
    GatewaySenderStats.unprocessedEventsRemovedByTimeoutId = type.nameToId(
        GatewaySenderStats.UNPROCESSED_EVENTS_REMOVED_BY_TIMEOUT);
    GatewaySenderStats.unprocessedTokensRemovedByTimeoutId = type.nameToId(
        GatewaySenderStats.UNPROCESSED_TOKENS_REMOVED_BY_TIMEOUT);
    GatewaySenderStats.unprocessedEventMapSizeId = type.nameToId(
        GatewaySenderStats.UNPROCESSED_EVENT_MAP_SIZE);
    GatewaySenderStats.unprocessedTokenMapSizeId = type.nameToId(
        GatewaySenderStats.UNPROCESSED_TOKEN_MAP_SIZE);
    GatewaySenderStats.conflationIndexesMapSizeId = type.nameToId(
        GatewaySenderStats.CONFLATION_INDEXES_MAP_SIZE);
    GatewaySenderStats.notQueuedEventsId = type.nameToId(GatewaySenderStats.NOT_QUEUED_EVENTS);
    GatewaySenderStats.eventsDroppedDueToPrimarySenderNotRunningId =
        type.nameToId(GatewaySenderStats.EVENTS_DROPPED_DUE_TO_PRIMARY_SENDER_NOT_RUNNING);
    GatewaySenderStats.eventsFilteredId = type.nameToId(GatewaySenderStats.EVENTS_FILTERED);
    GatewaySenderStats.eventsConflatedFromBatchesId = type.nameToId(
        GatewaySenderStats.EVENTS_CONFLATED_FROM_BATCHES);
    GatewaySenderStats.loadBalancesCompletedId = type.nameToId(
        GatewaySenderStats.LOAD_BALANCES_COMPLETED);
    GatewaySenderStats.loadBalancesInProgressId = type.nameToId(
        GatewaySenderStats.LOAD_BALANCES_IN_PROGRESS);
    GatewaySenderStats.loadBalanceTimeId = type.nameToId(GatewaySenderStats.LOAD_BALANCE_TIME);
    GatewaySenderStats.synchronizationEventsEnqueuedId = type.nameToId(
        GatewaySenderStats.SYNCHRONIZATION_EVENTS_ENQUEUED);
    GatewaySenderStats.synchronizationEventsProvidedId = type.nameToId(
        GatewaySenderStats.SYNCHRONIZATION_EVENTS_PROVIDED);
  }

  /**
   * Constructor.
   *
   * @param f The <code>StatisticsFactory</code> which creates the <code>Statistics</code> instance
   * @param asyncQueueId The id of the <code>AsyncEventQueue</code> used to generate the name of the
   *        <code>Statistics</code>
   */
  public AsyncEventQueueStats(StatisticsFactory f, String asyncQueueId) {
    super(f, asyncQueueId, type);
  }
}
