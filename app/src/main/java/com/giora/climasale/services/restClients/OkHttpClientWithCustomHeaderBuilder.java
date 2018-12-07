package com.giora.climasale.services.restClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpClientWithCustomHeaderBuilder {
	public static OkHttpClient build(final HashMap<String, String> customHeaderParameters) {
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		httpClient.addInterceptor(new Interceptor() {
			@Override
			public Response intercept(Interceptor.Chain chain) throws IOException {
				Request original = chain.request();

				Request.Builder requestBuilder = original.newBuilder();
				addHeaderParameters(requestBuilder, customHeaderParameters);
				Request request = requestBuilder
						.method(original.method(), original.body())
						.build();

				return chain.proceed(request);
			}
		});

		return httpClient.build();
	}

	private static void addHeaderParameters(Request.Builder requestBuilder, HashMap<String, String> customHeaderParameters) {
		for (Map.Entry<String, String> parameterEntry : customHeaderParameters.entrySet())
			requestBuilder.header(parameterEntry.getKey(), parameterEntry.getValue());
	}
}
