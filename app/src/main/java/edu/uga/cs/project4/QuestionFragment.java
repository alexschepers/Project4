package edu.uga.cs.project4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {

    private static final int NUM_PAGES = 6;
    private ViewPager2 mPager;
    private PagerAdapter2 pagerAdapter;

    private TextView mQuestionView;
    private RadioButton mButtonChoice1;
    private RadioButton mButtonChoice2;
    private RadioButton mButtonChoice3;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    public QuestionFragment() {
        // Required empty public constructor
    }


    public static QuestionFragment newInstance(int position) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        /*
        args.putStringArray("questionsArray", questionsArray);
        args.putStringArray("correctAnswerArray", correctAnswerArray);
        args.putStringArray("wrongAnswerOneArray", wrongAnswerOneArray);
        args.putStringArray("wrongAnswerTwoArray", wrongAnswerTwoArray);
        */
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras()!= null) {
            String[] myArray = getIntent().getExtras().getStringArray("questionsArray");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_question, container, false);

        mQuestionView = (TextView) rootView.findViewById(R.id.quizQuestion);
        mButtonChoice1 = (RadioButton) rootView.findViewById(R.id.option1);
        mButtonChoice2 = (RadioButton) rootView.findViewById(R.id.option1);
        mButtonChoice3 = (RadioButton) rootView.findViewById(R.id.option1);

        mPager = (ViewPager2) rootView.findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter2(this, 6);

        String[] questionsArray = getArguments().getStringArray("questionArray");
        Log.i("questions fragment", String.valueOf(questionsArray));


        return rootView;

    }

}

class PagerAdapter2 extends FragmentStateAdapter {

    private final int mSize;

    public PagerAdapter2(Fragment fm, int size) {
        super(fm);
        this.mSize = size;

    }

    @Override
    public Fragment createFragment(int position) {
        return QuestionFragment.newInstance(position + 1);
    }

    @Override
    public int getItemCount() {
        return mSize;
    }

}