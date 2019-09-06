package com.example.likeofimdb.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.likeofimdb.InspectFilm
import com.example.likeofimdb.R
import com.example.likeofimdb.models.Film
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

class FilmRecycler (context: Context, filmList: MutableList<Film>) : RecyclerView.Adapter<FilmRecycler.FilmViewHolder>(){
    var filmList:List<Film>
    var context: Context
    internal var layoutInflater: LayoutInflater

    init {
        this.context=context
        this.filmList=filmList
        layoutInflater= LayoutInflater.from(context);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        var view = layoutInflater.inflate(R.layout.item_film,parent,false)
        return FilmViewHolder(view);
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        Picasso.get().load(filmList[position].profileUrl).into(holder.image)
        holder.clickArea.setOnClickListener{
            context.startActivity(Intent(context,InspectFilm::class.java).putExtra("film",filmList[position]))
        }
    }

    class FilmViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var image: RoundedImageView
        internal var clickArea : RelativeLayout
        init {
            this.image=itemView.findViewById(R.id.img_profile) as RoundedImageView
            this.clickArea = itemView.findViewById(R.id.rl_clickArea) as RelativeLayout
        }
    }
}