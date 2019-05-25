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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.embedded.ClaimsEmbeddedIntrospectionAuthenticationBuilder;

/**
 * @author Jérôme Wacongne &lt;ch4mp#64;c4-soft.com&gt;
 *
 */
public class IntrospectionAuthenticationTest {
	ClaimsEmbeddedIntrospectionAuthenticationBuilder<?> auth;

	@Before
	public void setUp() {
		auth = IntrospectionAuthentication.builder().claimSet(claims -> claims.subject("test").authorities("UNIT", "TEST"));
	}

	@Test
	public void nameIsUsernameClaimIfNonEmpty() {
		final IntrospectionAuthentication actual = auth.claimSet(claims -> claims.username("ch4mpy").build()).build();
		assertThat(actual.getName()).isEqualTo("ch4mpy");
		assertThat(actual.getClaims().getSubject()).isEqualTo("test");
	}

	@Test
	public void nameIsSubjectClaimIfUsernameEmpty() {
		final IntrospectionAuthentication actual = auth.build();
		assertThat(actual.getName()).isEqualTo("test");
	}

	@Test
	public void authoritiesWithDefaultConverterAreScopes() {
		final IntrospectionAuthentication actual = auth.build();
		assertThat(actual.getAuthorities()).containsExactlyInAnyOrder(new SimpleGrantedAuthority("UNIT"), new SimpleGrantedAuthority("TEST"));
	}

}
