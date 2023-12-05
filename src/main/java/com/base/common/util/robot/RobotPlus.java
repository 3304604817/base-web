package com.base.common.util.robot;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Robot 类增强
 */
public class RobotPlus extends Robot {
    public RobotPlus() throws AWTException {
    }

    public RobotPlus(GraphicsDevice screen) throws AWTException {
        super(screen);
    }

    /**
     * 鼠标右键
     */
    public void rightClick(){
        super.mousePress(InputEvent.BUTTON3_MASK);
        super.mouseRelease(InputEvent.BUTTON3_MASK);
    }

    /**
     * 鼠标移动到指定百分比的位置
     * @param xPercent x轴百分比 0到1
     * @param yPercent y轴百分比 0到1
     */
    public void mouseMovePercent(double xPercent, double yPercent){
        if(xPercent < 0 || xPercent > 1 || yPercent < 0 || yPercent > 1){
            return;
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double x = screenSize.getWidth() * xPercent;
        double y = screenSize.getHeight() * yPercent;
        super.mouseMove((int)x,(int)y);
    }
}
