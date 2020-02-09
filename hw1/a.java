public class a{
	private static String greeting(String name) {
		if (name.isEmpty()) {
			return "";
		} else {
			return "Hello, " + name;
		}
	}
	private static void greet(
	String name,
	String greeting
	){
		System.out.println(greeting(name) + "!");
	}
	public static void main(String[] args) {
		//System.out.println("Hello, World!");
		System.out.println("Called with " + args.length + " argument");
		if (args.length >= 1) {
			for (int i = 0; i < args.length; i++){
				greet(args[i], "Hello, ");
			}
		} else {
			System.out.println("Hello, World!");
		}
	}
}
