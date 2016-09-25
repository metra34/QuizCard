import java.io.*;

public class QuizCard implements Serializable{
    // A single card holds one question and the corresponding answer
    private String q;
    private String a;
    
    public QuizCard(String q, String a){
	this.q = q;
	this.a = a;
    }
    
    public String getQuestion(){
	return this.q;
    }
    
    public String getAnswer(){
	return this.a;
    }
    
    public void setCard(String q, String a){
	this.q = q;
	this.a = a;
    }
}
