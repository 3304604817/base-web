package com.base.basic;

import javafx.scene.effect.Bloom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Random;

/**
 * @author yang.gao
 * @description
 * @date 2/1/23 11:49 上午
 */
@RunWith(SpringRunner.class)
public class DistinctTest {

    // 产生数字个数
    int numCount = 999999999;
    // 数字范围
    int maxNum = 999;

    /**
     * 随机生成一个包含数字的文件
     */
    @Test
    public void createNumberFile(){
        Random rand = new Random();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/gaoyang/Downloads/numbers.txt"))) {
            int i = 0;
            for (; i < numCount; i++) {
                bw.write(String.valueOf(rand.nextInt(maxNum)));
                bw.newLine();
            }
            bw.flush();
            System.out.println("-----------------------------");
            System.out.println("finish:"+i);
            System.out.println("-----------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取数字文本并去重
     */
    @Test
    public void readNumberFile(){
        try {
            boolean[] booleanList = new boolean[maxNum];

            /**
             * 读取文件中的文本去重
             */
            BufferedReader br = new BufferedReader(new FileReader("/Users/gaoyang/Downloads/numbers.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                booleanList[Integer.valueOf(line)] = true;
            }
            System.out.println("-----------------------------");
            System.out.println("finish read");
            System.out.println("-----------------------------");

            /**
             * 去重后的数据重新写入一个文件
             */
            BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/gaoyang/Downloads/numbersDistinct.txt"));
            for (int i = 0; i < booleanList.length; i++) {
                if(booleanList[i]){
                    bw.write(String.valueOf(i));
                    bw.newLine();
                }
            }
            bw.flush();
            System.out.println("-----------------------------");
            System.out.println("finish write");
            System.out.println("-----------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
