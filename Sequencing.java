import java.util.*;

public class Sequencing {

    public static final char[] NUCLEOTIDES = {'A','C','G','T'};

    public static void main(String[] args) {
        String[] sequences = {"ACTGTG","ACTGCC","ATCGCT","ACTGTG"};
        int[][] counts = counts(sequences);
        double[][] probs = probabilities(counts);
        // Overall Results
        String[] correctSquence = result(probs);
        System.out.println(Arrays.toString(correctSquence));
    }

    //Returns the index of the nucleotide. 
    //Parameters:
    //      char nucleotide - takes a nucleotide token 
    //Returns:
    //      i - index of nucleotide. 
    public static int indexOfNucleotide(char nucleotide) {
        for (int i = 0; i < NUCLEOTIDES.length; i++) {
            if (nucleotide == NUCLEOTIDES[i]) {
                return i;
            }
        }
        return -1;
    }

    //Calculate the frequency of each nucleotide across the sequences. 
    //Parameters:
    //      sequences - takes a array of DNA sequences. 
    //Returns:
    //      nucPosition - Array representing frequency of each nucleotide in sequences.
    public static int[][] counts(String[] sequences) {
        int rows = sequences[0].length();
        int[][] nucPosition = new int[rows][NUCLEOTIDES.length];
        for (int i = 0; i < sequences.length; i++) {
            for (int j = 0; j < sequences[i].length(); j++) {
                int position = indexOfNucleotide(sequences[i].charAt(j)); 
                nucPosition[j][position] = nucPosition[j][position] + 1;
            }
        }
        return nucPosition;
    }

    //Determines the probability of each nucleotide occurring at each position.  
    //Parameters:
    //      counts - takes a 2D array representing frequency of nucleotides in sequences.
    //Returns:
    //      probArr - 2D array of probability of each nucleotides' position.
    public static double[][] probabilities(int[][] counts) {
        double[][] probArr = new double[counts.length][counts[0].length];
        double sum = 0;
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i].length; j++) {
                sum = sum + counts[i][j];
            }
            for (int k = 0; k < counts[i].length; k++) {
                probArr[i][k] = counts[i][k] / sum;
            }
            sum = 0;
        }
        return probArr; 
    }

    //Find the most likely DNA sequence. 
    //Parameters:
    //      probs - Probabilities of nucleotides
    //Returns:
    //      finalPrediction - predicted nucleotide array
    public static String[] result(double[][] probs) {
        String[] finalPrediction = new String[probs.length];
        for (int i = 0; i < probs.length; i++) {
            finalPrediction[i] = getMaxNucleotide(probs[i]);
        }
        return finalPrediction;
    }

    //Find the most probable nucleotide(s) given the probability array. 
    //Parameters:
    //      probs - the probabilities of different nucleotides. 
    //Returns:
    //      prediction - most probable nucleotide in the position. 
    public static String getMaxNucleotide(double[] probs) {
        double max = 0;
        String prediction = "";
        for (int i = 0; i < probs.length; i++) {
            max = Math.max(probs[i], max);
        }
        for (int i = 0; i < probs.length; i++) {
            if (max == probs[i]) {
                prediction = prediction + NUCLEOTIDES[i];
            }
        }
        return prediction; 
    }
}
