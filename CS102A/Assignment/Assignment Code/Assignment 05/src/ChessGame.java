import java.util.List;

public interface ChessGame {

    void loadChessGame(List<String> chessboard);


    ChessColor getCurrentPlayer();


    public ChessComponent getChess(int x, int y);


    public String getChessboardGraph();


    public String getCapturedChess(ChessColor player);


    public List<ChessboardPoint> getCanMovePoints(ChessboardPoint source);


    public boolean moveChess(int sourceX, int sourceY, int targetX, int targetY);

}
