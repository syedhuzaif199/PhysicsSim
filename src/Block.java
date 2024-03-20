import java.awt.*;

public class Block extends PhysicsOBJ implements Drawable
{

    int width, height;
    Color color;

    public Block() {
        super();
        width = height = 20;
        color = Color.black;
    }

    @Override
    public boolean contains(double x, double y) {
        return x > position.x - width/2 && x < position.x + width/2 && y > position.y - height/2 && y < position.y + height/2;
    }

    public Block(Vector2 position, int width, int height, int mass) {
        super();
        this.width = width;
        this.height = height;
        this.mass = mass;
        this.position = position;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int)(position.x - width/2), (int)(position.y - height/2), this.width, this.height);
    }

}
