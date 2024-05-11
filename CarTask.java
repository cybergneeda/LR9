import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class CarTask implements Runnable{
    final int SCALE = 4;
    private int speed;
    private static int idGen = 1;
    public int id;
    CyclicBarrier startLine;
    Semaphore tunnel;
    
    public CarTask(int speed,CyclicBarrier startLine,Semaphore tunnel)
    {
        this.speed=speed;
        this.id=idGen;
        idGen++;
        this.startLine=startLine;
        this.tunnel=tunnel;
    }

    @Override
    public void run() {
        System.out.println("Машина "+ id + " стартовала "+ new Date()+"\nСкорость машины "+id +": "+speed+" км/ч");
            try {             
                startLine.await();
            } catch (InterruptedException e) {            
                e.printStackTrace();
            } catch (BrokenBarrierException e) {   
                e.printStackTrace();
            }

            try {
                Thread.sleep((int)280000*SCALE/speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                tunnel.acquire();
                System.out.println("Машина "+id+" заехала в тоннель "+new Date());
                Thread.sleep((int)20000*SCALE/speed);
                System.out.println("Машина "+id+" выехала из тоннеля "+new Date());
                tunnel.release();             
            } catch (InterruptedException e) {             
                e.printStackTrace();
            }
       
        try {
            Thread.sleep((int)200000*SCALE/speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Машина "+id+" финишировала "+ new Date());
    }}