package util;

import java.util.Comparator;
import java.util.List;


/**
 * Utility methods for handling lists
 */
public class ListUtil {


    /**
     * Merge two sorted lists: insert the merged list into the target list
     *
     * @param target the target list which the merged list will be inserted into
     * @param merged the merged list
     * @param c the comparator which specifies how two objects can be compared
     */
    public static <T> void mergeList(List<T> target, List<T> merged, Comparator<T> c) {

        int k = 0;  // k stores the index in the target list that is to be visited next

        int i = 0;  // i stores the index in the merged list that is being inserted next

        for (; i < merged.size(); i++) {

            T m = merged.get(i);

            boolean terminated = true;  // a flag that signals whether the scanning of target by m has reached its end

            for (int j = k; j < target.size(); j++) {  // j is the current index in the target list being visited

                T t = target.get(j);

                int result = c.compare(m, t);

                if (result < 0) {
                    target.add(j, m);
                    k = j + 1;
                    terminated = false;
                    break;
                } else if (result == 0) {
                    target.add(j + 1, m);
                    k = j + 2;
                    terminated = false;
                    break;
                }
            }

            if (terminated) break;
        }

        if (i < merged.size()) {
            for (int j = i; j < merged.size(); j++)
                target.add(merged.get(j));
        }
    }



    private ListUtil() {}

}
