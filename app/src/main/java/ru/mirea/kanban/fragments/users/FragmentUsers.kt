package ru.mirea.kanban.fragments.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.mirea.kanban.R
import ru.mirea.kanban.fragments.FragmentTaskArgs
import ru.mirea.kanban.room.user.DBClientUser
import ru.mirea.kanban.room.user.EntityUser

/**
 * Фрагмент интерфейса "Пользователи".
 * Воспроизводит layout со списком пользователей доски.
 */
class FragmentUsers : Fragment() {

    private val args: FragmentUsersArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dbClient = DBClientUser(requireActivity())
        lifecycleScope.launch {
            dbClient.getAll()
        }
        val userEntries = dbClient.awaitResult() as List<EntityUser>

        val recycler: RecyclerView = view.findViewById(R.id.recycler_lists)
        val adapter = UserListAdapter(userEntries, args.taskID, requireActivity())
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }
}