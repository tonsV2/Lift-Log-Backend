package dk.fitfit.liftlog.security;

import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
	private final UserServiceInterface userService;

	@Autowired
	public TokenAuthenticationProvider(UserServiceInterface userService) {
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication.isAuthenticated()) {
			return authentication;
		}
		String token = authentication.getCredentials().toString();
		User user = findUserByToken(token);
		if (user != null) {
			return new PreAuthenticatedAuthenticationToken(user, token);
		} else {
			throw new BadCredentialsException("Invalid token " + token);
		}
	}

	private User findUserByToken(String token) {
		return userService.findByToken(token);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return TokenAuthentication.class.isAssignableFrom(authentication);
	}
}
