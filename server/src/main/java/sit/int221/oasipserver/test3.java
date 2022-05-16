package sit.int221.oasipserver;

import sit.int221.oasipserver.exception.ErrorDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class test3 {
    public static void main(String[] args) {
        Stack st = new Stack();
//        list.add(new Object());
        List<ErrorDetail> list = new ArrayList<>();
        st.push("Java");
        st.push("Shit");
        st.push("Language");
        ErrorDetail errorDetail = new ErrorDetail("email", "null");
        st.push(errorDetail);
        System.out.println(st);

        list.add(errorDetail);
        System.out.println(list.get(0).getField());

        StringBuilder str = new StringBuilder();
        System.out.println(str.length());
        str.append("overlap;");
        str.append("note empty;");
        System.out.println(str);
        System.out.println(str.length());
    }
}
