package com.barbearia.barbeariasrnc.cors;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
import com.barbearia.barbeariasrnc.security.Encriptador;
import com.barbearia.barbeariasrnc.security.GeradorChaves;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private FuncionarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Funcionario> usuarioOptional = repository.findByNmUsuario(email);
		Funcionario usuario = usuarioOptional
				.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos!"));
		try {
			String senha = Encriptador.encripta(usuario.getDsSenha(), GeradorChaves.getInstance().getChavePrivada());
			return new User(usuario.getNmFuncionario(), senha, getPermissoes(usuario));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Funcionario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add( new SimpleGrantedAuthority("ALL".toUpperCase()));
		return authorities;
	}

}
