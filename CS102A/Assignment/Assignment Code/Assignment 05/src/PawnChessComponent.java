import java.util.ArrayList;
import java.util.List;

public class PawnChessComponent extends ChessComponent {
    public PawnChessComponent(ChessboardPoint source, ChessColor color, ConcreteChessGame game) {
        super(source, color, color == ChessColor.BLACK ? 'P' : 'p', game);
    }

    @Override
    public List<ChessboardPoint> canMoveTo() {
        ArrayList<ChessboardPoint> list = new ArrayList<>();
        return list;
    }
}
