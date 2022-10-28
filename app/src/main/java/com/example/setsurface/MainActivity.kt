package com.example.setsurface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.TextureView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class MainActivity : AppCompatActivity() {
    private lateinit var playerCache: PlayerCache
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playerCache = PlayerCache(applicationContext)
        Handler().postDelayed(this::onTimer, 1000)
    }

    private fun onTimer() {
        // Stop the timer if we see an error.
        if (PlayerListener.lastPlaybackException != null) {
            return
        }

        Handler().postDelayed(this::onTimer, 200)
        playNextVideo()
    }

    private fun playNextVideo() {
        bindPlayer(findViewById(R.id.player_view_1))
        bindPlayer(findViewById(R.id.player_view_2))
//        bindPlayer(findViewById(R.id.player_view_3))
    }

    private fun bindPlayer(playerView: PlayerView) {
        // Stop the timer if we see an error.
        if (PlayerListener.lastPlaybackException != null) {
            return
        }

        playerView.bind(playerCache, VideoUrls.URLS[currentIndex])
        currentIndex = (currentIndex + 1) % VideoUrls.URLS.size
    }
}