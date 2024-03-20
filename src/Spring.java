import java.awt.*;
public class Spring extends PhysicsOBJ implements Drawable{
    double length;
    double stiffness;
    double dampingFactor;

    double width;

    Vector2 end;

    PhysicsOBJ obj1, obj2;


    public Spring(double stiffness, Vector2 position, Vector2 end) {
        super();
        this.mass = 1;
        this.stiffness = stiffness;
        this.position = position;
        this.end = end;
        this.length = Vector2.add(position, end.getScaled(-1)).magnitude();
        this.width = 0.1*length;
        this.dampingFactor = 0;
    }

    public Spring(double stiffness, double dampingFactor, Vector2 position, Vector2 end) {
        this(stiffness, position, end);
        this.dampingFactor = dampingFactor;
    }


    void addObjects(PhysicsOBJ obj1, PhysicsOBJ obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.position = obj1.getPosition();
        this.end = obj2.getPosition();
    }

    void addObject1(PhysicsOBJ obj) {
        this.obj1 = obj;
        this.position = obj.getPosition();
    }

    void addObject2(PhysicsOBJ obj) {
        this.obj2 = obj;
        this.end = obj.getPosition();
    }

    void setDampingFactor(double dampingFactor) {
        this.dampingFactor = dampingFactor;
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        if (obj1 != null && obj2 != null) {
            Vector2 dir = Vector2.getDirectionVector(end, position);
            double stretch = dir.magnitude() - length;
            dir.normalize();
            Vector2 force1 = Vector2.add(dir.getScaled(-stiffness*stretch),obj1.velocity.getScaled(-dampingFactor));
            obj1.applyForce(force1);
            Vector2 force2 = Vector2.add(dir.getScaled(stiffness*stretch),obj2.velocity.getScaled(-dampingFactor));
            obj2.applyForce(force2);

        }
    }

    @Override
    public boolean contains(double x, double y) {
        return x < position.x + width && x > position.x - width && y < position.y + width && y > position.y - width;// || x < end.x + width && x > end.x - width && y < end.y + width && y > end.y - width;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        Vector2 dir = Vector2.getDirectionVector(position, end);
        int n = 20;

        for(int i = 0; i < 5; i++) {
            g.drawLine((int)(position.x + 4*i*dir.x/n),
                    (int)(position.y + 4*i*dir.y/n),
                    (int)(position.x + (4*i+1)*dir.x/n + dir.getOrthogonal().x*width),
                    (int)(position.y + (4*i+1)*dir.y/n + dir.getOrthogonal().y*width));

            g.drawLine((int)(position.x + (4*i+1)*dir.x/n + dir.getOrthogonal().x*width),
                    (int)(position.y + (4*i+1)*dir.y/n + dir.getOrthogonal().y*width),
                    (int)(position.x + (4*i+3)*dir.x/n - dir.getOrthogonal().x*width),
                    (int)(position.y + (4*i+3)*dir.y/n - dir.getOrthogonal().y*width));

            g.drawLine((int)(position.x + (4*i+3)*dir.x/n - dir.getOrthogonal().x*width),
                    (int)(position.y + (4*i+3)*dir.y/n - dir.getOrthogonal().y*width),
                    (int)(position.x + 4*(i+1)*dir.x/n),
                    (int)(position.y + 4*(i+1)*dir.y/n));
        }

    }
}
