package com.zypuk.innovation.flogin;

import java.awt.*;
import javax.swing.*;

public class FormUser extends JFrame {
    JLabel lblUserName;
    JLabel lblTitle;

    public FormUser() {
        FormUserLayout customLayout = new FormUserLayout();

        getContentPane().setFont(new Font("Helvetica", Font.PLAIN, 12));
        getContentPane().setLayout(customLayout);

        lblUserName = new JLabel("");
        getContentPane().add(lblUserName);

        lblTitle = new JLabel("TB Login");
        getContentPane().add(lblTitle);

        setSize(getPreferredSize());
        
        this.setTitle("User found");
        this.pack();
        this.show();
    }

}

class FormUserLayout implements LayoutManager {

    public FormUserLayout() {
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 320 + insets.left + insets.right;
        dim.height = 240 + insets.top + insets.bottom;

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
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+96,296,72);}
        c = parent.getComponent(1);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+8,296,72);}
    }
}


