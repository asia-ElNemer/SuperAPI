import com.sun.org.apache.regexp.internal.RE;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import  org.slf4j.LoggerFactory;
/**
 * Created by asia on 11/7/16.
 */

@Path("/Operation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces (MediaType.APPLICATION_JSON)
public class Operations {

    private static final Logger LOG = LoggerFactory.getLogger(Operations.class);

    @POST
    @Path("/add")
    public Response add (@NotNull @Valid input input) {

        LOG.info("Adding numbers");
        if(input.hasNoNumbers()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return input.getNumbers().stream()

                .reduce((x, y) -> x + y)
                .map(sum -> Response.ok(sum).build())
                .get();

    }
}
