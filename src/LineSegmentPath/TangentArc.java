package LineSegmentPath;

import Common.Point;

public class TangentArc {
    double theta, radius, h, k;
    Point start, end, center;

    public TangentArc(LineSegment first, LineSegment second, double radius){
        //System.out.println(first);
        //System.out.println(second);
        double theta = Math.PI- Math.acos((first.getUnitVector().getX() * second.getUnitVector().getX()) + (first.getUnitVector().getY() * second.getUnitVector().getY()));
        //System.out.println("Unit Vectors: " + first.getUnitVector() + " " + second.getUnitVector());
        //System.out.println("Angle: " + theta);
        double remainder = radius / Math.tan(theta/2);
        //System.out.println("Remainder: " + remainder);


        this.start = getTangencyPoint(first, first.getEndPoint(), remainder, true);


        this.end = getTangencyPoint(second, second.getStartPoint(), remainder, false);
        this.radius = radius;

        //System.out.println("Starting Point: " + start);
        //System.out.println("Ending Point: " + end);

        //need to find equation for circle center
        //(x-h)^2 + (y-k)^ 2 = r^2
        //equation source: http://mathforum.org/library/drmath/view/53027.html

        LineSegment chord = new LineSegment(start, end);
        Point midpoint = new Point((start.getX() + end.getX())/2, (start.getY() + end.getY())/2 );
        double q = chord.getLength();
        double r = radius;
        double x3 = midpoint.getX();
        double y3 = midpoint.getY();
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();

        Point center1 = new Point((x3 + Math.sqrt(Math.pow(r,2) - Math.pow((q/2), 2)) * ((y1-y2)/q) ),(y3 + Math.sqrt(Math.pow(r,2) - Math.pow((q/2), 2)) * ((x2-x1)/q) ) );
        Point center2 = new Point((x3 - Math.sqrt(Math.pow(r,2) - Math.pow((q/2), 2)) * ((y1-y2)/q) ),(y3 - Math.sqrt(Math.pow(r,2) - Math.pow((q/2), 2)) * ((x2-x1)/q) ) );

        LineSegment radius1 = new LineSegment(center1, start);
        boolean isTangent = Math.abs((first.getUnitVector().getX() * radius1.getUnitVector().getX() + first.getUnitVector().getY() * radius1.getUnitVector().getY())) < 0.01;
        //System.out.println(isTangent);
        //System.out.println("Center 1: " + center1);
        //System.out.println("Center 2: " + center2);
        if(isTangent){
            center = center1;
        }else
            center = center2;

    }

    /*private Point getTangencyPoint(LineSegment l, Point p, double remainder){
        double a = Math.pow(l.getSlope(), 2) + 1;
        double b = 2 * (l.getYInt() * l.getSlope() - p.getX()*l.getSlope() - p.getY());
        double c = Math.pow(l.getYInt(), 2 ) - 2*l.getYInt()*p.getY() + Math.pow(p.getY(),2) + Math.pow(p.getX(), 2) - Math.pow(remainder,2);

        double x1 = (-1 * b + Math.sqrt(Math.pow(b,2 ) - 4 * a * c))/(2*a);
        double x2 = (-1 * b - Math.sqrt(Math.pow(b,2 ) - 4 * a * c))/(2*a);

        System.out.println("X1: " + x1 +" X2: "+ x2);
        if(x1 > l.getStartPoint().getX() && x1 < l.getEndPoint().getX()) {
            System.out.println(l.getNewPoint(x1));
            return l.getNewPoint(x1);
        }
        else {
            System.out.println(l.getNewPoint(x2));
            return l.getNewPoint(x2);
        }
    }*/

    private Point getTangencyPoint(LineSegment l, Point p, double remainder, boolean something){
        if(something) {
            Point point = new Point(-1 * (l.getUnitVector().getX() * remainder), -1 * (l.getUnitVector().getY() * remainder));
            Point tangencyPoint = new Point((p.getX() + point.getX()), (p.getY() + point.getY()));
            return tangencyPoint;
        }
        else{
            Point point = new Point((l.getUnitVector().getX() * remainder), (l.getUnitVector().getY() * remainder));
            Point tangencyPoint = new Point((p.getX() + point.getX()), (p.getY() + point.getY()));
            return tangencyPoint;
        }
    }

    public Point getCenter(){
        return center;
    }

    public double getRadius(){
        return radius;
    }

    public Point getStart(){
        return start;
    }

    public Point getEnd(){
        return end;
    }

}
