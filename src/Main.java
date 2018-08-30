import Common.Point;
import LineSegmentPath.*;

import java.util.ArrayList;
import SplinePath.CubicSpline;

public class Main {
    public static void main(String[] args) {
        double NUM_SAMPLES = 10;
        ArrayList<Point> waypoints = new ArrayList<>();
        for(int x = 0; x < NUM_SAMPLES; x++){
            waypoints.add(new Point(x, Math.pow(x,2)));
        }
        double start = System.nanoTime();
        ArcPath test = new ArcPath(waypoints.toArray(new Point[waypoints.size()]), 1/20, 2);
        double end = System.nanoTime();
        System.out.println((end-start)/1000000);
        start = System.nanoTime();
        CubicSpline test2 = new CubicSpline(waypoints.toArray(new Point[waypoints.size()]));
        end = System.nanoTime();
        System.out.println((end-start)/1000000);
    }
}
