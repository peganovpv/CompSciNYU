package edu.nyu.cs;

import java.util.ArrayList;

public interface SequentiallyOrdered {

    public abstract OrderedThing getFirst();
    public abstract OrderedThing getLast();
    public abstract ArrayList<OrderedThing> getSequence();

}

class OrderedThing {
    protected int position;
    
    public int getPosition() {
        return position;
    }
}

class MyCharacter extends OrderedThing {
    private char character;
    
    public MyCharacter(char character, int position) {
        this.character = character;
        this.position = position;
    }
    
    public char getCharacter() {
        return character;
    }
}

class Word extends OrderedThing implements SequentiallyOrdered {
    private ArrayList<MyCharacter> characters;
    
    public Word(String word, int position) {
        this.position = position;
        characters = new ArrayList<>();
        
        for (int i = 0; i < word.length(); i++) {
            characters.add(new MyCharacter(word.charAt(i), i));
        }
    }

    @Override
    public OrderedThing getFirst() {
        return characters.get(0);
    }

    @Override
    public OrderedThing getLast() {
        return characters.get(characters.size() - 1);
    }

    @Override
    public ArrayList<OrderedThing> getSequence() {
        ArrayList<OrderedThing> sequence = new ArrayList<>();
        sequence.addAll(characters);
        return sequence;
    }
}

class Sentence implements SequentiallyOrdered {
    private ArrayList<Word> words;
    
    public Sentence(String sentence) {
        words = new ArrayList<>();
        String[] wordArray = sentence.split("[^\\w']+");
        
        for (int i = 0; i < wordArray.length; i++) {
            words.add(new Word(wordArray[i], i));
        }
    }

    @Override
    public OrderedThing getFirst() {
        return words.get(0);
    }

    @Override
    public OrderedThing getLast() {
        return words.get(words.size() - 1);
    }

    @Override
    public ArrayList<OrderedThing> getSequence() {
        ArrayList<OrderedThing> sequence = new ArrayList<>();
        sequence.addAll(words);
        return sequence;
    }
}

class TestSequence {
    public static void main(String[] args) {
        Sentence sentence = new Sentence("Hello my name is Peter and this is my test sentence.");
        System.out.println("First word: " + ((Word) sentence.getFirst()).getPosition());
        System.out.println("Last word: " + ((Word) sentence.getLast()).getPosition());

        ArrayList<OrderedThing> wordSequence = sentence.getSequence();
        for (OrderedThing word : wordSequence) {
            Word currentWord = (Word) word;
            System.out.println("Word: " + currentWord.getPosition());
            System.out.println("First character: " + ((MyCharacter) currentWord.getFirst()).getCharacter());
            System.out.println("Last character: " + ((MyCharacter) currentWord.getLast()).getCharacter());

            ArrayList<OrderedThing> charSequence = currentWord.getSequence();
            for (OrderedThing character : charSequence) {
                MyCharacter currentChar = (MyCharacter) character;
                System.out.println("Character: " + currentChar.getCharacter() + " Position: " + currentChar.getPosition());
            }
        }
    }
}
