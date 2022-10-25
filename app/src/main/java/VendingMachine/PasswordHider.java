package VendingMachine;

import java.io.*;

class PasswordHider implements Runnable {
   private boolean finish;
 
   /**
    *@param The prompt displayed to the user
    */
   public PasswordHider(String message) {
       System.out.print(message);
   }

   /**
    * Begin masking...display asterisks (*)
    */
   public void run () {
      finish = true;
      while (finish) {
         System.out.print("\010*");
	      try {
	         Thread.currentThread().sleep(1);
         } catch(InterruptedException ie) {
            ie.printStackTrace();
         }
      }
   }

   /**
    * Instruct the thread to stop masking
    */
   public void stopMasking() {
      this.finish = false;
   }
}
