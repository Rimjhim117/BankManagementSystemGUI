// util/IconUtil.java
package util;
import javax.swing.*;

public class IconUtil {
    public static void setWindowIcon(JFrame frame) {
        try {
            ImageIcon icon = new ImageIcon("assets/bank.png");
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("Icon not found!");
        }
    }
}
