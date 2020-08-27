package de.arekkusu.runtime;

import de.arekkusu.proxyGeneration.Foo;
import org.apache.webbeans.service.ClassLoaderProxyService;
import org.apache.webbeans.spi.DefiningClassService;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.lang.reflect.Method;

public class App
{

    public static void main(String[] args) throws Exception
    {
        try (final SeContainer container = SeContainerInitializer.newInstance()
                .addProperty(DefiningClassService.class.getName(), ClassLoaderProxyService.LoadFirst.class.getName())
                .addProperty("org.apache.webbeans.proxy.useStaticNames", "true")
                .initialize())
        {
            Foo foo = container.select(Foo.class).get();
            System.err.println(foo);
        }

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
            Class classToProxy = Class.forName("de.arekkusu.proxyGeneration.Foo");
            System.out.println("Class to proxy found: " + classToProxy.getName());

            Class proxy = Class.forName("de.arekkusu.proxyGeneration.Foo$$OwbNormalScopeProxy0");
            System.out.println("Proxy class found: " + proxy.getName());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        Thread.sleep(4000);    }
}
