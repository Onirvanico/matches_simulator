package br.com.projeto.matchsimulator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.projeto.matchsimulator.databinding.MatchItemBinding
import br.com.projeto.matchsimulator.domain.Match
import com.bumptech.glide.Glide

class MatchesAdapter(val matches: List<Match>) : RecyclerView.Adapter<MatchesAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = MatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindView(matches.get(position))
    }

    override fun getItemCount() = matches.size

     inner class MatchViewHolder (private val binding: MatchItemBinding) : RecyclerView.ViewHolder(binding.root) {

         fun bindView(match : Match) {
             binding.leftTeamName.text = match.leftTeam.name
             binding.leftTeamScore.text = "3"

             Glide.with(binding.root.context)
                 .load(match.leftTeam.image)
                 .circleCrop()
                 .into(binding.leftTeamImage)

             binding.rightTeamName.text = match.rightTeam.name
             binding.rightTeamScore.text = "2"
             Glide.with(binding.root.context)
                 .load(match.rightTeam.image)
                 .circleCrop()
                 .into(binding.rightTeamImage)
         }
    }


}

