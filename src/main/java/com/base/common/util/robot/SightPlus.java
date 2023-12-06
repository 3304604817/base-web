package com.base.common.util.robot;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SightPlus {

    /**
     * BufferedImage 转黑白
     * @return BufferedImage
     */
    public BufferedImage binaryImage(BufferedImage image){
        BufferedImage binaryImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = binaryImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(binaryImage.getRGB(i, j));
                if (color.getRed() < 120) {
                    binaryImage.setRGB(i, j, Color.BLACK.getRGB());
                } else {
                    binaryImage.setRGB(i, j, Color.WHITE.getRGB());
                }
            }
        }
        return binaryImage;
    }
}
