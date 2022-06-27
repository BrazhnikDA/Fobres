package com.brazhnik.fobres.view.rating

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brazhnik.fobres.R
import com.brazhnik.fobres.data.model.ShortUser

class RatingAdapter(private val listUser: List<ShortUser>) :
    RecyclerView.Adapter<RatingAdapter.RatingViewHolder>() {


    class RatingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageProfile: ImageView = itemView.findViewById(R.id.imageViewProfile)
        val nameProfile: TextView = itemView.findViewById(R.id.textViewName)
        val textAmount: TextView = itemView.findViewById(R.id.textViewAmount)
        val textTopRating: TextView = itemView.findViewById(R.id.textViewTopRating)
        //val buttonViewLinks: Button = itemView.findViewById(R.id.buttonViewLinks)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RatingViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler_view_rating, parent, false)
        return RatingViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        //holder.imageProfile.setImageDrawable(listUser[position].profilePicture)
        holder.imageProfile.setImageResource(R.mipmap.ic_coin)
        holder.nameProfile.text = listUser[position].firstName
        holder.textAmount.text = "Coins: ${listUser[position].money}"
        holder.textTopRating.text = "# ${position+1}"
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}