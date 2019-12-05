package dpr.svich.nav4;


import androidx.fragment.app.Fragment;
import android.os.Bundle;

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
public class DestinationFragment extends Fragment {

    private RecyclerView destinationRecyclerView;
    private Room startRoom;
    private int pos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_destination, container, false);
        // TODO: eliminate bicycle
        final List<Room> rooms = new ArrayList<>();
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
        rooms.add(new Room(39,"Ауд. 19", RoomType.LECTYRE));
        rooms.add(new Room(41,"М. туалет", RoomType.TOILET));
        rooms.add(new Room(42,"Ж. туалет", RoomType.TOILET));

        try {
            pos = getArguments().getInt("position",0);
            startRoom = rooms.get(pos);
            rooms.remove(pos);
        } catch (NullPointerException e){
            e.printStackTrace();
        }


        destinationRecyclerView = view.findViewById(R.id.destinationsList);
        destinationRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        destinationRecyclerView.setAdapter(new RecyclerAdapter(getContext(), rooms));
        destinationRecyclerView.addOnItemTouchListener(new RecyclerItemListener(getContext(),
                destinationRecyclerView, new RecyclerItemListener.RecyclerTouchListener() {
            @Override
            public void onClickItem(View v, int position) {
                Toast.makeText(getContext(),
                        "Вы направляетесь из "+(startRoom!=null?startRoom.getLabel():"")
                                +" в "+rooms.get(position).getLabel()+
                                "\nЦель вашего визита?", Toast.LENGTH_LONG).show();
                // Start and destination positions
                Bundle bundle = new Bundle();
                bundle.putInt("positionS", pos);
                bundle.putInt("positionD", position);
                // Navigate to next screen
                Navigation.findNavController(v)
                        .navigate(R.id.action_destinationFragment_to_advActivity);
            }

            @Override
            public void onLongClickItem(View v, int position) {

            }
        }));
        return view;
    }

}
