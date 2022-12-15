package view;

import java.awt.*;

class JFrameLayout implements LayoutManager {

    public JFrameLayout() {
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 1140 + insets.left + insets.right;
        dim.height = 743 + insets.top + insets.bottom;

        return dim;
    }

    public Dimension minimumLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);
        return dim;
    }

    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();

        Component c;
        c = parent.getComponent(0);
        if (c.isVisible()) {c.setBounds(insets.left+0,insets.top+0,592,656);}
        c = parent.getComponent(1);
        if (c.isVisible()) {c.setBounds(insets.left+0,insets.top+656,592,72);}
//        c = parent.getComponent(2);
//        if (c.isVisible()) {c.setBounds(insets.left+16,insets.top+664,224,40);}
//        c = parent.getComponent(3);
//        if (c.isVisible()) {c.setBounds(insets.left+288,insets.top+664,224,40);}
        c = parent.getComponent(2);
        if (c.isVisible()) {c.setBounds(insets.left+592,insets.top+0,544,200);}
        c = parent.getComponent(3);
        if (c.isVisible()) {c.setBounds(insets.left+592,insets.top+200,544,456);}
        c = parent.getComponent(4);
        if (c.isVisible()) {c.setBounds(insets.left+592,insets.top+656,544,72);}
    }
}