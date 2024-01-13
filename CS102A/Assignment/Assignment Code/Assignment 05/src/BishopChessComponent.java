import java.util.ArrayList;
import java.util.List;

public class BishopChessComponent extends ChessComponent{
    public BishopChessComponent(ChessboardPoint source, ChessColor color, ConcreteChessGame game) {
        super(source, color, color == ChessColor.BLACK ? 'B' : 'b', game);
    }

    @Override
    public List<ChessboardPoint> canMoveTo() {
        List<ChessboardPoint> list = new ArrayList<>();
        for (int i = 1; i < 8; i++){
            if (canStep(getSource().offset(i,i)) == null){
                break;
            }
            list.add(getSource().offset(i,i));
            if (hasOpponentChess(getSource().offset(i,i))){
                break;
            }
        }
        for (int i = 1; i < 8; i++){
            if (canStep(getSource().offset(i,-i)) == null){
                break;
            }
            list.add(getSource().offset(i,-i));
            if (hasOpponentChess(getSource().offset(i,-i))){
                break;
            }
        }
        for (int i = 1; i < 8; i++){
            if (canStep(getSource().offset(-i,i)) == null){
                break;
            }
            list.add(getSource().offset(-i,i));
            if (hasOpponentChess(getSource().offset(-i,i))){
                break;
            }
        }
        for (int i = 1; i < 8; i++){
            if (canStep(getSource().offset(-i,-i)) == null){
                break;
            }
            list.add(getSource().offset(-i,-i));
            if (hasOpponentChess(getSource().offset(-i,-i))){
                break;
            }
        }
        return list;
    }
}
