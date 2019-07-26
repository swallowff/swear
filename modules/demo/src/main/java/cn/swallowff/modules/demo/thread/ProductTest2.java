package cn.swallowff.modules.demo.thread;

/**
 * @author Administrator
 * @description
 * @create 2019/7/26
 */
public class ProductTest2 {
    public static void main(String args[]) {
        Product product = new Product();
        new Thread(new Producer(product)).start();
        new Thread(new Consumer(product)).start();
    }
    static class Product {
        volatile private boolean isEmpty = true;
        synchronized public void add() {
            while (!isEmpty) {
                try {
                    System.out.println("已经有了产品，等待消费");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("生产者生产了一个产品，时间" + System.currentTimeMillis());
            isEmpty = false;
            notify();
        }
        synchronized public void sub() {
            while (isEmpty) {
                try {
                    System.out.println("没有了产品，等待生产");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("消费者消费了一个产品，时间" + System.currentTimeMillis());
            isEmpty = true;
            notify();
        }
    }
    static class Producer implements Runnable {
        private Product product;
        public Producer(Product product) {
            this.product = product;
        }
        @Override
        synchronized public void run() {
            while (true) {
                try {
                    Thread.sleep(1);//强制切换线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                product.add();//添加一个商品
            }
        }
    }
    static class Consumer implements Runnable {
        private Product product;
        public Consumer(Product product) {
            this.product = product;
        }
        @Override
        synchronized public void run() {
            while (true) {
                try {
                    Thread.sleep(1);//强制切换线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                product.sub();//减少一个商品
            }
        }
    }
}