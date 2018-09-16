package multithreading.consumerproducer;

import java.util.Queue;

public class Consumer extends Thread {

	private Queue<Integer> queue;

	public Consumer(Queue<Integer> queue, String name) {
		super(name);
		this.queue = queue;

	}

	public void run() {

		while (true) {

			synchronized (queue) {

				while (queue.isEmpty()) {
					System.out.println("Queue is empty and the consumer is waiting for the producer!");
					try {
						queue.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println("Consumed: " + queue.remove());
				queue.notifyAll();

			}

		}

	}

}
