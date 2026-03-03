package myUtility;
import java.lang.reflect.*;

public class MyReflector {

    public static String getClassInfo(String className) {
        Class <?> cls;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }

        return getClassInfo(cls);
    }

    public static String getClassInfo(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append("Class Name: " + clazz.getSimpleName() + "\n");
        sb.append("Package: " + clazz.getPackageName() + "\n");
        sb.append("Modifiers: " + Modifier.toString(clazz.getModifiers()) + "\n");
        sb.append("Superclass: " + clazz.getSuperclass().getName() + "\n");
        sb.append("Constructors:\n");
        for (Constructor<?> iface : clazz.getConstructors()) {
            sb.append("  - " + iface.toString() + "\n");
        }
        sb.append("Fields:\n");
        for (Field iface : clazz.getFields()) {
            sb.append("  - " + iface.toString() + "\n");
        }
        sb.append("Interfaces:\n");
        for (Class<?> iface : clazz.getInterfaces()) {
            sb.append("  - " + iface.toString() + "\n");
        }
        sb.append("Methods:\n");
        for (Method iface : clazz.getMethods()) {
            sb.append("  - " + iface.toString() + "\n");
        }
    
        return sb.toString();
    }
    
    private MyReflector() { }

}
