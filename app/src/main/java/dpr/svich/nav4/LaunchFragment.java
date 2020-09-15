package dpr.svich.nav4;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Стартовый фрагмент с описанием приложения
 */
public class LaunchFragment extends Fragment {

    public LaunchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_launch, container, false);
        // Set click listener for launch button
        ((Button)view.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to next fragment
                Navigation.findNavController(v)
                        .navigate(R.id.action_launchFragment_to_sourceFragment);
            }
        });
        return view;
    }
}
