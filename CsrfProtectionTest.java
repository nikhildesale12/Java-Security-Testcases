@SpringBootTest
@AutoConfigureMockMvc
public class CsrfProtectionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    void testCsrfProtection() throws Exception {
        // Send a request without CSRF token (this should fail)
        mockMvc.perform(post("/api/user/change-password")
                .param("newPassword", "newSecurePassword"))
                .andExpect(status().isForbidden());  // Expecting 403 Forbidden due to missing CSRF token
    }

    @Test
    void testCsrfWithValidToken() throws Exception {
        // Valid CSRF token is usually set by the application in a cookie or a header
        mockMvc.perform(post("/api/user/change-password")
                .param("newPassword", "newSecurePassword")
                .header("X-CSRF-TOKEN", "valid-csrf-token"))  // Assuming you can extract a valid token
                .andExpect(status().isOk());  // Expecting success with valid CSRF token
    }
}
