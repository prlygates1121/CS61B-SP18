public class NBody {
    private static final double G = 6.67e-11;

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int numPlanets = in.readInt();
        Planet[] ps = new Planet[numPlanets];
        in.readDouble();
        for (int i = 0; i < numPlanets; i++) {
            ps[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return ps;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dT = Double.parseDouble(args[1]);
        String filename = args[2];
        double universeRadius = readRadius(filename);
        Planet[] ps = readPlanets(filename);
        int numPlanets = ps.length;
        StdDraw.setScale(-universeRadius, universeRadius);
        StdDraw.clear();


        StdDraw.enableDoubleBuffering();
        for (double time = 0; time < T; time += dT) {
            double[] xForces = new double[numPlanets];
            double[] yForces = new double[numPlanets];
            for (int i = 0; i < numPlanets; i++) {
                xForces[i] = ps[i].calcNetForceExertedByX(ps);
                yForces[i] = ps[i].calcNetForceExertedByY(ps);
            }
            // only call update() after all xForces and yForces have been calculated
            for (int i = 0; i < numPlanets; i++) {
                ps[i].update(dT, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : ps) p.draw();
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", ps.length);
        StdOut.printf("%.2e\n", universeRadius);
        for (Planet p : ps) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p.xxPos, p.yyPos, p.xxVel,
                    p.yyVel, p.mass, p.imgFileName);
        }
    }

}
