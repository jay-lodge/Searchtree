package com.keyin;

import com.keyin.TreeStructure;
import com.sun.source.tree.Tree;

import javax.persistence.*;
import java.util.List;

@Entity
public class TreeNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int value;

    @ManyToOne
    @JoinColumn(name = "tree_id")
    private TreeStructure tree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setTree(TreeStructure tree) {
    }
}
