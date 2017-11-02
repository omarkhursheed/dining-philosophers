import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;  

public class Philosophy101{

 public static Semaphore monitor=new Semaphore(1);
	public static void main(String[] args) {
		System.out.println("Number of philosophers");
		String [] names = {"Spivak", "Derrida", "Zizek", "Hume", "Neitzche","Plato","Pythagoras"};
				Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();		
		int size = n;
		FileWriter[] fileList = new FileWriter[size];
		try{
		String fileName0 = "Spivak.txt";
		File file0 = new File(fileName0);
		FileWriter out0 = new FileWriter(file0);
		String fileName1 = "Derrida.txt";
		File file1 = new File(fileName1);
		FileWriter out1 = new FileWriter(file1);
		String fileName2 = "Zizek.txt";
		File file2 = new File(fileName2);
		FileWriter out2 = new FileWriter(file2);
		String fileName3 = "Hume.txt";
		File file3 = new File(fileName3);
		FileWriter out3 = new FileWriter(file3);
		String fileName4 = "Neitzche.txt";
		File file4 = new File(fileName4);
		FileWriter out4 = new FileWriter(file4);
				fileList[0] = out0;
		fileList[1] = out1;
		fileList[2] = out2;
		fileList[3] = out3;
		fileList[4] = out4;
		} catch(IOException e){
			e.printStackTrace();
		}
		Philosopher[] philosophers = new Philosopher[size];
		Chopstick[] stickList = new Chopstick[size];

		for (int i = 0; i < size; i++){
			stickList[i] = new Chopstick(i, monitor);  
		}
		for (int i = 0; i < size; i++){
			/*System.out.println("Enter philosophers name");
			String name = scan.next();
			*/
			System.out.println("Enter eating time for "+ names[i]);
			int tempEat = scan.nextInt();
			System.out.println("Enter thinking time for "+ names[i]);
			int tempThink = scan.nextInt();
			
			if (i != size-1){
				philosophers[i] = new Philosopher(stickList[i], stickList[i+1], names[i], tempThink, tempEat, monitor,fileList[i]);
			} else {
				philosophers[i] = new Philosopher(stickList[0], stickList[i], names[i], tempThink, tempEat, monitor, fileList[i]);	
			}
		}

		
		for( int i=0; i<size;i++)
		{
			philosophers[i].start();
			while(!monitor.tryAcquire());
			System.out.println(philosophers[i].name+ " is thinking");
			monitor.release();

		}


		
	}
}
class Philosopher extends Thread{
	Chopstick leftChopstick;
	Chopstick rightChopstick;
	String name;
	int initTime = 0;
	int thinkingTime;
	int eatingTime;
	int t=10;
	Semaphore monitor;
	int status;
	FileWriter out;
	Philosopher(Chopstick l, Chopstick r, String nam, int t, int e,Semaphore m, FileWriter out){
		this.leftChopstick = l;
		this.rightChopstick = r;
		this.name = nam;
		this.thinkingTime = t;
		this.eatingTime  = e;
		this.monitor=m;
		this.out = out;
	}
	public void getStatus(){

	}
	public void run(){
		while(t>0){
			think();
			int tempTime = initTime;
			while(!this.leftChopstick.getChopstick(this.name)) waiting();
			while(!this.rightChopstick.getChopstick(this.name)) waiting();
			int waitingTime = initTime-tempTime;
			try{
			this.out.write("Waiting,"+tempTime+'\n');
			} catch(IOException e){
				e.printStackTrace();
			}
			eat();
			this.leftChopstick.dropLeftChopstick(this.name);
			this.rightChopstick.dropRightChopstick(this.name);
			t--;	
		}
		try{
			this.out.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void eat(){
		this.status = 1;
		while(!this.monitor.tryAcquire());
		System.out.println(name + " is eating"); 
		this.monitor.release();
		try{	
			sleep(eatingTime);
		} catch(Exception e) {
			e.printStackTrace();
		}
		try{
			this.out.write("Eating,"+initTime+'\n');
		} catch (IOException e){
			e.printStackTrace();
		}
		initTime+=eatingTime;

	}

	public void think(){
		this.status = 0;
			try{
			sleep(thinkingTime);	
		} catch(Exception e) {
			e.printStackTrace();
		}
		try{
			this.out.write("Thinking,"+initTime+'\n');
		} catch(Exception e) {
			e.printStackTrace();
		}
				initTime+=thinkingTime;

}
	public void waiting(){
		initTime++;
		try{
		sleep(1);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
class Chopstick{
	int id;
	Semaphore monitor;
	Semaphore mutex = new Semaphore(1);
	Chopstick(int id, Semaphore m){
		this.id = id;
		this.monitor=m;
	}
	public boolean getChopstick(String s){
		boolean f= mutex.tryAcquire();
		if(f==false)
			return f;
		else {
			while(!this.monitor.tryAcquire());
			System.out.println( "Chopstick number "+this.id+ " is picked up by "+s);
			this.monitor.release();
			return f;
		}
	}
	public void dropRightChopstick(String s){
		while(!this.monitor.tryAcquire());
		System.out.println( "Chopstick number "+this.id+ " is dropped by "+s); 
		
		mutex.release();
		this.monitor.release();
	}
	public void dropLeftChopstick(String s){
		while(!this.monitor.tryAcquire());
		System.out.println( "Chopstick number "+this.id+ " is dropped by "+s); 
		System.out.println(s + " is thinking");
		mutex.release();
		this.monitor.release();
	}
}