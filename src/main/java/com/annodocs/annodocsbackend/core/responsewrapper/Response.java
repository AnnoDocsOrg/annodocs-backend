package com.annodocs.annodocsbackend.core.responsewrapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Response<T>
{
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    public T payload;
    public String message;

    public static <K> Response of(K payload, String message)
    {
        Response<K> response = new Response<>();
        response.setMessage(message);
        response.setPayload(payload);
        return response;
    }

    public static <K> Response of(K payload, boolean success)
    {
        Response<K> response = new Response<>();

        if(success)
        {
            response.setMessage(SUCCESS);
        } else
        {
            response.setMessage(FAILURE);
        }
        response.setPayload(payload);
        return response;
    }

    public static Response of(boolean success)
    {
        if(success)
        {
            return of(SUCCESS);
        } else
        {
            return of(FAILURE);
        }
    }

    public static Response success()
    {
        return of(SUCCESS);
    }

    public static Response of(String payload)
    {
        Response response = new Response<>();
        response.setMessage(payload);
        return response;
    }


}
