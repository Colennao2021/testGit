package com.example.roomdata1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdata1.R
import com.example.roomdata1.model.User
import com.example.roomdata1.viewmodel.UserViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var userViewModel: UserViewModel

    lateinit var firstName: EditText
    lateinit var lastName: EditText
    lateinit var age: EditText
    lateinit var update: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        loadData(view)
        event()
        update.setOnClickListener {
            updateData(view)
        }
        return view
    }

    private fun updateData(view: View) {
        firstName = view.findViewById(R.id.first_name)
        lastName = view.findViewById(R.id.last_name)
        age = view.findViewById(R.id.age)

        val user = User(
            args.user.id,
            firstName.text.toString(),
            lastName.text.toString(),
            Integer.parseInt(age.text.toString())
        )
        userViewModel.updateUser(user)
        Toast.makeText(requireContext(), "Update Successfully", Toast.LENGTH_SHORT).show()
        val action = UpdateFragmentDirections.actionUpdateFragmentToListFragment()
        findNavController().navigate(action)
    }

    private fun loadData(view: View) {
        firstName = view.findViewById(R.id.first_name)
        lastName = view.findViewById(R.id.last_name)
        age = view.findViewById(R.id.age)
        update = view.findViewById(R.id.update_user)
    }

    private fun event() {
        firstName.setText(args.user.firstName)
        lastName.setText(args.user.lastName)
        age.setText(args.user.age.toString())
    }
}