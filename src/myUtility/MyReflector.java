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
                in.close();
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

    public static Object invokeMethdod(Object obj, String methodName, Object... args) {
        Class<?> cls = obj.getClass();
        try {
            Method method = cls.getMethod(methodName);
            method.setAccessible(true);
            return method.invoke(obj, args);
        } catch (Exception e) {
            System.out.println("Failed to invoke method: " + e.getMessage());
        }
        return null;
    }

    //gemini's version
    public static Object invokeMethod(Object obj, String methodName, Object... args) {
        // Handle null check if calling a static method by passing a Class object instead
        Class<?> cls = (obj instanceof Class) ? (Class<?>) obj : obj.getClass();
        
        try {
            // 1. Get parameter types from the args array
            Class<?>[] parameterTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                parameterTypes[i] = args[i].getClass();
                // Note: This may need adjustment for primitive types (int.class vs Integer.class)
            }

            // 2. Find the method with the correct signature
            Method method = cls.getDeclaredMethod(methodName, parameterTypes);
            
            method.setAccessible(true);
            
            // 3. If it's a static method, 'obj' can be null or the Class itself
            Object target = (obj instanceof Class) ? null : obj;
            
            return method.invoke(target, args);
            
        } catch (Exception e) {
            e.printStackTrace(); // getMessage() is often null for reflection errors
        }
        return null;
    }
    
    public static Object callMethod(Object obj, String methodName, Object... args) {
        try {
            // Визначаємо типи параметрів на основі переданих об'єктів
            Class<?>[] parameterTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                parameterTypes[i] = args[i].getClass();
            }

            // Шукаємо метод у класі об'єкта
            Method method = obj.getClass().getMethod(methodName, parameterTypes);

            // Викликаємо метод та повертаємо результат
            return method.invoke(obj, args);

        } catch (NoSuchMethodException e) {
            System.out.println("error: " + e.getMessage());
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("error: " + e.getMessage());
        }

        return null;
    }

    private MyReflector() { }

}
