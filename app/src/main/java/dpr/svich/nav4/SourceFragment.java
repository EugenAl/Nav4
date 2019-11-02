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
        rooms.add(new Room("Вход", RoomType.ADMINISTRATION));
        rooms.add(new Room("Ауд. 3а", RoomType.LECTYRE));
        rooms.add(new Room("Ауд. 4", RoomType.LECTYRE));
        rooms.add(new Room("Ауд. 5", RoomType.LECTYRE));
        rooms.add(new Room("Ауд. 6", RoomType.LECTYRE));
        rooms.add(new Room("Ауд. 31", RoomType.LECTYRE));
        rooms.add(new Room("Ауд. 19", RoomType.LECTYRE));
        rooms.add(new Room("Ауд. 20", RoomType.LECTYRE));
        rooms.add(new Room("М. туалет", RoomType.TOILET));
        rooms.add(new Room("Каб. декана", RoomType.ADMINISTRATION));
        rooms.add(new Room("Деканат", RoomType.ADMINISTRATION));
        rooms.add(new Room("Ауд. 35", RoomType.LECTYRE));
        rooms.add(new Room("Ауд. 37", RoomType.LECTYRE));
        rooms.add(new Room("Ауд. 40", RoomType.LECTYRE));
        rooms.add(new Room("Ж. туалет", RoomType.TOILET));

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
                bundle.putInt("position", position);
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
