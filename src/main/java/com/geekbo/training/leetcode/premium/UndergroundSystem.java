package com.geekbo.training.leetcode.premium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 1396. 设计地铁系统
 * 已解答
 * 中等
 * 地铁系统跟踪不同车站之间的乘客出行时间，并使用这一数据来计算从一站到另一站的平均时间。
 *
 * 实现 UndergroundSystem 类：
 *
 * void checkIn(int id, string stationName, int t)
 * 通行卡 ID 等于 id 的乘客，在时间 t ，从 stationName 站进入
 * 乘客一次只能从一个站进入
 * void checkOut(int id, string stationName, int t)
 * 通行卡 ID 等于 id 的乘客，在时间 t ，从 stationName 站离开
 * double getAverageTime(string startStation, string endStation)
 * 返回从 startStation 站到 endStation 站的平均时间
 * 平均时间会根据截至目前所有从 startStation 站 直接 到达 endStation 站的行程进行计算，也就是从 startStation 站进入并从 endStation 离开的行程
 * 从 startStation 到 endStation 的行程时间与从 endStation 到 startStation 的行程时间可能不同
 * 在调用 getAverageTime 之前，至少有一名乘客从 startStation 站到达 endStation 站
 * 你可以假设对 checkIn 和 checkOut 方法的所有调用都是符合逻辑的。如果一名乘客在时间 t1 进站、时间 t2 出站，那么 t1 < t2 。所有时间都按时间顺序发生。
 */
class UndergroundSystem {
    class Start {
        String station;
        int time;

        public Start(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    class StartEnd {
        String start;
        String end;

        public StartEnd(String start, String end) {
            this.start = start;
            this.end = end;
        }

        public int hashCode() {
            return (start + end).hashCode();
        }

        public boolean equals(Object obj2) {
            if (obj2 instanceof StartEnd) {
                StartEnd startEnd2 = (StartEnd) obj2;
                return this.start.equals(startEnd2.start) && this.end.equals(startEnd2.end);
            }
            return false;
        }
    }

    class SumAmount {
        int sum;
        int amount;

        public SumAmount(int sum, int amount) {
            this.sum = sum;
            this.amount = amount;
        }
    }

    Map<Integer, Start> startInfo;
    Map<StartEnd, SumAmount> table;

    public UndergroundSystem() {
        startInfo = new HashMap<Integer, Start>();
        table = new HashMap<StartEnd, SumAmount>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        startInfo.put(id, new Start(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        Start start = startInfo.get(id);
        String startStation = start.station;
        int startTime = start.time;
        StartEnd startEnd = new StartEnd(startStation, stationName);
        SumAmount sumAmount = table.getOrDefault(startEnd, new SumAmount(0, 0));
        sumAmount.sum += t - startTime;
        sumAmount.amount++;
        table.put(startEnd, sumAmount);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        StartEnd index = new StartEnd(startStation, endStation);
        SumAmount sumAmount = table.get(index);
        int sum = sumAmount.sum, amount = sumAmount.amount;
        return 1.0 * sum / amount;
    }
}