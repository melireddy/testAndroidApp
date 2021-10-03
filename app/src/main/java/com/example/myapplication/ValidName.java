package com.example.myapplication;

public class ValidName {
    public boolean isNameValid(String name){
        return name.length() > 1 && name.matches("[a-zA-Z]+");
    }

    public boolean validTwoNames(String name){
        String[] arrNames = name.split(" ");

        for(int i = 0; i < arrNames.length; ++i){
            if(isNameValid(arrNames[i]) == false){
                return false;
            }
        }
        return true;
    }
}
