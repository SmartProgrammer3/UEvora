import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        Perishable p2 = new Perishable("Bread", 12, new Date());
        System.out.println(p2.isFromToday());
        /* true */

        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = sdformat.parse("2022-12-13");
            Perishable p1 = new Perishable("Fruit", 12, d1);
            System.out.println(p1.isOutDated());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } /* true */

        NonPerishable np = new NonPerishable("Lotion", 1.2, 1.3);
        System.out.println(np.getVolume());
        /* 1.3 */
    }
}
