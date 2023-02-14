package donjon;

public interface Case {

    default Object apply(Object obj) {
        return obj;
    }
}
