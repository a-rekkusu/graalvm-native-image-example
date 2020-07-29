package de.arekkusu;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.pool.TypePool;

import java.io.File;

import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;

public class ProxyGenerator
{
    public ProxyGenerator()
    {
    }

    public static void main(String[] args)
    {
        try
        {
            TypePool typePool = TypePool.Default.of(Thread.currentThread().getContextClassLoader());
            File file = new File("C:\\Users\\USERNAME\\Desktop");

            new ByteBuddy()
                    .subclass(Foo.class)
                    .name("example.FooProxy")
                    .method(isDeclaredBy(Foo.class)).intercept(MethodDelegation.to(Foo.instance))
                    .make()
                    .saveIn(file);

            new ByteBuddy()
                    .redefine(typePool.describe("FooProxy").resolve(),
                            //ClassFileLocator.ForFolder(file) ... ?
                            ClassFileLocator.ForClassLoader.of(Thread.currentThread().getContextClassLoader()))
                    .make()
                    .load(ClassLoader.getSystemClassLoader());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}