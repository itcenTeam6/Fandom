package com.example.fandomTest.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoRegisteredArgumentsException extends RuntimeException {
    //에러 메시지를 처리하는 생성자
    public NoRegisteredArgumentsException(String message){
        super(message);
    }
}
