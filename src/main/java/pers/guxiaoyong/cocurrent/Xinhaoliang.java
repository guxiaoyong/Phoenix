package pers.guxiaoyong.cocurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Author:guxiaoyong.
 * Date:2017/5/3.
 * Time:下午7:43.
 * Description:
 */
public class Xinhaoliang {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(1,true);
        List<Integer> start = new ArrayList<>();
        for(int i = 0 ; i < 100 ; i++){
            start.add(i);
        }
        Thread t1 = new Thread(new Consumer(semaphore,start), "线程1");
        t1.start();

        Thread t2 = new Thread(new Consumer(semaphore,start), "线程2");
        t2.start();

        Thread t3 = new Thread(new Consumer(semaphore,start), "线程3");
        t3.start();

        Thread t4 = new Thread(new Consumer(semaphore,start), "线程4");
        t4.start();

        Thread t5 = new Thread(new Consumer(semaphore,start), "线程5");
        t5.start();

        Thread t6 = new Thread(new Consumer(semaphore,start), "线程6");
        t6.start();

    }

}
