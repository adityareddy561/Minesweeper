import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Mines extends JFrame {

    private JLabel statusbar;
    
    public Mines(String k) {
        
        initUI(k);
    }
    
   

	private void initUI(String s) {
    	
        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new Board(statusbar,s));        
        
        setResizable(false);
        pack();
        
        setTitle("Minesweeper");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
//    public static void main(String[] args) {
//        
//        EventQueue.invokeLater(() -> {
//            Mines ex = new Mines();
//            ex.setVisible(true);
//        });
//    }
}