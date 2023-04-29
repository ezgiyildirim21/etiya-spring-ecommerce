package com.etiya.ecommerce.core.utils.result;

import lombok.Getter;


public class ErrorDataResult<T> extends DataResult<T>{

    public ErrorDataResult(T data) {
        super(data,false);
    }

    public ErrorDataResult(T data, String message) {
        super(data, false,message);
    }

    public ErrorDataResult( String message) {
        super(null, false,message);
    }

    public ErrorDataResult(){
        super(null,false);
    }


}
