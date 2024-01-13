import java.util.*;

public class ConcreteChessGame implements ChessGame{

    private ChessComponent[][] chessComponents = new ChessComponent[8][8];
    private ChessColor currentPlayer;


    @Override
    public void loadChessGame(List<String> chessboard) {
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++){
                ChessColor color = ChessColor.NONE;
                if (chessboard.get(x).charAt(y) >= 65 && chessboard.get(x).charAt(y) <= 90){
                    color = ChessColor.BLACK;
                } else if (chessboard.get(x).charAt(y) >= 97 && chessboard.get(x).charAt(y) <= 122){
                    color = ChessColor.WHITE;
                }

                if (chessboard.get(x).charAt(y) == 'N' || chessboard.get(x).charAt(y) == 'n'){
                    ChessComponent cc = new KnightChessComponent(new ChessboardPoint(x, y), color, this);
                    chessComponents[x][y] = cc;
                } else if (chessboard.get(x).charAt(y) == 'B' || chessboard.get(x).charAt(y) == 'b'){
                    ChessComponent cc = new BishopChessComponent(new ChessboardPoint(x, y), color, this);
                    chessComponents[x][y] = cc;
                } else if (chessboard.get(x).charAt(y) == 'R' || chessboard.get(x).charAt(y) == 'r'){
                    ChessComponent cc = new RookChessComponent(new ChessboardPoint(x, y), color, this);
                    chessComponents[x][y] = cc;
                } else if (chessboard.get(x).charAt(y) == 'Q' || chessboard.get(x).charAt(y) == 'q'){
                    ChessComponent cc = new QueenChessComponent(new ChessboardPoint(x, y), color, this);
                    chessComponents[x][y] = cc;
                } else if (chessboard.get(x).charAt(y) == 'K' || chessboard.get(x).charAt(y) == 'k'){
                    ChessComponent cc = new KingChessComponent(new ChessboardPoint(x, y), color, this);
                    chessComponents[x][y] = cc;
                } else if (chessboard.get(x).charAt(y) == 'P' || chessboard.get(x).charAt(y) == 'p'){
                    ChessComponent cc = new PawnChessComponent(new ChessboardPoint(x, y), color, this);
                    chessComponents[x][y] = cc;
                } else if (chessboard.get(x).charAt(y) == '_'){
                    ChessComponent cc = new EmptySlotComponent(new ChessboardPoint(x, y), color, this);
                    chessComponents[x][y] = cc;
                }

            }
        }
        if (chessboard.get(8).equals("w")){
            currentPlayer = ChessColor.WHITE;
        } else {
            currentPlayer = ChessColor.BLACK;
        }
    }

    @Override
    public ChessColor getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public ChessComponent getChess(int x, int y) {
        return chessComponents[x][y];
    }

    @Override
    public String getChessboardGraph() {
        StringBuilder str = new StringBuilder();
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++){
                str.append(chessComponents[x][y]);
            }
            str.append("\n");
        }
        return str.toString();
    }

    @Override
    public String getCapturedChess(ChessColor player) {
        StringBuilder str = new StringBuilder();
        if (player == ChessColor.BLACK){
            str.append(toCapturedString(countChess('K'), 1, 'K'));
            str.append(toCapturedString(countChess('Q'), 1, 'Q'));
            str.append(toCapturedString(countChess('R'), 2, 'R'));
            str.append(toCapturedString(countChess('B'), 2, 'B'));
            str.append(toCapturedString(countChess('N'), 2, 'N'));
            str.append(toCapturedString(countChess('P'), 8, 'P'));
        } else if (player == ChessColor.WHITE){
            str.append(toCapturedString(countChess('k'), 1, 'k'));
            str.append(toCapturedString(countChess('q'), 1, 'q'));
            str.append(toCapturedString(countChess('r'), 2, 'r'));
            str.append(toCapturedString(countChess('b'), 2, 'b'));
            str.append(toCapturedString(countChess('n'), 2, 'n'));
            str.append(toCapturedString(countChess('p'), 8, 'p'));
        }
        return str.toString();
    }

    @Override
    public List<ChessboardPoint> getCanMovePoints(ChessboardPoint source) {
        ChessComponent chess = chessComponents[source.getX()][source.getY()];
        //System.out.println(chess);
        List<ChessboardPoint> list = chess.canMoveTo();
        Collections.sort(list, new Comparator<ChessboardPoint>() {
            @Override
            public int compare(ChessboardPoint o1, ChessboardPoint o2) {
                if ((o1.getX()-o2.getX() > 0 ? 1 : o1.getX()==o2.getX() ? 0 : -1) != 0){
                    return (o1.getX()-o2.getX() > 0 ? 1 : o1.getX()==o2.getX() ? 0 : -1);
                } else {
                    return (o1.getY()-o2.getY() > 0 ? 1 : -1);
                }
            }
        });
        return list;
    }

    @Override
    public boolean moveChess(int sourceX, int sourceY, int targetX, int targetY) {
        List<ChessboardPoint> list =  getChess(sourceX,sourceY).canMoveTo();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getX() == targetX && list.get(i).getY() == targetY){

                chessComponents[targetX][targetY] = new EmptySlotComponent(new ChessboardPoint(targetX, targetY), ChessColor.NONE, this);
                return true;
            }
        }
        return false;
    }

    private int countChess(char name){
        int cnt = 0;
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++){
                if (chessComponents[x][y].getName() == name){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private String toCapturedString(int cnt, int all, char name){
        if (all - cnt == 0){
            return "";
        } else {
            return name + " " + (all - cnt) + "\n";
        }
    }

    public ChessComponent[][] getChessComponents() {
        return chessComponents;
    }

    public void setChessComponents(ChessComponent[][] chessComponents) {
        this.chessComponents = chessComponents;
    }

    public void setCurrentPlayer(ChessColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
