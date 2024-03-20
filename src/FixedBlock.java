public class FixedBlock extends Block{

    public FixedBlock() {
        super();
        mass = 1;
        locked = true;

    }

    public FixedBlock(Vector2 position) {
        this.position = position;
    }

    public FixedBlock(double x, double y) {
        this.position.x = x;
        this.position.y = y;
    }

    @Override
    public void update(double deltaTime) {

    }



}
