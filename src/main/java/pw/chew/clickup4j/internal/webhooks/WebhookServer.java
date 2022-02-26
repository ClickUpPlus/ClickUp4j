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

package pw.chew.clickup4j.internal.webhooks;

import io.javalin.Javalin;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class WebhookServer {
    private final Javalin app;
    private int port = 4545;
    private boolean isRunning = false;
    private String secret = null;

    public WebhookServer() {
        app = Javalin.create();
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void start() {
        isRunning = true;
        if (secret == null)
            throw new IllegalStateException("Secret must be set before starting the server");

        app.start(port);
        app.post("/webhook", ctx -> {
            String signature = ctx.header("X-Signature");

            if (signature == null) {
                ctx.status(401);
                ctx.result("Unauthorized");
                return;
            }

            String body = ctx.body();

            // Create an HMAC SHA-256 using the secret and the body as the input data and compare the output to the signature
            // If the signature matches, the request is valid and the body can be processed
            String key = this.secret;

            Mac hasher = Mac.getInstance("HmacSHA256");
            hasher.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));

            byte[] hash = hasher.doFinal(body.getBytes());

            // to lowercase hexits
            String hex = bytesToHex(hash);

            if (signature.equalsIgnoreCase(hex)) {
                ctx.status(200);
                ctx.result("Hello World");
            } else {
                ctx.status(401);
                ctx.result("Unauthorized");
            }
        });
    }

    public void stop() {
        if (!isRunning) {
            throw new IllegalStateException("Server is not running");
        }

        isRunning = false;
        app.close();
    }

    public boolean isRunning() {
        return isRunning;
    }

    String bytesToHex(byte[] bytes) {
        final char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0, v; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
