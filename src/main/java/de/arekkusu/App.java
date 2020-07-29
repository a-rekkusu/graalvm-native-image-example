package de.arekkusu;

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

        Thread.sleep(4000);
    }
}
