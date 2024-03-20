public class Vector2 {

    double x, y;


    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void add(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void scale(double factor) {
        this.x *= factor;
        this.y *= factor;
    }

    public void normalize() {
        scale(1.0/this.magnitude());
    }

    public Vector2 normalized() {
        Vector2 v = new Vector2(this);
        v.normalize();
        return v;
    }

    public Vector2 copy() {
        return new Vector2(this);
    }

    public Vector2 getOrthogonal() {
        Vector2 v = copy();
        v.x = -this.y;
        v.y = this.x;

        return v.normalized();

    }

    public double magnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public Vector2 getScaled(double factor) {
        return new Vector2(this.x*factor, this.y*factor);
    }

    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    public static Vector2 getDirectionVector(Vector2 from, Vector2 to) {
        return Vector2.add(to, from.getScaled(-1));
    }

    public static double dot(Vector2 a, Vector2 b) {
        return a.x*b.x + a.y*b.y;
    }

    public static boolean equals(Vector2 a, Vector2 b) {
        return a.x == b.x && a.y == b.y;
    }

    public static Vector2 zero() {
        return new Vector2(0,0);
    }
}
