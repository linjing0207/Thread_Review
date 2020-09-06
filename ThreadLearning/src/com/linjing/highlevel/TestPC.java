package com.linjing.highlevel;


//测试：生产者消费者模式->利用缓存区解决：管程法
//生产者，消费者，产品，缓冲区
//理解：生产者"放"和消费者"拿走"不能同时进行，同一时间只能有一个操作。
//notifyAll()：唤醒所有线程。
public class TestPC {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        Producer p = new Producer(container);
        Consumer c = new Consumer(container);

        p.start();
        c.start();
    }
}


//生产者
class Producer extends Thread {
    SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }

    @Override public void run() {
        for (int i = 0; i < 100; i++) {
            container.put(new Product(i));
            System.out.println("生产了第" + i + "个商品");
        }
    }
}


//消费者
class Consumer extends Thread {
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了-->第" + container.take().id + "个商品");
        }
    }
}


//产品
class Product {
    int id; //产品编号

    public Product(int id) {
        this.id = id;
    }
}


//缓冲区：同一块资源（生产者和消费者要一起竞争）
class SynContainer {

    //需要一个容器
    Product[] products = new Product[10];
    //容器计数器
    int count = 0;

    //生产者放入产品
    public synchronized void put(Product product) {
        //容器满了
        if (count == products.length) {
            //生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //容器没满，则需要放入产品
        products[count] = product;
        count++;
        //有产品了，通知消费者消费，
        this.notifyAll();
    }

    //消费者消费
    public synchronized Product take() {
        //容器为空：没货了
        if (count == 0) {
            //通知生产者生产，消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //有产品了，可以消费
        count--;
        Product product = products[count]; //返回消费的产品
        //此时通知生产者去生产
        this.notifyAll();
        return product;
    }

}
