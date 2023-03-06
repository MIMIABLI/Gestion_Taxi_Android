package org.doranco.utils;

import android.content.Context;
import android.content.SharedPreferences;
import org.doranco.parcours.client.ESharedDatasRefs;

public class GetToken {

    private String token;
    private SharedPreferences sharedPreferences;
    Context context;

    public GetToken(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(ESharedDatasRefs.USER_SHARED_DATAS.name(), Context.MODE_PRIVATE);
        this.token = sharedPreferences.getString(ESharedDatasRefs.USER_SHARED_TOKEN.name(), "");
    }

    public String getToken() {
        return token;
    }
}
