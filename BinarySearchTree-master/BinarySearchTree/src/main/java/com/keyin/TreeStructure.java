package com.keyin;

import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class TreeStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL)
    private List<TreeNode> nodes;

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
