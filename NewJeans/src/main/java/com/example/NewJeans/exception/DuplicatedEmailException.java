package com.example.NewJeans.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DuplicatedEmailException  extends  RuntimeException{

    public DuplicatedEmailException(String message){
        super(message);
    }
}
