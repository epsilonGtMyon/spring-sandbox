package com.example.mfasandbox.app.common.security;

import java.util.Objects;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.mfasandbox.app.common.security.users.MfaUserDetails;

public class MfaDaoAuthenticationProvider extends DaoAuthenticationProvider {

	@Override
	@SuppressWarnings("deprecation")
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		super.additionalAuthenticationChecks(userDetails, authentication);
		
		// 通常のusername/passwordのチェックに加えて認証コードのチェックを行う


		// 型の条件 チェック
		if (!(userDetails instanceof MfaUserDetails)) {
			return;
		}
		if (!(authentication instanceof MfaAuthenticationToken)) {
			return;
		}
		

		final MfaUserDetails mfaUserDetails = (MfaUserDetails) userDetails;
		final MfaAuthenticationToken mfaAuthentication = (MfaAuthenticationToken) authentication;

		if (!mfaUserDetails.isMfaRequired()) {
			// このユーザーはチェック不要
			return;
		}

		final String authCode = mfaAuthentication.getAuthCode();
		if (authCode == null || authCode.isEmpty()) {
			// 入力されてないときは認証コードを入力してほしいので専用の例外
			throw new MfaRequiredException("mfa required");
		}
		
		
		if(!Objects.equals(authCode, mfaUserDetails.getMfaAuthCode())) {
			// 認証コードが異なるときはログイン失敗
			// 実際はキーからトークン生成とかして検証することもあるだろう
			throw new BadCredentialsException("mfa failed");
		}

	}
}
