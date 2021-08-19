package com.sabarishdev.calculator;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View;


public class MainActivity extends Activity
{
    private TextView Screen;
    private EditText result;
    private Button AC,Back,ans,Divide,One,Two,Three,Four,Five,Six,Seven,Eight,Nine,Zero,Point,Add,Subtract,Multiply,Ans,Equal;
    private String input="",answer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Screen=findViewById(R.id.display);
        result=(EditText) findViewById(R.id.result);
        AC=findViewById(R.id.clear);
        Back=findViewById(R.id.dele);
        Divide=findViewById(R.id.div);
        One=findViewById(R.id.one);
        Two=findViewById(R.id.two);
        Three=findViewById(R.id.three);
        Four=findViewById(R.id.four);
        Five=findViewById(R.id.five);
        Six=findViewById(R.id.six);
        Seven=findViewById(R.id.seven);
        Eight=findViewById(R.id.eight);
        Nine=findViewById(R.id.nine);
        Zero=findViewById(R.id.zero);
        Point=findViewById(R.id.dot);
        Add=findViewById(R.id.plus);
        Subtract=findViewById(R.id.sub);
        Multiply=findViewById(R.id.mul);
        Equal=findViewById(R.id.equal);

    }

    public void ButtonClick(View view){

        Button button=(Button) view;
        char newTxt;
        String data=button.getText().toString();
        switch(data){
            case "AC":
                input="";
                break;
            case "ANS":
                input=answer;
                break;

            case "X":
                if(input==""){
                    break;
                }
                newTxt=input.charAt(input.length()-1);
                if(newTxt=='*' || newTxt=='.' ||input.contains("/") || input.contains("+")  ){
                    input=input;
                    break;
                }
                if(input.contains("-")){
                    if(input.contains(".")&&input.charAt(0)=='-'&&input.split("\\-").length<=2){
                        input+="*";
                        break;
                    }
                    if(input.split("\\-").length>=2&&input.contains(".")==true){
                        input=input;
                        break;
                    }
                    if(input.split("\\-").length==2&&input.contains(".")==false){
                        input+="*";
                        break;
                    }

                }
                else{
                    input+="*";
                    Solve();
                }
                break;


            case "=":
                if(input==""){
                    break;
                }
                Solve();
                answer=input;
                Screen.setText(answer);
                break;
            case "DEL":
                if(input==""){
                    break;
                }
                String newText=input.substring(0,input.length()-1);
                input=newText;
                break;

            case "+":
                if(input==""){
                    break;
                }
                newTxt=input.charAt(input.length()-1);
                if(newTxt=='+' || newTxt=='.' || input.contains("/") || input.contains("*") ){
                    input=input;
                    break;
                }
                if(input.contains("-")){
                    if(input.contains(".")&&input.charAt(0)=='-'&&input.split("\\-").length<=2){
                        input+="+";
                        break;
                    }
                    if(input.split("\\-").length>=2&&input.contains(".")==true){
                        input=input;
                        break;
                    }
                    if(input.split("\\-").length==2&&input.contains(".")==false){
                        input+="/";
                        break;
                    }
                }
                else{
                    input+="+";
                    Solve();
                }
                break;
            case "/":
                if(input==""){
                    break;
                }
                newTxt=input.charAt(input.length()-1);
                if(newTxt=='/' || newTxt=='.' || input.contains("*") || input.contains("+") || input.contains("^") ){
                    input=input;
                    break;
                }
                if(input.contains("-") ){
                    if(input.contains(".")&&input.charAt(0)=='-'&&input.split("\\-").length<=2){
                        input+="/";
                        break;
                    }
                    if(input.split("\\-").length>=2&&input.contains(".")==true){
                        input=input;
                        break;
                    }
                    if(input.split("\\-").length==2&&input.contains(".")==false){
                        input+="/";
                        break;
                    }
                }
                else{
                    input+="/";
                    Solve();
                }
                break;
            case "-":
                if(input==""){
                    input="-";
                    break;
                }

                newTxt=input.charAt(input.length()-1);
                if(newTxt=='.'){
                    input=input;
                    break;
                }
                if(newTxt=='+' ){
                    String s=input.substring(0,input.length()-1);
                    input=s+"-";
                    break;
                }
                if(newTxt=='-' ){
                    input=input;
                    break;
                }
                else{
                    Solve();
                    input+="-";

                }
                break;
            case ".":
                if(input==""){
                    input="0.";
                    break;
                }
                newTxt=input.charAt(input.length()-1);
                if(newTxt=='.'){
                    input=input;
                    break;
                }
                String[] letters=input.split("(?<=[\\d.])(?=[^\\d.])|(?<=[^\\d.])(?=[\\d.])");
                int len=letters.length;
                if(letters[len-1].contains(".")){
                    input=input;
                    break;
                }
                else{
                    input+="0.";
                }
                break;
            default:
                if(input==null){
                    input="";
                }

                input+=data;

        }
        result.setText(input);
    }

    private void Solve(){
        if(input.split("\\*").length==2){
            String number[]=input.split("\\*");
            try{
                double mul=Double.parseDouble(number[0])*Double.parseDouble(number[1]);
                input=mul+"";
            }
            catch(Exception e){

            }
        }
        else if(input.split("\\/").length==2){
            String number[]=input.split("\\/");
            try{
                double div=Double.parseDouble(number[0])/Double.parseDouble(number[1]);
                input=div+"";
            }
            catch(Exception e){

            }
        }
        else if(input.split("\\+").length==2){
            String number[]=input.split("\\+");
            try{
                double add=Double.parseDouble(number[0])+Double.parseDouble(number[1]);
                input=add+"";
            }
            catch(Exception e){

            }
        }
        else if(input.split("\\-").length==3){
            String numbers[]=input.split("\\-");
            //to subtract from negative number

            try{
                double sub=Double.parseDouble(numbers[1])-Double.parseDouble(numbers[2]);
                input=sub+"";
            }
            catch(Exception e){

            }
            return;

        }
        else if(input.split("\\-").length==2){
            String number[]=input.split("\\-");
            //to subtract from negative number
            if(number[0]==""&&number.length==2){
                number[0]=0+"";
                try{
                    double sub=Double.parseDouble(number[0])-Double.parseDouble(number[1]);
                    input=sub+"";
                }
                catch(Exception e){

                }
                return;
            }
            try{
                double sub=Double.parseDouble(number[0])-Double.parseDouble(number[1]);
                input=sub+"";
            }
            catch(Exception e){

            }
        }
        String n[]=input.split("\\.");
        if(n.length>1){
            if(n[1].equals("0")){
                input=n[0];
            }
        }
        result.setText(input);
    }

}