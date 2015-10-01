package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class QuoteNotFoundExceptionMapper implements ExceptionMapper<QuoteNotFoundException> {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    ServletContext context;

    @Override
    public Response toResponse(QuoteNotFoundException ex) {
        boolean isDebug;
        int code = ex.getCode();
        if(context == null)
            isDebug = false;
        else
            isDebug = context.getInitParameter("debug").equals("true");
        ErrorMessage err = new ErrorMessage(ex, code, isDebug);
        return Response.status(code)
                .entity(gson.toJson(err))
                .type(MediaType.APPLICATION_JSON).
                build();
    }

}
