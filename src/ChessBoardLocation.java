public class ChessBoardLocation {

  private int row;
  private int col;

  public ChessBoardLocation(int row_, int col_) {
    row = row_;
    col = col_;
  }

  public int getRow() { return row; }

  public int getCol() { return col; }

  public boolean isInBounds() {
    return row >= 0 && row < ChessBoard.BOARD_SIZE &&
           col >= 0 && col < ChessBoard.BOARD_SIZE;
  }

}
