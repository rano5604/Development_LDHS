package com.mysql.jdbc.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;





















public class LRUCache<K, V>
  extends LinkedHashMap<K, V>
{
  private static final long serialVersionUID = 1L;
  protected int maxElements;
  
  public LRUCache(int maxSize)
  {
    super(maxSize, 0.75F, true);
    maxElements = maxSize;
  }
  





  protected boolean removeEldestEntry(Map.Entry<K, V> eldest)
  {
    return size() > maxElements;
  }
}
