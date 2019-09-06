# Software Engineering

## Description

This is the codebase for my YSC3232 Software Engineering homework, in Java.

## Contents

### Week 4: A quiz

The project for this week was to make "a series of objects that can be reused to store a student examination". I did this in two parts. First off, there is the 'Quiz' class. This can be set up to contain any number of 'Question' objects, providing a template for the exam. Secondly, a Quiz object can be used to initialize the 'AnswerBooklet' class. One AnswerBooklet represents a single student's attempt at the exam, and the subsequent grading thereof. It has an 'Answer' object for each 'Question' object in the template quiz, which contains the additional information of the students answer and grade.

#### How to run:
* Download folder
* From root, run 'javac XMLTests.java' and 'javac GiveQuizExample.java'
* For an idea of how the exam is given and graded, run: 'java GiveQuizExample'
* To validate the XML, run: 'java XMLTests'

#### Questions: 
Each question starts with two base characteristics; the query asked, and the number of points it is worth (defaults 1). I created two types of question: (the abstract class) 'PresetQuestion' and 'LongAnswer'. The difference here is simple; a PresetQuestion comes with a preset solution, which is the only accepted answer to that question. A LongAnswer question, however, has no set response. Both types of questions are useful to have. Preset questions allow for automated grading, which makes things a lot easier for the prof. On the other hand, they can be too restrictive, and essay-style questions provide the strongest basis for evaluating understanding.

The abstract 'PresetQuestion' then led to two concrete classes, 'MCQ' and 'ShortAnswer'. These differ from each other in what is stored (the additional options for a MCQ) as well as presentation when the quiz is given (MCQ displays all options, including solution; ShortAnswer distinctly does not.)

All Questions have appropriate functions for storing all necessary data into xml, and then recovering it from a DOM node. They also come with functions to check whether an answer is accurate - a PresetQuestion will return an Optional Double, while the LongAnswer returns the empty Option. This comes in useful for grading.

#### Quiz:
The Quiz basically just stores Questions, although it also keeps track of the total number of points involved for easier grading. Of course, it also has the necessary functions to read from and right to xml.

#### AnswerBooklet:
A Quiz is a mere template for an exam; it doesn't turn into something actionable until it's used to create an AnswerBooklet. This stores not only the questions, but the student's name, answers and grades. The AnswerBooklet was separated from the Quiz so that the same Quiz could be used as a template to create AnswerBooklets for many students to use, without needing to worry about storing, overwriting or sharing data. It means each student's answers and grades are considered and stored separately, and that a specific student can be given (and re-given) the test easily. The AnswerBooklet contains a lot of the logic for iterating over asking and grading each Answer, then stores and keeps track of the resultant grades.

#### Answers:
Each Answer links to the relevant Question, but also has fields for the response and the grade. It's asking and grading presentation depend on the type of Question asked, but this difference is handled in the different Question objects so that the Answer interface can work the same. 



### Week 2

Summary statistics: create a java program that takes in *n* ints and prints the min, max and average.