import java.util.LinkedList;

public class BishopPiece extends ChessPiece {

  public BishopPiece(ChessBoard board_, boolean isBlack_) {
    super(board_, isBlack_);
    type = "Bishop";
  }

  @Override
  public boolean getValidMoves(LinkedList<ChessBoardLocation> validMoves) {

    addRow(validMoves,  1,  1);
    addRow(validMoves,  1, -1);
    addRow(validMoves, -1,  1);
    addRow(validMoves, -1, -1);

    return true;
  }

}

