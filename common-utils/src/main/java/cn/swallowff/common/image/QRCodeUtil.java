package cn.swallowff.common.image;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;
import java.util.Random;

/**
 * @author shenyu
 * @create 2019/5/9
 */
public class QRCodeUtil {
    /**
     * 指定编码
     */
    private static final String CHARSET = "UTF-8";

    /**
     * 指定二维码类型
     */
    private static final String FORMAT = "PNG";

    /**
     * 指定二维码尺寸
     */
    private static final int QRCODE_SIZE = 300;

    /**
     * 指定LOGO宽度
     */
    private static final int LOGO_WIDTH = 60;

    /**
     * 指定LOGO高度
     */
    private static final int LOGO_HEIGHT = 60;

    /**
     *
     * @方法名：encode
     * @方法描述【方法功能描述】生成二维码(可指定内嵌LOGO)可指定二维码文件名
     * @param content 内容
     * @param logoPath LOGO地址，为空则不指定logo
     * @param destPath 存放目录
     * @param fileName 二维码文件名,为空则二维码文件名随机，文件名可能会有重复
     * @param needCompress 是否压缩LOGO
     * @return 二维码生成路径
     * @throws Exception
     */
    public static String encode(String content, String logoPath, String destPath, String fileName, boolean needCompress)
            throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, logoPath, needCompress);
        mkdirs(destPath);
        if (fileName != null && !"".equals(fileName)) {
            fileName = fileName.substring(0, fileName.indexOf(".") > 0 ? fileName.indexOf(".") : fileName.length())
                    + "." + FORMAT.toLowerCase();
        }
        else {
            fileName = new Random().nextInt(99999999) + "." + FORMAT.toLowerCase();
        }
        ImageIO.write(image, FORMAT, new File(destPath + "/" + fileName));
        return fileName;
    }

    /**
     *
     * @方法名：decode
     * @方法描述【方法功能描述】 解析二维码
     * @param path 二维码路径
     * @return 二维码内容
     */
    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }

    /**
     *
     * @方法名：decode
     * @方法描述【方法功能描述】 解析二维码
     * @param file 二维码图片
     * @return 二维码内容
     * @throws Exception
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable <DecodeHintType, Object > hints = new Hashtable <DecodeHintType, Object >();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     *
     * @方法名：createImage
     * @方法描述【方法功能描述】 生成二维码
     * @param content 二维码内容
     * @param logoPath LOGO地址，为空则不指定logo
     * @param needCompress 是否需要压缩
     * @return 图片
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String logoPath, boolean needCompress) throws Exception {
        Hashtable<EncodeHintType, Object > hints = new Hashtable <EncodeHintType, Object >();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (logoPath == null || "".equals(logoPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtil.insertImage(image, logoPath, needCompress);
        return image;
    }

    /**
     *
     * @方法名：insertImage
     * @方法描述【方法功能描述】 插入LOGO
     * @param source 二维码图片
     * @param logoPath LOGO图片地址
     * @param needCompress 是否压缩
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, String logoPath, boolean needCompress) throws Exception {
        File file = new File(logoPath);
        if (!file.exists()) {
            throw new Exception("logo file not found.");
        }
        Image src = ImageIO.read(new File(logoPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > LOGO_WIDTH) {
                width = LOGO_WIDTH;
            }
            if (height > LOGO_HEIGHT) {
                height = LOGO_HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     *
     * @方法名：mkdirs
     * @方法描述【方法功能描述】 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir． (mkdir如果父目录不存在则会抛出异常)
     * @param destPath 存放目录
     */
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


}
