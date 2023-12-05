package com.base.common.util.robot;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 屏幕工具类
 * @author yang.gao
 */
public class ScreenUtil {

    /**
     * 所有显示器信息
     */
    private List<Rectangle> rectangles = getDisplays();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static ScreenUtil getInstance(){
        return new ScreenUtil();
    }

    /**
     * 获取所有显示器
     * @return
     */
    public List<Rectangle> getDisplays() {
        return Arrays.stream(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices())
                .map(GraphicsDevice::getDefaultConfiguration)
                // For scaled sizes use .map(GraphicsConfiguration::getBounds) instead of:
                .map(c -> {
                    DisplayMode dm = c.getDevice().getDisplayMode();
                    Rectangle bounds = c.getBounds();
                    return new Rectangle((int)bounds.getX(), (int)bounds.getY(), dm.getWidth(), dm.getHeight());
                })
                .sorted(Comparator.comparing(Rectangle::getX))
                .collect(toList());
    }

    /**
     * 获取屏幕原始宽度
     * @return
     */
    public double getWidth() {
        return rectangles.get(0).getWidth();
    }

    /**
     * 获取屏幕原始高度
     * @return
     */
    public double getHeight() {
        return rectangles.get(0).getHeight();
    }

    /**
     * 获取当前屏幕缩放后宽度
     */
    public double getCurrentWidth() {
        return screenSize.getWidth();
    }

    /**
     * 获取当前屏幕缩放后高度
     */
    public double getCurrentHeight() {
        return screenSize.getHeight();
    }


    /**
     * getters/setters
     */

    public List<Rectangle> getRectangles() {
        return rectangles;
    }

    public void setRectangles(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
    }

    public Dimension getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Dimension screenSize) {
        this.screenSize = screenSize;
    }
}
