package common.entities;

public class Ingredient {
    private String _id;

    public Ingredient(String _id) {
        this._id = _id;
    }

    public Ingredient() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

}
