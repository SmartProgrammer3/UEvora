public class test2 {
    public static void main(String[] args) {
        ElementarMachine<String> sm1 = new ElementarMachine<String>();
        sm1.addThings(10, "foo");
        sm1.addThings(10, "bar");
        sm1.addThings(10, "foo");
        sm1.removeOneThing("foo");
        sm1.listAll();
        /*
         * Element [String=foo, count = 19]
         * Element [String=bar, count = 10]
         */
    }
}
