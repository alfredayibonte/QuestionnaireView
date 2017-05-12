# QuestionnaireView
A simple view to be able to display question and various field (Radio, EditText, checkbox ) for answers


Including in your project
-------------------------

```groovy
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
dependencies {
	compile 'com.github.alfredayibonte:QuestionnaireView:0.1.1'
}
```

Usage
-----

```java
public class MainActivity extends AppCompatActivity implements
        RadioListAdapter.OnRadioItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuestionnaireView questionnaireView = (QuestionnaireView)findViewById(R.id.questionnaire);
        questionnaireView.setQuestion("What is the name of this library ?");
        questionnaireView.setViewType(AnswerType.RADIO);
        CharSequence[] answers = new CharSequence[]{
                        "Questionnaire", "QuestionnaireView", "Question"};
        questionnaireView.setAnswers(answers);
        questionnaireView.setAnswers(answers);
        questionnaireView.addRadioItemListener(this);
    }

    @Override
    public void onRadioItemClick(List<Answer> answers) {
        Log.e("radio answers: ", answers.toString());
    }
}
```

APIs offered by **QuestionnaireView**.

|APIs | Usage|
|---|---|
|setQuestion(String text)|Set the question on a webview|
|setViewType(int viewType)|Set the viewType to either RADIO, EDITTEXT or CHECKLIST|
|addRadioItemListener(OnRadioItemClickListener listener)|Sets  a listener for radioButton|
|addCheckItemListener(OnCheckItemClickListener listener)|Sets a listener for check list|
|addOnEditorActionListener(OnEditorActionListener listener)|Sets a listener for EditText|
|addTextChangedListener(TextWatcher watcher)|Sets a watcher for EditText|
|setAnswers(CharSequence[] answers)|Sets all possible answers for radio and checklist|
