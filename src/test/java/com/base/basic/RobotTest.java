package com.base.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import sun.awt.image.MultiResolutionImage;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

@RunWith(SpringRunner.class)
public class RobotTest {

    @Test
    public void test() throws AWTException, InterruptedException {
        // 创建Robot类的实例，用于模拟键盘输入
        Robot robot = new Robot();

        //鼠标右键
        System.out.println("右击");
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);

        System.out.println("当前光标坐标");
        int x = MouseInfo.getPointerInfo().getLocation().x;
        int y = MouseInfo.getPointerInfo().getLocation().y;
        System.out.println("X:" + x + "," + "Y:" + y);

        System.out.println("屏幕长宽");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        System.out.println("width:" + width + "," + "height:" + height);
    }
}
