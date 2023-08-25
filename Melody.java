package PP3;//Creates Melody object.

public class Melody {
	
	//Creates a note queue that will hold the song and its notes.
	public MelodyQueue song;

	//Initializes the member variable.
	public Melody (MelodyQueue n)
	{
		song = n;
	}
	//Returns total duration.
	public double getTotalDuration()
	{
		return song.duration();
	}
	//Returns a String interpretation of the song.
	public String toString()
	{
		return song.makeString();
	}
	//Changes the tempo of the song (duration of each note) by a multiplier "tempo".
	public void changeTempo(double tempo)
	{
		song.tempoChange(tempo);
	}
	//Reverses the order of the notes of the notes.
	public void reverse()
	{
		song = song.reverseMelody();
	}
	//Adds a song onto the end of another song.
	public void append(Melody other)
	{
		song.appendMelody(other.song);
	}
	//Plays the loaded melody.
	public void play()
	{
		song.play();
	}
}