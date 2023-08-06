import com.ryvkin.ss.demo.Application;
import com.ryvkin.ss.demo.controller.ResourceController;
import com.ryvkin.ss.demo.domain.model.ResourceObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
public class SpringSecurityTests {

    private final Authentication admin = new UsernamePasswordAuthenticationToken(
        "admin", "xxx",
        AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER")
    );

    private final Authentication user = new UsernamePasswordAuthenticationToken(
        "user", "xxx",
        AuthorityUtils.createAuthorityList("ROLE_USER")
    );

    @Autowired
    private ResourceController controller;

    @Test
    public void testSecuredMethodNotAuthenticated() {
        Assertions.assertThrows(AuthenticationCredentialsNotFoundException.class, () -> controller
            .createResourceObject(new ResourceObject()));
    }

    @Test
    public void testUserTriesToCreateResource() {
        SecurityContextHolder.getContext().setAuthentication(user);
        Assertions.assertThrows(AccessDeniedException.class, () -> controller
            .createResourceObject(new ResourceObject()));
    }

    @Test
    public void testAdminTriesToCreateResource() {
        SecurityContextHolder.getContext().setAuthentication(admin);
        controller.createResourceObject(new ResourceObject());
    }

    @Test
    public void testUserTriesToGetResource() {
        SecurityContextHolder.getContext().setAuthentication(user);
        controller.getResourceObject(1);
    }

    @Test
    public void testAdminTriesToGetResource() {
        SecurityContextHolder.getContext().setAuthentication(admin);
        controller.getResourceObject(1);
    }
}