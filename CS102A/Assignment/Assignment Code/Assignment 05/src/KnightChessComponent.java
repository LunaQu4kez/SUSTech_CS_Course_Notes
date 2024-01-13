import java.util.ArrayList;
import java.util.List;

public class KnightChessComponent extends ChessComponent{
    public KnightChessComponent(ChessboardPoint source, ChessColor color, ConcreteChessGame game) {
        super(source, color, color == ChessColor.BLACK ? 'N' : 'n', game);
    }

    @Override
    public List<ChessboardPoint> canMoveTo() {
        List<ChessboardPoint> list = new ArrayList<>();
        if (canStep(getSource().offset(1,2)) != null){
            list.add(getSource().offset(1,2));
        }
        if (canStep(getSource().offset(2,1)) != null){
            list.add(getSource().offset(2,1));
        }
        if (canStep(getSource().offset(-1,2)) != null){
            list.add(getSource().offset(-1,2));
        }
        if (canStep(getSource().offset(2,-1)) != null){
            list.add(getSource().offset(2,-1));
        }
        if (canStep(getSource().offset(1,-2)) != null){
            list.add(getSource().offset(1,-2));
        }
        if (canStep(getSource().offset(-2,1)) != null){
            list.add(getSource().offset(-2,1));
        }
        if (canStep(getSource().offset(-1,-2)) != null){
            list.add(getSource().offset(-1,-2));
        }
        if (canStep(getSource().offset(-2,-1)) != null){
            list.add(getSource().offset(-2,-1));
        }
        return list;
    }
}
