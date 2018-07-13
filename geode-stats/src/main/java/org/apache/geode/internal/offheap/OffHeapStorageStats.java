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
package org.apache.geode.internal.offheap;

import java.lang.reflect.Method;

import org.apache.geode.cache.CacheException;
import org.apache.geode.distributed.DistributedSystem;
import org.apache.geode.distributed.internal.DistributionConfig;
import org.apache.geode.distributed.internal.DistributionStats;
import org.apache.geode.distributed.internal.InternalDistributedSystem;
import org.apache.geode.distributed.internal.InternalLocator;
import org.apache.geode.internal.ClassPathLoader;
import org.apache.geode.internal.i18n.LocalizedStrings;
import org.apache.geode.internal.statistics.StatisticsTypeFactoryImpl;
import org.apache.geode.statistics.StatisticDescriptor;
import org.apache.geode.statistics.Statistics;
import org.apache.geode.statistics.StatisticsFactory;
import org.apache.geode.statistics.StatisticsType;
import org.apache.geode.statistics.StatisticsTypeFactory;

/**
 * Enables off-heap storage by creating a MemoryAllocator.
 *
 * @since Geode 1.0
 */
public class OffHeapStorageStats implements OffHeapMemoryStats {
  public static final String STAY_CONNECTED_ON_OUTOFOFFHEAPMEMORY_PROPERTY =
      DistributionConfig.GEMFIRE_PREFIX + "offheap.stayConnectedOnOutOfOffHeapMemory";

  // statistics type
  private static final StatisticsType statsType;
  private static final String statsTypeName = "OffHeapMemoryStats";
  private static final String statsTypeDescription = "Statistics about off-heap memory storage.";

  // statistics instance
  private static final String statsName = "offHeapMemoryStats";

  // statistics fields
  private static final int freeMemoryId;
  private static final int maxMemoryId;
  private static final int usedMemoryId;
  private static final int objectsId;
  private static final int readsId;
  private static final int defragmentationId;
  private static final int fragmentsId;
  private static final int largestFragmentId;
  private static final int defragmentationTimeId;
  private static final int fragmentationId;
  private static final int defragmentationsInProgressId;
  // NOTE!!!! When adding new stats make sure and update the initialize method on this class

  // creates and registers the statistics type
  static {
    final StatisticsTypeFactory f = StatisticsTypeFactoryImpl.singleton();



    final String usedMemory = "usedMemory";
    final String defragmentations = "defragmentations";
    final String defragmentationsInProgress = "defragmentationsInProgress";
    final String defragmentationTime = "defragmentationTime";
    final String fragmentation = "fragmentation";
    final String fragments = "fragments";
    final String freeMemory = "freeMemory";
    final String largestFragment = "largestFragment";
    final String objects = "objects";
    final String reads = "reads";
    final String maxMemory = "maxMemory";

    statsType = f.createType(statsTypeName, statsTypeDescription,
        new StatisticDescriptor[] {f.createLongGauge(usedMemory, usedMemoryDesc, "bytes"),
            f.createIntCounter(defragmentations, defragmentationDesc, "operations"),
            f.createIntGauge(defragmentationsInProgress, defragmentationsInProgressDesc,
                "operations"),
            f.createLongCounter(defragmentationTime, defragmentationTimeDesc, "nanoseconds", false),
            f.createIntGauge(fragmentation, fragmentationDesc, "percentage"),
            f.createLongGauge(fragments, fragmentsDesc, "fragments"),
            f.createLongGauge(freeMemory, freeMemoryDesc, "bytes"),
            f.createIntGauge(largestFragment, largestFragmentDesc, "bytes"),
            f.createIntGauge(objects, objectsDesc, "objects"),
            f.createLongCounter(reads, readsDesc, "operations"),
            f.createLongGauge(maxMemory, maxMemoryDesc, "bytes"),});

    usedMemoryId = statsType.nameToId(usedMemory);
    defragmentationId = statsType.nameToId(defragmentations);
    defragmentationsInProgressId = statsType.nameToId(defragmentationsInProgress);
    defragmentationTimeId = statsType.nameToId(defragmentationTime);
    fragmentationId = statsType.nameToId(fragmentation);
    fragmentsId = statsType.nameToId(fragments);
    freeMemoryId = statsType.nameToId(freeMemory);
    largestFragmentId = statsType.nameToId(largestFragment);
    objectsId = statsType.nameToId(objects);
    readsId = statsType.nameToId(reads);
    maxMemoryId = statsType.nameToId(maxMemory);
  }

