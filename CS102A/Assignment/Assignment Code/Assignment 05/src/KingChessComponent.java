import java.util.ArrayList;
import java.util.List;

public class KingChessComponent extends ChessComponent {
    public KingChessComponent(ChessboardPoint source, ChessColor color, ConcreteChessGame game) {
        super(source, color, color == ChessColor.BLACK ? 'K' : 'k', game);
    }

    @Override
    public List<ChessboardPoint> canMoveTo() {
        List<ChessboardPoint> list = new ArrayList<>();
        if (canStep(getSource().offset(1,0)) != null){
            list.add(getSource().offset(1,0));
        }
        if (canStep(getSource().offset(-1,0)) != null){
            list.add(getSource().offset(-1,0));
        }
        if (canStep(getSource().offset(0,1)) != null){
            list.add(getSource().offset(0,1));
        }
        if (canStep(getSource().offset(0,-1)) != null){
            list.add(getSource().offset(0,-1));
        }
        if (canStep(getSource().offset(1,1)) != null){
            list.add(getSource().offset(1,1));
        }
        if (canStep(getSource().offset(1,-1)) != null){
            list.add(getSource().offset(1,-1));
        }
        if (canStep(getSource().offset(-1,1)) != null){
            list.add(getSource().offset(-1,1));
        }
        if (canStep(getSource().offset(-1,-1)) != null){
            list.add(getSource().offset(-1,-1));
        }
        return list;
    }
}
