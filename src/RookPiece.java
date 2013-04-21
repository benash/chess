import java.util.LinkedList;

public class RookPiece extends ChessPiece {

  public RookPiece(ChessBoard board_, boolean isBlack_) {
    super(board_, isBlack_);
    type = "Rook";
  }

  @Override
  public boolean getValidMoves(LinkedList<ChessBoardLocation> validMoves) {

    addRow(validMoves,  0,  1);
    addRow(validMoves,  0, -1);
    addRow(validMoves,  1,  0);
    addRow(validMoves, -1,  0);

    return true;
  }

}
