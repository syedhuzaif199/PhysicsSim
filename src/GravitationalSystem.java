import java.util.ArrayList;
import java.util.Arrays;

public class GravitationalSystem extends PhysicsOBJ{

    ArrayList<PhysicsOBJ> objects;
    float G;

    public GravitationalSystem(PhysicsOBJ obj1, PhysicsOBJ obj2) {
        this.objects = new ArrayList<>();
        this.objects.add(obj1);
        this.objects.add(obj2);
        G = 1000;
    }

    public GravitationalSystem(PhysicsOBJ... objs) {
        this.objects = new ArrayList<>();
        for(PhysicsOBJ obj : objs)
            this.objects.add(obj);
        G = 1000;
    }

    @Override
    public void update(double deltaTime) {
        for(int i = 0; i < objects.size() - 1; i++) {
            for(int j = i + 1; j < objects.size(); j++) {
                PhysicsOBJ obj1 = objects.get(i), obj2 = objects.get(j);
                Vector2 dir = Vector2.getDirectionVector(obj2.position, obj1.position);
                Vector2 force = dir.normalized().getScaled(G*obj2.mass*obj1.mass/(dir.magnitude()*dir.magnitude()));
                obj1.applyForce(force.getScaled(-1));
                obj2.applyForce(force);

            }
        }
    }

    public void add(PhysicsOBJ obj) {
        this.objects.add(obj);
    }



    @Override
    public boolean contains(double x, double y) {
        return false;
    }


}
