package com.example.likeofimdb

import android.animation.ValueAnimator
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.likeofimdb.PlayerController.PlayerController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.videoplayer_ui.view.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val mutableList : MutableList<String> = arrayListOf("mw1","mw2","mw3")
        var customPlayerUi:View
        var recyclerView=findViewById<RecyclerView>(R.id.recyclerview);
        var youTubePlayerView=findViewById<YouTubePlayerView>(R.id.youtube_player)
        lifecycle.addObserver(youTubePlayerView)

        customPlayerUi=youTubePlayerView.inflateCustomPlayerUi(R.layout.videoplayer_ui)
        youTubePlayerView.addYouTubePlayerListener(object :AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                var playerController = PlayerController(this@MainActivity,customPlayerUi,youTubePlayer,youTubePlayerView)
                youTubePlayer.addListener(playerController)
                youTubePlayer.cueVideo("l-eMi1xJ2dM",0f)

                playerController.playStopButton.setOnClickListener{
                    if(playerController.playerTracker.state == PlayerConstants.PlayerState.PLAYING) {
                        youTubePlayer.pause()
                        playerController.playStopButton.setImageDrawable(playerController.playDrawable)
                    }
                    else {
                        youTubePlayer.play()
                        playerController.playStopButton.setImageDrawable(playerController.pauseDrawable)

                    }
                }
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        var adapter = PhotoRecycler(applicationContext,mutableList);
        recyclerView.adapter=adapter



    }
}
