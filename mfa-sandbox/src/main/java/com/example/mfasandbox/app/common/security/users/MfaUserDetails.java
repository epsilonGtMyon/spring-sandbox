package com.example.mfasandbox.app.common.security.users;

import org.springframework.security.core.userdetails.UserDetails;

public interface MfaUserDetails extends UserDetails {

	// TOTPだと生成のキーが入ってたりするので名称は検討
	/**
	 * 多要素認証のコードを取得する
	 * 
	 * @return
	 */
	String getMfaAuthCode();

	
	/**
	 * 多要素認証が必要か
	 * @return
	 */
	default boolean isMfaRequired() {
		final String mfaAuthCode = getMfaAuthCode();
		if (mfaAuthCode == null || mfaAuthCode.isEmpty()) {
			return false;
		}
		return true;
	}
}
