import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.LinkedList;

public class Controller {
    public Pane canvas;
    public ColorPicker backgroundPicker;
    public ColorPicker brushPicker;
    public ChoiceBox<Integer> shapeSizeBox;
    public ChoiceBox<String> shapeBox;
    public ChoiceBox<Integer> shapeNumBox;
    public ChoiceBox<Integer> delayBox;
    private Color brushColor;
    private LinkedList<Shape> list = new LinkedList<>();
    private long startTime = System.currentTimeMillis();
    private int shapesNum;
    private double shapeSize;
    private int waitMilis;
    private String shape;

    public void initialize() {
        shapeBox.setItems(FXCollections.observableArrayList("Circle", "Square", "Line"));
        shapeSizeBox.setItems(FXCollections.observableArrayList(5, 10, 20, 50, 100, 300));
        shapeNumBox.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 50));
        delayBox.setItems(FXCollections.observableArrayList(0, 25, 50, 100, 200, 500, 1000));
        shapeBox.setValue("Circle");
        shapeSizeBox.setValue(20);
        shapeNumBox.setValue(5);
        delayBox.setValue(50);

        brushPicker.setValue(Color.BLACK);
        canvas.setStyle("-fx-background-color: " + toRGBCode(backgroundPicker.getValue()));
        brushColor = brushPicker.getValue();

        shape = shapeBox.getValue();
        shapesNum = shapeNumBox.getValue();
        shapeSize = shapeSizeBox.getValue();
        waitMilis = delayBox.getValue();
    }

    public void chooseBgColor(ActionEvent actionEvent) {
        canvas.setStyle("-fx-background-color: " + toRGBCode(backgroundPicker.getValue()));
    }

    public void chooseBrushColor(ActionEvent actionEvent) {
        brushColor = brushPicker.getValue();
    }

    public void drawObject(MouseEvent mouseDragEvent) {
        if (System.currentTimeMillis() - startTime >= waitMilis) {
            Shape s = null;
            startTime = System.currentTimeMillis();
            while (list.size() >= shapesNum) {
                canvas.getChildren().remove(list.removeLast());
            }
            switch (shape) {
                case "Circle":
                    s = new Circle(mouseDragEvent.getX(), mouseDragEvent.getY(), shapeSize, brushColor);
                    break;
                case "Square":
                    s = new Rectangle(mouseDragEvent.getX() - shapeSize / 2, mouseDragEvent.getY() - shapeSize / 2, shapeSize, shapeSize);
                    s.setFill(brushColor);
                    break;
                case "Line":
                    if (list.size() == 0) {
                        s = new Line(mouseDragEvent.getX() - shapeSize / 2, mouseDragEvent.getY(), mouseDragEvent.getX() + shapeSize / 2, mouseDragEvent.getY());
                    } else {
                        double[] v = getNormallizedVector(mouseDragEvent, ((Line)list.getFirst()));
                        s = new Line(mouseDragEvent.getX() - shapeSize / 2, mouseDragEvent.getY(),
                                mouseDragEvent.getX() +shapeSize / 2, mouseDragEvent.getY());
                        s.setRotate(Math.atan2(v[1], v[0])*180/Math.PI);
                    }
                    s.setFill(brushColor);
                    s.setStrokeWidth(shapeSize);
                    s.setStroke(brushColor);
                    s.setStrokeLineCap(StrokeLineCap.ROUND);
                    break;
            }
            list.push(s);
            canvas.getChildren().add(s);
        }

    }

    private double[] getNormallizedVector(MouseEvent mouseDragEvent, Line first) {
        double dx = first.getStartX()-mouseDragEvent.getX();
        double dy = first.getStartY()-mouseDragEvent.getY();
        double dlength = Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
        double[] res = new double[2];
        res[0] = dx/dlength;
        res[1] = dy/dlength;
        return res;
    }

    private static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public void endDrawing(MouseEvent mouseEvent) {
        for (Shape obj : list) {
            canvas.getChildren().remove(obj);
        }
        list.clear();
    }

    public void chooseObject(ActionEvent actionEvent) {
        shape = shapeBox.getValue();
    }

    public void chooseSize(ActionEvent actionEvent) {
        shapeSize = shapeSizeBox.getValue();
    }

    public void chooseNum(ActionEvent actionEvent) {
        shapesNum = shapeNumBox.getValue();
    }

    public void chooseDelay(ActionEvent actionEvent) {
        waitMilis = delayBox.getValue();
    }
}
