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
package pw.chew.clickup4j.internal.requests;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

public class Requester<T> {
    private final OkHttpClient client;
    private final Request request;
    private final Function<String, T> handler;

    public Requester(OkHttpClient client, Request request, Function<String, T> handler) {
        this.client = client;
        this.request = request;
        this.handler = handler;
    }

    public void queue(Consumer<T> onSuccess, Consumer<Throwable> onFailure) {
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                // get the response as JSON
                try {
                    T result = handler.apply(response.body().string());
                    onSuccess.accept(result);
                } catch (Exception e) {
                    onFailure.accept(e);
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                onFailure.accept(e);
            }
        });
    }

    public T complete() {
        Call call = client.newCall(request);

        try(Response response = call.execute()) {
            return handler.apply(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
