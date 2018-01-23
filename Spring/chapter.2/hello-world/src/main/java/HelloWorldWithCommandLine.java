public class HelloWorldWithCommandLine {
    public static void main(String[] args) {
        if (args.length > 0) {
            for (String arg : args)
                System.out.print(arg + " ");
            System.out.println();
        } else {
            System.out.println("Hello World!");
        }
    }
}