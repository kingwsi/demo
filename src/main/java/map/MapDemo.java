package map;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * description:  <br>
 * date: 2022/1/11 16:48 <br>
 * author: ws <br>
 */
public class MapDemo {
    public static void main(String[] args) {
        TreeSet<Object> objectObjectTreeMap = new TreeSet<Object>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o1).compareTo((String)o2);
            }

            @Override
            public boolean equals(Object obj) {
                return super.equals(obj);
            }
        });
        objectObjectTreeMap.add("a");
        objectObjectTreeMap.add("c");
        objectObjectTreeMap.add("b");
        objectObjectTreeMap.add("e");
        objectObjectTreeMap.add("g");
        objectObjectTreeMap.add("f");
        objectObjectTreeMap.add("d");
        System.out.println(objectObjectTreeMap);
    }
}
