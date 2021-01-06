package com.example.quizzo

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mTotalCorrectAanswer: Int = 0
    private var mUserName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        /* //Testing Purpose

        val questionList = Constants.getQuestions()
         Log.i("Questions Size",  "${questionList.size}")

         val currentPosition = 1
         val question: Question? = questionList[currentPosition - 1]

         progressBar.progress = currentPosition
         tv_progress.text = "$currentPosition" + "/" + progressBar.max

         tv_question.text = question!!.question
         iv_image.setImageResource(question.image)
         tv_optionOne.text = question.optionOne
         tv_optionTwo.text = question.optionTwo
         tv_optionThree.text = question.optionThree
         tv_optionFour.text = question.optionFour*/


        mUserName = intent.getStringExtra(Constants.USER_NAME)



        mQuestionList = Constants.getQuestions()

        setQuestion()

        tv_optionOne.setOnClickListener(this)
        tv_optionTwo.setOnClickListener(this)
        tv_optionThree.setOnClickListener(this)
        tv_optionFour.setOnClickListener(this)

        btn_submit.setOnClickListener(this)


    }

    private fun setQuestion() {

       // mCurrentPosition = 1 // for first question

        val question = mQuestionList!![mCurrentPosition - 1]

        defaultOptionsView()

        if (mCurrentPosition == mQuestionList!!.size){
            btn_submit.text = "FINISH!"
        }else{
            btn_submit.text = "SUBMIT"
        }

        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + mQuestionList!!.size

        tv_question.text = question!!.question
        iv_image.setImageResource(question.image)
        tv_optionOne.text = question.optionOne
        tv_optionTwo.text = question.optionTwo
        tv_optionThree.text = question.optionThree
        tv_optionFour.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, tv_optionOne)
        options.add(1, tv_optionTwo)
        options.add(2, tv_optionThree)
        options.add(3, tv_optionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_optionOne -> {
                selectedOptionView(tv_optionOne, 1)
            }
            R.id.tv_optionTwo -> {
                selectedOptionView(tv_optionTwo, 2)
            }
            R.id.tv_optionThree -> {
                selectedOptionView(tv_optionThree, 3)
            }
            R.id.tv_optionFour -> {
                selectedOptionView(tv_optionFour, 4)
            }
            R.id.btn_submit -> {
                //SubmitButton Functionality
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            /*Toast.makeText(
                                this,
                                "You Have Successfully completed the Quiz",
                                Toast.LENGTH_LONG
                            ).show()*/

                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mTotalCorrectAanswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)

                            startActivity(intent)
                            finish()
                        }
                    }

                }else{
                    val question = mQuestionList?.get(mCurrentPosition -1)
                    if (question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_bg)
                    }else{
                        mTotalCorrectAanswer++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_bg)

                    if (mCurrentPosition == mQuestionList!!.size){
                        btn_submit.text = "FINISH!"
                    }else{
                        btn_submit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }

            }



        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tv_optionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                tv_optionTwo.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                tv_optionThree.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                tv_optionFour.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border_bg
        )

    }
}