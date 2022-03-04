package br.com.projeto.matchsimulator.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnRepeat
import br.com.projeto.matchsimulator.adapter.MatchesAdapter
import br.com.projeto.matchsimulator.data.MatchesRetrofit
import br.com.projeto.matchsimulator.databinding.ActivityMainBinding
import br.com.projeto.matchsimulator.domain.Match
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

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
                /*.setListener( object : Animator.AnimatorListener {
                    override fun onAnimationStart(p0: Animator?) {
                        p0?.start()
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        p0?.end()
                    }

                    override fun onAnimationCancel(p0: Animator?) {
                        p0?.cancel()
                    }

                    override fun onAnimationRepeat(p0: Animator?) {
                        if (p0 != null) {
                            p0.doOnRepeat {  }
                        }
                    }

                })*/
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
                        Log.i("SIZE", "onResponse: " + response.body()?.size)

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
        val adapter = MatchesAdapter(response.body() ?: Collections.emptyList())
        binding.matchRecyclerview.adapter = adapter
    }
}