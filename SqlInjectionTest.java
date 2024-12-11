@SpringBootTest
@AutoConfigureMockMvc
public class SqlInjectionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSqlInjectionPrevention() throws Exception {
        // Simulate a malicious SQL input
        String maliciousInput = "' OR '1'='1'; --";

        mockMvc.perform(post("/api/user/login")
                .param("username", maliciousInput)
                .param("password", maliciousInput))
                .andExpect(status().isBadRequest());  // Expecting a bad request status due to invalid input
    }
}
