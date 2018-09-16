package multithreading.consumerproducer;

import java.util.LinkedList;
import java.util.Queue;

public class ConsumerProducerTest {

	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<>();
		int maxSize = 10;

		Producer producer = new Producer(queue, maxSize, "Producer");
		Consumer consumer = new Consumer(queue, "Consumer");

		producer.start();
		consumer.start();
		
		
	}

}
