import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ChessBoardLocationLabel extends JLabel {

  private ChessBoardLocation location;

  public ChessBoardLocationLabel(ChessBoardLocation location_) {

    location = location_;
    clear(null);

  }

  public ChessBoardLocation getChessBoardLocation() { return location; }

  public void activate(ChessPiece piece) {
    set(piece, "Green");
  }

  public void clear(ChessPiece piece) {
    set(piece, getRegularColor());
  }

  private void set(ChessPiece piece, String color) {

    String img = getType(piece) + color;
    setIcon(new ImageIcon(ChessGUI.ICON_DIR + "/" + img + ".png"));

  }

  private String getType(ChessPiece piece) {

    if (piece == null)
      return "Empty";
    else
      return piece.getColor() + piece.getType() + "On";

  }

  private String getRegularColor() {

    if (location.getRow() % 2 == location.getCol() % 2)
      return "White";
    else
      return "Black";

  }

}

