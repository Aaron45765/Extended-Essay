import Common.Point;
import LineSegmentPath.*;
public class Main {
    public static void main(String[] args) {
        LineSegment first = new LineSegment(new Point(0, 0), new Point(2, 6));
        LineSegment second = new LineSegment(new Point(2, 6), new Point(10, 4));

        TangentArc test = new TangentArc(first, second, 2);
        System.out.println(test.getCenter());
    }
}
