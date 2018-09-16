package multithreading.raceConditions;

import java.util.concurrent.Semaphore;

public class CheckThenAct {

	private int number;
	Semaphore s = new Semaphore(1);

	private void changeNumber() {
		try {
			s.acquire();
			
			if (number == 0) {
				System.out.println(Thread.currentThread().getName() + " changed!");
				number = -1;

			} else {
				System.out.println(Thread.currentThread().getName() + " unchanged!");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		s.release();
	}

	public static void main(String[] args) {

		final CheckThenAct unsafe = new CheckThenAct();

		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					unsafe.changeNumber();

				}
			}, "T" + i).start();

		}

	}

}
