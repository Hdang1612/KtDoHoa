package com.example.ktdohoa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MidpointLineDrawing extends Application {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawLine(gc, 50, 50, 100, 500); // Vẽ đoạn thẳng từ (50,50) đến (400,300)
        gc.fillRect(50, 50, 20, 20);
//        gc.fillRect(400, 300, 20, 20);
        gc.fillRect(100, 100, 20, 20);
        StackPane root = new StackPane(canvas);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.setTitle("Bresenham Line Drawing");
        primaryStage.show();
    }

    private void drawLine(GraphicsContext gc, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        float k=(float)dy/dx;

        if (k>0 && k<1){
            int y=y1;
            float p=2*dy-dx ;
            for (int x=x1;x<=x2;x++) {
                gc.setFill(Color.RED);
                gc.fillRect(x, y, 20, 20);
                if (p<=0) p+=2*dy;
                else {
                    p+=2*(dy-dx);
                    y++;
                }
            }
        }
        else if (k>1){
            int x=x1;
            float p=dy-2*dx ;
            for (int y=y1;y<=y2;y++) {
                gc.setFill(Color.BLACK);
                gc.fillRect(x, y, 1, 1);
                if (p>=0) p+=-2*dx;
                else {
                    p+=2*(dy-dx);
                    x++;
                }
            }
        }
        else if (k>-1&& k<0){
            int y=x1;
            float p=2*dy+dx ;
            for (int x=x1;x<=x2;x++) {
                gc.setFill(Color.BLACK);
                gc.fillRect(x, y, 3, 3);
                if (p>=0) p+=2*dy;
                else {
                    p+=2*(dx+dy);
                    y--;
                }
            }
        }
        else {
            int x=x1;
            float p=-dy-2*dx ;
            for (int y=x1;y<=x2;y++) {
                gc.setFill(Color.BLACK);
                gc.fillRect(x, y, 3, 3);
                if (p<=0) p+=-2*dx;
                else {
                    p+=2*(dy-dx);
                    y++;
                }
            }
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
