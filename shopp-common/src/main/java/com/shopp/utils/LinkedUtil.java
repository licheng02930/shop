package com.shopp.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * 类描述:
 *
 * @author: licheng
 * @date: 2019-07-01 09:24
 */
public class LinkedUtil<E> {

    /**
     * 第一个节点
     */
    private Node<E> firstNode;
    /**
     * 第二个节点
     */
    private Node<E> lastNode;

    private int size;

    public LinkedUtil() {
    }


    /**
     * 添加节点
     */
    public void add(E e) {
        addLast(e);
    }

    /**
     * 插入节点
     */
    public void add(int index, E e) {
        linkBefore(e, node(index));
    }

    void linkBefore(E e, Node<E> succ) {
        final Node<E> pred = succ.prefix;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prefix = newNode;
        if (pred == null) {
            firstNode = newNode;
        } else {
            pred.suffix = newNode;
        }
        size++;
    }

    private Node<E> node(int index) {
        if (index < size >> 1) {
            Node<E> f = firstNode;
            for (int i = 0; i < index; i++) {
                f = f.suffix;
            }
            return f;
        } else {
            Node<E> l = lastNode;
            for (int i = size - 1; i > index; i--) {
                l = l.prefix;
            }
            return l;
        }
    }

    public void addFirst(E e) {
        Node<E> first = firstNode;
        Node<E> newNode = new Node<>(null, e, first);
        firstNode = newNode;
        if (first == null) {
            firstNode = newNode;
        } else {
            first.prefix = newNode;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> last = lastNode;
        Node<E> newNode = new Node<>(last, e, null);
        lastNode = newNode;
        if (last == null) {
            firstNode = newNode;
        } else {
            last.suffix = newNode;
        }
        size++;
    }

    private static class Node<E> {

        E v;
        /**
         * 前驱
         */
        private Node<E> prefix;

        /**
         * 后驱
         */
        private Node<E> suffix;

        Node(Node<E> p, E v, Node<E> s) {
            this.prefix = p;
            this.v = v;
            this.suffix = s;
        }
    }


    public static void main(String[] args) {
        List ss = new LinkedList();
        for (int i = 0; i < 5; i++) {
            ss.add("XX" + i);
        }

        ss.add(3, "xxxxx");
        System.out.println(5 >> 1);
    }
}
