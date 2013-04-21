import java.util.LinkedList;

public class KnightPiece extends ChessPiece {

  public KnightPiece(ChessBoard board_, boolean isBlack_) {
    super(board_, isBlack_);
    type = "Knight";
  }

  @Override
  public boolean getValidMoves(LinkedList<ChessBoardLocation> validMoves) {

    int[] offsets = new int[]{-2, -1, 1, 2};

    for (int x : offsets)
      for (int y : offsets)
        if (x != y && x + y != 0)
          addOffsetLocation(validMoves, x, y);

    return true;
  }

}

