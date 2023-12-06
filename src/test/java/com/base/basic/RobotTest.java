package com.base.basic;

import com.base.common.util.robot.RobotPlus;
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
        RobotPlus robotPlus = new RobotPlus();

        System.out.println("当前光标坐标");
        int x = MouseInfo.getPointerInfo().getLocation().x;
        int y = MouseInfo.getPointerInfo().getLocation().y;
        System.out.println("X:" + x + "," + "Y:" + y);

        robotPlus.mouseMovePercent(0.9,0.025);
        robotPlus.leftClick();
        robotPlus.mouseMovePercent(0.5,0.5);

        robotPlus.leftClick();

        robotPlus.slipWheel(-100);

        BufferedImage image = robotPlus.screenshot();
        File outputfile = new File("C:\\Users\\yang.gao11\\Downloads\\image.png");
        ImageIO.write(image, "png", outputfile);
        System.out.println(1);
    }
}
