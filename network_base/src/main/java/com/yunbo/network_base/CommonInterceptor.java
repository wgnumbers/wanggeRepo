/*
 * Copyright 2018 cxx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wangge.network_base;

import androidx.annotation.NonNull;
import android.text.TextUtils;

import com.wangge.app.tools.C;
import com.wangge.app.tools.SPUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Author: cxx
 * Date: 2018-07-10
 * GitHub: https://github.com/ccolorcat
 */
public class CommonInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request old = chain.request();
        final Request.Builder newBuilder = old.newBuilder();
        final List<NameAndValue> commonParameters = loadCommonParameters();
        if (!commonParameters.isEmpty()) {
            if (needBody(old.method())) {
                newBuilder.method(old.method(), newRequestBody(old, commonParameters));
            } else {
                newBuilder.url(newHttpUrl(old, commonParameters));
            }
        }
        final List<NameAndValue> commonHeaders = loadCommonHeaders();
        for (NameAndValue nameAndValue : commonHeaders) {
            if (TextUtils.isEmpty(old.header(nameAndValue.name))) {
                newBuilder.addHeader(nameAndValue.name, nameAndValue.value);
            }
        }
        final Response response = chain.proceed(newBuilder.build());
        save(response);
        return response;
    }

    private RequestBody newRequestBody(Request old, List<NameAndValue> parameters) {
        RequestBody oldBody = old.body();
        if (oldBody instanceof FormBody) {
            FormBody oldFormBody = (FormBody) oldBody;
            FormBody.Builder newBuilder = new FormBody.Builder();
            for (int i = 0, size = oldFormBody.size(); i < size; ++i) {
                newBuilder.addEncoded(oldFormBody.encodedName(i), oldFormBody.encodedValue(i));
            }
            for (NameAndValue nameAndValue : parameters) {
                newBuilder.add(nameAndValue.name, nameAndValue.value);
            }
            return newBuilder.build();
        }

        MultipartBody.Builder newBuilder = new MultipartBody.Builder();
        if (oldBody instanceof MultipartBody) {
            MultipartBody oldMultipartBody = (MultipartBody) oldBody;
            for (int i = 0, size = oldMultipartBody.size(); i < size; ++i) {
                newBuilder.addPart(oldMultipartBody.part(i));
            }
            MediaType oldType = oldMultipartBody.contentType();
            if (oldType != null) {
                newBuilder.setType(oldType);
            }
        } else if (oldBody != null) {
            newBuilder.addPart(oldBody);
        }
        for (NameAndValue nameAndValue : parameters) {
            newBuilder.addFormDataPart(nameAndValue.name, nameAndValue.value);
        }

        return newBuilder.build();
    }

    private HttpUrl newHttpUrl(Request old, List<NameAndValue> parameters) {
        HttpUrl.Builder urlBuilder = old.url().newBuilder();
        for (NameAndValue nameAndValue : parameters) {
            urlBuilder.addQueryParameter(nameAndValue.name, nameAndValue.value);
        }
        return urlBuilder.build();
    }

    protected void save(Response response) {
    }

    /**
     * 公共参数在此返回。
     */
    protected List<NameAndValue> loadCommonParameters() {
        return Collections.emptyList();
    }

    /**
     * 公共 Header 在此返回。
     */
    protected List<NameAndValue> loadCommonHeaders() {
        return Collections.singletonList(new NameAndValue("Authorization", SPUtils.getString(C.Constant.TOKEN)));
    }

    private static boolean needBody(String method) {
        return "POST".equalsIgnoreCase(method)
                || "DELETE".equalsIgnoreCase(method)
                || "PUT".equalsIgnoreCase(method)
                || "PATCH".equalsIgnoreCase(method);
    }
}
