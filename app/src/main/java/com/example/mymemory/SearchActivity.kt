package com.example.mymemory

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.custom_list.*
import java.util.*

data class Game(
        val displayGameName: String = "",
        val sizeCard: String = ""
)

class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


class SearchActivity : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.title = "Comunidad"
        val query = db.collection("games")
        val options = FirestoreRecyclerOptions.Builder<Game>().setQuery(query, Game::class.java)
                .setLifecycleOwner(this).build()
        val adapter = object: FirestoreRecyclerAdapter<Game, GameViewHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
                val view = LayoutInflater.from(this@SearchActivity).inflate(R.layout.custom_list, parent, false)
                return GameViewHolder(view)
            }

            override fun onBindViewHolder(holder: GameViewHolder, position: Int, model: Game) {
                val tvGameName: TextView = holder.itemView.findViewById(R.id.list_name)
                val tvGameSize: TextView = holder.itemView.findViewById(R.id.list_size)
                val cardView: CardView = holder.itemView.findViewById(R.id.mCardView)


                cardView.setCardBackgroundColor(getRandomColorCode())

                tvGameName.text = model.displayGameName
                tvGameSize.text = model.sizeCard
            }
        }
        rvGames.adapter = adapter
        rvGames.layoutManager = LinearLayoutManager(this)
    }


    fun getRandomColorCode(): Int {
        val random = Random()
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }


}