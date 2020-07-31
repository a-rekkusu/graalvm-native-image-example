package de.arekkusu.proxyGeneration;

import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.proxy.NormalScopeProxyFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BuildTimeProxyGenerator
{
    private List<Class> beansList = new ArrayList<>();
    private NormalScopeProxyFactory pf = new NormalScopeProxyFactory(new WebBeansContext());
    private List<Class> proxyList = new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        BuildTimeProxyGenerator proxyGenerator = new BuildTimeProxyGenerator();
        proxyGenerator.beansList.add(Foo.class);
        proxyGenerator.beansList.add(Foo2.class);
        proxyGenerator.beansList.add(Foo3.class);
        proxyGenerator.beansList.add(Foo4.class);

        for (Class clazz : proxyGenerator.beansList)
        {
            //NamingStrategy mitgeben - static names
            Class proxy = proxyGenerator.pf.createProxyClass(ClassLoader.getSystemClassLoader(), clazz);
            proxyGenerator.proxyList.add(proxy);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            try
            {
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(proxy);
                oos.flush();
                byte[] data = bos.toByteArray();
                File file = new File("C:\\Users\\Alex\\Desktop\\" + proxy.getSimpleName() + ".class");
                OutputStream out = new FileOutputStream(file);
                out.write(data);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                bos.close();
            }

            System.out.println("Proxy generated: " + proxy.getSimpleName());
        }
    }
}