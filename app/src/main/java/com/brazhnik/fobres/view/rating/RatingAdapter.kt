package com.brazhnik.fobres.view.rating

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brazhnik.fobres.R
import com.brazhnik.fobres.repository.viewmodel.VModelRating

class RatingAdapter(private val listUser: List<VModelRating>) :
    RecyclerView.Adapter<RatingAdapter.RatingViewHolder>() {

    class RatingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageProfile: ImageView = itemView.findViewById(R.id.imageViewProfile)
        val nameProfile: TextView = itemView.findViewById(R.id.textViewName)
        val textAmount: TextView = itemView.findViewById(R.id.textViewAmount)
        val textTopRating: TextView = itemView.findViewById(R.id.textViewTopRating)
        val buttonViewLinks: Button = itemView.findViewById(R.id.buttonViewLinks)
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
        holder.imageProfile.setImageResource(R.drawable.tmp_image_profile)
        holder.nameProfile.text = listUser[position].name
        holder.textAmount.text = "Amount: ${listUser[position].amount}"
        //holder.textTopRating.text = "Top Rating: ${listUser[position].placeRating}"
        holder.buttonViewLinks.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = listUser.size
}