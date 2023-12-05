package com.base.common.util.robot;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ScreenUtil {

    public static List<Rectangle> getDisplays() {
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
}
