import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class ArrayPanel extends JPanel{

    private static final int HEIGHT = 200;

    private int[] array;
    private int min;
    private int max;

    public ArrayPanel( int[] array ) {
        super();
        this.array = array;
        setPreferredSize(new Dimension(array.length,HEIGHT));

        min = Integer.MAX_VALUE;
        for( int i : array )
            if( i < min )
                min = i;
        max = Integer.MIN_VALUE;
        for( int i : array )
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

        for( int i = 0; i < array.length; ++ i )
            if( array[i] > min )
                g.drawLine(i, getHeight()-1, i, HEIGHT-(array[i]-min)*HEIGHT/(max-min));
    }

    public static ArrayPanel startArrayGraphic( int[] array ) {
        JFrame jf = new JFrame("Graphics Array");
        ArrayPanel panel = new ArrayPanel(array);
        jf.add(panel);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
        return panel;
    }
}
