package com.example.employee.model;
//Added second value to enum as enum does not suppport Hyphen '-' in names
public enum State {
    ADDED("ADDED"),
    CHECK_IN("CHECK-IN"),
    APPROVED("APPROVED"),
    ACTIVE("ACTIVE");

    private String actualValue;
    private State(String actualValue){
        this.actualValue = actualValue;
    }

    /**
     * Method to return value for coresponding actualValue
     * @return coresponding actualValue
     */
    public  String getActualValue(){
        return actualValue;
    }

    /**
     * Method to return the state ENUM value for string value
     * @param value
     * @return Enum value for the string
     */
    public  static State getValue(String value) {
        State returnValue = null;
        for (State st : State.values()) {
            if (st.getActualValue().equals(value)) {
                returnValue = st;
            }
        }
        return returnValue;
    }

    /**
     * Methos to check if calue passed exist in the actual value
     * @param state
     * @return
     */
    public static boolean contains(String state){
        boolean isContained = false;
        for(State st:State.values()){
            if(st.getActualValue().equals(state)){
                isContained = true;
                break;
            }
        }
        return isContained;
    }

}