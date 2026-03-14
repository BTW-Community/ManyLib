package fi.dy.masa.malilib.gui;

public interface ParentElement extends Element {

    Element getFocused();

    void setFocused(Element focused);

    @Override
    default void setFocused(boolean focused) {
    }

    @Override
    default boolean isFocused() {
        return this.getFocused() != null;
    }
}
