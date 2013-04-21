import java.util.LinkedList;

public class QueenPiece extends ChessPiece {

  public QueenPiece(ChessBoard board_, boolean isBlack_) {
    super(board_, isBlack_);
    type = "Queen";
  }

  @Override
  public boolean getValidMoves(LinkedList<ChessBoardLocation> validMoves) {

    for (int x = -1; x <= 1; x++)
      for (int y = -1; y <= 1; y++)
        if (x != 0 || y != 0)
          addRow(validMoves, x, y);

    return true;
  }

}

