package multithreading.raceConditions;

public class ReadModifyWrite {

	private int number;
	Object lock = new Object();

	private  void incrementNumber() {
		synchronized (lock) {
			number++;
		}
	

	}

	private synchronized int getNumber() {
		return number;

	}

	public static void main(String[] args) throws InterruptedException {
		final ReadModifyWrite unsafe = new ReadModifyWrite();

		for (int i = 0; i < 1000; i++) {

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						//Thread.sleep((int)(Math.random()*100));
					} catch (Exception e) {}
						unsafe.incrementNumber();

					
				}
			}, "T" + i).start();

		}
		Thread.sleep(1000);
		System.out.println("Final number should be 1000 : " + unsafe.getNumber());
	}

}
