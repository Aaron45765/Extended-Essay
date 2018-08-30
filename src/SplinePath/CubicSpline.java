package SplinePath;

import Common.Point;
import org.ejml.*;
import org.ejml.data.*;
import org.ejml.simple.SimpleMatrix;
import org.ejml.sparse.csc.CommonOps_DSCC;
import org.ejml.simple.ops.SimpleOperations_DDRM;

public class CubicSpline {
    Matrix tridiagonal, dXMat, dYMat, xMat, yMat, inverse;
    private int m, n; // row x column
    public CubicSpline(Point[] trajectory){
        m = trajectory.length;
        n = m;
        tridiagonal = new DMatrixSparseCSC(m,n);
        ((DMatrixSparseCSC) tridiagonal).set(0, 0, 2);
        ((DMatrixSparseCSC) tridiagonal).set(0, 1, 1);

        ((DMatrixSparseCSC) tridiagonal).set(m-1, n-1, 2);
        ((DMatrixSparseCSC) tridiagonal).set(m-1, n-2, 1);

        for(int i = 1; i <= m-2; i++ ){
            int j = i-1;
            ((DMatrixSparseCSC) tridiagonal).set(i, j, 1);
            ((DMatrixSparseCSC) tridiagonal).set(i, j+1, 4);
            ((DMatrixSparseCSC) tridiagonal).set(i, j+2, 1);
        }

        xMat = new DMatrixRMaj(m, 1);
        yMat = new DMatrixRMaj(m, 1);
        for(int i = 0; i < m; i++){
            ((DMatrixRMaj) xMat).set(i, 0, trajectory[i].getX());
            ((DMatrixRMaj) yMat).set(i, 0, trajectory[i].getY());
        }

        dXMat = new DMatrixRMaj(m, 1);
        dYMat = new DMatrixRMaj(m, 1);
        inverse = new DMatrixRMaj(m,n);
        CommonOps_DSCC.invert((DMatrixSparseCSC) tridiagonal, (DMatrixRMaj) inverse);


        SimpleOperations_DDRM operations = new SimpleOperations_DDRM();
        operations.mult( (DMatrixRMaj)inverse,(DMatrixRMaj) xMat, (DMatrixRMaj)dXMat);
        operations.mult((DMatrixRMaj)inverse, (DMatrixRMaj) yMat, (DMatrixRMaj)dYMat);
        DMatrixRMaj temp = new DMatrixRMaj(inverse.getNumRows(), inverse.getNumCols());
        operations.scale((DMatrixRMaj)inverse, 1/operations.determinant((DMatrixRMaj)inverse), temp);
        temp.print();
    }
}
