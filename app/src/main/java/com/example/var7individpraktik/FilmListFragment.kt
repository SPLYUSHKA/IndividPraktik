package com.example.var7individpraktik

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import com.example.var7individpraktik.UserActionListener as UserActionListener1

interface  UserActionListener{
    fun Sort()
    fun Deteils(film :Film)
    fun delete(film: Film)
    fun Search(namefilm : String)
}
private const val ID_DELETE =0
private const val ID_DETEILS =1
private  const val ID_SORT =2
private const val ID_SEARCH =3
private const val TAG = "FilmListFragment"
class FilmListFragment : Fragment() {
    interface Callbacks {
        fun onCrimeSelected(crimeId: UUID)
    }
    private var callbacks: Callbacks? = null

    private lateinit var filmRecyclerView: RecyclerView

    private var adapter: FilmAdapter? = FilmAdapter(emptyList())
    private val filmListViewModel: FilmListViewModel by lazy {
        ViewModelProviders.of(this).get(FilmListViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_film_list,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when(item.itemId){
            R.id.new_crime ->{
                val film =Film()
                filmListViewModel.addFilm(film)
                callbacks?.onCrimeSelected(film.id)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_film_list,
                container, false)

        filmRecyclerView =
            view.findViewById(R.id.film_recycler_view) as RecyclerView
        filmRecyclerView.layoutManager = LinearLayoutManager(context)
        filmRecyclerView.adapter = adapter


        return view
    }
    override fun onViewCreated(view: View,
                               savedInstanceState: Bundle?) {
        super.onViewCreated(view,
            savedInstanceState)
        filmListViewModel.filmListLiveData.observe(
            viewLifecycleOwner,
            Observer { films ->
                films?.let {
                    Log.i(TAG, "Got crimes${films.size}")
                    updateUI(films)
                }
            })
    }
    private fun updateUI(filmes: List<Film>) {

        adapter = FilmAdapter(filmes)
        filmRecyclerView.adapter = adapter
    }
    private inner class FilmHolder(view: View,   public  var  actionListener : UserActionListener1)
        : RecyclerView.ViewHolder(view),View.OnClickListener {

        private lateinit var film: Film

        private val titleTextView: TextView =
            itemView.findViewById(R.id.TitleFilm)
        val imageFilmView: ImageView =
            itemView.findViewById(R.id.ImageFilm)
        val morebutton : ImageView = itemView.findViewById(R.id.more)
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(_film:Film) {
            this.film = _film
            titleTextView.text = this.film.Title

            film.Image?.let { imageFilmView.setImageResource(it) }

            itemView.tag = film
            morebutton.tag = film
        }
        override fun onClick(v: View) {
            val film =v.tag as Film
            showPopupMenu(v)


        }



        fun showPopupMenu(view:View) {
            val film2 = view.tag as Film
            val popupMenu = PopupMenu(view.context, view)
            popupMenu.menu.add(0, ID_DETEILS, Menu.NONE, "Details")
            popupMenu.menu.add(0, ID_DELETE, Menu.NONE, "Delete")
            popupMenu.menu.add(0, ID_SEARCH, Menu.NONE, "Search")
            popupMenu.menu.add(0, ID_SORT, Menu.NONE, "Sort")
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                if (item.itemId === ID_DETEILS) {
                    actionListener.Deteils(film2)
                    Toast.makeText(context,"${film2.Image}",Toast.LENGTH_SHORT).show()
                }
                if (item.itemId === ID_SORT) {
                    actionListener.Sort()
                }
                if (item.itemId === ID_DELETE) {
                   actionListener.delete(film2)
                }
                if (item.itemId === ID_SEARCH) {
                    actionListener.Search("Котовасия")
                }
                false
            })
            popupMenu.show()
        }
    }

    private inner class FilmAdapter(var films: List<Film>) : RecyclerView.Adapter<FilmHolder>() {

        override fun onCreateViewHolder(parent:
                                        ViewGroup, viewType: Int): FilmHolder {
            val view =
                layoutInflater.inflate(R.layout.list_item_film, parent, false)

            return FilmHolder(view,object : UserActionListener1 {
                override fun Deteils(film: Film) {
                    callbacks?.onCrimeSelected(film.id)

                }

                override fun Sort() {
                    films.sortedBy {
                        it.Genre

                    }
                    notifyDataSetChanged()
                }

                override fun delete(film: Film) {
                   filmListViewModel.deleteFilm(film)
                }

                override fun Search(namefilm: String) {
                    films.forEach {
                        film ->film.Title ==namefilm
                    }
                }
            }
            )
        }


        override fun getItemCount() =
            films.size
        override fun onBindViewHolder(holder:
                                      FilmHolder, position: Int) {
            val film = films[position]
            holder.bind(film)


        }
    }

    companion object {
        fun newInstance(): FilmListFragment {
            return FilmListFragment()

        }
    }
}
