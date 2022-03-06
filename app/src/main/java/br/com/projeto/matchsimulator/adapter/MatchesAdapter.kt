package br.com.projeto.matchsimulator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.projeto.matchsimulator.databinding.MatchItemBinding
import br.com.projeto.matchsimulator.domain.Match
import com.bumptech.glide.Glide

class MatchesAdapter(private val matches: List<Match>) : RecyclerView.Adapter<MatchesAdapter.MatchViewHolder>() {

    var onItemClick: (Match, Int) -> Unit ={ match: Match, position: Int -> }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = MatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindView(matches[position])
    }

    override fun getItemCount() = matches.size

     inner class MatchViewHolder (private val binding: MatchItemBinding) : RecyclerView.ViewHolder(binding.root) {


         fun bindView(match : Match) {

             with(binding) {
                 leftTeamName.text = match.leftTeam.name
                 leftTeamScore.text = match.leftTeam.score.toString()

                 Glide.with(root.context)
                     .load(match.leftTeam.image)
                     .circleCrop()
                     .into(leftTeamImage)

                rightTeamName.text = match.rightTeam.name
                rightTeamScore.text = match.rightTeam.score.toString()
                 Glide.with(root.context)
                     .load(match.rightTeam.image)
                     .circleCrop()
                     .into(rightTeamImage)

                 onItemClickListener(match)

             }

         }

         private fun MatchItemBinding.onItemClickListener(match: Match) {
            root.setOnClickListener {
                onItemClick(match, adapterPosition)
            }
         }
    }


}

