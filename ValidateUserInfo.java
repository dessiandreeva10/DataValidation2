import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUserInfo {
	private String name;
	private String EGN;
	private String BULSTAT;
	private String FK;
	private String email;
	
	public ValidateUserInfo(){
		this.name = " ";
		this.EGN = " ";
		this.BULSTAT = " ";
		this.FK = " ";
		this.email = " ";
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getBulstat(){
		return this.BULSTAT;
	}
	
	public String getFK(){
		return this.FK;
	}
	
	public String getEGN(){
		return this.EGN;
	}
	
	public void setName(String name){
	  this.name = name;
	}
		
	public void setEGN(String EGN){
		this.EGN = EGN;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setBulstat(String BULSTAT){
		this.BULSTAT = BULSTAT;
	}
	
	public void setFK(String FK){
		this.FK = FK;
	}
	
	
	public String getEmail(){
		return this.email;
	}
	
	private static String namePattern = "[A-Z]+[a-z]{1,15} [A-Z]+[a-z]{1,15} [A-Z]+[a-z]{1,15}";
	private static String EGNPattern = "[0-9]{10}";
	private static String BULSTATPattern = "[0-9]{9}";
	private static String FKPattern = "[0-9]{2}[-]{1}[0-9]{3}";
	private static String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	
	private static Pattern pattern;
	private static Matcher matcher;
	
	public String askForName(Scanner sc, Pattern p, Matcher m, ValidateUserInfo v){
		String name;
		boolean ret;
		
		System.out.println("Enter your name:");
		sc.nextLine();
		name = sc.nextLine();
		p = Pattern.compile(namePattern);
		m = p.matcher(name);
		ret = m.matches();
		
		if(ret == true){
			v.setName(name);
		}else{
			v.setName(name);
			return " is not a valid name!";
		}
		
		return " is a valid name!";	
	}
	
	public String askForEGN(Scanner sc, Pattern p, Matcher m, ValidateUserInfo v){
		String EGN;
		boolean ret;
		
		System.out.println("Enter your EGN:");
		EGN = sc.next();
		p = Pattern.compile(EGNPattern);
		m = p.matcher(EGN);
		ret = m.matches();
		
		if(ret == true){
			v.setEGN(EGN);
		}else{
			v.setEGN(EGN);
			return " is not a valid EGN!";
		}
		
		return " is a valid EGN!";	
	}
	
	public String askForBulstat(Scanner sc, Pattern p, Matcher m, ValidateUserInfo v){
		String BULSTAT;
		boolean ret;
		
		System.out.println("Enter your Bulstat:");
		BULSTAT = sc.next();
		p = Pattern.compile(BULSTATPattern);
		m = p.matcher(BULSTAT);
		ret = m.matches();
		
		if(ret == true){
			v.setBulstat(BULSTAT);
		}else{
			v.setBulstat(BULSTAT);
			return " is not a valid Bulstat!";
		}
		
		return " is a valid Bulstat!";	
	}
	
	public String askForFK(Scanner sc, Pattern p, Matcher m, ValidateUserInfo v){
		String FK = null;
		boolean ret;
		
		System.out.println("Enter your FK:");
		FK = sc.next();
		p = Pattern.compile(FKPattern);
		m = p.matcher(FK);
		ret = m.matches();
		
		if(ret == true){
			v.setFK(FK);
		}else{
			v.setFK(FK);
			return " is not a valid facility key!";
		}
		
		return " is a valid facility key!";	
	}
	
	public String askForEmail(Scanner sc, Pattern p, Matcher m, ValidateUserInfo v){
		String email;
		boolean ret;
		
		System.out.println("Enter your email:");
		email = sc.next();
		p = Pattern.compile(emailPattern);
		m = p.matcher(email);
		ret = m.matches();
		
		if(ret == true){
			v.setEmail(email);
		}else{
			v.setEmail(email);
			return "is not a valid e-mail!";
		}
		
		return " is a valid e-mail!";	
	}
	
	public static void printResult( ValidateUserInfo v, String[] mesg, String user){
		int i = 0;
		
		for(i = 0 ; i < mesg.length; i++){
			
			if(i == 0){
				System.out.println(v.getName() + mesg[i]);
			} else if(i == 1){
				
				if(user.equals("company")){
					System.out.println(v.getBulstat() + mesg[i]);
				} else if(user.equals("person")){
					System.out.println(v.getEGN() + mesg[i]);	
				}
				
			}else if(i == 2){
				System.out.println(v.getFK() + mesg[i]);
			}else if(i == 3){
				System.out.println(v.getEmail() + mesg[i]);
			}
		}
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		ValidateUserInfo v = new ValidateUserInfo();
		boolean run = true;
		String begin;
		String[] retMesg = new String[4];
		
		while(run){
			System.out.println("Enter are you entering personal info or for legal entity?");
			System.out.println("Press person or company:");
			begin = sc.next();
			
			if(begin.equals("person")){
				try{
					retMesg[0] = v.askForName(sc, pattern, matcher, v);
					retMesg[1] = v.askForEGN(sc, pattern, matcher, v);
					retMesg[2] = v.askForFK(sc, pattern, matcher, v);
					retMesg[3] = v.askForEmail(sc, pattern, matcher, v);
					printResult(v, retMesg, begin);
				}catch(Exception e){
					System.out.println("Something went wrong!");
				}
			}else if(begin.equals("company")){
				try{
					retMesg[0] = v.askForName(sc, pattern, matcher, v);
					retMesg[1] = v.askForBulstat(sc, pattern, matcher, v);
					retMesg[2] = v.askForFK(sc, pattern, matcher, v);
					retMesg[3] = v.askForEmail(sc, pattern, matcher, v);
					printResult(v, retMesg, begin);
				}catch(Exception e){
					System.out.println("Something went wrong!");
				}
			}
		}
		
	
	}

}
