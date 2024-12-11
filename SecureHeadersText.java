@SpringBootTest
@AutoConfigureMockMvc
public class SecureHeadersTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSecurityHeaders() throws Exception {
        mockMvc.perform(get("/api/user/profile"))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Content-Type-Options", "nosniff"))
                .andExpect(header().string("X-Frame-Options", "DENY"))
                .andExpect(header().string("Strict-Transport-Security", "max-age=31536000; includeSubDomains"));
    }
}
