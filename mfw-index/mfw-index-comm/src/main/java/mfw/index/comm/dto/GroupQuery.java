package mfw.index.comm.dto;


import mfw.index.comm.util.StrUtil;
import mtime.lark.util.lang.FaultException;

import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mtime on 2016/11/3.
 */
public class GroupQuery {


    /**
     * 聚合字段
     * 不包含范围聚合字段
     */
    private Set<String> faectField;

    /**
     * 聚合条件
     */
    private Map<String, String[]> aggQuery;

    /**
     * 范围聚合
     * Map<String,RangeAgg[]>
     *     key:field
     *     value: 范围是 [ from TO to }  前 闭 后 开
     */
    private Map<String,RangeAgg<Number>[]> numRangeAgg;

    /**
     * 日期范围聚合
     * Map<String,RangeAgg[]>
     *     key:field
     *     value: 范围是 [ from TO to }  前 闭 后 开
     */
    private Map<String,RangeAgg<Temporal>[]> dateRangeAgg;

    /**
     * 对某个字段 执行聚合函数
     * 包括 ：求和  求最大值   求最小值  求平均值  求数量
     */
    private Map<String,Func[]> groupAgg;

    /**
     * 构造方法
     */
    public GroupQuery() {
        this.aggQuery = new HashMap<>();
        this.faectField = new HashSet<>();
        this.numRangeAgg = new HashMap<>();
        this.dateRangeAgg = new HashMap<>();
        this.groupAgg = new HashMap<>();
    }

    /**
     * 添加集合字段
     * @param fieldName
     */
    public void addAggField(String fieldName){
        if (StrUtil.isEmpty(fieldName)){
            throw new FaultException("需要聚合的字段不可以为空");
        }
        this.faectField.add(fieldName);
    }


    /**
     * 添加集合类型
     * @param fieldName
     * @param func
     */
    public void addFuncAgg(String fieldName , Func ... func){
        if (StrUtil.isEmpty(fieldName)){
            throw new FaultException("需要聚合的字段不可以为空");
        }
        if (func == null){
            throw new FaultException("需要聚合的函数不可以为空");
        }
        for (Func f : func) {
            this.groupAgg.put(fieldName+f.name(),func);
        }

    }


    public Set<String> getFaectField() {
        return faectField;
    }

    public void setFaectField(Set<String> faectField) {
        this.faectField = faectField;
    }

    public Map<String, String[]> getAggQuery() {
        return aggQuery;
    }


    public Map<String, RangeAgg<Number>[]> getNumRangeAgg() {
        return numRangeAgg;
    }


    public Map<String, RangeAgg<Temporal>[]> getDateRangeAgg() {
        return dateRangeAgg;
    }


    public Map<String, Func[]> getGroupAgg() {
        return groupAgg;
    }

    public void setGroupAgg(Map<String, Func[]> groupAgg) {
        this.groupAgg = groupAgg;
    }

    /**
     * 范围是 [ from TO to }  前 闭 后 开
     * @param <T>
     */
    public static class RangeAgg<T>{
        String key;

        T start;
        T end;

        /**
         *
         * @param start
         * @param end
         * @param key
         */
        public RangeAgg(T start, T end , String key) {
            this.start = start;
            this.end = end;
            this.key = key;
        }

        public T getStart() {
            return start;
        }

        public void setStart(T start) {
            this.start = start;
        }

        public T getEnd() {
            return end;
        }

        public void setEnd(T end) {
            this.end = end;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }


    /**
     * 聚合函数枚举
     */
    public enum Func{

        /**
         * 求最小值
         */
        MIN("min"),

        /**
         * 求最大值
         */
        MAX("max"),

        /**
         * 求平均值
         */
        AVG("avg"),

        /**
         * 求和
         */
        SUM("sum"),

        /**
         * 求数量
         */
        COUNT("count");

        private String name;

        Func(String name) {
            this.name = name;
        }
    }


}
