import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.text.Position;


public class todoList extends Application implements EventHandler<ActionEvent> {
    ArrayList<todoItem> todoList;
    ArrayList<todoItem> doneList;

    public todoList() {
        todoList = new ArrayList<todoItem>();
    }

    public void addItem(String description){
        todoItem item = new todoItem(description);
        todoList.add(item);
    }

    public void finishItem(todoItem item){
        item.finishItem();
        doneList.add(item);
    }

    public StackPane updateList(){
        StackPane updatedLayout = new StackPane();
        Iterator<todoItem> iter = todoList.iterator();
        while (iter.hasNext()){
            todoItem item = iter.next();
            if (item.getCheckBox().isSelected()){
                System.out.println(item.getDescription());
                this.finishItem(item);
                iter.remove();
            } else {
                updatedLayout.getChildren().add(item.getCheckBox());
            }
        }
        return updatedLayout;
    }

    Stage primaryStage;
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Todo List");

        this.addItem("Shower");
        this.addItem("Make Breakfast");
        StackPane layout = new StackPane();
        ArrayList<Pos> posArrayList = new ArrayList<Pos>();
        posArrayList.add(Pos.BASELINE_CENTER);
        posArrayList.add(Pos.BOTTOM_LEFT);
        int i = 0;

        for (todoItem item : todoList ){
            CheckBox checkBox = item.getCheckBox();
            checkBox.setAlignment(posArrayList.get(i));
            i++;
            layout.getChildren().add(checkBox);
            item.getCheckBox().setOnAction(event -> {
                StackPane updatedLayout = updateList();
                this.primaryStage.setScene(new Scene(updatedLayout, 400, 400));
            });
        }

        this.scene = new Scene(layout, 400, 400);
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
