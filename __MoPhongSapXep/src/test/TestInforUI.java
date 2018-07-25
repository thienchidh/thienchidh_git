
package test;

import getobj.GetUI;
import ui.InforUI;

public class TestInforUI {

    public TestInforUI() {}

    public static void main(String args[]) {

        InforUI ui = GetUI.getInforUI();
        ui.showWindow();
    }
}
