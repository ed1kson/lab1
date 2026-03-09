package myUtility;
import java.lang.reflect.*;

public class MatrixBuilder {
    public static <T> T[] createArray(Class <T> cls, int size) {
        if ( size <= 0 ) return null;

        @SuppressWarnings("unchecked")
        T[] result = (T[]) Array.newInstance(cls, size);
        return result;
    } 

    public static <T> T[][] createMatrix(Class <T> cls, int rows, int columns) {
        if ( rows <= 0 || columns <= 0 ) return null;

        @SuppressWarnings("unchecked")
        T[][] result = (T[][]) Array.newInstance(cls, rows, columns);
        return result;
    }

    public static Object create(Class<?> cls, int size) {
        if (size < 0) return null;

        Object result = Array.newInstance(cls, size);
        return result;
    }

    public static Object create(Class<?> cls, int rows, int columns) {
        if ( rows <= 0 || columns <= 0 ) return null;
        if ( rows == 1 || columns == 1 ) {
            return Array.newInstance(cls, rows>columns?rows:columns);
        }

        Object result = Array.newInstance(cls, rows, columns);
        return result;
    }

    public static Object setSize(Object arr, int newSize) {
        if (newSize == 0) return null;

        int currSize = Array.getLength(arr);

        Object newArr = create(arr.getClass().getComponentType(), newSize);

        if ( currSize > newSize ) {
            System.arraycopy(arr, 0, newArr, 0, newSize);
        } else {
            System.arraycopy(arr, 0, newArr, 0, currSize);
        }

        return newArr;
    }

    public static Object setSize(Object arr, int rows, int columns) {
        if ( rows <= 0 || columns <= 0 ) return null;

        int currRows = Array.getLength(arr);
        int currColumns = Array.getLength(Array.get(arr, 0));
        Object newArr = create(arr.getClass().arrayType(), rows, columns);

        int rowsCopy = Math.min(currRows, rows);
        int columnsCopy = Math.min(currColumns, columns);
        System.arraycopy(arr, 0, newArr, 0, rowsCopy);

        for ( int i = 0 ; i<rowsCopy ; i++ ) {
            Object oldRow = Array.get(arr, i);
            Object newRow = Array.get(newArr, i);
            System.arraycopy(oldRow, 0, newRow, 0, columnsCopy);
        }

        return newArr;
    }
}
