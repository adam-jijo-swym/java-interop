import com.swym.Instrumented;

class FromJava {
    public static void main(String[] a){
        String s = Instrumented.myrandom("fromjava");
        System.out.println(s);
        s = Instrumented.mynotrandom("fromjava");
        System.out.println(s);
    }
}
