package com.project.view.sell;

import com.project.domain.Product;
import com.project.view.common.NormalButton;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProductListPanel extends JPanel {
    private final NormalButton minusButton = new NormalButton("-");
    private final NormalButton addButton = new NormalButton("+");
    private Long id;
    private final JLabel name = new JLabel();
    private final JLabel price = new JLabel();

    public ProductListPanel() {
        setLayout(new FlowLayout());
        name.setPreferredSize(new Dimension(80, 20));
        price.setPreferredSize(new Dimension(80, 20));
        minusButton.setPreferredSize(new Dimension(20, 20));
        addButton.setPreferredSize(new Dimension(20, 20));
        addButton.setParentComponent(this);
        minusButton.setParentComponent(this);
        add(name);
        add(price);
        add(addButton);
        add(minusButton);
    }

    // 상품 세팅
    public void setProduct(final Product product) {
        id = product.getId();
        name.setText(product.getName());
        price.setText(String.valueOf(product.getPrice()));
    }

    public String getIdText() {
        return String.valueOf(id);
    }

    public String getNameText() {
        return name.getText();
    }

    public String getPriceText() {
        return price.getText();
    }

    public NormalButton getAddButton() {
        return addButton;
    }

    public NormalButton getMinusButton() {
        return minusButton;
    }
}