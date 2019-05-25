/*
 * Copyright 2019 Jérôme Wacongne
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.oauth2.server.resource.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.c4soft.oauth2.rfc7519.JwtClaimSet;

/**
 * @author Jérôme Wacongne &lt;ch4mp#64;c4-soft.com&gt;
 *
 */
public class JwtAuthentication extends AbstractOAuth2Authentication<JwtClaimSet> {
	private static final long serialVersionUID = -8450928725079141394L;

	/**
	 * @param claims
	 * @param authorities
	 */
	protected JwtAuthentication(JwtClaimSet claims, Collection<GrantedAuthority> authorities) {
		super(claims, authorities);
	}

	public JwtAuthentication(JwtClaimSet claims, PrincipalGrantedAuthoritiesService authoritiesService) {
		this(claims, authoritiesService.getAuthorities(claims));
	}

	@Override
	public String getName() {
		return getClaims().getSubject();
	}
}
