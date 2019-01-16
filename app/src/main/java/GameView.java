import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class GameView extends View {

    private Cell[][] cells;
    private Cell spieler, raus;
    private static final int SPALTES = 7, REIHEN = 10;
    private float cellSize, hMargin, vMargin;
    private static final float Wandgrosse = 4;
    private Paint wandFarbe, spielerFarbe, rausFarbe;
    private Random random;


    public GameView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

        wandFarbe = new Paint();
        wandFarbe.setColor(Color.BLACK);
        wandFarbe.setStrokeWidth(Wandgrosse);
        random = new Random();
        CreateMaze();


    }
    private Cell getNachbar(Cell cell) {
        ArrayList<Cell> nachbar = new ArrayList<>();

        //link nachbar
        if (cell.spalte > 0)
            if (!cells[cell.spalte - 1][cell.reihe].visited)
                nachbar.add(cells[cell.spalte - 1][cell.reihe]);
        //recht nachbar
        if (cell.spalte < SPALTES - 1)
            if (!cells[cell.spalte + 1][cell.reihe].visited)
                nachbar.add(cells[cell.spalte + 1][cell.reihe]);
        //hoch nachbar
        if (cell.reihe > 0)
            if (!cells[cell.spalte][cell.reihe - 1].visited)
                nachbar.add(cells[cell.spalte][cell.reihe - 1]);
        //untere nachbar
        if (cell.reihe < REIHEN - 1)
            if (!cells[cell.spalte][cell.reihe + 1].visited)
                nachbar.add(cells[cell.spalte][cell.reihe + 1]);

        if (nachbar.size() > 0) {
            int index = random.nextInt(nachbar.size());
            return nachbar.get(index);
        }
        return null;
    }

    private void removeWand(Cell current, Cell next) {
        if (current.spalte == next.spalte && current.reihe == next.reihe +1) {
            current.hoch = false;
            next.unter = false;
        }
        if (current.spalte == next.spalte && current.reihe == next.reihe -1) {
            current.unter = false;
            next.hoch = false;
        }
        if (current.spalte == next.spalte + 1 && current.reihe == next.reihe ) {
            current.link = false;
            next.recht = false;
        }
        if (current.spalte == next.spalte - 1 && current.reihe == next.reihe ) {
            current.recht = false;
            next.link = false;
        }
    }



    public void CreateMaze() {
        Stack<Cell> stack = new Stack<>();
        Cell current, next;
        cells = new Cell[SPALTES][REIHEN];
        for (int x = 0; x < SPALTES; x++) {
            for (int y = 0; y < REIHEN; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }


        current = cells[0][0];
        current.visited = true;

        do {
            next = getNachbar(current);
            if (next != null) {
                removeWand(current, next);
                stack.push(current);
                current = next;
                current.visited = true;
            } else
                current = stack.pop();
        }
        while (!stack.empty());
    }


     @Override
     //color
     protected void onDraw(Canvas canvas){

         canvas.drawColor(Color.YELLOW );
          int width = getWidth() ;
          int height = getHeight() ;
          if (width/height < SPALTES/REIHEN)
              cellSize = width/(SPALTES+1);
          else
              cellSize = height/(REIHEN+1);

          hMargin = (width - SPALTES*cellSize)/2;
          vMargin = (height - REIHEN*cellSize)/2;

         canvas.translate(hMargin, vMargin);

         for (int x=0; x< SPALTES ; x++){
             for (int y=0; y<REIHEN; y++){
                 if(cells[x][y].hoch)
                     canvas.drawLine(
                             x*cellSize,
                             y*cellSize,
                             (x+1)*cellSize,
                             y*cellSize,
                             wandFarbe
                     );
                 if(cells[x][y].recht)
                     canvas.drawLine(
                             (x+1)*cellSize,
                             y*cellSize,
                             (x+1)*cellSize,
                             (y+1)*cellSize,
                             wandFarbe
                     );
                 if(cells[x][y].unter)
                     canvas.drawLine(
                             (x+1)*cellSize,
                             (y+1)*cellSize,
                             x*cellSize,
                             (y+1)*cellSize,
                             wandFarbe
                     );
                 if(cells[x][y].link)
                     canvas.drawLine(
                             x*cellSize,
                             (y+1)*cellSize,
                             x*cellSize,
                             y*cellSize,
                             wandFarbe
                     );
             }
         }

         float margin = cellSize/10;

         canvas.drawRect(
                 spieler.spalte*cellSize,
                 spieler.reihe*cellSize,
                 (spieler.spalte+1)*cellSize,
                 (spieler.reihe+1)*cellSize ,
                 spielerFarbe );

         canvas.drawRect(
                 raus.spalte*cellSize,
                 raus.reihe*cellSize,
                 (raus.spalte+1)*cellSize,
                 (raus.reihe+1)*cellSize ,
                 rausFarbe );
     }

    private class Cell {
        boolean
                hoch = true,
                recht = true,
                link = true,
                unter = true,
                visited = false;
        int spalte, reihe;

        public Cell (int spalte, int reihe){

            this.spalte = spalte;
            this.reihe = reihe;
        }


    }

}