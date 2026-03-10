package myUtility;
import java.lang.reflect.*;
import java.util.Arrays;

public class MyHandler implements InvocationHandler {

    private final Object target;

    public MyHandler(Object tgt) {
        target = tgt;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("name: " + method.getName());
        System.out.println("args: " + args == null ? "absent" :Arrays.toString(args));

        long startTime = System.nanoTime();

        Object result = method.invoke(target, args);

        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime)/1000000.0;
        
        System.out.println("result: " + result);
        System.out.println("runtime(ms): " + durationMs);

        return result;
    }
    
}
