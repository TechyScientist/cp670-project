package net.johnnyconsole.cp670.project.fragment;

import static net.johnnyconsole.cp670.project.helper.ApplicationSession.database;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import net.johnnyconsole.cp670.project.R;

public class CourseInformationFragment extends Fragment {

    private final String termCode;
    private final int crn;
    private final CourseListFragment callingFragment;

    public CourseInformationFragment(String termCode, int crn, CourseListFragment callingFragment) {
        this.termCode = termCode;
        this.crn = crn;
        this.callingFragment = callingFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_information, container, false);
        view.findViewById(R.id.btBack).setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fvFragment, callingFragment)
                        .commitNow());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 10;

        Cursor cursor = database.rawQuery("SELECT * FROM courses WHERE term=\"" + termCode + "\" AND crn = " + crn + ";", null);
        cursor.moveToFirst();

        String code = cursor.getString(2),
                title = cursor.getString(3),
                prerequisites = cursor.getString(4),
                exclusions = cursor.getString(5),
                instructor = cursor.getString(6),
                daytime = cursor.getString(7);

        LinearLayout layout = (LinearLayout) view.getRootView();

        TextView tv = new TextView(container.getContext());
        tv.setText(getString(R.string.crn, termCode, crn));
        tv.setTextColor(getResources().getColor(R.color.darkPurple, null));
        tv.setLayoutParams(params);
        layout.addView(tv);

        tv = new TextView(container.getContext());
        tv.setText(getString(R.string.codeTitle, code, title));
        tv.setTextColor(getResources().getColor(R.color.darkPurple, null));
        tv.setLayoutParams(params);
        layout.addView(tv);

        tv = new TextView(container.getContext());
        tv.setText(getString(R.string.prerequistes, prerequisites == null ? "--" : prerequisites));
        tv.setTextColor(getResources().getColor(R.color.darkPurple, null));
        tv.setLayoutParams(params);
        layout.addView(tv);

        tv = new TextView(container.getContext());
        tv.setText(getString(R.string.exclusions, exclusions == null ? "--" : exclusions));
        tv.setTextColor(getResources().getColor(R.color.darkPurple, null));
        tv.setLayoutParams(params);
        layout.addView(tv);

        tv = new TextView(container.getContext());
        tv.setText(getString(R.string.instructor, instructor == null ? "--" : instructor));
        tv.setTextColor(getResources().getColor(R.color.darkPurple, null));
        tv.setLayoutParams(params);
        layout.addView(tv);

        tv = new TextView(container.getContext());
        tv.setText(getString(R.string.daytime, daytime == null ? "--" : daytime));
        tv.setTextColor(getResources().getColor(R.color.darkPurple, null));
        tv.setLayoutParams(params);
        layout.addView(tv);

        return view;
    }
}