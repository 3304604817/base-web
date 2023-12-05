package com.base.basic;

import com.base.common.util.robot.ScreenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RunWith(SpringRunner.class)
public class RobotTest {

    @Test
    public void test() throws AWTException, InterruptedException, IOException {
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

        System.out.println("移动光标");
        robot.mouseMove(1910,1070);

        System.out.println("屏幕长宽");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;


        System.out.println("屏幕长宽");
        System.out.println("width:" + ScreenUtil.getInstance().getWidth() + "," + "height:" + ScreenUtil.getInstance().getHeight());


        BufferedImage screenCapture = robot.createScreenCapture(ScreenUtil.getInstance().getRectangles().get(0));

        ImageIO.write(screenCapture,"png", new File("C:\\Users\\yang.gao11\\Downloads\\888888.png"));


        System.out.println(1);
    }
}
