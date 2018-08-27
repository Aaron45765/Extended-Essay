import Common.Point;
import LineSegmentPath.*;
public class Main {
    public static void main(String[] args) {
        LineSegment first = new LineSegment(new Point(0, 1), new Point(1, 4));
        LineSegment second = new LineSegment(new Point(1, 4), new Point(4, 4));

        TangentArc test = new TangentArc(first, second, 1);

        System.out.println(test.getCenter());
    }
}
