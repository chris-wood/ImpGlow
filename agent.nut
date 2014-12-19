// Default count for the lights
limit <- 500;

// HTTP request handler
function request_handler(request, response)
{
    try
    {
        if ("time" in request.query) {
            count <- request.query.time.tointeger();
        } else {
            count <- limit; // default to limit
        }

        device.send("pulse.start", count);
        response.send(200, "OK"); 
    }
    catch (ex)
    {
        response.send(500, ("Agent Error: " + ex)); // Send 500 response if error occured
    }
}

// Register the callback function that will be triggered by incoming HTTP requests
http.onrequest(request_handler);