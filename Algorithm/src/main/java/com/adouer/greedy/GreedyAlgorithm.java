package com.adouer.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 贪心算法
 * @author adouer
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        //创建广播电台,放入到 Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //将各个电台放入到 broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");


        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");


        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

// 加 入 到 map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        //创建 ArrayList,  存放选择的电台集合
        ArrayList<String> selects = new ArrayList<String>();
        //保存每轮最大的key
        String maxKey = null;
        HashSet<String> tempSet = new HashSet<String>();

        while (allAreas.size() != 0) {
            //保存本轮覆盖地区最大值数量
            int maxNum = 0;
            maxKey = null;
            for (Map.Entry<String, HashSet<String>> entry : broadcasts.entrySet()) {
                String key = entry.getKey();
                HashSet<String> set = entry.getValue();
                tempSet.clear();
                tempSet.addAll(set);
                tempSet.retainAll(allAreas);    //交集后的内容赋值给tempSet中
                //找出本轮覆盖最大key
                // tempSet.size() >broadcasts.get(maxKey).size()):这个有问题，已经修改为maxNum
                if (maxKey == null || (maxKey != null && tempSet.size() > maxNum)) {
                    maxKey = key;
                    maxNum = tempSet.size();
                }

            }
            selects.add(maxKey);
            allAreas.removeAll(broadcasts.get(maxKey));
        }
        System.out.println("selects = " + selects);
    }
}
