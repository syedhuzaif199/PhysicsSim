import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

abstract public class PhysicsOBJ implements MouseListener, MouseMotionListener{

    double mass;
    Vector2 velocity;
    Vector2 position;
    Vector2 acceleration;


    boolean locked;


    public PhysicsOBJ() {
        position = Vector2.zero();
        velocity = Vector2.zero();
        acceleration = Vector2.zero();
        mass = 1;
        locked = false;

    }
    public void update(double deltaTime) {
        if(locked) {
            return;
        }
        int steps = 10;

        velocity.x = RungeKuttaSolver.solve(0, velocity.x, deltaTime / steps, steps, new DiffFunction() {
            @Override
            public double call(double x, double y) {
                return acceleration.x;
            }
        });

        velocity.y = RungeKuttaSolver.solve(0, velocity.y, deltaTime / steps, steps, new DiffFunction() {
            @Override
            public double call(double x, double y) {
                return acceleration.y;
            }
        });


        position.x = RungeKuttaSolver.solve(0, position.x, deltaTime / steps, steps, new DiffFunction() {
            @Override
            public double call(double x, double y) {
                return velocity.x;
            }
        });

        position.y = RungeKuttaSolver.solve(0, position.y, deltaTime / steps, steps, new DiffFunction() {
            @Override
            public double call(double x, double y) {
                return velocity.y;
            }
        });


        acceleration = Vector2.zero();


    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getVelocity() {
        return this.velocity;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public Vector2 getAcceleration() {
        return this.acceleration;
    }

    public void applyNetForce(Vector2 force) {
        acceleration = force.getScaled(1.0/mass);
    }

    public void applyForce(Vector2 force) {
        acceleration.add(force.getScaled(1.0/mass));
    }

    private void lock() {
        this.locked = true;
    }

    private void unlock() {
        if(locked) {
            this.velocity = Vector2.zero();
            this.acceleration = Vector2.zero();
        }
        this.locked = false;
    }

    abstract public boolean contains(double x, double y);

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!contains(e.getX(), e.getY()))
            return;
        lock();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        unlock();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(!locked)
            return;
        position.x = e.getX();
        position.y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}