  public static long parseOffHeapMemorySize(String value) {
    final long parsed = parseLongWithUnits(value, 0L, 1024 * 1024);
    if (parsed < 0) {
      return 0;
    }
    return parsed;
  }

  private final Statistics stats;

  private OffHeapStorageStats(StatisticsFactory f) {
    this.stats = f.createAtomicStatistics(statsType, statsName);
  }

  public void incFreeMemory(long value) {
    this.stats.incLong(freeMemoryId, value);
  }

  public void incMaxMemory(long value) {
    this.stats.incLong(maxMemoryId, value);
  }

  public void incUsedMemory(long value) {
    this.stats.incLong(usedMemoryId, value);
  }

  public void incObjects(int value) {
    this.stats.incInt(objectsId, value);
  }

  public long getFreeMemory() {
    return this.stats.getLong(freeMemoryId);
  }

  public long getMaxMemory() {
    return this.stats.getLong(maxMemoryId);
  }

  public long getUsedMemory() {
    return this.stats.getLong(usedMemoryId);
  }

  public int getObjects() {
    return this.stats.getInt(objectsId);
  }

  @Override
  public void incReads() {
    this.stats.incLong(readsId, 1);
  }

  @Override
  public long getReads() {
    return this.stats.getLong(readsId);
  }

  private void incDefragmentations() {
    this.stats.incInt(defragmentationId, 1);
  }

  @Override
  public int getDefragmentations() {
    return this.stats.getInt(defragmentationId);
  }

  @Override
  public void setFragments(long value) {
    this.stats.setLong(fragmentsId, value);
  }

  @Override
  public long getFragments() {
    return this.stats.getLong(fragmentsId);
  }

  @Override
  public void setLargestFragment(int value) {
    this.stats.setInt(largestFragmentId, value);
  }

  @Override
  public int getLargestFragment() {
    return this.stats.getInt(largestFragmentId);
  }

  @Override
  public int getDefragmentationsInProgress() {
    return this.stats.getInt(defragmentationsInProgressId);
  }

  @Override
  public long startDefragmentation() {
    this.stats.incInt(defragmentationsInProgressId, 1);
    return DistributionStats.getStatTime();
  }

  @Override
  public void endDefragmentation(long start) {
    incDefragmentations();
    this.stats.incInt(defragmentationsInProgressId, -1);
    if (DistributionStats.enableClockStats) {
      stats.incLong(defragmentationTimeId, DistributionStats.getStatTime() - start);
    }
  }

  @Override
  public long getDefragmentationTime() {
    return stats.getLong(defragmentationTimeId);
  }

  @Override
  public void setFragmentation(int value) {
    this.stats.setInt(fragmentationId, value);
  }

  @Override
  public int getFragmentation() {
    return this.stats.getInt(fragmentationId);
  }

  public Statistics getStats() {
    return this.stats;
  }

  @Override
  public void close() {
    this.stats.close();
  }

  @Override
  public void initialize(OffHeapMemoryStats oldStats) {
    setFreeMemory(oldStats.getFreeMemory());
    setMaxMemory(oldStats.getMaxMemory());
    setUsedMemory(oldStats.getUsedMemory());
    setObjects(oldStats.getObjects());
    setReads(oldStats.getReads());
    setDefragmentations(oldStats.getDefragmentations());
    setDefragmentationsInProgress(oldStats.getDefragmentationsInProgress());
    setFragments(oldStats.getFragments());
    setLargestFragment(oldStats.getLargestFragment());
    setDefragmentationTime(oldStats.getDefragmentationTime());
    setFragmentation(oldStats.getFragmentation());

    oldStats.close();
  }

  private void setDefragmentationTime(long value) {
    stats.setLong(defragmentationTimeId, value);
  }

  private void setDefragmentations(int value) {
    this.stats.setInt(defragmentationId, value);
  }

  private void setDefragmentationsInProgress(int value) {
    this.stats.setInt(defragmentationsInProgressId, value);
  }

  private void setReads(long value) {
    this.stats.setLong(readsId, value);
  }

  private void setObjects(int value) {
    this.stats.setInt(objectsId, value);
  }

  private void setUsedMemory(long value) {
    this.stats.setLong(usedMemoryId, value);
  }

  private void setMaxMemory(long value) {
    this.stats.setLong(maxMemoryId, value);
  }

  private void setFreeMemory(long value) {
    this.stats.setLong(freeMemoryId, value);
  }
}
