package LineSegmentPath;

import Common.Point;

import java.util.ArrayList;

public class ArcPath {
    ArrayList<LineSegment> lines;
    ArrayList<TangentArc> arcs;
    public ArcPath(Point[] controlPoints, double timeStep, double radius){
        lines = new ArrayList<>();
        arcs = new ArrayList<>();
        for(int idx = 0; idx < controlPoints.length-1; idx++){
            lines.add(new LineSegment(controlPoints[idx], controlPoints[idx+1]));
        }

        for(int idx = 0; idx < controlPoints.length-2; idx++){
            arcs.add(new TangentArc(lines.get(idx), lines.get(idx+1), radius));
        }

    }
}
