package de.arekkusu.proxyGeneration;

import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.proxy.NormalScopeProxyFactory;
import org.apache.webbeans.service.ClassLoaderProxyService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuildTimeProxyGenerator
{
    private List<Class> beansList = new ArrayList<>();
    private WebBeansContext context;
    private NormalScopeProxyFactory pf;
    private ClassLoaderProxyService.Spy spy;

    public BuildTimeProxyGenerator()
    {
        this.context = new WebBeansContext();
        this.context.getOpenWebBeansConfiguration().setProperty("org.apache.webbeans.proxy.useStaticNames", "true");
        this.pf = new NormalScopeProxyFactory(context);
        this.spy = new ClassLoaderProxyService.Spy(context);
    }

    public static void main(String[] args) throws IOException
    {
        BuildTimeProxyGenerator proxyGenerator = new BuildTimeProxyGenerator();

        proxyGenerator.beansList.add(Foo.class);
        proxyGenerator.beansList.add(Foo2.class);
        proxyGenerator.beansList.add(Foo3.class);
        proxyGenerator.beansList.add(Foo4.class);

        for (Class clazz : proxyGenerator.beansList)
        {
            proxyGenerator.pf.createProxyClass(ClassLoader.getSystemClassLoader(), clazz);
        }

        for (Map.Entry<String, byte[]> entry : proxyGenerator.spy.getProxies().entrySet())
        {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            try
            {
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(entry.getValue());
                oos.flush();
                File file = new File("C:\\Users\\Alex\\Desktop\\" + entry.getKey() + ".class");
                OutputStream out = new FileOutputStream(file);
                out.write(entry.getValue());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                bos.close();
            }

            System.out.println("Proxy generated: " + entry.getKey());
        }
    }
}