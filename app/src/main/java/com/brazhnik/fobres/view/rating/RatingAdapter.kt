package com.brazhnik.fobres.view.rating

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.brazhnik.fobres.R
import com.brazhnik.fobres.data.SharedData
import com.brazhnik.fobres.data.model.ShortUser
import com.brazhnik.fobres.utilities.App.Companion.resources
import com.squareup.picasso.Picasso

class RatingAdapter(
    private val listUser: List<ShortUser>,
    private val cellClickListener: ItemClickListener
) :
    RecyclerView.Adapter<RatingAdapter.RatingViewHolder>() {


    class RatingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageProfile: ImageView = itemView.findViewById(R.id.imageViewProfile)
        val nameProfile: TextView = itemView.findViewById(R.id.textViewName)
        val textAmount: TextView = itemView.findViewById(R.id.textViewAmount)
        val textTopRating: TextView = itemView.findViewById(R.id.textViewTopRating)
        val status: TextView = itemView.findViewById(R.id.textStatus)

        //val buttonViewLinks: Button = itemView.findViewById(R.id.buttonViewLinks)
        val cardView: CardView = itemView.findViewById(R.id.itemCardView)
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

    // TODO USE Res String
    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        resources?.let { holder.cardView.setCardBackgroundColor(it.getColor(R.color.base_gray)) }
        if (SharedData.isLogged) {
            if (listUser[position].id == SharedData.profileFullCurrent.id) {
                resources?.let { holder.cardView.setCardBackgroundColor(it.getColor(R.color.base_perlamuter)) }
            } else {
                resources?.let { holder.cardView.setCardBackgroundColor(it.getColor(R.color.base_gray)) }
            }
        }
        Picasso.get().load(listUser[position].profilePicture).into(holder.imageProfile)
        holder.nameProfile.text = listUser[position].firstName + " " + listUser[position].lastName
        holder.textAmount.text = "Coins: ${listUser[position].money}"
        holder.textTopRating.text = "# ${position + 1}"

        if (listUser[position].status != null) {
            holder.status.text = listUser[position].status
        } else {
            holder.status.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(listUser[position].id)
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}