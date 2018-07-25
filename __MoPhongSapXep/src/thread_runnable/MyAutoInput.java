
package thread_runnable;

import java.util.Random;

import javax.swing.JTextArea;

public class MyAutoInput extends Thread {

    private int           delay;
    private boolean       flagAutoInput;
    private int           maxRand;
    private int           minRand;
    private Random        r;
    private JTextArea     txtInput;

    private StringBuilder sb;

    public MyAutoInput(JTextArea txtInput) {

        delay = 100;
        flagAutoInput = true;
        maxRand = 10;
        minRand = 1;
        this.txtInput = txtInput;
        sb = new StringBuilder();
    }

    public void continues() {

        flagAutoInput = true;
        synchronized(this) {
            notify();
        }
    }

    public int getDelay() {

        return delay;
    }

    public int getMaxRand() {

        return maxRand;
    }

    public int getMinRand() {

        return minRand;
    }

    public void pause() {

        flagAutoInput = false;
    }

    @Override
    public void run() {

        if(r == null) {
            r = new Random();
        }

        while(true) {
            try {
                sb.setLength(0);
                txtInput.append((sb.append(String.valueOf(minRand + r.nextInt((maxRand - minRand) + 1)))).append(" ").toString());
                txtInput.setCaretPosition(txtInput.getText().length());

                Thread.sleep(delay);
                synchronized(this) {
                    if(!flagAutoInput) {
                        wait();
                    }
                }
            } catch(InterruptedException e) {
                System.out.println("Thread autoInput is stopped!.");
            }
        }
    }

    public void setDelay(int delay) throws Exception {

        if(delay < 1) throw new Exception("delay must be >= 1!");
        this.delay = delay;
    }

    public void setMaxRand(int maxRand) {

        this.maxRand = maxRand;
    }

    public void setMinRand(int minRand) {

        this.minRand = minRand;
    }
}
