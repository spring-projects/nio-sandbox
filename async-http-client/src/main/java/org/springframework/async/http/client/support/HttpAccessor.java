/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.async.http.client.support;

import java.io.IOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.async.http.client.ClientHttpRequest;
import org.springframework.async.http.client.ClientHttpRequestFactory;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

/**
 * @author Jon Brisbin <jon@jbrisbin.com>
 */
public abstract class HttpAccessor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private ClientHttpRequestFactory requestFactory;

	/**
	 * Set the request factory that this accessor uses for obtaining {@link ClientHttpRequest HttpRequests}.
	 */
	public void setRequestFactory(ClientHttpRequestFactory requestFactory) {
		Assert.notNull(requestFactory, "'requestFactory' must not be null");
		this.requestFactory = requestFactory;
	}

	/**
	 * Return the request factory that this accessor uses for obtaining {@link ClientHttpRequest HttpRequests}.
	 */
	public ClientHttpRequestFactory getRequestFactory() {
		return this.requestFactory;
	}

	/**
	 * Create a new {@link ClientHttpRequest} via this template's {@link ClientHttpRequestFactory}.
	 *
	 * @param url		the URL to connect to
	 * @param method the HTTP method to exectute (GET, POST, etc.)
	 * @return the created request
	 * @throws IOException in case of I/O errors
	 */
	protected ClientHttpRequest createRequest(URI url, HttpMethod method) throws IOException {
		ClientHttpRequest request = getRequestFactory().createRequest(url, method);
		if (logger.isDebugEnabled()) {
			logger.debug("Created " + method.name() + " request for \"" + url + "\"");
		}
		return request;
	}

}
