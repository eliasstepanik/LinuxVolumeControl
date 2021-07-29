package de.sailehd.support;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Key extends KeyAdapter{
    public static void type(String str) throws AWTException {
        Robot robot = new Robot();
        for(char ch:str.toCharArray()){
            if(Character.isUpperCase(ch)){
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress((int)ch);
                robot.keyRelease((int)ch);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }else{
                char upCh = Character.toUpperCase(ch);
                robot.keyPress((int)upCh);
                robot.keyRelease((int)upCh);
            }
        }
    }

    public static void press(int code){

    }


}
