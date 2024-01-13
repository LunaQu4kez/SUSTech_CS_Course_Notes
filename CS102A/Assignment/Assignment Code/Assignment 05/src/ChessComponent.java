import java.util.List;

public abstract class ChessComponent {
    //should design
    private ChessboardPoint source;
    private ChessColor chessColor;
    protected char name;
    private ConcreteChessGame game;

    //should design
    public ChessComponent(){}

    public ChessComponent(ChessboardPoint source, ChessColor chessColor, char name, ConcreteChessGame game) {
        this.source = source;
        this.chessColor = chessColor;
        this.name = name;
        this.game = game;
    }

    // should design
    public abstract List<ChessboardPoint> canMoveTo();

    /**
     * should design
     * @return String
     */
    @Override
    public String toString() {
        return String.valueOf(this.name);
    }

    public ChessboardPoint canStep(ChessboardPoint source){
        if (source == null){
            return null;
        }
        if (game.getChessComponents()[source.getX()][source.getY()].getChessColor() == chessColor){
            return null;
        } else {
            return source;
        }
    }

    public boolean hasOpponentChess(ChessboardPoint source){
        ChessColor color = game.getChessComponents()[source.getX()][source.getY()].getChessColor();
        if (color == (chessColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK)){
            return true;
        } else {
            return false;
        }
    }

    public ChessboardPoint getSource() {
        return source;
    }

    public void setSource(ChessboardPoint source) {
        this.source = source;
    }

    public ChessColor getChessColor() {
        return chessColor;
    }

    public void setChessColor(ChessColor chessColor) {
        this.chessColor = chessColor;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public ChessGame getGame() {
        return game;
    }

    public void setGame(ConcreteChessGame game) {
        this.game = game;
    }
}
