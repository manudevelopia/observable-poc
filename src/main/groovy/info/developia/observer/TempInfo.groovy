package info.developia.observer

class TempInfo {
    public static final Random random = new Random()

   final String town
   final int temp

    TempInfo(String town, int temp) {
        this.town = town
        this.temp = temp
    }

    static TempInfo fetch(String town) {
        if (random.nextInt(10) == 0) {
            throw new RuntimeException("Error fetching temperature from $town!")
        }
        return new TempInfo(town, random.nextInt(100))
    }

    @Override
    String toString() {
        return "town: $town : temp: $temp"
    }
}
