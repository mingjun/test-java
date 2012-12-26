package name.xumingjun.lang;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import name.xumingjun.util.CodeBeautifier;

import org.junit.Test;
class NonAtomicInteger {
	int count = 0;
}
public class TestConcurrent {
	@Test
	public void testAtomic() throws InterruptedException {
		final Random r = new Random();
		final AtomicInteger count = new AtomicInteger(0);
		final NonAtomicInteger w = new NonAtomicInteger();
		final int SIZE = 100, loop=100;
		Thread [] ts = new Thread[SIZE];
		for(int i=0;i<ts.length;i++) {
			ts[i] = new Thread(){
				public void run() {
					for(int j=0;j<loop;j++) {
						count.incrementAndGet();
						w.count ++;
						CodeBeautifier.sleepThread(r.nextInt(10));
					}
				}
			};
		}
		for(Thread t: ts) {
			t.start();
		}

		for(Thread t: ts) {
			t.join();
		}
		
		System.out.println(count.get() + "\t" + w.count);
	}
}
