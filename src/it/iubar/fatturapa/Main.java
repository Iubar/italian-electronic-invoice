package it.iubar.fatturapa;

public class Main   
{  
	public static void main (String[] args)  
	{  
//		System.out.println("This is currently running on the main thread, " +  
//				"the id is: " + Thread.currentThread().getId());  
		FatturaPaRead worker = new FatturaPaRead();   
		Thread thread = new Thread(worker);  
		thread.start();  

		System.out.println("Sto elaborando...");
		int max = 60; // 60 loop da mezzo secondo, ovvero 30 secondi
		for (int i = 0; i < max; i++) {
			if(!thread.isAlive() || thread.isInterrupted()){
				break;
			}else{
				System.out.print("#");
				try {
					Thread.sleep(500); // 1/2 secondo
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		}
	}  


} 