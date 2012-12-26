package name.xumingjun.lang;

import name.xumingjun.util.CodeBeautifier;

import org.junit.Test;
//轻量级 读写并发控制
class Volatiled {
	volatile int count = 0; //will NOT be copy in each thread as an "Optimization"
}

public class TestVolatile {
	
	@Test
	public void testVolatiled() {
		final int size = 10;
		final Volatiled target = new Volatiled();
		Thread [] ts = new Thread[size];
		for(int i=0;i<ts.length;i++) {
			ts[i] = new Thread(){
				public void run() {
					while(target.count == 0) {
						CodeBeautifier.sleepThread(100); //FIXME not work
					}
				}
			};
		}
		for(Thread t: ts) {
			t.start();
		}
		
		CodeBeautifier.sleepThread(5000);
		target.count ++;
		long incTime = System.currentTimeMillis();
		System.out.println("++ ed ");
		
		try {
			for(Thread t: ts) {
				t.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("over " + (System.currentTimeMillis()-incTime));
		
	}

}
