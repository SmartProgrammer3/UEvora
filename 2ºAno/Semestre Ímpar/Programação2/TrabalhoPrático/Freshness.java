public interface Freshness {

    /**
     * @return true if is outdated and false otherwise
     */
    public boolean isOutDated();

    /**
     * @return true if is from today and false otherwise
     */
    public boolean isFromToday();

}