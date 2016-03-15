package org.game.tanks.utils;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class GuavaExamples {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    System.out.println(Splitter.on(',')
        .trimResults()
        .omitEmptyStrings()
        .split(" foo, ,bar, quux,"));

    System.out.println(Joiner.on(", ").skipNulls().join("a", "b", null, "d"));

    System.out.println(CharMatcher.DIGIT.or(CharMatcher.is('*')).retainFrom("*u*s*er-aa123aa"));
    // CharMatcher.ASCII.matchesAnyOf("a").retainFrom("aabbccddee");

    Multimap<String, Object> multimap = ArrayListMultimap.create();
    multimap.put("cats", "bonzo");
    multimap.put("cats", "fafik");
    multimap.put("dogs", "pimpek");
    System.out.println(multimap);

    BiMap<Integer, String> biMap = HashBiMap.create();
    biMap.put(1, "a");
    biMap.put(12, "b");
    biMap.put(3, "x");
    System.out.println(biMap);
    System.out.println(biMap.inverse());

    // Alternative to tideous comparators:
    // ComparisonChain.start()
    // .compare(x, x)
    // .compare(x, x)
    // .result();

    // Hashing: choose algorythm and what to hash
    // PlayerInfo playerInfo;
    // Hashing.murmur3_128().newHasher()
    // .putString(playerInfo.getPlayerName())
    // .putin("person.firstName").hash();

    // TODO: BLOOMFILTER

    // Objects equal (Java 7)
    String a = "aaa";
    String b = null;
    Objects.equals(a, b);

    // real array list
    ArrayList<String> newArrayList = Lists.newArrayList("a", "b", "c");

    // Map utils
    Map<String, Integer> map = new HashMap<>();
    map.put("A", 1);
    map.put("B", 2);
    map.put("C", 3);

    System.out.println(Maps.filterEntries(map, e -> e.getValue() > 2));
    System.out.println(Maps.filterKeys(map, k -> k.equals("B") || k.equals("C")));
    System.out.println(Maps.filterValues(map, v -> v != 2));

    // Finding common entries
    Map<String, String> stateCaps = ImmutableMap.<String, String> builder()
        .put("Tallahassee", "Florida")
        .put("Santa Fe", "New Mexico")
        .put("Trenton", "New Jersey")
        .put("Olympia", "Washington")
        .put("Albany", "New York").build();

    Map<String, String> stateCaps2 = ImmutableMap.<String, String> builder()
        .put("Tallahassee", "Florida")
        .put("Raleigh", "North Carolina")
        .put("Olympia", "Washington")
        .put("Bismarck", "North Dakota").build();

    System.out.println(Maps.difference(stateCaps, stateCaps2).entriesInCommon());
    System.out.println(Maps.difference(stateCaps, stateCaps2).entriesOnlyOnLeft());
    System.out.println(Maps.difference(stateCaps, stateCaps2).entriesOnlyOnRight());

    // Cycling iterator (looped) - will infinitely loop around finite list
    List<Integer> list = Lists.newArrayList(1, 3, 5, 6, 7);
    Iterable iterable = Iterables.cycle(list);
    Iterator it = iterable.iterator();
    for (int i = 0; i < 1000; i++) {
      it.next();
    }

    // Partitioning list
    list = Lists.newArrayList(1, 2, 3, 4, 5);
    iterable = Iterables.partition(list, 2);
    it = iterable.iterator();
    assertEquals(it.next(), Lists.newArrayList(1, 2));
    assertEquals(it.next(), Lists.newArrayList(3, 4));
    assertEquals(it.next(), Lists.newArrayList(5));

    // Partitioning padded list
    list = Lists.newArrayList(1, 2, 3, 4, 5);
    iterable = Iterables.paddedPartition(list, 2);
    it = iterable.iterator();
    assertEquals(it.next(), Lists.newArrayList(1, 2));
    assertEquals(it.next(), Lists.newArrayList(3, 4));
    assertEquals(it.next(), Lists.newArrayList(5, null));

    // NullToEmpty String converter
    String s1 = "aa";
    String s2 = null;
    System.out.println("[" + s1 + "]");
    System.out.println("[" + s2 + "]");
    System.out.println("[" + Strings.nullToEmpty(s1) + "]");
    System.out.println("[" + Strings.nullToEmpty(s2) + "]");

    // Preconditions (validating input arguments and throwing exceptions if they fail) - fast fail mechanism
    addGrade(100);
    try {
      addGrade(null);
    } catch (NullPointerException e) {
      System.out.println("Input arguments are not valid: " + e.getMessage());
    }

    try {
      addGrade(-1);
    } catch (IllegalArgumentException e) {
      System.out.println("Input arguments are not valid: " + e.getMessage());
    }

    // Stopwatch
    Stopwatch stopwatch = Stopwatch.createStarted();
    // Do somemething extensive
    Thread.sleep(123l);
    stopwatch.stop();

    long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
    System.out.println("Time elapsed: " + millis);

    // LoadCache
    loadingCacheExample();

    // Future callbacks - no need to wait in whileLoop(task.isDone()) all the time to do some additional computation
    futureListenersExample();
  }

  public static void futureListenersExample() {
    ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    Callable<String> task = () -> {
      Thread.sleep(1000);
      return "Task String Result";
    };

    ListenableFuture<String> future = executor.submit(task);

    Futures.addCallback(future, new FutureCallback<String>() {
      @Override
      public void onSuccess(String taskResult) {
        System.out.println("Great success! Task result is: " + taskResult);

      }

      @Override
      public void onFailure(Throwable e) {
        System.out.println("Something went wrong: " + e.getMessage());
      }
    });
  }

  public static void loadingCacheExample() throws ExecutionException {
    LoadingCache<Integer, BigInteger> map = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .expireAfterWrite(60, TimeUnit.MINUTES)
        .expireAfterAccess(120, TimeUnit.MINUTES)
        .initialCapacity(50)
        .concurrencyLevel(4)
        .build(
            new CacheLoader<Integer, BigInteger>() {
              @Override
              public BigInteger load(Integer source) throws Exception {
                Thread.sleep(1500);
                return BigInteger.valueOf(source).multiply(new BigInteger("500"));
              }
            });

    // getting value 4 first time will take 5 seconds
    System.out.println("Calling cache get 4 for the first time: ");
    System.out.println(map.get(4));
    // getting value 4 for the second time will return immediately because its already cached
    System.out.println("Calling cache get 4 for the second time time: ");
    System.out.println(map.get(4));
  }

  public static void addGrade(Integer grade) {
    checkNotNull(grade, "Grade cannot be null");
    checkArgument(grade >= 0 && grade <= 100, "Grade must be between 0 and 100");

    System.out.println("Adding grades");

  }

  @Test
  public void testCycle() {
    List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
    Iterable iterable = Iterables.cycle(list);
    Iterator it = iterable.iterator();
    for (int i = 0; i < 1000; i++) {
      it.next();
    }
    assertEquals(it.next(), 1);

  }

  @Test
  public void testPartition() {

  }

  @Test
  public void testPaddedPartition() {
    List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
    Iterable iterable = Iterables.paddedPartition(list, 2);
    Iterator it = iterable.iterator();
    assertEquals(it.next(), Lists.newArrayList(1, 2));
    assertEquals(it.next(), Lists.newArrayList(3, 4));
    assertEquals(it.next(), Lists.newArrayList(5, null));
  }
}
