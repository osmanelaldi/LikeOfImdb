package com.example.likeofimdb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.likeofimdb.R
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

class PhotoRecycler (context: Context,photoList: List<String>?) : RecyclerView.Adapter<PhotoRecycler.PhotoViewHolder>(){
    var photoList:List<String>?
    var context:Context
    internal var layoutInflater:LayoutInflater

    init {
        this.context=context;
        this.photoList=photoList;
        layoutInflater= LayoutInflater.from(context);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        var view = layoutInflater.inflate(R.layout.photos_card,parent,false)
        return PhotoViewHolder(view);
    }

    override fun getItemCount(): Int {
        return photoList!!.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        photoList?.let {
            Picasso.get().load(it[position]).into(holder.image)
        }
    }

    class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var image:RoundedImageView
        init {
            this.image=itemView.findViewById(R.id.cardphoto) as RoundedImageView
        }
    }
}