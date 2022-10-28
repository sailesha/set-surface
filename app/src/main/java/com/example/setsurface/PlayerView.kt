package com.example.setsurface

import android.content.Context
import android.util.AttributeSet
import android.view.TextureView
import android.widget.FrameLayout
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class PlayerView : FrameLayout {
    private var player: ExoPlayer? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : super(context, attrs, defStyleAttr) {
    }

    fun bind(playerCache: PlayerCache, url: String) {
        val oldPlayer = player
        oldPlayer?.setVideoTextureView(null)

        val newPlayer = playerCache.getPlayer()
        player = newPlayer

        var textureView = findViewById<TextureView?>(R.id.texture_view)
        if (textureView == null) {
            textureView = findViewById<TextureView>(R.id.texture_view)
        }
        newPlayer.setVideoTextureView(textureView)

        val mediaItem = MediaItem.fromUri(url)
        newPlayer.setMediaItem(mediaItem)
        newPlayer.repeatMode = ExoPlayer.REPEAT_MODE_ONE
        newPlayer.prepare()
        newPlayer.play()

        if (oldPlayer != null) {
            playerCache.releasePlayer(oldPlayer)
        }
    }
}