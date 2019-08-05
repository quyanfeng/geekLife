package com.geek.life.util;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * @Description:
 * @Author: 屈艳锋
 * @Date: 2019/7/3 19:56
 */

public class WeightRandom<K,V extends Number> {
    private TreeMap<Double, K> weightMap = new TreeMap<Double, K>();
    private static final Logger logger = LoggerFactory.getLogger(WeightRandom.class);

    public WeightRandom(List<Pair<K, V>> list) {
        Preconditions.checkNotNull(list, "list can NOT be null!");
        for (Pair<K, V> pair : list) {
            double lastWeight = this.weightMap.size() == 0 ? 0 : this.weightMap.lastKey().doubleValue();//统一转为double
            this.weightMap.put(pair.getValue().doubleValue() + lastWeight, pair.getKey());//权重累加
        }
    }

    public K random() {
        double randomWeight = this.weightMap.lastKey() * Math.random();
        SortedMap<Double, K> tailMap = this.weightMap.tailMap(randomWeight, false);
        return this.weightMap.get(tailMap.firstKey());
    }




    public  static void main(String[] args){
        Pair< String,Integer> pair=new ImmutablePair<>("zero",1);
        Pair< String,Integer> pair1=new ImmutablePair<>("one",3);
        List<Pair< String,Integer>> list=new ArrayList<>();
        list.add(pair);
        list.add(pair1);
        WeightRandom weightRandom=new WeightRandom(list);
        for (int i=0;i<4;i++){

            System.out.println(weightRandom.random());
        }
    }
}
