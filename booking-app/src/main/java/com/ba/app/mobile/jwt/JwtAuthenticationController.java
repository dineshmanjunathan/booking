package com.ba.app.mobile.jwt;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ba.app.entity.User;
import com.ba.app.mobile.util.BookAppResponse;
import com.ba.app.mobile.util.CommonUtil;
import com.ba.app.model.UserRepository;

@RestController
@CrossOrigin
public class JwtAuthenticationController {



	@Autowired
	private JwtTokenUtil jwtTokenUtil;



	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public BookAppResponse createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {
		return authenticate(authenticationRequest.getUserId(), authenticationRequest.getPassword());
	}

	private BookAppResponse authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			User user = userRepository.findByUserIdIgnoreCaseAndPassword(username, password);

			if (Objects.isNull(user)) {
				return CommonUtil.errorResponse(null, "INVALID_CREDENTIALS");

			}
			else
			{
				final String token = jwtTokenUtil.generateToken(user);
				user.setToken(token);
				user.setPassword(null);

				return CommonUtil.successResponse(user, "Login Successfully");
			}

		} catch (Exception e) {
			return CommonUtil.errorResponse(null, "INVALID_CREDENTIALS");

		}
	}
}
