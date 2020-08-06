package de.arekkusu.reflection;

import java.lang.reflect.Method;

public class App
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Hello World!");
        String userName = args.length >= 1 ? args[0] : "Johnny";
        User user = new User(userName, 25);

        Object o = User.class.getMethod("getName").invoke(user);

        try
        {
            String name = (String) o;
            System.out.println("This is my user name: " + name);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Method[] userMethods = User.class.getDeclaredMethods();

        for (Method method : userMethods)
        {
            System.out.println("Reflection used: Available user method: " + method.getName());
        }

        try
        {
            Class clazz1 = Class.forName("resultingProxies/Foo1$$OwbNormalScopeProxy0.class");
            System.out.println("Proxy class found: " + clazz1.getName());
            Class clazz2 = Class.forName("resultingProxies/Foo2$$OwbNormalScopeProxy0.class");
            System.out.println("Proxy class found: " + clazz2.getName());
            Class clazz3 = Class.forName("resultingProxies/Foo3$$OwbNormalScopeProxy0.class");
            System.out.println("Proxy class found: " + clazz3.getName());
            Class clazz4 = Class.forName("resultingProxies/Foo4$$OwbNormalScopeProxy0.class");
            System.out.println("Proxy class found: " + clazz4.getName());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        Thread.sleep(4000);
    }
}
