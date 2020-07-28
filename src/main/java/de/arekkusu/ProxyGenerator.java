package de.arekkusu;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;

import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;

public class ProxyGenerator
{
    public ProxyGenerator() throws IllegalAccessException, InstantiationException
    {

    }

    public Foo dynamicFoo = new ByteBuddy()
            .subclass(Foo.class)
            .method(isDeclaredBy(Foo.class)).intercept(MethodDelegation.to(Foo.instance))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded()
            .newInstance();

}
