@SpringBootTest
@AutoConfigureMockMvc
public class XssProtectionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testXssPrevention() throws Exception {
        // Simulating a user input with a malicious script
        String maliciousScript = "<script>alert('XSS')</script>";

        mockMvc.perform(post("/api/user/comment")
                .param("content", maliciousScript))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("&lt;script&gt;alert('XSS')&lt;/script&gt;"));  // Ensure the script is sanitized
    }
}
