import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class ArrayPanel extends JPanel{

    private static final int HEIGHT = 200;

    private int[] arrayUp;
    private int[] arrayDown;
    private int min;
    private int max;

    public ArrayPanel( int[] arrayUp, int[] arrayDown ) {
        super();
        this.arrayUp = arrayUp;
        this.arrayDown = arrayDown;
        setPreferredSize(new Dimension(arrayUp.length,HEIGHT*2+1));

        min = Integer.MAX_VALUE;
        for( int i : arrayDown )
            if( i < min )
                min = i;
        max = Integer.MIN_VALUE;
        for( int i : arrayDown )
            if( i > max )
                max = i;
        
        if( min == max ) {
            if( min > Integer.MIN_VALUE )
                min --;
            if( max < Integer.MAX_VALUE )
                max ++;
        }
    }

    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);

        for( int i = 0; i < arrayUp.length; ++ i )
            if( arrayUp[i] > min )
                g.drawLine(i, HEIGHT-1, i, HEIGHT-(arrayUp[i]-min)*HEIGHT/(max-min));

        
        for( int i = 0; i < arrayDown.length; ++ i )
            if( arrayDown[i] > min )
                g.drawLine(i, 2*HEIGHT, i, 2*HEIGHT+1-(arrayDown[i]-min)*HEIGHT/(max-min));
    }

    public static ArrayPanel startArrayGraphic( int[] arrayUp, int[] arrayDown ) {
        JFrame jf = new JFrame("Graphics Array");
        ArrayPanel panel = new ArrayPanel(arrayUp, arrayDown);
        jf.add(panel);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
        return panel;
    }
}
