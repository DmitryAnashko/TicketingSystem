package com.example.apigateway.route;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.*;

import java.net.URISyntaxException;

@Configuration
public class InventoryServiceRoutes {
    //defined path and where to forward it. forwardWithPathVariable handles path variable
    @Bean
    public RouterFunction<ServerResponse> inventoryRoutes(){
        return GatewayRouterFunctions.route("inventory-service")
                .route(RequestPredicates.path("/api/v1/inventory/venue/{venueId}"),
                        request -> forwardWithPathVariable(request, "venueId", "http://localhost:8080/api/v1/inventory/venue/"))

                .route(RequestPredicates.path("api/v1/inventory/event/{eventId}"),
                        request -> forwardWithPathVariable(request, "eventId", "http://localhost:8080/api/v1/inventory/event/"))
                .build();
    }
    //get path variable from Server Request and then we create HandlerFunction
    private static ServerResponse forwardWithPathVariable(ServerRequest request, String pathVariable, String baseUrl) throws Exception {
        String value = request.pathVariable(pathVariable);
        return HandlerFunctions.http(baseUrl + value).handle(request);
    }
}
