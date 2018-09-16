package multithreading.consumerproducer;

import java.util.Queue;
import java.util.Random;

public class Producer extends Thread {
	private Queue<Integer> queue;
	int maxSize;

	public Producer(Queue<Integer> queue, int maxSize, String name) {
		super(name);
		this.queue = queue;
		this.maxSize = maxSize;

	}

	public void run() {
		Random rand = new Random();
		while (true) {
			synchronized (queue) {
				while (queue.size() == maxSize) {
					try {
						System.out.println("Queue is full, waiting for consumer! ");
						queue.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				int i = rand.nextInt();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Added object: " + i);
				queue.add(i);

				queue.notifyAll();

			}

		}

	}

}
