package dao.groupDao;
public class test implements Runnable {
    public void run() {
        for (int i = 0; i < 10; ++i) {
            synchronized (this){
                if (count > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(count--);
                }
            }
        }
    }

    public static void main(String[] args) {
        test he = new test();
        Thread h1 = new Thread(he);
        Thread h2 = new Thread(he);
        Thread h3 = new Thread(he);
        h1.start();
        h2.start();
        h3.start();
    }

    private int count = 5;
}