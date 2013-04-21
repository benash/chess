public class ChessBoard {

  public static final int BOARD_SIZE = 8;

  private ChessPiece[][] squares = new ChessPiece[BOARD_SIZE][BOARD_SIZE];

  public ChessPiece getPiece(ChessBoardLocation location) {
    return squares[location.getRow()][location.getCol()];
  }

  public void addPiece(ChessPiece piece, ChessBoardLocation location) {
    squares[location.getRow()][location.getCol()] = piece;
    piece.setLocation(location);
  }

  public boolean isOccupied(ChessBoardLocation location) {
    return getPiece(location) != null;
  }

  public boolean unoccupiedBy(ChessBoardLocation location, String color) {
    return !(isOccupied(location) && color == getPiece(location).getColor());
  }

}

