package cn.swallowff.modules.demo.thread;

/**
 * @author Administrator
 * @description
 * @create 2019/7/26
 */
public class ProductTest {
    public static void main(String args[]) {
        Product product = new Product();
        new Thread(new Producer(product)).start();
        new Thread(new Consumer(product)).start();
    }
    static class Product {
        private static final int MAX_PRODUCT = 20;
        private static final int MIN_PRODUCT = 0;
        private int PRODUCT = 0;
        synchronized public void add() {
            if (this.PRODUCT >= MAX_PRODUCT) {
                try {
                    System.out.println("产品已满，请稍后生产");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
            this.PRODUCT++;
            System.out.println("生产者生产了第" + this.PRODUCT + "个产品" + ",时间：" + System.currentTimeMillis());
            notify();
        }
        synchronized public void sub() {
            if (this.PRODUCT <= MIN_PRODUCT) {
                try {
                    System.out.println("产品处于缺货状态");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
            System.out.println("消费者消费了第" + this.PRODUCT + "个产品" + ",时间：" + System.currentTimeMillis());
            this.PRODUCT--;
            notifyAll();
        }
    }
    static class Producer implements Runnable {
        private Product product;
        public Producer(Product product) {
            this.product = product;
        }
        @Override
        public void run() {
            System.out.println("生产者开始生产产品");
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                product.add();
            }
        }
    }
    static class Consumer implements Runnable {
        private Product product;
        public Consumer(Product product) {
            this.product = product;
        }
        @Override
        public void run() {
            System.out.println("消费者开始消费产品");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                product.sub();
            }
        }
    }
}


