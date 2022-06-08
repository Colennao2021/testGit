package com.example.roomdata1.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdata1.R
import com.example.roomdata1.adapter.UserAdapter
import com.example.roomdata1.viewmodel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var addUser: FloatingActionButton
    private lateinit var userViewModel: UserViewModel
    private lateinit var recycleView: RecyclerView
    private lateinit var search: FloatingActionButton
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        search = view.findViewById(R.id.btn_search)
        addUser = view.findViewById(R.id.float_add)
        recycleView = view.findViewById(R.id.reycleview_list)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserAdapter()
        recycleView.adapter = adapter
        recycleView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setListData(user)
        })

        addUser.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToAddFragment()
            findNavController().navigate(action)
        }

        search.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToSearchFragment()
            findNavController().navigate(action)
        }
    }
}