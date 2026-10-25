package org.example.companies;

import java.net.URI;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Route an HTTP method and URI to an existing endpoint, or return empty if
 * no endpoint is registered for that method and path.
 *
 * <p>Supported routes:
 * <pre>
 * GET, POST           /networks                           getNetworks, addNetwork
 * GET, DELETE         /networks/{id}                      getNetwork, deleteNetwork
 * GET, POST           /networks/{id}/devices              getDevices, addDevice
 * GET, PATCH, DELETE  /networks/{id}/devices/{deviceId}   getDevice, patchDevice, deleteDevice
 * GET                 /checks                             getChecks
 * GET                 /checks/{id}                        getCheck
 * </pre>
 *
 * <p>Build a path trie with a child for each literal(paths) segment and a parameter
 * child for segments such as {@code {id}}. Each terminal node stores its
 * method-to-endpoint mappings. Literal children take precedence over parameter
 * children. Query strings do not affect matching; trailing slashes do not match.
 *
 * <p>Dry run for {@code GET /networks/1/devices/nyc-rtr01?verbose=true}:
 * <pre>
 * root
 * +-- networks                         [GET: getNetworks, POST: addNetwork]
 * |   +-- {id}                         [GET: getNetwork, DELETE: deleteNetwork]
 * |       +-- devices                  [GET: getDevices, POST: addDevice]
 * |           +-- {deviceId}           [GET: getDevice, PATCH: patchDevice,
 * |                                      DELETE: deleteDevice]
 * +-- checks                           [GET: getChecks]
 *     +-- {id}                         [GET: getCheck]
 * </pre>
 * Follow {@code networks -> {id} (1) -> devices -> {deviceId} (nyc-rtr01)}.
 * The final node returns {@code getDevice} for GET, but no endpoint for POST.
 * Lookup takes time proportional to the number of path segments.
 */
public class ForwardNetworksRound3 {
    public enum HttpMethod {
        GET, POST, PUT, PATCH, DELETE
    }

    public interface Endpoint {
        void handleRequest(Map<String, Object> parameters, byte[] requestBody);
    }

    public interface Router {
        Optional<Endpoint> route(HttpMethod method, URI uri);
    }

    // The original exercise supplies endpoint implementations. These local
    // placeholders preserve their identities without inventing request-handling logic.
    private abstract static class EndpointPlaceholder implements Endpoint {
        @Override
        public void handleRequest(Map<String, Object> parameters, byte[] requestBody) {
            throw new UnsupportedOperationException(getClass().getSimpleName() + " is not implemented");
        }
    }

    private static final class GetNetworks extends EndpointPlaceholder {}
    private static final class AddNetwork extends EndpointPlaceholder {}
    private static final class GetNetwork extends EndpointPlaceholder {}
    private static final class DeleteNetwork extends EndpointPlaceholder {}
    private static final class GetDevices extends EndpointPlaceholder {}
    private static final class AddDevice extends EndpointPlaceholder {}
    private static final class GetDevice extends EndpointPlaceholder {}
    private static final class PatchDevice extends EndpointPlaceholder {}
    private static final class DeleteDevice extends EndpointPlaceholder {}
    private static final class GetChecks extends EndpointPlaceholder {}
    private static final class GetCheck extends EndpointPlaceholder {}

    public static class RestApiRouter implements Router {
        private final Endpoint getNetworks = new GetNetworks();
        private final Endpoint addNetwork = new AddNetwork();
        private final Endpoint getNetwork = new GetNetwork();
        private final Endpoint deleteNetwork = new DeleteNetwork();
        private final Endpoint getDevices = new GetDevices();    // /networks/{id}/devices
        private final Endpoint addDevice = new AddDevice();
        private final Endpoint getDevice = new GetDevice();
        private final Endpoint patchDevice = new PatchDevice();
        private final Endpoint deleteDevice = new DeleteDevice();
        private final Endpoint getChecks = new GetChecks();
        private final Endpoint getCheck = new GetCheck();

        private static final class Node {
            final Map<String, Node> literals = new HashMap<>(); // literals = paths, literal path segment to child node
            Node parameter;
            final Map<HttpMethod, Endpoint> endpoints = new EnumMap<>(HttpMethod.class);
        }

        private final Node root = new Node();

        public RestApiRouter() {
            register(HttpMethod.GET, "/networks", getNetworks);
            register(HttpMethod.POST, "/networks", addNetwork);
            register(HttpMethod.GET, "/networks/{id}", getNetwork);
            register(HttpMethod.DELETE, "/networks/{id}", deleteNetwork);

            register(HttpMethod.GET, "/networks/{id}/devices", getDevices);
            register(HttpMethod.POST, "/networks/{id}/devices", addDevice);
            register(HttpMethod.GET, "/networks/{id}/devices/{deviceId}", getDevice);
            register(HttpMethod.PATCH, "/networks/{id}/devices/{deviceId}", patchDevice);
            register(HttpMethod.DELETE, "/networks/{id}/devices/{deviceId}", deleteDevice);

            register(HttpMethod.GET, "/checks", getChecks);
            register(HttpMethod.GET, "/checks/{id}", getCheck);
        }

        private void register(HttpMethod method, String pattern, Endpoint endpoint) {
            Node node = root;

            for (String segment : pattern.substring(1).split("/")) {
                if (segment.startsWith("{") && segment.endsWith("}")) {
                    if (node.parameter == null) {
                        node.parameter = new Node();
                    }
                    node = node.parameter;
                } else {
                    node = node.literals.computeIfAbsent(segment, ignored -> new Node());
                }
            }

            node.endpoints.put(method, endpoint);

            // no need to check for duplicates since the exercise guarantees no duplicate routes
//            if (node.endpoints.putIfAbsent(method, endpoint) != null) {
//                throw new IllegalArgumentException("Duplicate route: " + method + " " + pattern);
//            }
        }

        @Override
        public Optional<Endpoint> route(HttpMethod method, URI uri) {
            String path = uri.getRawPath();
            if (path == null || !path.startsWith("/") || path.length() <= 1) {
                return Optional.empty();
            }

            Node node = root;
            for (String segment : path.substring(1).split("/", -1)) {
                if (segment.isEmpty()) {
                    return Optional.empty();
                }

                Node literal = node.literals.get(segment);
                node = literal != null ? literal : node.parameter;

                if (node == null) {
                    return Optional.empty();
                }
            }

            return Optional.ofNullable(node.endpoints.get(method));
        }
    }

    public static class Main {
        public static void main(String[] args) {
            Router router = new RestApiRouter();
            Optional<Endpoint> match = router.route(
                    HttpMethod.GET, URI.create("/networks/1/devices/nyc-rtr01?verbose=true"));
            Optional<Endpoint> missing = router.route(
                    HttpMethod.POST, URI.create("/networks/1/devices/nyc-rtr01"));

            if (match.isEmpty() || !(match.get() instanceof GetDevice) || missing.isPresent()) {
                throw new AssertionError("Router did not match the example requests");
            }
        }
    }
}
