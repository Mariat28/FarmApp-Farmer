package ug.global.glofarmedited.supporttools.adapters;

public class ListViewObjects {

    private String question;
    private String answer;

    public ListViewObjects(String answer, String question) {
        this.answer = answer;
        this.question = question;
    }

    String getAnswer() {
        return answer;
    }

    String getQuestion() {
        return question;
    }


}
