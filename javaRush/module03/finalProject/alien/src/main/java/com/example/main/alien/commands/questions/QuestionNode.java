package com.example.main.alien.commands.questions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.openapi.alien.model.OptionUI;

@Builder
public class QuestionNode {
    private String question;

    @Getter
    private int id;

    @Getter
    private int parentId;

    @Getter
    @Setter
    private QuestionNodeDirection directionInTree;

    @Getter
    @Setter
    private QuestionNodeDirection selectedDirection;

    @Getter
    @Setter
    private OptionUI optionLeft;

    @Getter
    @Setter
    private OptionUI optionRight;

    @Getter
    @Setter
    private QuestionNode leftNode;

    @Getter
    @Setter
    private QuestionNode rightNode;


    public QuestionNode getNextQuestion() {
        if (selectedDirection == null)
            throw new IllegalArgumentException("selectedDirection is null");

        if (selectedDirection.equals(QuestionNodeDirection.Left)) {
            return leftNode;
        } else if (selectedDirection.equals(QuestionNodeDirection.Right))
            return rightNode;
        else return null;
    }


    public static QuestionNode newNode(
            int id,
            int parentId,
            String question,
            OptionUI optionLeft,
            OptionUI optionRight,
            QuestionNodeDirection directionInTree
    ) {
        return QuestionNode
                .builder()
                .id(id)
                .parentId(parentId)
                .question(question)
                .optionLeft(optionLeft)
                .optionRight(optionRight)
                .directionInTree(directionInTree)
                .build();
    }

}
