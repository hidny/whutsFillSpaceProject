package drawShape;

import java.awt.event.* ;
import javax.swing.* ;

public class MyFrame extends JFrame {
Pizarron pp = new Pizarron ( ) ;
MyFrame ( ) {
getContentPane().add ( pp ) ;

setBounds ( 100, 100, 500, 500 ) ;
pack() ;
show() ;
}

public static void main( String[] args ) {
new MyFrame ( ) ;
}
}

class Pizarron extends JPanel {

Pizarron() {
super() ;
addKeyListener(new MyKeyListener());
}

// Override the isFocusTraversable method and return true
public boolean isFocusTraversable ( ) {
return true ;
}

private class MyKeyListener extends KeyAdapter {
public void keyPressed (KeyEvent e) {
System.out.println(e.getKeyText (e.getKeyCode()));
}

public void keyReleased (KeyEvent e) {
System.out.println(e.getKeyText(e.getKeyCode()));
}

public void keyTyped (KeyEvent e) {
System.out.println(e.getKeyChar());
}
}
}


