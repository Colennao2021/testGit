package com.example.roomdata1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdata1.R
import com.example.roomdata1.adapter.UserAdapter
import com.example.roomdata1.model.User
import com.example.roomdata1.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var recycleView: RecyclerView
    private lateinit var adapter: UserAdapter
    private lateinit var search: Button
    private var count: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        recycleView = view.findViewById(R.id.reycleview_update)
        search = view.findViewById(R.id.btn_search)
        adapter = UserAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search.setOnClickListener {
            searchDatabase(txt_search.text.toString())
        }
    }

    private fun searchDatabase(query: String?) {
        val searchQuery = "%$query%"
        userViewModel.search(searchQuery).observe(viewLifecycleOwner, Observer { list ->
            list.let {
                if (it.size == 0) {
                    Toast.makeText(requireContext(), "Not found ", Toast.LENGTH_SHORT).show()
                }
                adapter.setListData(list)
                recycleView.adapter = adapter
                recycleView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        })
    }


}