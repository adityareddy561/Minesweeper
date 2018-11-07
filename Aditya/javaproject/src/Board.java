import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
public class Board extends JPanel {

    private final int images = 13;//no.of images
    private final int Csize = 15;//cell size
    
    
    private final int coc = 10;//cover for cell
    private final int mfc = 10;//mark for cell
    private final int EMPTY_CELL = 0;//empty cell
    private final int MINE_CELL = 9;//mine cell
    private final int COVERED_MINE_CELL = MINE_CELL + coc;//covered mine cell
    private final int MARKED_MINE_CELL = COVERED_MINE_CELL + mfc;//marked mine cell

 
    private final int dmine = 9;//draw mine
    private final int dcover = 10;//draw cover
    private final int dmark = 11;//draw mark
    private final int dwrongmark = 12;//draw wrong mark

    
    private  int nmines=2; //no.of mines
    private final int nrows=20;   //no.of rows
    private  final int ncolums=20;   //no.of columns

    private final int bwidth = nrows * Csize + 1;//board width
    private final int bheight = ncolums * Csize + 1;//board height

    private int[] field;
    private boolean inGame;
    private int minesLeft;
    private Image[] img;

    private int allCells;
    private final JLabel statusbar;

    public Board(JLabel statusbar,String k) {
    	
        this.statusbar = statusbar;
        initBoard();
        if(k=="Easy") {
        	this.nmines =40;
        }
        else if(k=="Medium") {
        	this.nmines =80;
        }
        else {
        	this.nmines = 120;
        }
    }

