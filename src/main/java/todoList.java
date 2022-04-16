import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;




public class todoList extends Application implements EventHandler<ActionEvent> {
    ArrayList<todoItem> todoList;
    ArrayList<todoItem> doneList;
    TilePane currentLayout;
    TilePane nextLayout;

    private static final int WIDTH = 200;
    private static final int HEIGHT = 500;


    public todoList() {
        todoList = new ArrayList<todoItem>();
        doneList = new ArrayList<todoItem>();
    }

    public void addItem(String description){
        todoItem item = new todoItem(description);
        item.getCheckBox().setOnAction(checked -> {
            updateLayout();
            primaryStage.setScene(new Scene(this.nextLayout, WIDTH, HEIGHT));
            currentLayout = nextLayout;
            nextLayout = new TilePane();
        });
        todoList.add(item);
        updateLayout();
    }

    public void finishItem(todoItem item){
        item.finishItem();
        doneList.add(item);
    }

    public void updateLayout(){
        nextLayout = new TilePane();
        nextLayout.getChildren().add(this.input);
        nextLayout.setAlignment(Pos.TOP_CENTER);

        Iterator<todoItem> iter = todoList.iterator();
        while (iter.hasNext()){
            todoItem item = iter.next();
            if (item.getCheckBox().isSelected()){
                this.finishItem(item);
                iter.remove();
            } else {
                nextLayout.getChildren().add(item.getCheckBox());
            }
        }
    }

    Stage primaryStage;
    Scene scene;
    TextField input = new TextField();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Todo List");

        currentLayout = new TilePane();
        currentLayout.setAlignment(Pos.TOP_CENTER);
        currentLayout.getChildren().add(this.input);

        input.setOnAction(event -> {
            addItem(input.getText());
            primaryStage.setScene(new Scene(this.nextLayout, WIDTH, HEIGHT));
            currentLayout = nextLayout;
            nextLayout = new TilePane();
            nextLayout.setAlignment(Pos.TOP_CENTER);
        });

        this.scene = new Scene(currentLayout, WIDTH, HEIGHT);
        this.primaryStage.setScene(this.scene);
        this.primaryStage.show();
    }
    @Override
    public void handle(ActionEvent event) {
        System.out.println("handle");
    }

    public static void main(String[] args){
        launch(args);
    }


}
