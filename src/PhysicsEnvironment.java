import java.util.ArrayList;

public class PhysicsEnvironment implements Runnable{

    final int FPS = 120;
    final long delay = 1000/FPS;

    Thread thread;

    double time;
    ArrayList<PhysicsOBJ> objects;

    PhysicsEnvironment() {
        objects = new ArrayList<>();
        thread = new Thread(this);
        time = 0;
        thread.start();
    }

    public void addObject(PhysicsOBJ obj) {
        objects.add(obj);
    }

    public void update() {
        for (PhysicsOBJ obj : objects) {
            obj.update(1f/FPS);
        }
    }


    @Override
    public void run() {
        while(Thread.currentThread() == thread) {
            update();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
