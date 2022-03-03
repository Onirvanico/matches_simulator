package br.com.projeto.matchsimulator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.projeto.matchsimulator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}