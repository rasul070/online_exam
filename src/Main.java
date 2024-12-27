import java.util.*;

class Question {
    private String questionText;
    private String[] options;
    private int correctOption;

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }

    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public boolean checkAnswer(int selectedOption) {
        return selectedOption == correctOption;
    }

    @Override
    public String toString() {
        return "Question: " + questionText + ", Options: " + Arrays.toString(options);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Question question = (Question) obj;
        return correctOption == question.correctOption &&
                Objects.equals(questionText, question.questionText) &&
                Arrays.equals(options, question.options);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(questionText, correctOption);
        result = 31 * result + Arrays.hashCode(options);
        return result;
    }
}

class Candidate {
    private String name;
    private int age;
    private String email;

    public Candidate(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void displayDetails() {
        System.out.println("Candidate Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
    }

    @Override
    public String toString() {
        return "Candidate: " + name + ", Age: " + age + ", Email: " + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Candidate candidate = (Candidate) obj;
        return age == candidate.age &&
                Objects.equals(name, candidate.name) &&
                Objects.equals(email, candidate.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, email);
    }
}

class Exam {
    private String examTitle;
    private List<Question> questions;
    private Candidate candidate;

    public Exam(String examTitle, List<Question> questions, Candidate candidate) {
        this.examTitle = examTitle;
        this.questions = questions;
        this.candidate = candidate;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public void conductExam() {
        System.out.println("Welcome to the " + examTitle + " exam!");
        candidate.displayDetails();

        int score = 0;
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            question.displayQuestion();
            System.out.print("Your answer (enter option number): ");
            int answer = scanner.nextInt();
            if (question.checkAnswer(answer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong answer.");
            }
        }

        System.out.println("Exam finished! " + candidate.getName() + ", your score is: " + score + "/" + questions.size());
    }

    @Override
    public String toString() {
        return "Exam: " + examTitle + ", Candidate: " + candidate + ", Questions: " + questions;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Exam exam = (Exam) obj;
        return Objects.equals(examTitle, exam.examTitle) &&
                Objects.equals(questions, exam.questions) &&
                Objects.equals(candidate, exam.candidate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examTitle, questions, candidate);
    }

    public List<Question> searchQuestions(String keyword) {
        List<Question> results = new ArrayList<>();
        for (Question q : questions) {
            if (q.getQuestionText().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(q);
            }
        }
        return results;
    }

    public void sortQuestionsByText() {
        questions.sort(Comparator.comparing(Question::getQuestionText));
    }
}
