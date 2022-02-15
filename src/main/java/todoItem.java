import javafx.scene.control.CheckBox;
public class todoItem {
    private boolean done;
    private String description;
    private CheckBox checkBox;

    public todoItem(String description){
        this.description = description;
        this.done = false;
        this.checkBox = new CheckBox(description);
    }

    public String getDescription(){
        return this.description;
    }

    public void updateDescription(String description){
        this.description = description;
        this.checkBox.setText(this.description);
    }

    public boolean getStatus(){
        return this.done;
    }

    public void finishItem(){
        this.done = true;
        this.checkBox.setSelected(true);
    }

    public CheckBox getCheckBox(){
        return checkBox;
    }

}
