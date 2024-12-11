@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationAndAuthorizationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAccessWithoutAuthentication() throws Exception {
        // Attempt to access a protected endpoint without authentication
        mockMvc.perform(get("/api/user/profile"))
                .andExpect(status().isUnauthorized());  // Expecting 401 Unauthorized
    }

    @Test
    void testAccessWithAuthentication() throws Exception {
        // Simulating a request with an authenticated user (using JWT or session authentication)
        String token = "Bearer valid-jwt-token";  // Replace with a valid JWT token

        mockMvc.perform(get("/api/user/profile")
                .header("Authorization", token))
                .andExpect(status().isOk());  // Expecting 200 OK if the user is authenticated
    }

    @Test
    void testRoleBasedAccessControl() throws Exception {
        // Simulating a request with a user who does not have the required role
        String token = "Bearer user-jwt-token";  // Simulated user JWT token

        mockMvc.perform(get("/api/admin/dashboard")
                .header("Authorization", token))
                .andExpect(status().isForbidden());  // Expecting 403 Forbidden due to insufficient role
    }
}
