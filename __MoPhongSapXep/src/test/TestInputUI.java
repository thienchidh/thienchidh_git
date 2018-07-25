
package test;

import getobj.GetUI;
import ui.InputUI;

public class TestInputUI {

    public TestInputUI() {}

    public static void main(String args[]) {

        InputUI ui = GetUI.getInputUI();
        ui.showWindow();
    }
}
