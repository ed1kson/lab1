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
        for (Constructor<?> constructor : clazz.getConstructors()) {
            sb.append("  - " + constructor.toString() + "\n");
        }
        sb.append("Fields:\n");
        for (Field field : clazz.getFields()) {
            sb.append("  - " + field.toString() + "\n");
        }
        sb.append("Interfaces:\n");
        for (Class<?> iface : clazz.getInterfaces()) {
            sb.append("  - " + iface.toString() + "\n");
        }
        sb.append("Methods:\n");
        for (Method method : clazz.getMethods()) {
            sb.append("  - " + method.toString() + "\n");
        }
    
        return sb.toString();
    }
    
    public static String getObjectState(Object obj) {
        Class<?> cls = obj.getClass();
        StringBuilder sb = new StringBuilder();

        sb.append("fields: \n");
        for ( Field field : cls.getDeclaredFields() ) {
            field.setAccessible(true);
            if ( field.getModifiers() == Modifier.STATIC ) {
                try {
                    sb.append("  - (static field)" + field.getName() + " = " + field.get(null) + "\n");
                } catch (IllegalAccessException e) {
                    sb.append("  - " + field.getName() + " = " + "ACCESS DENIED\n");
                }
            } else {
                try {
                    sb.append("  - " + field.getName() + " = " + field.get(obj) + "\n");
                } catch (IllegalAccessException e) {
                    sb.append("  - " + field.getName() + " = " + "ACCESS DENIED\n");
                }
            }
        }

        sb.append("methods: \n");
        int i = 1;
        for ( Method method : cls.getDeclaredMethods() ) {
            // method.setAccessible(true);
            sb.append("  " + i + ". " + method.getName() + "\n");
            i++;
        }

        //task 2 stuff
        System.out.println(sb.toString());

        java.util.Scanner in = new java.util.Scanner(System.in);
        int numMethods = cls.getDeclaredMethods().length;
        System.out.printf("pick a method to invoke(%d-%d): \n", 1, numMethods);
            int methodNum = in.nextInt();
            in.nextLine(); // consume the newline
    
            if ( methodNum < 1 || methodNum > cls.getDeclaredMethods().length ) {
                sb.append("invalid method number");
                return sb.toString();
            }
    
            Method methodToInvoke = cls.getDeclaredMethods()[methodNum - 1];
            methodToInvoke.setAccessible(true);
            try {
                Object result = methodToInvoke.invoke(obj);
                sb.append("result of invoking " + methodToInvoke.getName() + ": " + result + "\n");
            } catch (Exception e) {
                sb.append("failed to invoke method: " + e.getMessage() + "\n");
            }
        in.close();
        // end of task 2 stuff

        return sb.toString();
    }
    
    private MyReflector() { }

}
