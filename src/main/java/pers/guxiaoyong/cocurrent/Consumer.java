package pers.guxiaoyong.cocurrent;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {
    Semaphore semaphore;
    List<Integer> nums;

    public Consumer(Semaphore semaphore,List<Integer> nums) {
        this.semaphore = semaphore;
        this.nums = nums ;
    }

    @Override
    public void run() {
        try {
            while (nums.size()>0) {
                //critical section
//                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ",消费了" + nums.remove(nums.size()-1));
//                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}