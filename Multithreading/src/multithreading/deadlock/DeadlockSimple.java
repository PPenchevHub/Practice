package multithreading.deadlock;

public class DeadlockSimple {

	public static Object lock1 = new Object();
	public static Object lock2 = new Object();

	public static void main(String[] args) {

		new ThreadDemo().start();
		new ThreadDemo2().start();

	}

}

class ThreadDemo extends Thread {
	public void run() {
		synchronized (DeadlockSimple.lock2) {

			System.out.println(Thread.currentThread().getName() + " is holding lock1!");

			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}

			System.out.println(Thread.currentThread().getName() + " is waiting for lock2!");
			synchronized (DeadlockSimple.lock1) {
				System.out.println(Thread.currentThread().getName() + " is holding lock1 && lock2!");

			}
		}

	}

}

class ThreadDemo2 extends Thread {
	public void run() {
		synchronized (DeadlockSimple.lock2) {
			System.out.println(Thread.currentThread().getName() + " is holding lock2!");

			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}

			System.out.println(Thread.currentThread().getName() + " is waiting for lock1!");
			synchronized (DeadlockSimple.lock1) {
				System.out.println(Thread.currentThread().getName() + " is holding lock1 && lock2!");

			}
		}

	}

}
