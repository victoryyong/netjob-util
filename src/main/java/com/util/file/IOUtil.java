package com.util.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * 流操�?
 *
 * @author 李小勇
 */
public class IOUtil {

    /**
     * 关闭
     *
     * @param input
     */
    public static void closeQuietly(Reader input) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 关闭
     *
     * @param output
     */
    public static void closeQuietly(Writer output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 关闭
     *
     * @param input
     */
    public static void closeQuietly(InputStream input) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 关闭
     *
     * @param output
     */
    public static void closeQuietly(OutputStream output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static String toString(InputStream in, String encoding) {
        BufferedReader reader = null;
        StringBuilder sb = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in, encoding));
            sb = new StringBuilder();
            int c;
            c = reader.read();
            while (c != -1) {
                sb.append((char) c);
                c = reader.read();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtil.closeQuietly(reader);
        }
        return sb.toString();
    }

}
