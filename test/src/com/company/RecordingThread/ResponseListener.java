package com.company.RecordingThread;

import com.darkprograms.speech.recognizer.GoogleResponse;

/**
 * Created by user on 21.11.16.
 */
public interface ResponseListener {
    void onResponce(GoogleResponse response);
}
