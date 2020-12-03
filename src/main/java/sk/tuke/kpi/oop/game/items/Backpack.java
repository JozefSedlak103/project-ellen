package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {

    private String name;
    private int capacity;
    private List<Collectible> content;

    public Backpack(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
        content = new ArrayList<>();
    }

    @Override
    public @NotNull List<Collectible> getContent() {
        return new ArrayList<Collectible>(content);
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getSize() {
        return content.size();
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public void add(@NotNull Collectible actor) {
        if (this.getSize()<this.getCapacity()) {
            content.add(actor);
            return;
        }
        throw new IllegalStateException(getName() + " is full");
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        content.remove(actor);
    }

    @Nullable
    @Override
    public Collectible peek() {
        if (content.isEmpty()) {
            return null;
        }
        return content.get(content.size()-1);
    }

    @Override
    public void shift() {
        Collections.rotate(content,1);
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return content.iterator();
    }
}
