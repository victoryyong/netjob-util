package com.thsword.utils.object;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


/**
 * User: 李小勇
 * Date: 13-11-8
 * Time: 下午2:15
 */
public class ClassLoaderUtil {

    /**
     * �?加载Java类�? 使用全限定类�?
     * �?@paramclassName
     * �?@return
     */
    @SuppressWarnings("rawtypes")
	public static Class loadClass(String className) {
        try {
            return getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not found '" + className + "'", e);
        }
    }

    /**
     * �?得到类加载器
     * �?@return
     */
    public static ClassLoader getClassLoader() {
        return ClassLoaderUtil.class.getClassLoader();
    }

    /**
     * �?提供相对于classpath的资源路径，返回文件的输入流
     * �?@paramrelativePath必须传�?资源的相对路径�?是相对于classpath的路径�?如果�?��查找classpath外部的资源，�?��使用�?./来查�?
     * �?@return 文件输入�?
     * �?@throwsIOException
     * �?@throwsMalformedURLException
     */
    public static InputStream getStream(String relativePath) throws IOException {
        if (!relativePath.contains("../")) {
            return getClassLoader().getResourceAsStream(relativePath);
        } else {
            return ClassLoaderUtil.getStreamByExtendResource(relativePath);
        }
    }

    /**
     * �?
     * �?@paramurl
     * �?@return
     * �?@throwsIOException
     */
    public static InputStream getStream(URL url) throws IOException {
        if (url != null) {
            return url.openStream();
        } else {
            return null;
        }
    }


    /**
     * �?
     * �?@paramrelativePath必须传�?资源的相对路径�?是相对于classpath的路径�?如果�?��查找classpath外部的资源，�?��使用�?./来查�?
     * �?@return
     * �?@throwsMalformedURLException
     * �?@throwsIOException
     */
    public static InputStream getStreamByExtendResource(String relativePath) throws IOException {
        return ClassLoaderUtil.getStream(ClassLoaderUtil.getExtendResource(relativePath));
    }

    /**
     * �?提供相对于classpath的资源路径，返回属�?对象，它是一个散列表
     * �?@paramresource
     * �?@return
     */
    public static Properties getProperties(String resource) {
        Properties properties = new Properties();
        try {
            properties.load(getStream(resource));
        } catch (IOException e) {
            throw new RuntimeException("couldn't load properties file '" + resource + "'", e);
        }
        return properties;
    }

    /**
     * �?得到本Class�?��的ClassLoader的Classpat的绝对路径�?
     * �?URL形式�?
     * �?@return
     */
    public static String getAbsolutePathOfClassLoaderClassPath() {
        return ClassLoaderUtil.getClassLoader().getResource("").toString();
    }

    /**
     * @paramrelativePath 必须传�?资源的相对路径�?是相对于classpath的路径�?如果�?��查找classpath外部的资源，�?��使�?�?./来查�?
     * @return资源的绝对URL
     * @throwsMalformedURLException
     */
    public static URL getExtendResource(String relativePath) throws MalformedURLException {
//        ClassLoaderUtil.log.info("传入的相对路径：" + relativePath);
        //ClassLoaderUtil.log.info(Integer.valueOf(relativePath.indexOf("../"))) ;
        if (!relativePath.contains("../")) {
            return ClassLoaderUtil.getResource(relativePath);
        }
        String classPathAbsolutePath = ClassLoaderUtil.getAbsolutePathOfClassLoaderClassPath();
        if (relativePath.substring(0, 1).equals("/")) {
            relativePath = relativePath.substring(1);
        }
//        ClassLoaderUtil.log.info(Integer.valueOf(relativePath.lastIndexOf("../")));
        String wildcardString = relativePath.substring(0, relativePath.lastIndexOf("../") + 3);
        relativePath = relativePath.substring(relativePath.lastIndexOf("../") + 3);
        int containSum = ClassLoaderUtil.containSum(wildcardString, "../");
        classPathAbsolutePath = ClassLoaderUtil.cutLastString(classPathAbsolutePath, "/", containSum);
        String resourceAbsolutePath = classPathAbsolutePath + relativePath;
//        ClassLoaderUtil.log.info("绝对路径�? + resourceAbsolutePath);
        URL resourceAbsoluteURL = new URL(resourceAbsolutePath);
        return resourceAbsoluteURL;
    }

    /**
     * �?
     * �?@paramsource
     * �?@paramdest
     * �?@return
     */
    private static int containSum(String source, String dest) {
        int containSum = 0;
        int destLength = dest.length();
        while (source.contains(dest)) {
            containSum = containSum + 1;
            source = source.substring(destLength);
        }
        return containSum;
    }

    /**
     * �?
     * �?@paramsource
     * �?@paramdest
     * �?@paramnum
     * �?@return
     */
    private static String cutLastString(String source, String dest, int num) {
        // String cutSource=null;
        for (int i = 0; i < num; i++) {
            source = source.substring(0, source.lastIndexOf(dest, source.length() - 2) + 1);
        }
        return source;
    }

    /**
     * �?
     * �?@paramresource
     * �?@return
     */
    public static URL getResource(String resource) {
//        ClassLoaderUtil.log.info("传入的相对于classpath的路径：" + resource);
        return ClassLoaderUtil.getClassLoader().getResource(resource);
    }

    /**
     * �?@paramargs
     * �?@throwsMalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException {
        //ClassLoaderUtil.getExtendResource("../spring/dao.xml");
        //ClassLoaderUtil.getExtendResource("../../../src/log4j.properties");
        ClassLoaderUtil.getExtendResource("log4j.properties");
        System.out.println(ClassLoaderUtil.getClassLoader().getResource("log4j.properties").toString());
    }
}
