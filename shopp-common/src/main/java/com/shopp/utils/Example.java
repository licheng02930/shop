package com.shopp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类描述:
 *
 * @author: licheng
 * @date: 2019-06-15 17:24
 */
public class Example extends Demo{

    @Override
     void getS(){

    }
    @Override
    public void getA(){

    }
    @Override
    public void getB(){

    }


    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();

        threadLocal.set("wo");
        threadLocal.get();

       Lock loc = new ReentrantLock();
    }

}



abstract class Demo{

     abstract void getS();
    public abstract void getA();
    public abstract void getB();
}