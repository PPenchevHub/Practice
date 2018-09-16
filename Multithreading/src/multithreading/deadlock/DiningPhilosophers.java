package multithreading.deadlock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {

	public static void main(String[] args) {
		int philNumber = 5;
		Philosopher phils[] = new Philosopher[philNumber];
		Chopsticks chopsticks[] = new Chopsticks[philNumber];

		for (int i = 0; i < philNumber; i++) {
			chopsticks[i] = new Chopsticks();

		}

		for (int i = 0; i < philNumber; i++) {

			phils[i] = new Philosopher(i, chopsticks[(i + 1) % philNumber], chopsticks[i]);
			phils[i].start();
		}

	}

}

class Philosopher extends Thread {

	public int number;
	public Chopsticks left;
	public Chopsticks right;
	ReentrantLock nlock = new ReentrantLock();

	public Philosopher(int n, Chopsticks left, Chopsticks right) {
		number = n;
		this.left = left;
		this.right = right;

	}

	public void run() {
		while (true) {

			nlock.lock();
			synchronized (left) {

				left.pickUp();
				System.out.println("Phil: " + number + " locked up the left ");

				synchronized (right) {

					right.pickUp();

					System.out.println("Phil: " + number + " locked up the right ");
					eat();
					System.out.println("Phil: " + number + " is eating");

					left.putDown();

					System.out.println("Phil: " + number + " put down the left ");
					right.putDown();

					System.out.println("Phil: " + number + " put down the right ");
				}
			}
			nlock.unlock();

			think();
		}

	}

	private void think() {
		try {
			System.out.println("Phil: " + number + " is thinking");

			Thread.sleep(100);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void eat() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

class Chopsticks {

	boolean inUse = false;

	public void pickUp() {

		while (inUse) {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		inUse = true;

	}

	public void putDown() {

		inUse = false;

	}

	public boolean isInUse() {

		return inUse;
	}

}
