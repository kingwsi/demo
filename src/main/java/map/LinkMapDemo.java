package map;

import java.util.*;

/**
 * description:  <br>
 * date: 2022/1/26 15:16 <br>
 * author: ws <br>
 */
public class LinkMapDemo {
    public static void main(String[] args) {
        Map<String, String> treeMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });
        treeMap.put("a", "1");
        treeMap.put("c", "1");
        treeMap.put("b", "1");
        treeMap.put("f", "1");
        treeMap.put("d", "1");
        treeMap.put("g", "1");
        treeMap.put("e", "1");
        System.out.println(treeMap);
    }
}
