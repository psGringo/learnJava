package com.example.main.alien.commands.questions;

import java.util.List;
import java.util.stream.Collectors;
import org.openapi.alien.model.DirectionUI;
import org.openapi.alien.model.OptionUI;

public class QuestionNodes {

    List<QuestionNode> nodes;

    QuestionNode firstNode;

    public void collectTree(QuestionNode questionNode) {

        int parentId = questionNode.getParentId();
        var children = nodes.stream().filter(node -> node.getParentId() == parentId).collect(Collectors.toList());

        if (children.isEmpty())
            return;

        children.forEach(node -> {
            var direction = node.getDirectionInTree();
            if (direction == QuestionNodeDirection.Left) {
                questionNode.setLeftNode(node);
            } else if (direction == QuestionNodeDirection.Right) {
                questionNode.setRightNode(node);

            }
            throw new IllegalArgumentException(String.format("questionNode direction set to %s", direction.toString()));
        });

    }

    public QuestionNodes() {
//        nodes.add(QuestionNode.
//                newNode(
//                        0,
//                        -1,
//                        "Ты потерял память." + System.lineSeparator() + "Принять вызов НЛО ?",
//                        new OptionUI().value("Принять вызов"),
//                        new OptionUI().value("Отклонить вызов"),
//                        QuestionNodeDirection.None
//                ));
//
//
//        nodes.add(QuestionNode.
//                newNode(
//                        1,
//                        0,
//                        "Ты принял вызов." + System.lineSeparator() + "Поднимешься на мостик к капитану ?",
//                        "Подняться на мостик",
//                        "Отказаться подниматься на мостик",
//                        QuestionNodeDirection.Left
//                ));
//
//        nodes.add(QuestionNode.
//                newNode(
//                        2,
//                        0,
//                        "Ты отклонил вызов." + System.lineSeparator() + "Поражение",
//                        "",
//                        "",
//                        QuestionNodeDirection.Right
//                ));
//
//
//        nodes.add(QuestionNode.
//                newNode(
//                        3,
//                        1,
//                        "Ты не пошел на переговоры." + System.lineSeparator() + "Поражение",
//                        "",
//                        "",
//                        QuestionNodeDirection.Right
//                ));
//
//        nodes.add(QuestionNode.
//                newNode(
//                        4,
//                        1,
//                        "Ты поднялся на мостик. Ты кто ?" + System.lineSeparator() + "Поражение",
//                        "Рассказать правду",
//                        "Солгать",
//                        QuestionNodeDirection.Left
//                ));
//
//        nodes.add(QuestionNode.
//                newNode(
//                        5,
//                        4,
//                        "Тебя разоблачили. " + System.lineSeparator() + "Поражение",
//                        "",
//                        "",
//                        QuestionNodeDirection.Right
//                ));
//
//        nodes.add(QuestionNode.
//                newNode(
//                        6,
//                        4,
//                        "Тебя вернули домой. " + System.lineSeparator() + "Победа",
//                        "",
//                        "",
//                        QuestionNodeDirection.Left
//                ));
//
//        firstNode = nodes.stream().filter((questionNode) -> questionNode.getId() == -1).findFirst().get();

//        collectTree(firstNode);
    }

}
