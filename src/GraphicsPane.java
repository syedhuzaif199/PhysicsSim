import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

class GraphicsPane extends JPanel implements Runnable, MouseListener, MouseMotionListener {
    final int FPS = 120;
    final long delay = 1000 / FPS;
    int width, height;
    Thread thread;

    long time;

    JLabel timeLabel;

    ArrayList<Drawable> drawables;

    GraphicsPane(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));

        time = 0;
        timeLabel = new JLabel("time: " + TimeFormatter.colonSeparated(0));
        add(timeLabel);

        drawables = new ArrayList<>();
        addMouseListener(this);
        addMouseMotionListener(this);

        thread = new Thread(this);
        thread.start();


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        timeLabel.setText("time: " + TimeFormatter.colonSeparated(time));
        for (Drawable d : drawables) {
            d.draw(g);
        }

    }

    void addDrawable(Drawable d) {
        drawables.add(d);
    }

    @Override
    public void run() {
        while (Thread.currentThread() == thread) {
            repaint();
            try {
                Thread.sleep(delay);
                time += delay;
            } catch (InterruptedException e) {
                System.out.println("Exception: " + e);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(Drawable d : drawables) {
                PhysicsOBJ p = (PhysicsOBJ)d;
                p.mouseClicked(e);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(Drawable d : drawables) {
            PhysicsOBJ p = (PhysicsOBJ)d;
            p.mousePressed(e);

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(Drawable d : drawables) {
            PhysicsOBJ p = (PhysicsOBJ)d;
            p.mouseReleased(e);

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for(Drawable d : drawables) {
            PhysicsOBJ p = (PhysicsOBJ)d;
            p.mouseDragged(e);
        }


    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(Drawable d : drawables) {
            PhysicsOBJ p = (PhysicsOBJ)d;
            p.mouseMoved(e);
        }
    }
}
