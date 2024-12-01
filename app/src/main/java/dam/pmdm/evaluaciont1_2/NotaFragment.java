package dam.pmdm.evaluaciont1_2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotaFragment extends Fragment {

    private static final String ARG_ASIGNATURA = "asignatura";
    private static final String ARG_NOTA = "nota";

    private TextView tvAsignatura;
    private TextView tvNota;

    private String asignatura;
    private String nota;

    public static NotaFragment newInstance(String asignatura, String nota) {
        NotaFragment fragment = new NotaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ASIGNATURA, asignatura);
        args.putString(ARG_NOTA, nota);
        fragment.setArguments(args);
        return fragment;
    }

    public NotaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            asignatura = getArguments().getString(ARG_ASIGNATURA);
            nota = getArguments().getString(ARG_NOTA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nota, container, false);
        tvAsignatura = view.findViewById(R.id.tvAsignatura);
        tvNota = view.findViewById(R.id.tvNota);

        tvAsignatura.setText(asignatura);
        tvNota.setText(nota);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(ARG_ASIGNATURA, tvAsignatura.getText().toString());
        outState.putString(ARG_NOTA, tvNota.getText().toString());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            tvAsignatura.setText(savedInstanceState.getString(ARG_ASIGNATURA, ""));
            tvAsignatura.setText(savedInstanceState.getString(ARG_NOTA, ""));
        }

    }
}