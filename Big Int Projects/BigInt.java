import java.util.ArrayList;
import java.util.List;

public class BigInt {
   int[] digits;
   boolean isNegative;
   int chunkSizes = 0;

   //a BigInt constructor with the attribute digs, a deep copy of the string digits
   public BigInt(String digits) {
      this.isNegative = false;

      if (digits.charAt(0) == '-') {
         this.isNegative = true;
         String sub = "";
         for (int i = 1; i < digits.length(); i++) {
            sub += digits.charAt(i);
            this.digits = new int[digits.length() - 1];
         }
         digits = sub;
      } else {
         this.digits = new int[digits.length()];
      }

      for (int i = 0; i < digits.length(); i++) {
         this.digits[i] = Character.getNumericValue(digits.charAt(i));
      }

      /*if(this.isNegative == true) {
         this.digits[0] = this.digits[0] * -1;
      }*/
   }

   //a method to convert bigInt digits to chunks of variable length
   public int[] toChunk(int chunkLen) {
      int[] chunks = new int[this.digits.length / chunkLen + 1];
      int k = 0;
      for (int i = 0; i < this.digits.length; i++) {
         k = i / chunkLen;
         chunks[chunks.length - 1 - k] += this.digits[this.digits.length - 1 - i] * Math.pow(10, i % chunkLen);
      }

      return chunks;
   }

   //finds the bigint with the maximum length of digits
   public BigInt findMax(BigInt another) {
      if (this.digits.length > another.digits.length) {
         return this;
      } else {
         return another;
      }
   }

   public BigInt add(BigInt another) {
      List<Integer> ret = new ArrayList<>();
      int numOfOps = 0;
      int[] longer;
      int[] shorter;
      int chunkLen = 9;
      boolean negative = false;
      //a series of if statements to operate on the bigints based on their signs
      if (this.isNegative == true && another.isNegative == false) {
         return another.subtract(this);
      } else if (this.isNegative == false && another.isNegative == true) {
         return this.subtract(another);
      } else if (this.isNegative == true && another.isNegative == true) {
         negative = true;
      }

      //finds the longer and shorter of the digits and assigns them to respective arrays
      if (this.findMax(another).digits.length == this.digits.length) {
         longer = this.toChunk(chunkLen);
         shorter = another.toChunk(chunkLen);
      } else {
         longer = another.toChunk(chunkLen);
         shorter = this.toChunk(chunkLen);
      }

      addHelper(longer, shorter, chunkLen, ret);
      carry(ret, chunkLen);
      if (negative == true) {
         BigInt retBI = new BigInt("-" + arrListToStr(ret, chunkLen));
         retBI.chunkSizes = chunkLen;
         return retBI;
      }
      BigInt retBI = new BigInt(arrListToStr(ret, chunkLen));
      return retBI;
   }

   //an addHelper method to simplify the add method
   //this is where the actual addition takes place
   public void addHelper(int[] longer, int[] shorter, int chunkLen, List<Integer> ret) {
      int numOfOps = 0;
      double remainder = 0;
      int j = 0;
      //adds chunks until the shorter is completely added
      for (int i = 0; i < shorter.length; i++) {
         ret.add(0, shorter[shorter.length - 1 - i] + longer[longer.length - 1 - i]);
         j++;
         numOfOps++;
      }

      //a loop that adds the rest of the longer array
      for (int i = j; i < longer.length; i++) {
         ret.add(0, longer[longer.length - 1 - i]);
         numOfOps++;
      }
      //System.out.println(numOfOps);
   }

   public BigInt subtract(BigInt another) {
      boolean negative = false;
      int chunkLen = 9;
      //if statements that operate appropriately on this and another based on their signs
      if (this.isNegative == false && another.isNegative == true) {
         return this.add(another);
      } else if (this.isNegative == true && another.isNegative == false) {
         BigInt retBI = new BigInt("-" + this.add(another).digits);
         return retBI;
      } else if (this.isNegative == false && another.isNegative == true) {
         return this.add(another);
      }
      //operates on the BigInts based on if the first is bigger than the second
      if (this.digits.length < another.digits.length) {

         BigInt retBI = new BigInt(another.subtract(this).toString());
         retBI.isNegative = true;
         retBI.chunkSizes = chunkLen;
         return retBI;
      }

      int[] one;
      one = this.toChunk(chunkLen);
      int[] two;
      two = another.toChunk(chunkLen);
      List<Integer> ret = new ArrayList<>();
      int j = 0;
      int k = 0;
      int num = 0;
      subtractionCarry(one, two, k, chunkLen);

      //a loop that subtracts until one number is empty
      for (int i = 0; i < one.length && i < two.length; i++) {
         ret.add(0, one[one.length - 1 - i] - two[two.length - 1 - i]);
         j++;
         num++;
      }
      //a loop that subtracts the remainder of one if it is left over
      while (j < one.length) {
         ret.add(0, one[one.length - 1 - j]);
         j++;
         num++;
      }
      //a loop that subtracts the remainder of two if it is left over
      while (j < two.length) {
         if (this.isNegative == false)
            negative = true;
         else if (another.isNegative == true)
            negative = false;
         ret.add(0, two[two.length - 1 - j]);
         j++;
         num++;
      }

      BigInt resBI;
      if (negative == true) {
         resBI = new BigInt("-" + arrListToStr(ret, chunkLen));
         resBI.isNegative = true;
      } else {
         resBI = new BigInt(arrListToStr(ret, chunkLen));
      }
      //System.out.println(num);
      resBI.chunkSizes = chunkLen;
      return resBI;

   }

