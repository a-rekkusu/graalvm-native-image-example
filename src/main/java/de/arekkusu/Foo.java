package de.arekkusu;

import java.util.UUID;

public class Foo
{
    private String id;
    public static final Foo instance = new Foo();

    public Foo()
    {
        this.id = UUID.randomUUID().toString();
    }

    public void sayHelloFoo()
    {
        System.out.println("Hello from Foo with id " + id);
    }
}
