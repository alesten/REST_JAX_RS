/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author AlexanderSteen
 */
@Path("quote")
public class quoteResource {
    private static Gson gson = new Gson();
    private static JsonParser jsonParser = new JsonParser();
    private static Map<Integer, String> quotes = new HashMap() {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of quoteResource
     */
    public quoteResource() {
    }

    /**
     * Retrieves representation of an instance of rest.quoteResource
     *
     * @return an instance of java.util.HashMap
     */
    @GET
    @Produces("application/json")
    @Path("{id}")
    public String getJson(@PathParam("id") int id) {
        JsonObject obj = new JsonObject();
        obj.addProperty("quote", quotes.get(id));
        
        return gson.toJson(obj);
    }

    /**
     * PUT method for updating or creating an instance of quoteResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(HashMap content) {
    }
    
    @POST
    @Consumes("application/json")    
    @Produces("application/json")
    public String addQuote(String text){
        JsonObject obj = jsonParser.parse(text).getAsJsonObject();
        String quote = obj.get("quote").getAsString();
        
        int id = quotes.size() + 1;
        quotes.put(id, quote);
        
        JsonObject response = new JsonObject();
        response.addProperty("id", id);
        response.addProperty("quote", quote);
        
        return gson.toJson(response);
    }
}
