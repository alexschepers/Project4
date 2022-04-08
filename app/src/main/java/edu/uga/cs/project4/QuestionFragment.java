package edu.uga.cs.project4;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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
import android.widget.RadioGroup;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class QuestionFragment extends Fragment {

    private static final int NUM_PAGES = 6;

    public TextView mQuestionView;
    public RadioButton mButtonChoice1;
    public RadioButton mButtonChoice2;
    public RadioButton mButtonChoice3;
    public RadioGroup radioGroup;

    public String question;
    public String correct;
    public String correctAnswer;
    public String wrongAnswerOne;
    public String wrongAnswerTwo;

    public int mScore = 0;
    public int mQuestionNumber = 0;

    public QuestionFragment() {
        // Required empty public constructor
    }

    public static QuestionFragment newInstance(int position) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt("questionNum", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        mQuestionView = (TextView) rootView.findViewById(R.id.quizQuestion);
        mButtonChoice1 = (RadioButton) rootView.findViewById(R.id.option1);
        mButtonChoice2 = (RadioButton) rootView.findViewById(R.id.option2);
        mButtonChoice3 = (RadioButton) rootView.findViewById(R.id.option3);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radiogroup);

       if (getArguments() != null) {
            question = getArguments().getString("question");
            mQuestionView.setText(question);

            correct = getArguments().getString("correct");
            correctAnswer = correct;
            mButtonChoice1.setText(correct);
            Log.i("onViewCreated", String.valueOf(mButtonChoice1.getText()));

            wrongAnswerOne = getArguments().getString("wrongAnswerOne");
            mButtonChoice2.setText(wrongAnswerOne);

            wrongAnswerTwo = getArguments().getString("wrongAnswerTwo");
            mButtonChoice3.setText(wrongAnswerTwo);

       }

        clearSelection();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            RadioButton buttonPressed;

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("in listener", "in OnCheckedChanged()");
                Log.i("checkedId", String.valueOf(checkedId));
                Log.i("R.id.option1", String.valueOf(R.id.option1));
                Log.i("R.id.option2", String.valueOf(R.id.option2));
                Log.i("R.id.option3", String.valueOf(R.id.option3));

                RadioButton selectedButton = rootView.findViewById(group.getCheckedRadioButtonId());

                String answerSelection = (String) selectedButton.getText();

                Log.i("answer selection", answerSelection);

                if (checkedId == R.id.option1) {
                    Log.i("mbuttonchoice1text", String.valueOf(mButtonChoice1.getText()));
                    buttonPressed = mButtonChoice1;
                }
                if (checkedId == R.id.option2) {
                    Log.i("mbuttonchoice2text", String.valueOf(mButtonChoice2.getText()));
                    buttonPressed = mButtonChoice2;
                }
                if (checkedId == R.id.option3) {
                    Log.i("mbuttonchoice3text", String.valueOf(mButtonChoice3.getText()));
                    buttonPressed = mButtonChoice3;
                }

                Log.i("button pressed", String.valueOf(buttonPressed.getText()));
                Log.i("correct", String.valueOf(correct) );

                if (buttonPressed.getText() == correct) {
                    Log.i("onCheckedChanged()", "selected answer was correct");
                }
            }

        });


        return rootView;

    }

    /*

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

     */

    private void clearSelection(){
        if(radioGroup != null) radioGroup.clearCheck();
    }

    private String getCorrectAnswer() {
        return correctAnswer;
    }

}

class MyAdapter extends FragmentStateAdapter {

    private final int NUM_PAGES;

    ArrayList<String> questionArray;
    ArrayList<String> correctAnswerArray;
    ArrayList<String> wrongAnswerOneArray;
    ArrayList<String> wrongAnswerTwoArray;
    Country[] countryObjectArray;

    @SuppressLint("LongLogTag")


    public MyAdapter(FragmentActivity fm, int size) {
        super(fm);
        this.NUM_PAGES = size;

        try {
            InputStream in_s = fm.getAssets().open("country_continent.csv");
            CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );
            String[] nextRow;
            int i = 0;
            countryObjectArray = new Country[196];
            while( ( nextRow = reader.readNext() ) != null ) {
                String countryName = nextRow[0];
                String continentName = nextRow[1];
                Country newCountry = new Country(countryName, continentName);
                countryObjectArray[i] = newCountry;
                i++;
            }
            in_s.close();
        } catch (Exception e) {
            Log.e( "question fragment adapter my adapter constructor", e.toString() );
        }

        Random randomNum = new Random();
        int upper = 196;

        questionArray = new ArrayList<>();
        correctAnswerArray = new ArrayList<>();
        wrongAnswerOneArray = new ArrayList<>();
        wrongAnswerTwoArray = new ArrayList<>();
        int randomNumArray[] = new int[6];
        for (int i = 0; i < randomNumArray.length; i++) {
            randomNumArray[i] = randomNum.nextInt(upper);
            Question newQuestion = new Question(countryObjectArray[randomNumArray[i]]);

            questionArray.add(newQuestion.toString());
            correctAnswerArray.add(newQuestion.getCorrectAnswer());
            wrongAnswerOneArray.add(newQuestion.getWrongAnswerOne());
            wrongAnswerTwoArray.add(newQuestion.getWrongAnswerTwo());
        }

    }

    @Override
    public Fragment createFragment(int position) {

        QuestionFragment questionFragment = QuestionFragment.newInstance(position + 1);
        Bundle bundle = new Bundle();
        bundle.putString("question", questionArray.get(position));
        Log.i("bundle", bundle.getString("question"));
        bundle.putString("correct", correctAnswerArray.get(position));
        Log.i("bundle", bundle.getString("correct"));
        bundle.putString("wrongAnswerOne", wrongAnswerOneArray.get(position));
        Log.i("bundle", bundle.getString("wrongAnswerOne"));
        bundle.putString("wrongAnswerTwo", wrongAnswerTwoArray.get(position));
        Log.i("bundle", bundle.getString("wrongAnswerTwo"));
        questionFragment.setArguments(bundle);

        return questionFragment;
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }

}