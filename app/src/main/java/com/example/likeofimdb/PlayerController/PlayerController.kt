package com.example.likeofimdb.PlayerController

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import com.example.likeofimdb.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker

class PlayerController  (context: Context, customView:View, youTubePlayer: YouTubePlayer,youTubePlayerView: YouTubePlayerView ) : AbstractYouTubePlayerListener(){
    val playerUi:View
    val panel:View
    val context:Context
    val youTubePlayer:YouTubePlayer
    val youTubePlayerView:YouTubePlayerView
    val playerTracker:YouTubePlayerTracker
    val playStopButton:ImageButton
    val forwardButton:ImageButton
    val backwardButton:ImageButton
    val bigPlayButton:ImageButton
    val playDrawable:Drawable
    val pauseDrawable:Drawable
    var isPlaying:Boolean=false
    init {
        this.playerUi=customView
        this.context=context
        this.youTubePlayer=youTubePlayer
        this.youTubePlayerView=youTubePlayerView
        playerTracker= YouTubePlayerTracker()
        youTubePlayer.addListener(playerTracker)
        this.panel=customView.findViewById(R.id.panel)
        playStopButton=customView.findViewById<ImageButton>(R.id.stop_play)
        forwardButton=customView.findViewById(R.id.forward)
        backwardButton=customView.findViewById(R.id.backward)
        bigPlayButton=customView.findViewById(R.id.big_play)
        playDrawable=this.context.resources.getDrawable(R.drawable.ic_play)
        pauseDrawable=this.context.resources.getDrawable(R.drawable.ic_pause)
        
    }

    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
        if(state == PlayerConstants.PlayerState.PLAYING || state==PlayerConstants.PlayerState.PAUSED) {
            playStopButton.visibility = View.VISIBLE
            forwardButton.visibility = View.VISIBLE
            backwardButton.visibility = View.VISIBLE
            bigPlayButton.visibility = View.GONE
            panel.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        }
        else {
            if (state == PlayerConstants.PlayerState.BUFFERING || state == PlayerConstants.PlayerState.VIDEO_CUED){
                playStopButton.visibility = View.GONE
                forwardButton.visibility = View.GONE
                backwardButton.visibility = View.GONE
                bigPlayButton.visibility = View.VISIBLE
                panel.setBackgroundResource(R.drawable.warfare)
            }
        }
    }


}