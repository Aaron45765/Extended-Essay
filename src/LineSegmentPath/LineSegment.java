package LineSegmentPath;
import Common.Point;

public class LineSegment {
    Point first, second;
    double m, b;
    public LineSegment(Point first, Point second){
        this.first = first;
        this.second = second;
        //y = mx + b
        //where m = slope and b = y-intercept
        //calculate slope (rise)/(run)
        m = (second.getY()-first.getY())/(second.getX()-first.getX());
        //calculate y-intercept
        //y-mx = b, where y and x are any of the two points given (first point is chosen arbitrarily)
        b = first.getY() - (m * first.getX());

    }

    public double getSlope(){
        return m;
    }

    public double getYInt(){
        return b;
    }

    public double getLength(){
        return Math.sqrt(Math.pow((second.getX()-first.getX()), 2) + Math.pow((second.getY()-first.getY()), 2));
    }

    public Point getNewPoint(double x){
        double y = m * x + b;
        return new Point(x, y);
    }

    public Point getUnitVector(){
        return new Point((second.getX()-first.getX())/getLength(), (second.getY()-first.getY())/getLength());
    }

    public Point getStartPoint(){
        return first;
    }

    public Point getEndPoint(){
        return second;
    }

    public String toString(){
        return "y=" + m + "x+" + b;
    }
}
