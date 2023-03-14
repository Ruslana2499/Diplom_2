package common.entities;

public class IngredientsResponse {
    private Ingredient[] data;
    private boolean success;

    public IngredientsResponse() {
    }

    public Ingredient[] getData() {
        return data;
    }

    public void setData(Ingredient[] data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
