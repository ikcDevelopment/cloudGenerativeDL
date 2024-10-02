/**
 * Copyright [2024] [Juan Estuardo Wyss Rosito] [estuardo.wyss@yahoo.com  estuardo.wyss@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wyss.generative.ms.learning;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter {
    private static final String ALLOWED_ORIGIN = "http://localhost:8080, http://localhost:4200";
    private static final String ALLOWED_HEADERS = "origin, content-type, accept, authorization";
    private static final String ALLOWED_METHODS = "GET, POST, PUT, DELETE, OPTIONS, HEAD";

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext responseContext) throws IOException {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        // Check if the request origin is allowed
        String origin = containerRequestContext.getHeaderString("Origin");

        if (isOriginAllowed(origin)) {
            headers.add("Access-Control-Allow-Origin", origin);
        }
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
        headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
    }

    private boolean isOriginAllowed(String origin) {
        return origin != null && ALLOWED_ORIGIN.contains(origin);
    }
}
