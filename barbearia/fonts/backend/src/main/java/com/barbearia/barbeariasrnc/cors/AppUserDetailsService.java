package com.barbearia.barbeariasrnc.cors;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.CorsFilter;

import com.barbearia.barbeariasrnc.model.Funcionario;
import com.barbearia.barbeariasrnc.repository.FuncionarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private FuncionarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Funcionario> usuarioOptional = repository.findByNmUsuario(email);
		Funcionario usuario = usuarioOptional
				.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos!"));
		return new User(usuario.getNmFuncionario(), usuario.getDsSenha(), getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Funcionario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add( new SimpleGrantedAuthority("ALL".toUpperCase()));
		return authorities;
	}

}
