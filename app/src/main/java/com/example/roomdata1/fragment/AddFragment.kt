package com.example.roomdata1.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdata1.R
import com.example.roomdata1.model.User
import com.example.roomdata1.viewmodel.UserViewModel

class AddFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var age: EditText
    private lateinit var addUser: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        firstName = view.findViewById(R.id.first_name)
        lastName = view.findViewById(R.id.last_name)
        age = view.findViewById(R.id.age)
        addUser = view.findViewById(R.id.add_user)

        addUser.setOnClickListener {
            insertData()
        }
        return view

    }

    private fun insertData() {
        val strFirstName = firstName.text.toString()
        val strLastname = lastName.text.toString()
        val strAge = age.text.toString()

        if (inputCheck(strFirstName, strLastname, strAge)) {
            val user = User(0, strFirstName, strLastname, Integer.parseInt(strAge))
            userViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            val action = AddFragmentDirections.actionAddFragmentToListFragment()
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}