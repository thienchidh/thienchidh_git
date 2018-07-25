
package test;

import getobj.GetUI;
import ui.HelpUI;

public class TestHelpUI {

    public TestHelpUI() {}

    public static void main(String args[]) {

        HelpUI ui = GetUI.getHelpUI();
        ui.showWindow();
    }
}
