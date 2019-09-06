package com.example.likeofimdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.likeofimdb.PlayerController.PlayerController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.GridLayoutManager
import com.example.likeofimdb.adapters.FilmRecycler
import com.example.likeofimdb.adapters.PhotoRecycler
import com.example.likeofimdb.models.Film
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)


        rv_films.layoutManager = GridLayoutManager(this, 2)
        var list = ArrayList<Film>()
        var databaseReference = FirebaseDatabase.getInstance().reference.child("films")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach { eachfilm ->
                    list.add(eachfilm.getValue(Film::class.java)!!)
                }
                rv_films.adapter = FilmRecycler(this@MainActivity, list)
            }
        })

    }
}
