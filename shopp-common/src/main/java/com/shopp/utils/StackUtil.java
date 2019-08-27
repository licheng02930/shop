package com.shopp.utils;

import java.util.Arrays;

/**
 * 类描述:
 *
 * @author: licheng
 * @date: 2019-06-17 15:52
 */
public class StackUtil {

    /**
     * 默认栈大小
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * 数组
     */
    private Object[] tables = new Object[DEFAULT_SIZE];


    private int currentIndex;

    /**
     * 出栈
     *
     * @return
     */
    public Object pop() {
        if (currentIndex == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        //获取下一个元素
        Object o = tables[--currentIndex];
        //清除当前元素
        tables[currentIndex] = null;
        return o;
    }

    /**
     * 入栈
     * @param o
     */
    public void push(Object o) {
        ensureCapacityInternal(currentIndex);
        tables[currentIndex++] = o;
    }

    private void ensureCapacityInternal(int size) {
        if (size == tables.length) {
            tables = Arrays.copyOf(tables, size + (size >> 1));
        }
    }

    public static void main(String[] args) {
        StackUtil stackUtil = new StackUtil();
        int size = 20;
        for (int i = 0; i < size; i++) {
            stackUtil.push(i);
        }
        for (int i = 0; i < size; i++) {
            System.out.println(stackUtil.pop());
        }
    }
}
