import java.util.*;

class algo2{
    public static int wordToNumber(String s){
        String[] s1 = s.split(" ");
        int res = 0;

        for(int i = 0 ; i < s1.length ; i++){
            if(s1[i].equals("one")){
                res = res+ 1;
            }
            else if(s1[i].equals("two")){
                res= res + 2;
            }
            else if(s1[i].equals("three")){
                res= res + 3;
            }
            else if(s1[i].equals("four")){
                res = res+ 4;
            }
            else if(s1[i].equals("five")){
                res = res+ 5;
            }
            else if(s1[i].equals("six")){
                res = res+ 6;
            }
            else if(s1[i].equals("seven")){
                res= res + 7;
            }
            else if(s1[i].equals("eight")){
                res = res + 8;
            }
            else if(s1[i].equals("nine")){
                res= res + 9;
            }
            else if(s1[i].equals("ten")){
                res= res + 10;
            }
            else if(s1[i].equals("eleven")){
                res= res+ 11;
            }
            else if(s1[i].equals("twelve")){
                res= res+ 12;
            }
            else if(s1[i].equals("thirteen")){
                res= res+ 13;
            }
            else if(s1[i].equals("fourteen")){
                res= res + 14;
            }
            else if(s1[i].equals("fifteen")){
                res = res + 15;
            }
            else if(s1[i].equals("sixteen")){
                res = res + 16;
            }
            else if(s1[i].equals("seventeen")){
                res= res + 17;
            }
            else if(s1[i].equals("eighteen")){
                res= res+ 18;
            }
            else if(s1[i].equals("nineteen")){
                res = res + 19;
            }
            else if(s1[i].equals("twenty")){
                res= res + 20;
            }
            else if(s1[i].equals("thirty")){
                res = res + 30;
            }
            else if(s1[i].equals("forty")){
                res= res+ 40;
            }
            else if(s1[i].equals("fifty")){
                res = res + 50;
            }
            else if(s1[i].equals("sixty")){
                res= res + 60;
            }
            else if(s1[i].equals("seventy")){
                res= res + 70;
            }
            else if(s1[i].equals("eighty")){
                res = res+ 80;
            }
            else if(s1[i].equals("ninety")){
                res= res + 90;
            }
            else if(s1[i].equals("hundred")){
                res = res * 100;
            }
            else if(s1[i].equals("thousand")){
                res= res * 1000;
            }
        }
            return result;
    }
    public static String[] Convert(String s){
        s = s.replaceAll(" + ", "=_ + _");
        s = s.replaceAll("-", "_-_");
        s = s.replaceAll("/", "_/_");
        // s = s.replaceAll(" * ", "multiple");
        s = s.replaceAll("plus", "_+_");
        s = s.replaceAll("substraction", "_-_");
        s = s.replaceAll("division", "_/_");
        s = s.replaceAll("multiplication", "_*_");
        s = s.replaceAll("=", "_=_");

        s = s.replaceAll(" and", "");
        String[] Stringfin = s.split("_");
        for(int i = 0 ; i < Stringfin.length ; i++){


            if(!Stringfin[i].equals("+")&&!Stringfin[i].equals("-")&&!Stringfin[i].equals("*")&&!Stringfin[i].equals("/")&&!Stringfin[i].equals("=")){
                if(Stringfin[i].contains("1")||Stringfin[i].contains("2")||Stringfin[i].contains("3")||Stringfin[i].contains("4")||Stringfin[i].contains("5")||Stringfin[i].contains("6")||Stringfin[i].contains("7")||Stringfin[i].contains("8")||Stringfin[i].contains("9")||Stringfin[i].contains("0")){
                    Stringfin[i] = Stringfin[i].trim();
                }
                else{
                    Stringfin[i] = Integer.toString(wordToNumber(Stringfin[i]));
                }
            }

            System.out.println(Stringfin[i]);
        }
        return Stringfin;
    }

   public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = "";
        String[] answerArray = new String[n];
        int k = 0;

        for(int j = 0 ; j <= n ; j++){
            s = sc.nextLine();
            Stack<String> opr = new Stack<String>();
            Stack<String> ope = new Stack<String>();
            String[] s1 = Convert(s);
            for(int i = 0 ; i < s1.length ; i++){
                if(s1[i].equals("+") || s1[i].equals("-") || s1[i].equals("*") || s1[i].equals("/")){
                    oprator.push(s1[i]);
                }
                else if(s1[i].equals("=")){
                    while(!oprator.isEmpty()){
                        String op = opr.pop();
                        String op1 = ope.pop();
                        String op2 = ope.pop();
                        if(op.equals("+")){
                            double ans = Double.parseDouble(op1) + Double.parseDouble(op2);
                            operand.push(Double.toString(ans));
                        }
                        else if(op.equals("-")){
                            double ans = Double.parseDouble(op2) - Double.parseDouble(op1);
                            operand.push(Double.toString(ans));
                        }
                        else if(op.equals("*")){
                            double ans = Double.parseDouble(op1) * Double.parseDouble(op2);
                            operand.push(Double.toString(ans));
                        }
                        else if(op.equals("/")){
                            double ans = Double.parseDouble(op2) / Double.parseDouble(op1);
                            operand.push(Double.toString(ans));
                        }

                    }
                    String answerValue = operand.pop();

                    if(Double.parseDouble(answerValue) == Double.parseDouble(s1[i+1])){
                        answerArray[k] = "true";
                    k++;
                    }
                    else{
                        answerArray[k] = "flase";
                        System.out.println(answerValue);
                    k++;

                    }
                }
                else{
                    operand.push(s1[i]);
                }
            }
        }

        for(int i = 0 ; i < answerArray.length ; i++){
            System.out.println(answerArray[i]);
        }
    }
}