    private void initBoard() {

        setPreferredSize(new Dimension(bwidth, bheight));

        img = new Image[images];

        for (int i = 0; i < images; i++) {//getting images
            String path = "src/resources/" + i + ".png";
            img[i] = (new ImageIcon(path)).getImage();
        }

        addMouseListener(new MinesAdapter());
        newGame();
    }

    
  
    
    private void newGame() {

        int cell;
        Random random = new Random();
        inGame = true;
        minesLeft = nmines;

        allCells = nrows * ncolums;
        field = new int[allCells];

        for (int i = 0; i < allCells; i++) {
            field[i] = coc;
        }

        statusbar.setText(Integer.toString(minesLeft));

        int i = 0;
        
        while (i < nmines) {

            int position = (int) (allCells * random.nextDouble());

            if ((position < allCells)
                    && (field[position] != COVERED_MINE_CELL)) {

                int current_col = position % ncolums;
                field[position] = COVERED_MINE_CELL;
                i++;
                if (current_col > 0) {
                    cell = position - 1 - ncolums;
                    if (cell >= 0) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                    cell = position - 1;
                    if (cell >= 0) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }

                    cell = position + ncolums - 1;
                    if (cell < allCells) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                }

                cell = position - ncolums;
                if (cell >= 0) {
                    if (field[cell] != COVERED_MINE_CELL) {
                        field[cell] += 1;
                    }
                }
                
                cell = position + ncolums;
                if (cell < allCells) {
                    if (field[cell] != COVERED_MINE_CELL) {
                        field[cell] += 1;
                    }
                }

                if (current_col < (ncolums - 1)) {
                    cell = position - ncolums + 1;
                    if (cell >= 0) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                    cell = position + ncolums + 1;
                    if (cell < allCells) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                    cell = position + 1;
                    if (cell < allCells) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                }
            }
        }
    }

    public void find_empty_cells(int j) {

        int current_col = j % ncolums;
        int cell;

        if (current_col > 0) {
            cell = j - ncolums - 1;
            if (cell >= 0) {
                if (field[cell] > MINE_CELL) {
                	
                    field[cell] -= coc;
                    if (field[cell] == EMPTY_CELL) {
                    	
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j - 1;
            if (cell >= 0) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= coc;
                    if (field[cell] == EMPTY_CELL) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + ncolums - 1;
            if (cell < allCells) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= coc;
                    if (field[cell] == EMPTY_CELL) {
                        find_empty_cells(cell);
                    }
                }
            }
        }

        cell = j - ncolums;
        if (cell >= 0) {
            if (field[cell] > MINE_CELL) {
                field[cell] -= coc;
                if (field[cell] == EMPTY_CELL) {
                    find_empty_cells(cell);
                }
            }
        }

        cell = j + ncolums;
        if (cell < allCells) {
            if (field[cell] > MINE_CELL) {
                field[cell] -= coc;
                if (field[cell] == EMPTY_CELL) {
                    find_empty_cells(cell);
                }
            }
        }

        if (current_col < (ncolums - 1)) {
            cell = j - ncolums + 1;
            if (cell >= 0) {
                if (field[cell] > MINE_CELL) {
                	
                    field[cell] -= coc;
                    
                    if (field[cell] == EMPTY_CELL) {
                    	
                        find_empty_cells(cell);
                        
                    }
                }
            }

            cell = j + ncolums + 1;
            if (cell < allCells) {
                if (field[cell] > MINE_CELL) {
                	
                    field[cell] -= coc;
                    
                    if (field[cell] == EMPTY_CELL) {
                    	
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + 1;
            if (cell < allCells) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= coc;
                    if (field[cell] == EMPTY_CELL) {
                    	
                        find_empty_cells(cell);
                    }
                }
            }
        }

    }

    
    
    
    
    @Override
    public void paintComponent(Graphics g) {

        int uncover = 0;

        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncolums; j++) {

                int cell = field[(i * ncolums) + j];

                if (inGame && cell == MINE_CELL) {
                    inGame = false;
                }

                
                
                if (!inGame) {
                    if (cell == COVERED_MINE_CELL) {
                        cell = dmine;
                        
                    } else if (cell == MARKED_MINE_CELL) {
                    	
                        cell = dmark;
                        
                    } else if (cell > COVERED_MINE_CELL) {
                    	
                        cell = dwrongmark;
                        
                    } else if (cell > MINE_CELL) {
                    	
                        cell = dcover;
                    }

                } else {
                    if (cell > COVERED_MINE_CELL) {
                        cell = dmark;
                    } else if (cell > MINE_CELL) {
                        cell = dcover;
                        uncover++;
                    }
                }

                g.drawImage(img[cell], (j * Csize),
                        (i * Csize), this);
            }
        }

        if (uncover == 0 && inGame) {
            inGame = false;
            statusbar.setText("You Won");
        } else if (!inGame) {
            statusbar.setText("Try again             ");         
        }
    }

    
    
    private class MinesAdapter extends MouseAdapter {

    	
    	
        @Override
        public void mousePressed(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();

            int cCol = x / Csize;
            int cRow = y / Csize;

            boolean doRepaint = false;

            if (!inGame) {

                newGame();
                repaint();
            }

            if ((x < ncolums * Csize) && (y < nrows * Csize)) {

                if (e.getButton() == MouseEvent.BUTTON3) {

                    if (field[(cRow * ncolums) + cCol] > MINE_CELL) {
                        doRepaint = true;

                        if (field[(cRow * ncolums) + cCol] <= COVERED_MINE_CELL) {
                            if (minesLeft > 0) {
                                field[(cRow * ncolums) + cCol] += mfc;
                                minesLeft--;
                                String msg = Integer.toString(minesLeft);
                                statusbar.setText(msg);
                            } else {
                                statusbar.setText("No flags left");
                            }
                        } else {

                            field[(cRow * ncolums) + cCol] -= mfc;
                            minesLeft++;
                            String msg = Integer.toString(minesLeft);
                            statusbar.setText(msg);
                        }
                    }

                } else {

                    if (field[(cRow * ncolums) + cCol] > COVERED_MINE_CELL) {
                        return;
                    }

                    if ((field[(cRow * ncolums) + cCol] > MINE_CELL)
                            && (field[(cRow * ncolums) + cCol] < MARKED_MINE_CELL)) {

                        field[(cRow * ncolums) + cCol] -= coc;
                        doRepaint = true;

                        if (field[(cRow * ncolums) + cCol] == MINE_CELL) {
                            inGame = false;
                        }
                        
                        if (field[(cRow * ncolums) + cCol] == EMPTY_CELL) {
                            find_empty_cells((cRow * ncolums) + cCol);
                        }
                    }
                }

                if (doRepaint) {
                    repaint();
                }

            }
        }
    }
}