package org.example;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {
    // Create a new JSONObject
    public static Gson gson;

    public static void main(String[] args) {


        List<String> data= cleanData();
        int numberOfRows=(data.size())/3;
        String[] emails=new String[numberOfRows];
        String[] subjects=new String[numberOfRows];
        String[] messages=new String[numberOfRows];

        emails[0]=data.get(0);
        int previousValue=0;
        for (int x=0;x< data.size();x+=1){

            if (previousValue>=numberOfRows*3)break;
            emails[x]=data.get(previousValue);
            previousValue+=3;

        }

         previousValue=1;
        for (int x=0;x< data.size();x+=1){

            if (previousValue>=numberOfRows*3)break;
            subjects[x]=data.get(previousValue);
            previousValue+=3;

        }
        previousValue=2;
        for (int x=0;x< data.size();x+=1){

            if (previousValue>=numberOfRows*3)break;
            messages[x]=data.get(previousValue);
            previousValue+=3;

        }
       // subjects[x]=data.get(x+1);
        //messages[x]=data.get(x+2);

        /*System.out.println(emails.length);
        System.out.println(subjects.length);
        System.out.println(messages.length)*/;

        List<PromptCompletionClass> promptCompletionClassList=new ArrayList<>();
        for (int x=0;x< 5;x+=1){

          promptCompletionClassList.add(getPromptChoice(subjects[x],messages[x]));

        }

        convertToJson(promptCompletionClassList);
        //System.out.println(subjects);
        //System.out.println(messages);

    }

    private static void convertToJson(List<PromptCompletionClass> promptCompletionClasses) {
        gson = new GsonBuilder().setPrettyPrinting().create();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        String json = gson.toJson(promptCompletionClasses.toArray());

        // print the JSON string
        System.out.println(json);
        for (int x=0;x< 5;x+=1){

            System.out.println(
                    "...."
            );

        }
        System.out.println(
                "Do you want to save the output? \n" +
                        "1: yes\n"
        );
        Scanner input=new Scanner(System.in);
        int answer=input.nextInt();

        if (answer == 1) {
            saveJSONFile(json);
        }

    }
    private static void saveJSONFile(String json){
        // save the JSON string to a file
        try (FileWriter fileWriter = new FileWriter("data.json")) {
            fileWriter.write(json);
            System.out.println("JSON data saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static PromptCompletionClass getPromptChoice( String subject, String message) {
        String prompt1="good";
        String prompt2="bad";
        String prompt3="kinda";
        System.out.println(
                "loading workstation"
        );
        for (int x=0;x< 7;x+=1){

            System.out.println(
                   "...."
            );

        }
        System.out.println(
                "Subjects:"+subject+"\n" +
                "Messages:"+message
        );
        System.out.println("what sort of prompt is this? \n " +
                "1. "+prompt1+"\n"+
                "2. "+prompt2+"\n"+
                "3. "+prompt3+"\n"
        );

        PromptCompletionClass promptCompletionClass=new PromptCompletionClass();
        promptCompletionClass.setCompletion(
                "Subjects:"+subject+"\n" +
                "Messages:"+message);
        Scanner input=new Scanner(System.in);
        int answer=input.nextInt();

        switch (answer) {
            case 1 -> promptCompletionClass.setPrompt(prompt1);
            case 2 -> promptCompletionClass.setPrompt(prompt2);
            case 3 -> promptCompletionClass.setPrompt(prompt3);
        }

        return promptCompletionClass;
    }

    private static List<String> cleanData() {
        BufferedReader reader = null;
        List<String> data=new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("C:/Users/USER/Downloads/mignon-merchants/untitled/src/main/java/org/example/cleanedData.txt"));

            String line;
            int x=0;
            while ((line = reader.readLine()) != null) {

                String emailId = null, subject = null, message = null;
                //skip blank lines
                //email Id
                if(line.trim().isEmpty()){
                    continue;
                }
                else if (line.endsWith(":**")){
                    emailId=line;
                }else if (line.startsWith("**") && line.endsWith("**")){

                    subject=line;


                    StringBuilder builder=new StringBuilder();

                    try {
                        while ((line = reader.readLine()) != null && !line.startsWith("&")) {
                            builder.append(line);
                        }
                        message=builder.toString();
                    }catch (Exception e){
                        e.printStackTrace();
                    }



                }
                else continue;


                if (emailId!=null){
                    data.add(emailId);
                }
                if (subject!=null){
                    data.add(subject);
                    data.add(message);
                }

              /*  System.out.println("email is "+emailId);
                System.out.println("subject is "+subject);
                System.out.println("message is "+message);*/
                System.out.println(data.get(x));
                x+=1;

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
