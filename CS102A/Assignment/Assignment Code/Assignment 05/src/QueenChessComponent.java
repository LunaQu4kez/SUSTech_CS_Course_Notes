import java.util.ArrayList;
import java.util.List;

public class QueenChessComponent extends ChessComponent {
    public QueenChessComponent(ChessboardPoint source, ChessColor color, ConcreteChessGame game) {
        super(source, color, color == ChessColor.BLACK ? 'Q' : 'q', game);
    }

    @Override
    public List<ChessboardPoint> canMoveTo() {
        ArrayList<ChessboardPoint> list = new ArrayList<>();
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
