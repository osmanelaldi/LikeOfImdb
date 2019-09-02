package com.example.likeofimdb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView

class PhotoRecycler (context: Context,photoList: MutableList<String>) : RecyclerView.Adapter<PhotoRecycler.PhotoViewHolder>(){
    var photoList:List<String>
    var context:Context
    internal var layoutInflater:LayoutInflater

    init {
        this.context=context;
        this.photoList=photoList;
        layoutInflater= LayoutInflater.from(context);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoRecycler.PhotoViewHolder {
        var view = layoutInflater.inflate(R.layout.photos_card,parent,false)
        return PhotoViewHolder(view);
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: PhotoRecycler.PhotoViewHolder, position: Int) {
        holder.image.setImageDrawable(context.resources.getDrawable(context.resources.getIdentifier(photoList[position],"drawable",context.packageName)));
    }

    class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var image:RoundedImageView
        init {
            this.image=itemView.findViewById(R.id.cardphoto) as RoundedImageView
        }
    }
}