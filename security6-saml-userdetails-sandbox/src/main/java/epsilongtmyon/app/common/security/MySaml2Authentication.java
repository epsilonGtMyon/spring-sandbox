package epsilongtmyon.app.common.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.saml2.provider.service.authentication.Saml2Authentication;

public class MySaml2Authentication extends AbstractAuthenticationToken {

	private final MyUserDetails userDetails;

	private final Saml2Authentication authentication;

	public MySaml2Authentication(
			MyUserDetails userDetails,
			Saml2Authentication authentication) {
		super(concat(userDetails.getAuthorities(), authentication.getAuthorities()));
		this.userDetails = userDetails;
		this.authentication = authentication;
		setAuthenticated(true);
	}

	private static List<GrantedAuthority> concat(Collection<? extends GrantedAuthority> c1,
			Collection<GrantedAuthority> c2) {
		return Stream.concat(c1.stream(), c2.stream()).toList();
	}

	@Override
	public Object getCredentials() {
		return this.authentication.getSaml2Response();
	}

	@Override
	public Object getPrincipal() {
		return userDetails;
	}

	public MyUserDetails getUserDetails() {
		return userDetails;
	}

	public Saml2Authentication getAuthentication() {
		return authentication;
	}

}
