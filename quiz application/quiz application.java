import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String questionText;
    List<String> options;
    int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;
    private List<String> summary;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        summary = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            System.out.println(question.questionText);
            for (int i = 0; i < question.options.size(); i++) {
                System.out.println((i + 1) + ". " + question.options.get(i));
            }
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up!");
                    summary.add(question.questionText + " - Time's up!");
                    System.exit(0);
                }
            };
            timer.schedule(task, 10000); // 10 seconds timer

            int userAnswer = scanner.nextInt();
            timer.cancel();

            if (userAnswer == question.correctOption) {
                score++;
                summary.add(question.questionText + " - Correct");
                System.out.println("Correct!");
            } else {
                summary.add(question.questionText + " - Incorrect");
                System.out.println("Incorrect.");
            }
        }

        System.out.println("\nQuiz finished! Your score is: " + score);
        System.out.println("Summary:");
        for (String result : summary) {
            System.out.println(result);
        }
    }
}

 class QuizApp {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        List<String> options1 = new ArrayList<>();
        options1.add("Mamata Banarji");
        options1.add("Narendra modi");
        options1.add("Naveen pattnaik");
        options1.add("Rahul Gandhi");
        quiz.addQuestion(new Question("who is the prime minister of india now?", options1, 2));

        List<String> options2 = new ArrayList<>();
        options2.add("Virat Kohli");
        options2.add("Rohit sharma");
        options2.add("Hardik panday");
        options2.add("Ravindra jadeja");
        quiz.addQuestion(new Question("India win 2024 world cup in whom captaincy?", options2, 2));

        List<String> options3 = new ArrayList<>();
        options3.add("Lebanon");
        options3.add("India");
        options3.add("Pakistan");
        options3.add("Iran");
        quiz.addQuestion(new Question("Khalil Girban is from?", options3, 1));

        quiz.start();
    }
}
