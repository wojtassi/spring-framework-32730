package com.example.springframework32730;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.Response;

@Path("/test")
public class TestResource {
    @GET
    public void get(@Suspended AsyncResponse response, @QueryParam("async") Boolean async, @QueryParam("error") Boolean error) {
        if (Boolean.TRUE.equals(async)) {
            new Thread(() -> reply(response, error)).start();
        } else {
            reply(response, error);
        }
    }

    private void reply(AsyncResponse response, Boolean error) {
        if (Boolean.TRUE.equals(error)) {
            response.resume(new WebApplicationException(417));
        } else {
            response.resume(Response.ok("success").build());
        }
    }
}
