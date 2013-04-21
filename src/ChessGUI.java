import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.LinkedList;

public class ChessGUI extends JFrame {

  public final static String ICON_DIR = "imgs";

  private ChessBoard board;
  private ChessBoardLocationLabel[][] labels;

  private JList<String> colorList;
  private JList<String> typeList;

  private boolean deployMode = false;

  public ChessGUI(ChessBoard board_) {
    board = board_;

    int size = ChessBoard.BOARD_SIZE;
    labels = new ChessBoardLocationLabel[size][size];

    setLayout(new FlowLayout());

    add(makeChessBoardPanel());
    add(makeBottomPanel());
  }

  private JPanel makeChessBoardPanel() {
    JPanel boardPanel = new JPanel();

    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    boardPanel.setLayout(gridbag);

    GridListener listener = new GridListener();

    for (int row = 0; row < labels.length; row++) {
      for (int col = 0; col < labels[row].length; col++) {
        ChessBoardLocation location = new ChessBoardLocation(row, col);
        ChessBoardLocationLabel label = new ChessBoardLocationLabel(location);

        label.addMouseListener(listener);
        c.gridx = col;
        c.gridy = row;
        boardPanel.add(label, c);

        labels[row][col] = label;
      }
    }

    return boardPanel;
  }

  private JPanel makeBottomPanel() {

    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();

    c.insets = new Insets(5, 0, 5, 0);

    c.gridy = 0;
    bottomPanel.add(makeColorPanel(), c);

    c.gridy = 1;
    bottomPanel.add(makePiecePanel(), c);

    c.gridy = 2;
    bottomPanel.add(makeDeployButton(), c);

    return bottomPanel;
  }

  private JPanel makeColorPanel() {

    JPanel colorPanel = new JPanel();
    colorPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    c.gridy = 0;
    JLabel label = new JLabel("Color");
    label.setFont(new Font("sans_serif", Font.BOLD, 13));
    colorPanel.add(label, c);

    c.gridy = 1;
    String[] data = {"Black", "White"};
    colorList = new JList<String>(data);
    colorPanel.add(colorList, c);

    return colorPanel;
  }

  private JPanel makePiecePanel() {

    JPanel piecePanel = new JPanel();

    piecePanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    c.gridy = 0;
    JLabel label = new JLabel("Piece");
    label.setFont(new Font("sans_serif", Font.BOLD, 13));
    piecePanel.add(label, c);

    c.gridy = 1;
    String[] data = {"Rook", "Knight", "Bishop", "Pawn", "Queen", "King"};
    typeList = new JList<String>(data);
    piecePanel.add(typeList, c);

    return piecePanel;
  }

  private JButton makeDeployButton() {

    JButton button = new JButton("Add");
    button.addActionListener(new AddListener());

    return button;
  }

  private class AddListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      deployMode = true;
    }

  }

  private class GridListener extends MouseAdapter {

    public void mouseClicked(MouseEvent click) {

      ChessBoardLocationLabel label =
        (ChessBoardLocationLabel) click.getSource();

      if (deployMode) {
        deployTo(label);
        deployMode = false;
      }
      else
        showMovesFor(label);

    }

  }

  private void deployTo(ChessBoardLocationLabel label) {
    addPiece(label.getChessBoardLocation());
    clearAll();
  }

  private void addPiece(ChessBoardLocation location) {

    String type = typeList.getSelectedValue();
    boolean isBlack = colorList.getSelectedValue() == "Black";

    ChessPiece piece;

    if (type == "Bishop")
      piece = new BishopPiece(board, isBlack);
    else if (type == "Knight")
      piece = new KnightPiece(board, isBlack);
    else if (type == "Rook")
      piece = new RookPiece(board, isBlack);
    else if (type == "Pawn")
      piece = new PawnPiece(board, isBlack);
    else if (type == "King")
      piece = new KingPiece(board, isBlack);
    else
      piece = new QueenPiece(board, isBlack);

    board.addPiece(piece, location);

  }

  private void clearAll() {
    for (int i = 0; i < labels.length; i++) {
      for (int j = 0; j < labels[i].length; j++) {
        ChessPiece piece = board.getPiece(new ChessBoardLocation(i, j));
        labels[i][j].clear(piece);
      }
    }
  }

  private void showMovesFor(ChessBoardLocationLabel label) {
    ChessPiece piece = board.getPiece(label.getChessBoardLocation());

    if (piece != null)
      showMovesForNonNull(piece);
  }

  private void showMovesForNonNull(ChessPiece piece) {
    LinkedList<ChessBoardLocation> validMoves =
      new LinkedList<ChessBoardLocation>();

    if (piece.getValidMoves(validMoves))
      displaySuccess(validMoves);
    else
      displayFailure();

    clearAll();
  }

  private void displaySuccess(LinkedList<ChessBoardLocation> validMoves) {
    for (ChessBoardLocation location : validMoves) {
      activate(location);
    }

    String msg = "Piece has " + validMoves.size() + " moves";
    JOptionPane.showMessageDialog(this, msg);
  }

  private void displayFailure() {
    JOptionPane.showMessageDialog(this, "Piece type not supported.",
        "Error", JOptionPane.ERROR_MESSAGE);
  }

  private void activate(ChessBoardLocation location) {
    ChessPiece piece = board.getPiece(location);
    labels[location.getRow()][location.getCol()].activate(piece);
  }

}

