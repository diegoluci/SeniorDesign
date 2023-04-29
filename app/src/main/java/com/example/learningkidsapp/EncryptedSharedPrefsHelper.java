package com.example.learningkidsapp;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class EncryptedSharedPrefsHelper {
    private static final String SHARED_PREFS_FILE_NAME = "com.example.learningkidsapp.SHARED_PREFS";
    private static final String AUTH_TOKEN_KEY = "auth_token";

    private SharedPreferences encryptedSharedPreferences;

    public EncryptedSharedPrefsHelper(Context context) {
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            encryptedSharedPreferences = EncryptedSharedPreferences.create(
                    context,
                    SHARED_PREFS_FILE_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isLoggedIn() {
        return encryptedSharedPreferences.contains(AUTH_TOKEN_KEY);
    }

    public void saveAuthToken(String authToken) {
        encryptedSharedPreferences.edit().putString(AUTH_TOKEN_KEY, authToken).apply();
    }

    public String getAuthToken() {
        return encryptedSharedPreferences.getString(AUTH_TOKEN_KEY, null);
    }

    public void removeAuthToken() {
        encryptedSharedPreferences.edit().remove(AUTH_TOKEN_KEY).apply();
    }
}
