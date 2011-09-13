package org.test;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.Configuration;

import static java.lang.System.out;

import java.util.Calendar;
import java.util.Date;

public class App {
  public static void main(String[] args) {
    new App().testIt();
  }

  static CacheManager cacheManager = new CacheManager(App.class.getResourceAsStream("/ehcache.xml"));
  Ehcache cache = cacheManager.getEhcache("date-cache");
  private void testIt() {
    Date now = Calendar.getInstance().getTime();
    final Calendar instance = Calendar.getInstance();
    instance.add(Calendar.DAY_OF_MONTH, -1);
    Date before = instance.getTime();
    // cache.bootstrap();
    cache.put(new Element(1, now));
    cache.put(new Element(2, before));

    dumpCacheInfo(cache);

    out.println("value found with key 1: " + cache.get(1).getObjectValue());
    out.println("value found with key 2: " + cache.get(2).getObjectValue());
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
    out.println("value found with key 3: " + cache.get(3));

    dumpCacheInfo(cache);
  }

  private void dumpCacheInfo(final Ehcache cache) {
    out.println("cache-size: " + cache.getSize());
    out.println("cache-status: " + cache.getStatus());
    out.println("cache-guild: " + cache.getGuid());
    out.println("cache-name: " + cache.getName());
    out.println("mem-size: " + cache.calculateInMemorySize());
    out.println("heap-size: " + cache.calculateOffHeapSize());
    out.println("avg get-time: " + cache.getAverageGetTime());
    out.println("mem store-size: " + cache.getMemoryStoreSize());
    out.println("off-heap size: " + cache.getOffHeapStoreSize());
    out.println("cache-statistics: " + cache.getStatistics());
  }
}
