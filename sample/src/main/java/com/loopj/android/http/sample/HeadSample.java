package com.loopj.android.http.sample;


import com.loopj.android.http.handlers.AsyncHttpResponseHandler;
import com.loopj.android.http.interfaces.IAsyncHttpClient;
import com.loopj.android.http.interfaces.IRequestHandle;
import com.loopj.android.http.interfaces.IResponseHandler;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.jetbrains.annotations.NotNull;

public class HeadSample extends SampleParentActivity {
    private static final String LOG_TAG = "HeadSample";

    @NotNull
    @Override
    public IRequestHandle executeSample(@NotNull IAsyncHttpClient client, @NotNull String URL, Header[] headers, HttpEntity entity, IResponseHandler responseHandler) {
        return client.head(this, URL, headers, null, responseHandler);
    }

    @Override
    public int getSampleTitle() {
        return R.string.title_head_sample;
    }

    @Override
    public boolean isRequestBodyAllowed() {
        return true;
    }

    @Override
    public boolean isRequestHeadersAllowed() {
        return true;
    }

    @NotNull
    @Override
    public String getDefaultURL() {
        return "http://httpbin.org/";
    }

    @Override
    public IResponseHandler getResponseHandler() {
        return new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                clearOutputs();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                debugHeaders(LOG_TAG, headers);
                debugStatusCode(LOG_TAG, statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                debugHeaders(LOG_TAG, headers);
                debugStatusCode(LOG_TAG, statusCode);
                debugThrowable(LOG_TAG, e);
                if (errorResponse != null) {
                    debugResponse(LOG_TAG, new String(errorResponse));
                }
            }
        };
    }

}