import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Race {
    
    public static void main(String[] args) {
        CyclicBarrier startLine = new CyclicBarrier(4);
        Semaphore tunnel = new Semaphore(2);     
        Thread[]cars = new Thread[4];

        System.out.print("\033[H\033[2J");
        System.out.println("Гонка началась");

       for (int i = 0; i < 4; i++) {
            cars[i] = new Thread(new CarTask(60+(int)(Math.random()*100),startLine,tunnel));   
            cars[i].start();
       }

       while (cars[0].isAlive()&&cars[1].isAlive()&&cars[2].isAlive()&&cars[3].isAlive()) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}

       for (int i = 0; i < cars.length; i++) {
        if(!cars[i].isAlive())
        {
            System.out.println("Машина "+(i+1)+" победила, поздравляем!");
        }}}}
