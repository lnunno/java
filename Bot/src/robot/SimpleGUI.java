package robot;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Base graphical user interface.
 */
public class SimpleGUI extends JFrame {

  private static final long serialVersionUID = 1L;

  public SimpleGUI ( String title ) {

    // Set the title of this window in the title bar
    this.setTitle ( title );

    // Set the dimensions of this window
    this.setSize ( new Dimension ( 640, 480 ) );

    // Make application terminate when window closes
    this.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );

    // Center window on screen
    this.setLocationRelativeTo ( null );

  }

  public static void main ( String[] args ) {
    new SimpleGUI ( "Cool Window" ).setVisible ( true );
  }

}
