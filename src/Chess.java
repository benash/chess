import javax.swing.JFrame;

public class Chess {

  public static void main(String[] args) {

    ChessGUI theGui = new ChessGUI(new ChessBoard());

    theGui.setSize(410, 360);
    theGui.setVisible(true);
    theGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

}

