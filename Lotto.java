import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Lotto {
    private static boolean doesValueExistInArray(int value , int [] numbers ){
        for (int i = 0; i < numbers.length; i++){
            if(value == numbers[i]){
                return true;
            }
        }
        return false;
    }
    private static int outOfRange(int value) {
            do{
                if (value<1||value>46) {
                    System.err.println("The number "+value+" is out of range!");
                    break;
                }
            }while (value<1||value>46);

        return value;
    }


    private static int getIntegerFromUser() {

        boolean isNumberIntValid = true;
        int validInteger = 0;


        do{
            try {
                Scanner sc = new Scanner(System.in);
                validInteger=sc.nextInt();
                isNumberIntValid = true;

            }catch (InputMismatchException e) {

                System.err.println("The format is not correct, please, enter the numeric value!");
                isNumberIntValid = false;
            }
        }while(!isNumberIntValid);

        return validInteger;
    }
    private static int [] getUserArray() {
        Scanner sc = new Scanner(System.in);

        boolean isValid , isDuplicated;
        int tmpValue = 0;
        int[] userArray = new int[6];
        System.out.println("Enter six numbers of the array from 1 to 46, no duplicate numbers: ");
        boolean isNumberIntValid;
        for (int i = 0; i < userArray.length; i++) {
            do {
                isValid = false;
                isDuplicated = false;
                tmpValue = getIntegerFromUser();
                if(tmpValue<1||tmpValue>46) {
                        System.err.println("The number "+tmpValue+" is out of range!");
                        continue;
                }
                for (int j = 0; j <= i; j++) {
                    if (userArray[j] == tmpValue) {
                        System.err.println("The number " + tmpValue + " is duplicated!");
                        isDuplicated = true;
                        break;
                    }
                }
                isValid = !isDuplicated;
            }while (!isValid);
            // --- number is valid
            userArray[i] = tmpValue;
        }
        return userArray;
    }
    private static int [] getlotteryArray(){
        boolean isRepeated; //equal to doesValueExistInArray()
        Random rand = new Random();
        int min = 1, max = 46, slots = 6; //to define var to be const values "final"

        int lotteryArray[] = new int[slots]; //all elements are initialised to be zeros

        int randomNumber = 0;
        boolean isValueExist;
        for (int i = 0; i < slots; i++) {
            do {
                isRepeated = false;
                randomNumber = rand.nextInt(max + 1 - min) + min; //1 to 46 //do from 60 to 90
                isRepeated =  doesValueExistInArray(randomNumber,lotteryArray);
            }
            while (isRepeated);
            lotteryArray[i] = randomNumber;
        }

        return lotteryArray;

    }
    private static void getGuessed() {
        int[]userArray=getUserArray();
        System.out.println("The array created is "+ Arrays.toString(userArray));

        int[] lotteryArray = getlotteryArray();
        System.out.println("The lottery array is "+ Arrays.toString(lotteryArray));
        int counter = 0;

        for (int i = 0; i < userArray.length; i++) {

            if(doesValueExistInArray(userArray[i],lotteryArray)){
                counter++;
            }
            }
        System.out.println("You guessed "+counter+" numbers");
        }

    public static void main(String[] args) {
        getGuessed();
    }
}




