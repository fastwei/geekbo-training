package com.geekbo.training.leetcode.list;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 *
 * 460. LFU Cache
 * Hard
 * Topics
 * Companies
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 *
 * LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.
 *
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.
 *
 * The functions get and put must each run in O(1) average time complexity.
 *
 */
class LFUCache {
    // Variables to store the capacity and current size of the cache
    private int capacity;
    private int size;
    
    // Map to store the key-value pairs in the cache
    private Map<Integer, Integer> keyToValue;
    
    // Map to store the frequency of each key in the cache
    private Map<Integer, Integer> keyToFrequency;
    
    // Map to store the keys with the same frequency in a linked hash set
    private Map<Integer, LinkedHashSet<Integer>> frequencyToKeys;
    
    // Variable to store the minimum frequency in the cache
    private int minFrequency;
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.keyToValue = new HashMap<>();
        this.keyToFrequency = new HashMap<>();
        this.frequencyToKeys = new HashMap<>();
        this.minFrequency = 0;
    }
    
    public int get(int key) {
        // Check if the key is present in the cache
        if (!keyToValue.containsKey(key)) {
            return -1;
        }
        
        // Update the frequency of the key
        int frequency = keyToFrequency.get(key);
        keyToFrequency.put(key, frequency + 1);
        
        // Update the linked hash set for the old frequency
        frequencyToKeys.get(frequency).remove(key);
        if (frequencyToKeys.get(frequency).isEmpty() && frequency == minFrequency) {
            minFrequency++;
        }
        
        // Update the linked hash set for the new frequency
        frequencyToKeys.computeIfAbsent(frequency + 1, k -> new LinkedHashSet<>()).add(key);
        
        // Return the value of the key
        return keyToValue.get(key);
    }
    
    public void put(int key, int value) {
        // Check if the capacity of the cache is reached
        if (capacity == 0) {
            return;
        }
        
        // Check if the key is already present in the cache
        if (keyToValue.containsKey(key)) {
            // Update the value of the key
            keyToValue.put(key, value);
            
            // Get the frequency of the key and update it
            int frequency = keyToFrequency.get(key);
            keyToFrequency.put(key, frequency + 1);
            
            // Update the linked hash set for the old frequency
            frequencyToKeys.get(frequency).remove(key);
            if (frequencyToKeys.get(frequency).isEmpty() && frequency == minFrequency) {
                minFrequency++;
            }
            
            // Update the linked hash set for the new frequency
            frequencyToKeys.computeIfAbsent(frequency + 1, k -> new LinkedHashSet<>()).add(key);
        } else {
            // Check if the cache is full
            if (size == capacity) {
                // Get the least frequently used key from the minimum frequency linked hash set
                int evictKey = frequencyToKeys.get(minFrequency).iterator().next();
                
                // Remove the least frequently used key from all the data structures
                frequencyToKeys.get(minFrequency).remove(evictKey);
                keyToValue.remove(evictKey);
                keyToFrequency.remove(evictKey);
                
                // Decrease the size of the cache
                size--;
            }
            
            // Add the new key-value pair to the cache
            keyToValue.put(key, value);
            keyToFrequency.put(key, 1);
            frequencyToKeys.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
            minFrequency = 1;
            
            // Increase the size of the cache
            size++;
        }
    }
}