package cs.bham.ac.uk.assignment3;

public class FoodData {

    private String mealName;
    private String mealType;
    private Integer time;
    private Integer apiID;



    public FoodData(Integer apiID, String mealName, String mealType, Integer time) {
        this.mealName = mealName;
        this.mealType = mealType;
        this.time = time;
        this.apiID = apiID;
    }

    public String getMealName() {
        return mealName;
    }

    public String getMealType() {
        return mealType;
    }

    public Integer getTime() {
        return time;
    }

    public Integer getApiID() {
        return apiID;
    }

    public String toString() {
        return getMealName() + " " + getMealType() + " "+ getTime();
    }

}


