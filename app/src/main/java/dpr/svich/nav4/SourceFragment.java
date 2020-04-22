package dpr.svich.nav4;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dpr.svich.nav4.adapter.RecyclerAdapter;
import dpr.svich.nav4.adapter.RecyclerItemListener;
import dpr.svich.nav4.entity.Room;
import dpr.svich.nav4.enums.RoomType;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SourceFragment extends Fragment {

    private RecyclerView sourceRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_source, container, false);
        // TODO: eliminate bicycle
        final List<Room>  rooms = new ArrayList<>();
        rooms.add(new Room(1,"Вход", RoomType.ADMINISTRATION));
        rooms.add(new Room(4,"Ауд. 1", RoomType.LECTYRE));
        rooms.add(new Room(6,"Ауд. 2", RoomType.LECTYRE));
        rooms.add(new Room(9,"Ауд. 3", RoomType.LECTYRE));
        rooms.add(new Room(11,"Ауд. 3a", RoomType.LECTYRE));
        rooms.add(new Room(12,"Ауд. 4", RoomType.LECTYRE));
        rooms.add(new Room(7,"Ауд. 5", RoomType.LECTYRE));
        rooms.add(new Room(14,"Столовая", RoomType.ADMINISTRATION));
        rooms.add(new Room(20,"Ауд. 9", RoomType.LECTYRE));
        rooms.add(new Room(22,"Ауд. 10", RoomType.LECTYRE));
        rooms.add(new Room(24,"Ауд. 11", RoomType.LECTYRE));
        rooms.add(new Room(26,"Ауд. 12", RoomType.LECTYRE));
        rooms.add(new Room(28,"Ауд. 13", RoomType.LECTYRE));
        rooms.add(new Room(31,"Ауд. 14", RoomType.LECTYRE));
        rooms.add(new Room(33,"Ауд. 14a", RoomType.LECTYRE));
        rooms.add(new Room(36,"Ауд. 15", RoomType.LECTYRE));
        rooms.add(new Room(34,"Ауд. 16", RoomType.LECTYRE));
        rooms.add(new Room(30,"Ауд. 17", RoomType.LECTYRE));
        rooms.add(new Room(39,"Ауд. 19", RoomType.LECTYRE));
        rooms.add(new Room(40,"Ауд. 20", RoomType.LECTYRE));
        rooms.add(new Room(41,"М. туалет", RoomType.TOILET));
        rooms.add(new Room(42,"Ж. туалет", RoomType.TOILET));

        sourceRecyclerView = view.findViewById(R.id.sourcesList);
        sourceRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        sourceRecyclerView.setAdapter(new RecyclerAdapter(getContext(), rooms));
        sourceRecyclerView.addOnItemTouchListener(new RecyclerItemListener(getContext(),
                sourceRecyclerView, new RecyclerItemListener.RecyclerTouchListener() {
            @Override
            public void onClickItem(View v, int position) {
                Toast.makeText(getContext(),
                        "Вы находитесь в "+rooms.get(position).getLabel(),
                        Toast.LENGTH_LONG).show();
                // Position of start
                Bundle bundle = new Bundle();
                bundle.putInt("position", (int)rooms.get(position).getId());
                // Navigate to next screen
                Navigation.findNavController(v)
                        .navigate(R.id.action_sourceFragment_to_destinationFragment, bundle);
            }

            @Override
            public void onLongClickItem(View v, int position) {

            }
        }));
        return view;
    }

}
