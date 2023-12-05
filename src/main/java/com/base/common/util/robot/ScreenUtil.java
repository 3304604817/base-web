package com.base.common.util.robot;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ScreenUtil {

    /**
     * 所有显示器信息
     */
    private List<Rectangle> rectangles = getDisplays();

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

    public double getWidth() {
        return rectangles.get(0).getWidth();
    }

    public double getHeight() {
        return rectangles.get(0).getHeight();
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
}
