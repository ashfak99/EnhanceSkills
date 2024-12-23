package com.example.enhanceskills

import android.app.Application
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var mView: View = itemView
    private var exoPlayer: ExoPlayer? = null
    private var mExoPlayerView: PlayerView = itemView.findViewById(R.id.exoplayer_view)

    fun setVideo(ctx: Application, title: String, url: String) {
        val mTextView: TextView = mView.findViewById(R.id.Titletv)
        mTextView.text = title

        // URL validity check could be added if needed
        try {
            // Initialize ExoPlayer
            exoPlayer = ExoPlayer.Builder(ctx).build()
            val videoUri = Uri.parse(url)
            val mediaItem = MediaItem.fromUri(videoUri)

            // Assign ExoPlayer to PlayerView
            mExoPlayerView.player = exoPlayer
            exoPlayer?.setMediaItem(mediaItem)
            exoPlayer?.prepare()
            exoPlayer?.playWhenReady = false
        } catch (e: Exception) {
            Log.e("ViewHolder", "ExoPlayer error: ${e.message}")
        }
    }

    fun releasePlayer() {
        exoPlayer?.release()
        exoPlayer = null
    }
}
