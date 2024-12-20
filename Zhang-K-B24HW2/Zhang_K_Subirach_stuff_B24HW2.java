import java.util.Scanner;
public class Zhang_K_Subirach_stuff_B24HW2 {

    public static class subirachs{
        private int[] magicList = {1, 14, 14, 4, 11, 7, 6, 9, 8, 10, 10, 5, 13, 2, 3, 15};
        private int magicSize = magicList.length;
        private int checker = 0; // Debugging tool 

        public subirachs(){}

        /**
         * Checks if a list equals to the magic number
         */
        private boolean isSum(int[] nums, int magicNumber){
            int sum = 0;
            for(int i=0; i < nums.length; i++) sum+=nums[i];
            return sum==magicNumber;
        } 

        // /**
        //  * Checks if a list equals to the magic number but prints
        //  */
        // private boolean isSumPrint(int[] nums, int magicNumber){
        //     int sum = 0;
        //     for(int i=0; i < nums.length; i++) sum+=nums[i];
        //     System.out.println(sum);
        //     return sum==magicNumber;
        // } 

        /**
         * Finds the number of subsets of size 4 that sum to the magic number 
         * Run this function
         * @return
         */
        private int all4Subsets(){
            int count = 0;
            int combination = 0; // C(16, 4) = 1820
            // Number of combinations
            for (int b1 = 0; b1 < this.magicSize - 3; b1++) { // Order does not matter 
                for (int b2 = b1 + 1; b2 < this.magicSize - 2; b2++) { // Makes sure there is no dubplicates
                    for (int b3 = b2 + 1; b3 < this.magicSize - 1; b3++) {
                        for (int b4 = b3 + 1; b4 < this.magicSize; b4++) {
                            int[] subset = new int[]{magicList[b1], magicList[b2], magicList[b3], magicList[b4]};
                            //printList(new int[]{b1, b2, b3, b4}); // Debugging right combinations 
                            count += isSum(subset, 33) ? 1 : 0;
                            combination++;
                        }
                    }
                }
            }
            System.out.println("Subset Size: 4");
            System.out.println("Combinations: " + combination);
            System.out.println("Number of sum 33: " + count);
            return count;
        }

        /**
         * Recursive algorithm to find all possible combinations 
         * @param start Starting index 
         * @param depth Current index position in generating combination 
         * @param indices Current indices stored to find sum 
         * @param count Current count of sum 33 
         * @param size Size of the subset
         * @param magicNumber Magic number sum to be found 
         * @return number of combinations that sum to the magic number 
         */
        private int countSubsets(int start, int depth, int[] indices, int count, int size, int magicNumber) {
            if (depth == size) { 
                int[] subset = new int[size];
                for(int i = 0; i < size; i++) subset[i] = this.magicList[indices[i]];
            
                this.checker++;
                return count + (isSum(subset, magicNumber) ? 1 : 0);
            }

            for (int i = start; i < this.magicSize - (size - depth - 1); i++) {
                //System.out.println("Start: " + start + " Depth: " + depth + "|" + i);
                indices[depth] = i;
                count = countSubsets(i + 1, depth + 1, indices, count, size, magicNumber);
            }
            return count;
        }


        /**
         * Finds all possible subsets from 0 - 16 to find the magic sum 33 
         */
        private void allXSubsets(){
            for(int size = 0; size <= this.magicSize; size++){
                this.checker = 0;
                int count = countSubsets(0, 0, new int[size], 0, size, 33);
                System.out.print("Subset Size: " + size); 
                System.out.print(" |Number of Combinations: " + checker);
                System.out.println(" |Number of sum 33: " + count);
            }
        }

        /**
         * Finds all combinations sizes for all possible sums.
         * Call this function
         */
        private void allPossibleSums(){
            int max = 0;
            for(int i = 0; i<this.magicSize; i++) max += this.magicList[i]; // Find sum of all 16 numbers 
            for(int sumCheck = 0; sumCheck<=max; sumCheck++){ // Iterate throug all sums between 0-max
                System.out.println("Checking Sum: " + sumCheck);
                for(int size = 0; size <= this.magicSize; size++){ // find all combinations for x size and check for sum 
                    this.checker = 0;
                    int count = countSubsets(0, 0, new int[size], 0, size, sumCheck);
                    System.out.print("Subset Size: " + size); 
                    System.out.print(" |Number of Combinations: " + checker);
                    System.out.println(" |Number of sum "+ sumCheck + ": " + count);
                }
            }

        }

        private void greatestPossibleCombination(){
           // int max = 0;
            //for(int i = 0; i<this.magicSize; i++) max += this.magicList[i]; // Find sum of all 16 numbers 
            for(int sumCheck = 35; sumCheck<=98; sumCheck++){ // Iterate throug all sums between 35-98 
                //(visual help to see where the sums count start and end)
                int size = 8; // half of list size is the largest combination 
                this.checker = 0;
                int count = countSubsets(0, 0, new int[size], 0, size, sumCheck);
                System.out.print("Subset Size: " + size); 
                System.out.print(" |Number of Combinations: " + checker);
                System.out.println(" |Number of sum "+ sumCheck + ": " + count);
            }
        }

    }

    public static void printList(int[] list){
        for(int i = 0; i < list.length; i++){
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        subirachs magic = new subirachs();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Press enter to see number of sum 33 for subset of 4");
        scanner.nextLine();
        magic.all4Subsets();
        System.out.println("\nPress enter to see number of sum 33 for subset of 0-16");
        scanner.nextLine();
        magic.allXSubsets();
        System.out.println("\nPress enter to see number of sum 0-max possible sum for subset of 0-16");
        scanner.nextLine();
        magic.allPossibleSums();
        System.out.println("\nPress enter to see number of possible sums for largest number of combinations");
        System.out.println("Biggest combinations size is the when subset size is half of the original list");
        scanner.nextLine();
        magic.greatestPossibleCombination();
        System.out.println("Largest count was sum of 66 where it is exatly half of the max sum of 132");
    }

}
