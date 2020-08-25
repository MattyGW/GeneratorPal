package Styling;

import java.awt.*;

public class StyleManager {
    public static StyleManager styleManager;
    /*
    * Assign all CSS IDS
    * Assign all dimensions
    */
    Integer mainBodyHeight = Toolkit.getDefaultToolkit().getScreenSize().height/2;
    Integer mainBodyWidth = Toolkit.getDefaultToolkit().getScreenSize().width/2;
    Integer secondaryBodyHeight = mainBodyHeight/3;
    Integer secondaryBodyWidth = mainBodyWidth/4;

    public StyleManager(){}

    public static StyleManager getStyleManager() {
        return styleManager;
    }

    public static void setStyleManager(StyleManager styleManager) {
        StyleManager.styleManager = styleManager;
    }
}
