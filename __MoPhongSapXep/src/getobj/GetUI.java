
package getobj;

import ui.HelpUI;
import ui.InforUI;
import ui.InputUI;
import ui.MainUI;

public class GetUI {

    private static HelpUI  helpUI;
    private static InforUI inforUI;
    private static InputUI inputUI;
    private static MainUI  mainUI;

    private GetUI() {}

    public static synchronized HelpUI getHelpUI() {

        if(helpUI == null) {
            helpUI = new HelpUI("Help");
        }
        return helpUI;
    }

    public static synchronized InforUI getInforUI() {

        if(inforUI == null) {
            inforUI = new InforUI("Infor");
        }
        return inforUI;
    }

    public static synchronized InputUI getInputUI() {

        if(inputUI == null) {
            inputUI = new InputUI("Input");
        }
        return inputUI;
    }

    public static synchronized MainUI getMainUI() {

        if(mainUI == null) {
            mainUI = new MainUI("M\364 Ph\u1ECFng S\u1EAFp X\u1EBFp");
        }
        return mainUI;
    }
}
