
package test;

import java.awt.EventQueue;

import getobj.GetUI;
import ui.MainUI;

public class TestMainUI {

    public TestMainUI() {}

    public static void main(String args[]) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                MainUI ui = GetUI.getMainUI();
                ui.showWindow();
            }

        });
    }
}
