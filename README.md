# GraalVM native-image example

This repository is one of the results of a [GSOC 2020 (Google Summer of Code) project]((https://gist.github.com/a-rekkusu/b98ecd201d25102ca3e118a2fa38fbb4)) for [OpenWebBeans](https://openwebbeans.apache.org/) at ASF.
This code served as a sample project to use CDI and proxy classes in a GraalVM native-image.

The proxy classes must already be created and available in the classpath at runtime. This can be achieved by using OWB's [ClassLoaderProxyService.Spy](https://github.com/apache/openwebbeans/blob/master/webbeans-impl/src/main/java/org/apache/webbeans/service/ClassLoaderProxyService.java#L62) to generate at build-time (like [so](https://github.com/a-rekkusu/graalvm-native-image-example/blob/master/src/main/java/de/arekkusu/proxyGeneration/BuildTimeProxyGenerator.java#L31)), and [ClassLoaderProxyService.LoadFirst](https://github.com/apache/openwebbeans/blob/master/webbeans-impl/src/main/java/org/apache/webbeans/service/ClassLoaderProxyService.java#L85) at runtime like [so](https://github.com/a-rekkusu/graalvm-native-image-example/blob/master/src/main/java/de/arekkusu/runtime/App.java#L17).