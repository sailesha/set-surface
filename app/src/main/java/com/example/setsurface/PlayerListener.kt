package com.example.setsurface

import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.analytics.AnalyticsListener

class PlayerListener : AnalyticsListener {
    companion object {
        public var lastPlaybackException: PlaybackException? = null
            private set
    }


    override fun onPlayerError(eventTime: AnalyticsListener.EventTime, error: PlaybackException) {
        android.util.Log.i("SetSurface", "PlayerListener.onPlayerError, error: $error, type: ${error.errorCode}")
        lastPlaybackException = error
    }
}