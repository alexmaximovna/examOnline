package net.thumbtack.netexam.model;

public enum Filter {
    ALL("all"),
    PASSED("passed"),
    REMAINING("remaining"),
    CURRENT("current");

    private String filter;

    Filter(String filter) {
        this.filter = filter;
    }

    public static Filter getFilter(String filter) {
        for (Filter filter1 : Filter.values()) {
            if (filter1.filter.equalsIgnoreCase(filter)) {
                return filter1;
            }
        }
        return null;
    }


}
