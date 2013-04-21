import java.util.LinkedList;

public class PawnPiece extends ChessPiece {

  public PawnPiece(ChessBoard board_, boolean isBlack_) {
    super(board_, isBlack_);
    type = "Pawn";
  }

  @Override
  public boolean getValidMoves(LinkedList<ChessBoardLocation> validMoves) {
    return false;
  }

}
