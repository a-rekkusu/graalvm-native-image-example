package de.arekkusu.proxyGeneration;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class Foo1
{
    private String id;
    public static final Foo1 instance = new Foo1();

    public Foo1()
    {
        this.id = UUID.randomUUID().toString();
    }

    public void sayHelloFoo()
    {
        System.out.println("Hello from Foo with id " + id + "and class name: " + instance.getClass().getName());
    }
}
