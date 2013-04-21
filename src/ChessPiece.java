import java.util.LinkedList;

public abstract class ChessPiece {

  private boolean isBlack;
  private ChessBoard board;
  private ChessBoardLocation location;

  protected String type;

  public ChessPiece(ChessBoard board_, boolean isBlack_) {
    board = board_;
    isBlack = isBlack_;
  }

  public abstract boolean getValidMoves(LinkedList<ChessBoardLocation>
      validMoves);

  public boolean isBlack() { return isBlack; }

  public String getColor() {
    return isBlack ? "Black" : "White";
  }

  public ChessBoard getBoard() { return board; }

  public ChessBoardLocation getLocation() { return location; }

  public void setLocation(ChessBoardLocation location_) {
    location = location_;
  }

  public String getType() { return type; }

  protected void addOffsetLocation(LinkedList<ChessBoardLocation> validMoves,
      int x, int y) {

    ChessBoardLocation l =
      new ChessBoardLocation(location.getRow() + y,location.getCol() + x);

    if (l.isInBounds() && board.unoccupiedBy(l, getColor()))
      validMoves.add(l);
  }

  protected void addRow(LinkedList<ChessBoardLocation> validMoves, int x_diff,
      int y_diff) {

    int x = location.getRow() + x_diff;
    int y = location.getCol() + y_diff;
    ChessBoardLocation loc = new ChessBoardLocation(x, y);

    while (loc.isInBounds()) {
      
      if (board.unoccupiedBy(loc, getColor()))
        validMoves.add(loc);

      if (board.isOccupied(loc))
        break;

      x += x_diff;
      y += y_diff;
      loc = new ChessBoardLocation(x, y);

    }

  }

}

