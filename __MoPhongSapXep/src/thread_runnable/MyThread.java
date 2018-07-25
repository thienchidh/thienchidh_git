
package thread_runnable;

import getobj.GetFeature;
import model.ISort;

public class MyThread extends Thread {

    private ISort sort;

    public MyThread() {

        sort = GetFeature.getSort();
    }

    public MyThread(Runnable runnable) {

        super(runnable);
        sort = GetFeature.getSort();
    }

    public boolean continues() {

        return sort.continues();
    }

    public void endThread() {

        interrupt();
    }

    public boolean pause() {

        return sort.pause();
    }

    public void step() throws InterruptedException {

        sort.step();
    }
}
