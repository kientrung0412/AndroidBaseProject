package com.omt.app.baseproject.utils;

import android.content.Context;

import androidx.annotation.RawRes;

import com.omt.app.baseproject.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringUtils {

    public static String getStringFromRaw(Context context, @RawRes int raw) {
        InputStream inputStream = context.getResources().openRawResource(raw);
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textBuilder.toString();
    }

}
