package br.com.projeto.matchsimulator.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.projeto.matchsimulator.adapter.MatchesAdapter
import br.com.projeto.matchsimulator.data.MatchesRetrofit
import br.com.projeto.matchsimulator.databinding.ActivityMainBinding
import br.com.projeto.matchsimulator.domain.Match
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import java.util.*

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var matches: List<Match>
    private var adapter: MatchesAdapter = MatchesAdapter(Collections.emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fillList()
        configFABSwipe()
        configSwipe()

    }

    private fun configFABSwipe() {
        binding.mockFab.setOnClickListener {
            it.animate().rotationBy(360F).setDuration(500)
            val random = Random()
            matches.forEach { match ->
                match.leftTeam.score = random.nextInt(match.leftTeam.stars.plus(1))
                match.rightTeam.score = random.nextInt(match.rightTeam.stars.plus(1))
                adapter.notifyItemChanged(matches.indexOf(match))

            }
        }
    }

    private fun configSwipe() {
        binding.swipe.setOnRefreshListener {
            fillList()
        }
    }

    private fun fillList() {

        binding.swipe.isRefreshing = true

        val matchesRetrofit = MatchesRetrofit()
        val matchesApi = matchesRetrofit.getMatchesApi()

        matchesApi.getMatches().enqueue(object : Callback<List<Match>> {
            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        matches = response.body() ?: Collections.emptyList()

                        configAdapter(response)

                    } else {
                        Snackbar.make(
                            binding.root,
                            "Houve um erro" + response.code(),
                            Snackbar.LENGTH_LONG
                        )
                            .show()
                    }
                }

                binding.swipe.isRefreshing = false
            }

            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                Snackbar.make(binding.root, "Error: ${t.message}", Snackbar.LENGTH_LONG)
                    .show()
                Log.e("ERROR", t.message.toString())

                binding.swipe.isRefreshing = false
            }

        })
    }

    private fun configAdapter(response: Response<List<Match>>) {
        binding.matchRecyclerview.setHasFixedSize(true)
        adapter = MatchesAdapter(response.body() ?: Collections.emptyList())
        binding.matchRecyclerview.adapter = adapter

        configAdapterClick()
    }

    private fun configAdapterClick() {
        adapter.onItemClick = { match: Match, position: Int ->
            val intent = Intent(this, Details::class.java)
            intent.putExtra(Extra.MATCH_KEY, match)
            startActivity(intent)
           // throw  RuntimeException("new Error")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}