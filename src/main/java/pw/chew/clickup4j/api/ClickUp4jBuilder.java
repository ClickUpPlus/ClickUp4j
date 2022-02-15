/*
 * Copyright 2022 Chew and Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pw.chew.clickup4j.api;

import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pw.chew.clickup4j.internal.ClickUp4jImpl;

/**
 * Used to create new {@link ClickUp4j} instances.
 *
 * <p>A single builder can be reused multiple times. Each call to {@link #build()} creates a new {@link ClickUp4j}
 * instance using the same information.
 */
public class ClickUp4jBuilder {
    protected OkHttpClient.Builder httpClientBuilder = null;
    protected OkHttpClient httpClient = null;
    protected String token;

    private ClickUp4jBuilder(@Nullable String token) {
        this.token = token;
    }

    /**
     * Creates a builder with the predefined token.
     *
     * @param token The bot token to use
     * @return The builder instance
     * @see #setToken(String)
     */
    @NotNull
    public static ClickUp4jBuilder create(@Nullable String token) {
        return new ClickUp4jBuilder(token);
    }

    /**
     * Sets the token that will be used by the {@link ClickUp4j} instance to log in when {@link #build()} is called.
     *
     * <ol>
     *     <li>Go to your <a href="https://app.clickup.com/:space_id/settings/apps">Apps</a></li>
     *     <li>Locate "API Token" at the top.</li>
     *     <li>Either generate it, or use the current value.</li>
     * </ol>
     *
     * @param token The token of the account that you would like to use when accessing the API
     * @return This builder instance. Useful for chaining.
     */
    @NotNull
    public ClickUp4jBuilder setToken(@Nullable String token) {
        this.token = token;
        return this;
    }

    /**
     * Sets the {@link okhttp3.OkHttpClient.Builder Builder} that will be used by the requester.
     * <br>This can be used to set things such as connection timeout and proxy.
     *
     * @param builder The new {@link okhttp3.OkHttpClient.Builder Builder} to use
     * @return This builder instance. Useful for chaining.
     */
    @NotNull
    public ClickUp4jBuilder setHttpClientBuilder(@Nullable OkHttpClient.Builder builder) {
        this.httpClientBuilder = builder;
        return this;
    }

    /**
     * Sets the {@link okhttp3.OkHttpClient OkHttpClient} that will be used by the requester.
     * <br>This can be used to set things such as connection timeout and proxy.
     *
     * @param client The new {@link okhttp3.OkHttpClient OkHttpClient} to use
     * @return This builder instance. Useful for chaining.
     */
    @NotNull
    public ClickUp4jBuilder setHttpClient(@Nullable OkHttpClient client) {
        this.httpClient = client;
        return this;
    }

    /**
     * Builds a new {@link ClickUp4j} instance and uses the provided token.

     * @return A {@link ClickUp4j} instance.
     */
    @NotNull
    public ClickUp4j build() {
        OkHttpClient httpClient = this.httpClient;
        if (httpClient == null) {
            if (this.httpClientBuilder == null)
                this.httpClientBuilder = new OkHttpClient.Builder();
            httpClient = this.httpClientBuilder.build();
        }

        return new ClickUp4jImpl(this.token, httpClient);
    }
}
