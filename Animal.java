class Animal {
    @MyPattern(regex="[A-Za-z]+", message = "Invalid voice!")

    private String voice;
    // Konstruktor domyślny
    public Animal() {
    }
    // Metody dostępowe (gettery i settery)
    public String getVoice()
    {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    // Metoda toString() - opcjonalna
    @Override
    public String toString() {
        return "Animal [voice=" + voice + "]";
    }
}