package com.example.setsurface

import android.content.Context
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer

class PlayerCache(
    private val context: Context
) {
    private val playerPool = mutableListOf<ExoPlayer>()
    private var playerCount = 0

    fun getPlayer(): ExoPlayer {
        if (playerPool.isEmpty()) {
            return createPlayer()
        }
        return playerPool.removeLast()
    }

    fun releasePlayer(player: ExoPlayer) {
        player.setVideoTextureView(null)
        playerPool.add(player)
    }

    private fun createPlayer(): ExoPlayer {
        playerCount++
        android.util.Log.i("SetSurface", "createPlayer, playerCount: $playerCount")

        val renderersFactory = DefaultRenderersFactory(context)
        renderersFactory.forceDisableMediaCodecAsynchronousQueueing()
//        renderersFactory.forceEnableMediaCodecAsynchronousQueueing()
//        renderersFactory.experimentalSetSynchronizeCodecInteractionsWithQueueingEnabled(true)

        val player = ExoPlayer.Builder(context)
            .setRenderersFactory(renderersFactory)
            .build()
        player.addAnalyticsListener(PlayerListener())
        return player
    }
}