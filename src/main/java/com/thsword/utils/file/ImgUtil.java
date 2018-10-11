package com.thsword.utils.file;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;

import com.thsword.utils.validator.ValidatorUtil;

/**
 * 图像工具
 *
 * @author 李小勇
 */
public class ImgUtil {

    /**
     * 图片类型
     */
    public static String IMG_TYPE_JPG = "JPG";
    /**
     * 图片类型
     */
    public static String IMG_TYPE_PNG = "PNG";
    /**
     * 图片类型
     */
    public static String IMG_TYPE_GIF = "GIF";
    /**
     * 图片类型
     */
    public static String IMG_TYPE_BMP = "BMP";

    /**
     * 缩放图片
     *
     * @param file文件
     * @param width�?
     * @param height�?
     * @param filePath路径
     * @param fileName名称
     * @param fileType类型
     * @return
     */
    public static boolean zoomImage(File file, int width, int height,
                                    String filePath, String fileName, String fileType) {
        BufferedImage bufferedImage;
        try {
            if (!ValidatorUtil.isNull(file)) {
                bufferedImage = ImageIO.read(file);
                if (!ValidatorUtil.isNull(bufferedImage)) {
                    bufferedImage = zoomInImage(bufferedImage, width, height);
                    // 目录不存在，创建目录
                    FileUtil.newFolder(filePath);
                    try {
                        // 保存修改后的图像,全部保存为JPG格式
                        ImageIO.write(bufferedImage, fileType, new File(
                                filePath + File.separator + fileName + "."
                                        + fileType.toLowerCase()));
                        return true;
                    } catch (IOException e) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 对图片进行放�?
     *
     * @param srcPath原始图片路径  (绝对路径)
     * @param newPath放大后图片路�?（绝对路径）
     * @param times放大倍数
     * @return
     */
    public static boolean zoomInImage(String srcPath, String newPath,
                                      Integer times) {
        BufferedImage bufferedImage = null;
        try {
            File of = new File(srcPath);
            if (of.canRead()) {
                bufferedImage = ImageIO.read(of);
            }
        } catch (IOException e) {
            return false;
        }
        if (bufferedImage != null) {
            bufferedImage = zoomInImage(bufferedImage, times);
            try {
                ImageIO.write(bufferedImage, "JPG", new File(newPath)); // 保存修改后的图像,全部保存为JPG格式
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * 对图片进行缩�?
     *
     * @param srcPath源图片路�?（绝对路径）
     * @param newPath新图片路�?（绝对路径）
     * @param times缩小倍数
     * @return 保存是否成功
     */
    public static boolean zoomOutImage(String srcPath, String newPath,
                                       Integer times) {
        BufferedImage bufferedImage = null;
        try {
            File of = new File(srcPath);
            if (of.canRead()) {
                bufferedImage = ImageIO.read(of);
            }
        } catch (IOException e) {
            return false;
        }
        if (bufferedImage != null) {
            bufferedImage = zoomOutImage(bufferedImage, times);
            try {
                // 保存修改后的图像,全部保存为JPG格式
                ImageIO.write(bufferedImage, "JPG", new File(newPath));
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * 对图片进行指定缩�?
     *
     * @param originalImage原始图片
     * @param width�?
     * @param height�?
     * @return
     */
    public static BufferedImage zoomInImage(BufferedImage originalImage,
                                            int width, int height) {
        // 把类型为0其实是PNG图片。但是实例BufferedImage报错。所以改�?
        int type = originalImage.getType();
        if (0 == type) {
            type = 5;
        }
        BufferedImage newImage = new BufferedImage(width, height, type);
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return newImage;
    }

    /**
     * 对图片进行放�?
     *
     * @param originalImage原始图片
     * @param times放大倍数
     * @return
     */
    public static BufferedImage zoomInImage(BufferedImage originalImage,
                                            Integer times) {
        int width = originalImage.getWidth() * times;
        int height = originalImage.getHeight() * times;
        // 把类型为0其实是PNG图片。但是实例BufferedImage报错。所以改�?
        int type = originalImage.getType();
        if (0 == type) {
            type = 5;
        }
        BufferedImage newImage = new BufferedImage(width, height, type);
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return newImage;
    }

    /**
     * 对图片进行缩�?
     *
     * @param originalImage原始图片
     * @param times缩小倍数
     * @return 缩小后的Image
     */
    public static BufferedImage zoomOutImage(BufferedImage originalImage,
                                             Integer times) {
        int width = originalImage.getWidth() / times;
        int height = originalImage.getHeight() / times;
        // 把类型为0其实是PNG图片。但是实例BufferedImage报错。所以改�?
        int type = originalImage.getType();
        if (0 == type) {
            type = 5;
        }
        BufferedImage newImage = new BufferedImage(width, height, type);
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return newImage;
    }

    /**
     * 是否为图�?
     *
     * @param file
     * @return
     */
    public static boolean isImage(File file) {
        ByteArrayInputStream bais = null;
        MemoryCacheImageInputStream mcis;
        try {
            bais = new ByteArrayInputStream(
                    org.apache.commons.io.FileUtils.readFileToByteArray(file));
            mcis = new MemoryCacheImageInputStream(bais);
            Iterator<ImageReader> itr = ImageIO.getImageReaders(mcis);
            while (itr.hasNext()) {
                ImageReader reader = itr.next();
                String imageName = reader.getClass().getSimpleName();
                if (!ValidatorUtil.isNull(imageName)
                        && ("GIFImageReader".equals(imageName)
                        || "JPEGImageReader".equals(imageName)
                        || "PNGImageReader".equals(imageName) || "BMPImageReader"
                        .equals(imageName))) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(bais);
        }
        return false;
    }
}
