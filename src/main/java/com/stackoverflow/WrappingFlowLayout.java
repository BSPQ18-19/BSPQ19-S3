package com.stackoverflow;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

/**
 * This code has from https://stackoverflow.com/questions/8049561/flowlayout-does-not-use-multiple-lines-within-gridbaglayout
 * posted by de user vaughandroid.
 *
 * This class has been modified
 */
public class WrappingFlowLayout extends FlowLayout {

/**
	 * 
	 */
	private static final long serialVersionUID = 5870196906693900595L;

@Override
public Dimension preferredLayoutSize(Container target) {
    synchronized (target.getTreeLock()) {
        Dimension result;
        int w = target.getWidth();

        if (w == 0) {
            // The container hasn't been assigned any size yet; just behave like a regular flow layout.
            result = super.preferredLayoutSize(target);
        } else {
            Insets insets = target.getInsets();
            int wrapW = w - insets.left - insets.right;
            int maxW = 0; // Width of the widest row.
            int rowH = 0; // Current row height.
            int x = 0;
            int y = 0;
            boolean firstVisibleComponent = true;

            for (Component c : target.getComponents()) {
                if (c.isVisible()) {
                    Dimension d = c.getPreferredSize();
                    if (firstVisibleComponent) {
                        x = d.width + (getHgap() * 2);
                        y = getVgap();
                        rowH = d.height;
                        firstVisibleComponent = false;
                    } else if (x + d.width + getHgap() <= wrapW) {
                        // Add to current row.
                        x += d.width + getHgap();
                        rowH = Math.max(rowH, d.height);
                    } else {
                        // New row.
                        x = d.width + (getHgap() * 2);
                        y += rowH + getVgap();
                        rowH = d.height;
                    }
                    maxW = Math.max(maxW, x);
                }
            }
            y += rowH + getVgap();

            result = new Dimension(maxW + insets.left + insets.right, y + insets.top + insets.bottom);
        }
        return result;
    }
}
}