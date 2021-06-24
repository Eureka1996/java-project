package com.wufuqiang.hashmap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Wu Fuqiang
 * @create: 2021-04-05 18:34
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String,Integer> table = new HashMap<String, Integer>();

        table.put("语文",1);
        table.put("数学",2);
        table.put("英语",3);
        table.put("历史",4);
        table.put("政治",5);
        table.put("地理",6);
        table.put("生物",7);
        table.put("化学",8);

        for(Map.Entry<String,Integer> entry:table.entrySet()){
            System.out.println(entry.getKey()+"="+entry.getValue());
        }

        ConcurrentHashMap<String,Integer> concurrentHashMap = new ConcurrentHashMap<String, Integer>();
        concurrentHashMap.put("语文",1);

        TreeMap<Integer, String> tmap = new TreeMap<Integer, String>();
        tmap.put(8, "化学");
        tmap.put(1, "语文");
        tmap.put(3, "英语");
        tmap.put(2, "数学");
        tmap.put(4, "政治");
        tmap.put(5, "历史");
        tmap.put(6, "地理");
        tmap.put(7, "生物");

        for(Map.Entry<Integer, String> entry : tmap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        LinkedHashMap<String, Integer> lmap = new LinkedHashMap<String, Integer>();
        lmap.put("化学", 8);
        lmap.put("语文", 1);
        lmap.put("数学", 2);
        lmap.put("英语", 3);
        lmap.put("历史", 4);
        lmap.put("政治", 5);
        lmap.put("地理", 6);
        lmap.put("生物", 7);

        Integer zhengzhi = lmap.get("政治");
        for(Map.Entry<String, Integer> entry : lmap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
}
