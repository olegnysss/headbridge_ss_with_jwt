import com.ryvkin.ss.demo.config.WebSecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebSecurityConfig.class)
@ContextConfiguration(classes = WebSecurityConfig.class)
public class SpringSecurityTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @WithMockUser()
    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        mockMvc.perform(get("/resource/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

/*    @Test
    void testSuccessfulLogin() throws Exception {
        mockMvc.perform(formLogin().user("username").password("password"))
            .andExpect(status().isOk())
            .andExpect(authenticated());
    }

    @Test
    void testFailedLogin() throws Exception {
        mockMvc.perform(formLogin().user("wrongUsername").password("wrongPassword"))
            .andExpect(status().is4xxClientError())
            .andExpect(unauthenticated());
    }*/

/*    @Test
    @DisplayName("Login with Right Role Test")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testLoginWithRightRole() throws Exception {
        mockMvc.perform(get("/admin"))
            .andExpect(status().isOk())
            .andExpect(authenticated());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    @DisplayName("Login with Bad Role Test")
    void testLoginWithBadRole() throws Exception {
        mockMvc.perform(get("/admin"))
            .andExpect(status().isForbidden())
            .andExpect(unauthenticated());
    }*/
}