package security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.dad.UserRepository;
import com.dad.Usuario;

@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserRepository usuarioRepositorio;
	
	//@Override
	public Authentication autenticate(Authentication auth) throws AuthenticationException{
		Usuario usuario = (Usuario) usuarioRepositorio.findByLogin(auth.getName());
		
		if (usuario==null) {
			throw new BadCredentialsException("User not found");
		}
		
		String password = (String) auth.getCredentials();
		if(!new BCryptPasswordEncoder().matches(password, usuario.getPasswordHash())) {
			throw new BadCredentialsException("Wrong password");
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		for (String role : usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role));
		}
		
		return new UsernamePasswordAuthenticationToken(usuario.getLogin(), password, roles);
	}

	@Override
	public Authentication authenticate(Authentication arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
