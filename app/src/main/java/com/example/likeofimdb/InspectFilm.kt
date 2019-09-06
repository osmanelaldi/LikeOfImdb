package com.example.likeofimdb

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintSet
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.likeofimdb.PlayerController.PlayerController
import com.example.likeofimdb.adapters.PhotoRecycler
import com.example.likeofimdb.models.Film
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_inspect_film.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.constraintlayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class InspectFilm : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inspect_film)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val film = intent.getSerializableExtra("film") as Film

        var customPlayerUi: View
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerview);
        var youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player)
        lifecycle.addObserver(youTubePlayerView)

        Picasso.get().load(film.profileUrl).into(filmlogo)


                Picasso.get().load(film.posterUrl).into(object : com.squareup.picasso.Target{
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        GlobalScope.launch {
                            bitmap?.let {
                                Palette.from(it).generate(object : Palette.PaletteAsyncListener{
                                    override fun onGenerated(palette: Palette?) {
                                        palette?.darkVibrantSwatch?.let {
                                            nested_view.setBackgroundColor(it.rgb)
                                        }
                                    }
                                })
                            }
                        }

                    }
                })






        filmname.text = film.name
        category_hours.text = film.category + " - " + film.hour
        imdb.text = film.voteImdb + "\n IMDB"
        rottentomato.text = film.voteTomato + "\n Rotten Tomato"
        metacritic.text = film.voteMetacritic + "\n Metacritic"
        description.text = film.description



        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintlayout)
        customPlayerUi = youTubePlayerView.inflateCustomPlayerUi(R.layout.videoplayer_ui)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                var playerController =
                    PlayerController(
                        this@InspectFilm,
                        customPlayerUi,
                        youTubePlayer,
                        youTubePlayerView,
                        film.posterUrl!!
                    )
                youTubePlayer.addListener(playerController)
                film.videoUrl?.let {
                    youTubePlayer.cueVideo(it, 0f)
                }

                playerController.playStopButton.setOnClickListener {
                    if (playerController.playerTracker.state == PlayerConstants.PlayerState.PLAYING) {
                        youTubePlayer.pause()
                        playerController.playStopButton.setImageDrawable(playerController.playDrawable)
                    } else {
                        youTubePlayer.play()
                        TransitionManager.beginDelayedTransition(constraintlayout)
                        constraintSet.clear(R.id.filmlogo, ConstraintSet.BOTTOM)
                        constraintSet.applyTo(constraintlayout)
                        playerController.playStopButton.setImageDrawable(playerController.pauseDrawable)

                    }
                }
                playerController.bigPlayButton.setOnClickListener {
                    if (playerController.playerTracker.state == PlayerConstants.PlayerState.PLAYING) {
                        youTubePlayer.pause()
                        playerController.playStopButton.setImageDrawable(playerController.playDrawable)
                    } else {
                        youTubePlayer.play()
                        TransitionManager.beginDelayedTransition(constraintlayout)
                        constraintSet.clear(R.id.filmlogo, ConstraintSet.BOTTOM)
                        constraintSet.applyTo(constraintlayout)
                        playerController.playStopButton.setImageDrawable(playerController.pauseDrawable)

                    }
                }
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        var adapter = PhotoRecycler(applicationContext, film.ssUrlList)
        recyclerView.adapter = adapter
    }
}