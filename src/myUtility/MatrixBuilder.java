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
        if (rows <= 0 || columns <= 0 || arr == null) return null;

        // Отримуємо базовий тип (наприклад, із int[][] отримуємо int)
        Class<?> componentType = arr.getClass().getComponentType().getComponentType();
        
        // Створюємо нову матрицю правильно
        Object newArr = Array.newInstance(componentType, rows, columns);

        int currRows = Array.getLength(arr);
        int rowsCopy = Math.min(currRows, rows);

        for (int i = 0; i < rowsCopy; i++) {
            Object oldRow = Array.get(arr, i);
            Object newRow = Array.get(newArr, i); // Рядок уже створений Array.newInstance
            
            if (oldRow != null) {
                int currCols = Array.getLength(oldRow);
                int colsCopy = Math.min(currCols, columns);
                // Копіюємо дані в НОВИЙ рядок
                System.arraycopy(oldRow, 0, newRow, 0, colsCopy);
            }
        }

        return newArr;
    }

    public static String getString(Object arr) {

        try {
            Class <?> componentType = arr.getClass().getComponentType();
            if ( componentType == null ) {
                return arr.toString();
            }
        } catch (NullPointerException e) {
            return "null";
        }

        StringBuilder sb = new StringBuilder("[");

        int size = Array.getLength(arr);
        for ( int i = 0 ; i < size ; i++ ) {
            sb.append(getString(Array.get(arr, i)));
            if ( i+1 < size ) {
                sb.append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }

}
