package PP3;

public class MelodyQueue {

	private Node front;
	private Node back;

//Add all of your methods to this class. DO NOT CHANGE ANY OTHER CLASSES!!!!

	public MelodyQueue() {
		// Constructor with no parameters
	}

	public void enqueue(Object item) {
		// Adds item to the back of the queue. Make sure to cover the case the case that queue is null.
		Node node = new Node(item);
		if (back != null) {
			back.setNext(node); // make next node point to new node
			back = node; // update back
		} else if (front == null) { // if queue is empty
			front = node; // add the item to the front
			back = node;
		}
		size(); // update size

	}

	public Object dequeue() {
		// Removes and returns item from the front of queue. If the queue is null, print a message that queue is null.
		if (isEmpty()) {
			System.out.println("The queue is null"); // if list is empty, print this messsage
		}

		Object data = front.getItem(); // get item at the front
		front = front.getNext(); // make the next node become the first

		if (front == null) { // if front is null, then back will also be null
			back = null;

		}
		size();
		return data;

	}

	public Boolean isEmpty() {
		// Check if queue is empty
		return (size() == 0);
	}

	public double duration() {
		// Returns the total length of the song in seconds. If the song
		// includes a repeated section the length should include that repeated section twice.

		// zombie.txt  9.3    twinkle.txt = 24.5    birthday.txt = 13.0

		double duration = 0;
		Node curr;

		for (curr = this.front; curr != null; curr = curr.getNext()) {
			duration += ((Note) curr.getItem()).getDuration();

		}
		duration += timeRepeat(); // add repeated section to the duration


		return duration;

	}

	public double timeRepeat() {
		// This is an auxiliary method that you will call in duration method. The job of this method is to find the duration of
		// repeated sections.

		Node curr;
		double repeatedSection = 0;
		for (curr = this.front; curr != null; curr = curr.getNext()) {

			if (((Note) curr.getItem()).isRepeat()) { // if note is repeat
				repeatedSection += ((Note) curr.getItem()).getDuration();
				curr = curr.getNext();

				while (!((Note) curr.getItem()).isRepeat()) { // add the notes between the start and end of a repeated section
					repeatedSection += ((Note) curr.getItem()).getDuration();
					curr = curr.getNext();

					if (((Note) curr.getItem()).isRepeat()) { // if we hit a isRepeat again, add that to the repeated section before exiting loop
						repeatedSection += ((Note) curr.getItem()).getDuration();

					}
				}
			}

		}
		return repeatedSection;

	}

	public int size() {
		// Returns the size of the queue.
		Node curr;
		int size = 0;

		for (curr = this.front; curr != null; curr = curr.getNext()) {
			size++;

		}
		return size;

	}

	public String makeString() {
		// Returns String version of the notes in the queue.

		String string = "";
		Node curr;

		for (curr = this.front; curr != null; curr = curr.getNext()) {
			if (curr.getItem().equals(Pitch.R)) {
				string += ((Note) curr.getItem()).getDuration() + " " + ((Note) curr.getItem()).getPitch() + " " + ((Note) curr.getItem()).isRepeat() + "\n";

			} else {
				string += ((Note) curr.getItem()).getDuration() + " " + ((Note) curr.getItem()).getPitch() + " " + ((Note) curr.getItem()).getOctave() + " " + ((Note) curr.getItem()).getAccidental() + " " + ((Note) curr.getItem()).isRepeat() + "\n";

			}
		}
		return string;

	}

	public void tempoChange(double tempo) {
		// Changes the tempo of each note to be tempo percent of what it formerly was.

		Node curr;
		double temp;

		for (curr = this.front; curr != null; curr = curr.getNext()) {
			if ((tempo != 1) && (tempo > 1 || tempo < 1)) { // is tempo is 1, then the tempo will stay the same
				temp = (((Note) curr.getItem()).getDuration()) * tempo;
				((Note) curr.getItem()).setDuration(temp); // else, change the duration of the notes to the input tempo

			}
		}

	}

	public void playRepeat() {
		// The job of this method is to play the repeated sections.
		Node curr;

		for (curr = this.front; curr != null; curr = curr.getNext()) {
			((Note) curr.getItem()).play();

		}

	}

	public void appendMelody(MelodyQueue other) {
		// Adds one melody at the end of another one. In other words, it adds all notes from the other song (input parameter) to the end of this song.

		Node curr;

		for (curr = other.front; curr != null; curr = curr.getNext()) {
			enqueue(curr.getItem());

		}
		size(); // keep track of size of the queue

	}

	public MelodyQueue reverseMelody() {
		// Reverses the order of notes of a song.

		Node curr;
		NoteStack tempStack = new NoteStack();
		MelodyQueue reverseQueue = new MelodyQueue();

		for (curr = this.front; curr != null; curr = curr.getNext()) {
			tempStack.push(curr.getItem()); // push the items in the queue to the stack

		}

		for (curr = this.front; curr != null; curr = curr.getNext()) {
			curr.setItem(tempStack.pop()); // set curr to the items that gets popped from stack (this makes the order of notes reversed as a stack -> last in first out)
			reverseQueue.enqueue(curr.getItem()); // put them in the new queue

		}
		return reverseQueue;

	}

	public void play() {
		// Plays the song. Must call the playRepeat method. This method plays the song by calling each note's play method. The notes should be played from the beginning
		// of the queue to the end unless there are notes that are marked as being the beginning or end of a repeated section. When the first note that is a beginning or
		// end of a repeated section is found you should create a second queue. You should then get notes from the original queue until you see another marked as being
		// the beginning or end of a repeat.

		// As you get these notes you should play them and then place them back in both queues, Once you hit a second marked as beginning or end of a repeat you should play
		// everything in your secondary queue and then return to playing from the main queue. It should be possible to call this method multiple times and get the same result.

		Node curr;
		MelodyQueue newQueue = new MelodyQueue();
		boolean flag = true;

		for (curr = this.front; curr != null; curr = curr.getNext()) {

			if (((Note) curr.getItem()).isRepeat()) {
				((Note) curr.getItem()).play();
				newQueue.enqueue(curr.getItem()); // put notes in repeated section into a second queue

				curr = curr.getNext();

				while (flag) { // these are the notes that are between the start and end of a repeated section
					((Note) curr.getItem()).play();
					newQueue.enqueue(curr.getItem());

					curr = curr.getNext();
					if (((Note) curr.getItem()).isRepeat()) { // once we hit the end of the repeated section, play what's in the new queue
						((Note) curr.getItem()).play();
						newQueue.enqueue(curr.getItem());

						newQueue.playRepeat();
						flag = false;

					}
				}
			} else { // plays the notes that are not in a repeated section
				((Note) curr.getItem()).play();

			}
		}
	}
}


	


