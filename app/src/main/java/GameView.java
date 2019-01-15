import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {

    private Cell [][] cells ;


    public GameView(Context context , @Nullable AttributeSet attrs){

        super (context , attrs );

    }
    @Override
    //color
    protected void onDraw(Canvas canvas){

        canvas.drawColor(Color.YELLOW );
         int width = getWidth() ;
         int height = getHeight() ;
         if (width/height < SPALTES / REIHEN){
             cellSize = height / (SPALTES + 1);
         }
         else
             cellSize = width / (REIHEN + 1);
         hMargin = (width - (SPALTES*cellSize))/2;
         vMargin = (height - (SPALTES*cellSize))/2;
         canvas.translate(hMargin, vMargin);
    }

    private class Cell{
        boolean
             hoch = true,
                recht = true,
             link = true,

    }
}