   public void subtractionCarry(int[] one, int[] two, int k, int chunkLen) {
      //a loop to carry numbers to ease subtraction
      for (int i = 0; i < one.length && i < two.length; i++) {
         //a nested loop that carries numbers until proper subtracting conditions are acheived
         while (one[one.length - 1 - i] < two[two.length - 1 - i] && i < one.length - 1) {
            k = i;
            //a nested loop that carries from the nearest non-zero int
            while (one[one.length - 2 - k] == 0 && k < one.length - 2) {
               k++;

            }
            one[one.length - 1 - k] += Math.pow(10, chunkLen);
            one[one.length - 2 - k]--;
         }
      }
   }

   //a method to multiply two BigInt
   public BigInt multiply(BigInt another) {
      List<Integer> ret = new ArrayList<>();
      int[] longer;
      int[] shorter;
      int chunkLen = 4;
      //finds the longer and shorter of the digits and assigns them to respective arrays
      if (this.findMax(another).digits.length == this.digits.length) {
         longer = this.toChunk(chunkLen);
         shorter = another.toChunk(chunkLen);

      } else {
         longer = another.toChunk(chunkLen);
         shorter = this.toChunk(chunkLen);
      }
      int num = 0;
      //a loop that multiplies the first digit in shorter with the digits in longer
      for (int i = 0; i < longer.length; i++) {
         ret.add(0, longer[longer.length - 1 - i] * shorter[shorter.length - 1]);
         num++;
      }
      carry(ret, chunkLen);
      //a loop that multiplies the rest of shorter and longer
      for (int i = 1; i < shorter.length; i++) {
         for (int j = 0; j < longer.length; j++) {
            ret.set(ret.size() - 1 - j,
                  ret.get(ret.size() - 1 - j) + (shorter[shorter.length - 1 - i] * longer[longer.length - 1 - j])
                        * (int) Math.pow(10, chunkLen * i));
            num++;
         }
      }

      BigInt resBI = new BigInt(arrListToStr(ret, chunkLen));
      if ((this.isNegative == true && another.isNegative == false)
            || (another.isNegative == true && this.isNegative == false)) {
         resBI.isNegative = true;
      }
      //System.out.println(num);
      resBI.chunkSizes = chunkLen;
      return resBI;
   }

   //a method to carry numbers 
   public void carry(List<Integer> ret, int chunkLen) {
      //a loop that carries excess numbers in ret to acheive the proper bigInt
      int carry = 0;
      for (int i = ret.size() - 1; i >= 0; i--) {
         ret.set(i, ret.get(i) + carry);
         if (ret.get(i) >= (int) Math.pow(10, chunkLen)) {
            carry = ret.get(i) / (int) Math.pow(10, chunkLen);
            ret.set(i, ret.get(i) - carry * (int) Math.pow(10, chunkLen));
         } else {
            carry = 0;
         }
      }
   }

   //a method to divide one BigInt from another
   public BigInt divide(BigInt another) {
      boolean neg = this.isNegative;
      BigInt count = new BigInt("0");
      count.isNegative = count.isNegative;
      BigInt copy = new BigInt(this.toString());
      int j = 0;
      while (Integer.parseInt(copy.toString()) > Integer.parseInt(another.toString())) {
         copy = new BigInt(copy.subtract(another).toString());

         count = new BigInt(count.add(new BigInt("1")).toString());

         j++;
      }
      System.out.println(j);
      count = new BigInt(count.add(new BigInt("1")).toString());
      return count;
   }

   //a method to convert a Bigint to a string
   public String toString() {
      String ret = "";
      for (int i = 0; i < this.digits.length; i++) {
    	  int k = 0;
    	  while((int) (Math.log10(this.digits[i+k]) + 1) + k < this.chunkSizes) {
    		  ret = "0" +ret;
    		  k++;
    	  }
         ret += this.digits[i];
      }
      if (this.isNegative == true)
         ret = "-" + ret;
      return ret;
   }

   //a method to convert an array list to string
   public String arrListToStr(List<Integer> list, int chunkLen) {

      //a loop that removies excess 0s in list
      while (list.get(0) == 0) {
         list.remove(0);
      }

      String ret = "";
      //a loop that allocates the contents of list onto a string
      for (int k = 0; k < list.size(); k++) {
         ret += list.get(k);
         //a nested loop that adds necessary 0s
         for (int i = list.get(k).toString().length(); i < chunkLen; i++) {
            ret = "0" + ret;
         }
      }
      return removeZeroes(ret);
   }

   //a method to convert an array to a string
   public String arrToString(int[] array) {
      String ret = "";

      for (int i = 0; i < array.length; i++) {
         ret += array[i];
      }

      return ret;
   }

   //a method to remove excess zeroes in a string
   public String removeZeroes(String ret) {
      int toRemove = 0;
      //while there are excess zeroes in ret, the number to remove increments
      //in this loop, toRemove doubles as a means to progress the reading of ret
      while (ret.charAt(toRemove) == '0' && toRemove < ret.length()) {
         toRemove++;
      }
      String subRet = "";
      //the contents of ret until excess zeroes are added to substring
      for (int i = 0; i < ret.length() - toRemove; i++) {
         subRet = ret.charAt(ret.length() - 1 - i) + subRet;
      }
      return subRet;

   }

}