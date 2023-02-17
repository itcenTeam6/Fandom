package com.example.NewJeans.exception;


import lombok.NoArgsConstructor;

// 접미어로 Exception 붙이는게 관례 !
@NoArgsConstructor
public class NoRegisteredArgumentsException extends RuntimeException {

    //기본 생성자

    //에러 메시지를 처리하는 생성자
    public NoRegisteredArgumentsException(String message){
        super(message);
    }
}
