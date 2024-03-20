public class RungeKuttaSolver {

    public static double solve(double x0, double y0, double h, int n, DiffFunction func) {
        return rk(x0, y0, h, n, func);
    }

    private static double rk(double x0, double y0, double h, int n, DiffFunction func)  {
        if (n==0)
            return y0;

        double yn_1 = rk(x0, y0, h,n-1, func);
        double xn_1 = x0 + (n-1)*h;
        double k1 = func.call(xn_1, yn_1);
        double k2 = func.call(xn_1 + h/2, yn_1 + h*k1/2);
        double k3 = func.call(xn_1 + h/2, yn_1 + h*k2/2);
        double k4 = func.call(xn_1 + h, yn_1 + h*k3);
        double yn = yn_1 + (h/6)*(k1 + 2*k2 + 2*k3 + k4);
        return yn;
    }

    //Example use of RungeKuttaSolver
    double sol = RungeKuttaSolver.solve(0, 1, 0.005, 1000, new DiffFunction() {
        @Override
        //Derivative dy/dx = -2y
        public double call(double x, double y) {
            return -2*y;
        }
    });




}
