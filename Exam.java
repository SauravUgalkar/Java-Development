import java.util.*;

class Timer extends Thread{
    public void run(){
        try{
            
            Thread.sleep(60*1000);
            Exam.timeup = true;
            System.out.println("Time out!. Your score is "+Exam.score);
            System.exit(0);

        }catch(Exception e){
            System.out.println(e);
        }
         
    }

}

class Question {
    String question;
    String option1;
    String option2;
    String option3;
    String option4;
    String correctIndex;

    Question(String question, String option1,String option2,String option3,String option4,String correctIndex){
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctIndex = correctIndex;
        
    }

}

public class Exam {

    static Scanner sc = new Scanner(System.in);
    String username;
    String password;
    static int score;
    static boolean timeup = false;

    Exam(String username,String password){
        this.username = username;
        this.password = password;
    }

    static void login(List<Exam> users, List<Question> option) {
        while (true) {
            System.out.println("\nEnter your Credentials \n");
            System.out.println("Enter your username: ");
            String username = sc.nextLine();
            System.out.println("Enter your password: ");
            String pass = sc.nextLine();

            for (Exam u : users) {
                if (u.username.equals(username) && u.password.equals(pass)) {
                    System.out.println("Login Successful\n");
                    StartExam(option);
                    return;
                }
            }
            System.out.println("Invalid Credentials\n");
        }
    }
     static void StartExam(List<Question> option){
    while (true) {
        System.out.println("click 1 to start your exam");
        int i = sc.nextInt();
        if(i==1){
            System.out.println("\nstart your Exam.\n");
            // int count = 0;

            for(Question q : option){
                if(timeup){
                    break;
                }
                System.out.println("Q : "+q.question);
                System.out.println("  Opt : "+"1. "+q.option1+"  2. "+q.option2+"  3. "+q.option3+"  4. "+q.option4);
                System.out.println("Enter correct option : ");

                try{
                    String s = sc.next();
                    if(q.correctIndex.equals(s)){
                    System.out.println("\ncorrect! \n");
                    score++; 
                    }else{
                    System.out.println("sorry! your ans is incorrect.");
                    }
                }catch(Exception e){
                System.out.println(e);
                }

    
            }
            System.out.println("\nyou score is: "+score);
            if(score >= 3){
                System.out.println("\ncongratulations! you are pass.");
            }else{
                System.out.println("\nBetter luck next time.");
            }
            System.out.println("thank you for yout time.");
            System.exit(0);
        }else{
            System.out.println("ok! start your exam after some time");
        }
    }

  }
       


    public static void main(String[] args) {

        System.out.println("\nWelcome to Exam center \n");
        
        List<Exam> users = new ArrayList<>();
        users.add(new Exam("user1", "pass1"));
        users.add(new Exam("user2", "pass2"));

        List<Question> option =  new ArrayList<>();
        option.add(new Question("What is 5+5?","10","0", "15", "25","1"));
        option.add(new Question("\"What is 7+5?","-2","35", "12", "1","3"));
        option.add(new Question("What is 0*0?","00","0", "0^2", "nothing","2"));
        option.add(new Question("Who is the best friend of Mayur?","Lavkesh","Mahesh", "Saurav", "loyal with everyone","2"));

        System.out.println("Note: you need to complete your exam before 1 minute.");

        Timer obj = new Timer();
        obj.start();

       Exam.login(users,option);
       sc.close();
      

    }
    
}
