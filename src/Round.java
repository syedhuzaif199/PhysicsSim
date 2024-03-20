import java.awt.*;

public class Round extends PhysicsOBJ implements Drawable{

    int radius;
    Color color;

    int[] trailx, traily;
    int trailLength;

    int[] trailPolyX, trailPolyY, trailNormX, trailNormY;

    int trailDiff;
    public Round() {
        color = Color.black;
        radius = 10;
        trailLength = 200;
        trailx = new int[trailLength];
        traily = new int[trailLength];


        for(int i = 0; i < trailLength; i++) {
            trailx[i] = (int)this.position.x;
            traily[i] = (int)this.position.y;
        }


    }

    @Override
    public boolean contains(double x, double y) {
        return Math.pow((x-position.x),2) + Math.pow((y-position.y),2) < radius*radius;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)this.position.x-radius, (int)this.position.y-radius, 2*this.radius, 2*this.radius);

        for(int i = 0; i < trailLength - 1; i++) {
            trailx[i] = trailx[i+1];
            traily[i] = traily[i+1];
        }

        trailx[trailLength - 1] = (int)position.x;
        traily[trailLength - 1] = (int)position.y;

        g.setColor(new Color(0,0,0, 0));
        for(int i = 0; i < trailLength; i++) {
            int rad = (i+1) * radius / trailLength;
            g.fillOval(trailx[i] - rad, traily[i] - rad, 2*rad, 2*rad);
        }
    }



}
