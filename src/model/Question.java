package model;

public class Question {
    public int id;
    public String question;
    public String[] options;
    public int correct;
    public String difficulty;

    public Question(int id, String question, String[] options, int correct, String difficulty) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.correct = correct;
        this.difficulty = difficulty;

    }
    @Override
    public String toString() {
        return
                "\nID: " + id +
                        "\nQuestion: " + question +
                        "\n1. " + options[0] +
                        "\n2. " + options[1] +
                        "\n3. " + options[2] +
                        "\n4. " + options[3] +
                        "\nCorrect: Option " + correct +
                        "\nDifficulty: " + difficulty +
                        "\n----------------------------";
    }
}