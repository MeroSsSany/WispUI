package dev.merosssany.wispui.ui.base.component;

import dev.merosssany.wispui.ui.base.AbstractUI;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Radio implements Component {
    protected boolean active = false;
    protected Consumer<Boolean> event;
    protected List<AbstractUI> uis = new ArrayList<>();
    
    public Radio(Consumer<Boolean> event) {
        this.event = event;
    }
    
    public Radio() {
    }
    
    public int size() {
        return uis.size();
    }
    
    public boolean remove(AbstractUI o) {
        return uis.remove(o);
    }
    
    public ListIterator<AbstractUI> listIterator(int i) {
        return uis.listIterator(i);
    }
    
    public boolean removeIf(Predicate<AbstractUI> filter) {
        return uis.removeIf(filter);
    }
    
    public AbstractUI get(int i) {
        return uis.get(i);
    }
    
    public <T> T[] toArray(T[] ts) {
        return uis.toArray(ts);
    }
    
    public int lastIndexOf(AbstractUI o) {
        return uis.lastIndexOf(o);
    }
    
    public void forEach(Consumer<AbstractUI> action) {
        uis.forEach(action);
    }
    
    public void clear() {
        uis.clear();
    }
    
    public boolean add(AbstractUI ui) {
        return uis.add(ui);
    }
    
    public Stream<AbstractUI> stream() {
        return uis.stream();
    }
    
    public boolean contains(AbstractUI o) {
        return uis.contains(o);
    }
    
    public Iterator<AbstractUI> iterator() {
        return uis.iterator();
    }
    
    public AbstractUI remove(int i) {
        return uis.remove(i);
    }
    
    public int indexOf(AbstractUI o) {
        return uis.indexOf(o);
    }
    
    public Stream<AbstractUI> parallelStream() {
        return uis.parallelStream();
    }
    
    public Object[] toArray() {
        return uis.toArray();
    }
    
    public boolean addAll(Collection<AbstractUI> collection) {
        return uis.addAll(collection);
    }
    
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return uis.toArray(generator);
    }
    
    public void add(int i, AbstractUI ui) {
        uis.add(i, ui);
    }
    
    public boolean isEmpty() {
        return uis.isEmpty();
    }
    
    public void activate() {
        active = true;
        if (event != null) event.accept(true);
    }
    
    public void deactivate() {
        active = false;
        if (event != null) event.accept(false);
    }
    
    @Override
    public void draw() {
    }
    
    @Override
    public void setAngle(float angle) {
    
    }
    
    @Override
    public void setDrawOrder(int z) {
    
    }
    
    @Override
    public int getDrawOrder() {
        return 0;
    }
    
    @Override
    public void cleanup() {
        uis.clear();
        event = null;
    }
    
    @Override
    public void setParent(AbstractUI ui) {
    
    }
    
    @Override
    public Component copy() {
        Radio radio = new Radio();
        radio.uis = new ArrayList<>(uis);
        return radio;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public static class Builder {
        protected Radio radio = new Radio();
        
        public Builder add(AbstractUI ui) {
            radio.add(ui);
            return this;
        }
        
        public Builder addAll(Collection<AbstractUI> collection) {
            radio.addAll(collection);
            return this;
        }
        
        public Builder remove(AbstractUI o) {
            radio.remove(o);
            return this;
        }
        
        public Builder removeIf(Predicate<AbstractUI> filter) {
            radio.removeIf(filter);
            return this;
        }
        
        public Builder lastIndexOf(AbstractUI o) {
            radio.lastIndexOf(o);
            return this;
        }
        
        public Builder remove(int i) {
            radio.remove(i).close();
            return this;
        }
        
        public Builder add(int i, AbstractUI ui) {
            radio.add(i, ui);
            return this;
        }
        
        public Radio build() {
            return radio;
        }
    }
}
