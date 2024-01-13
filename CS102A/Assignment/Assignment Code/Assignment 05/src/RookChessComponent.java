import java.util.ArrayList;
import java.util.List;

public class RookChessComponent extends ChessComponent {
    public RookChessComponent(ChessboardPoint source, ChessColor color, ConcreteChessGame game) {
        super(source, color, color == ChessColor.BLACK ? 'R' : 'r', game);
    }

    @Override
    public List<ChessboardPoint> canMoveTo() {
        ArrayList<ChessboardPoint> list = new ArrayList<>();
        for (int i = 1; i < 8; i++){
            if (canStep(getSource().offset(i,0)) == null){
                break;
            }
            list.add(getSource().offset(i,0));
            if (hasOpponentChess(getSource().offset(i,0))){
                break;
            }
        }
        for (int i = 1; i < 8; i++){
            if (canStep(getSource().offset(0,-i)) == null){
                break;
            }
            list.add(getSource().offset(0,-i));
            if (hasOpponentChess(getSource().offset(0,-i))){
                break;
            }

        }
        for (int i = 1; i < 8; i++){
            if (canStep(getSource().offset(0,i)) == null){
                break;
            }
            list.add(getSource().offset(0,i));
            if (hasOpponentChess(getSource().offset(0,i))){
                break;
            }
        }
        for (int i = 1; i < 8; i++){
            if (canStep(getSource().offset(-i,0)) == null){
                break;
            }
            list.add(getSource().offset(-i,0));
            if (hasOpponentChess(getSource().offset(-i,0))){
                break;
            }
        }
        return list;
    }
}
