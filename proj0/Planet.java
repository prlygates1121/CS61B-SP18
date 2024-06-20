public class Planet {
    public static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        if (p != null) {
            xxPos = p.xxPos;
            yyPos = p.yyPos;
            xxVel = p.xxVel;
            yyVel = p.yyVel;
            mass = p.mass;
            imgFileName = p.imgFileName;
        }
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((p.xxPos - xxPos) * (p.xxPos - xxPos) + (p.yyPos - yyPos) * (p.yyPos - yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        return G * p.mass * mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
        double distance = calcDistance(p);
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / distance;
    }
    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        double netForceX = 0;
        for (Planet p : ps) {
            if (!p.equals(this)) {
                netForceX += calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double netForceY = 0;
        for (Planet p : ps) {
            if (!p.equals(this)) {
                netForceY += calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fx, double fy) {
        xxVel += fx / mass * dt;
        yyVel += fy / mass * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        String filePath = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, filePath);
    }

}
