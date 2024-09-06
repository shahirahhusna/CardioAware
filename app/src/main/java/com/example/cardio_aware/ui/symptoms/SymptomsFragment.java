package com.example.cardio_aware.ui.symptoms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cardio_aware.R;
import com.example.cardio_aware.SliderAdapter;
import com.example.cardio_aware.databinding.FragmentSymptomsBinding;

import java.util.Arrays;
import java.util.List;

public class SymptomsFragment extends Fragment {

    private FragmentSymptomsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSymptomsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
