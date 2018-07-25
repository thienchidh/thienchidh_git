
package model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.text.JTextComponent;

public class KeyActions extends KeyAdapter {

    private StringBuffer   sb;
    private JTextComponent txt;

    public KeyActions(JTextComponent txt) {

        this.txt = txt;
        sb = new StringBuffer();
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if((e.getKeyCode() == 32) || (e.getKeyCode() == 9) || (e.getKeyCode() == 10)) {
            sb.setLength(0);
            sb.append(txt.getText().trim()).append(" ");
            txt.setText(sb.toString());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if(e.getKeyChar() == ' ') { return; }
        if(!Character.isDigit(e.getKeyChar())) {
            e.consume();
        }
    }
}
