package de.arekkusu;

public class Bar
{
    private ProxyGenerator proxyGenerator = new ProxyGenerator();
    private Foo foo = proxyGenerator.dynamicFoo;

    public Bar() throws InstantiationException, IllegalAccessException
    {
    }

    public void test()
    {
        foo.sayHelloFoo();
    }
}