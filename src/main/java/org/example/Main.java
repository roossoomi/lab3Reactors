package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        JFrame frame = new JFrame("Реакторы на разных языках разметки");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


        JButton button = new JButton("Выбрать файл");
        button.setBounds(100, 100, 220, 30);
        frame.add(button);

        JButton showLog = new JButton("Показать журнал логирования");
        showLog.setBounds(100, 150, 220, 30);
        frame.add(showLog);

        JButton closeButton = new JButton("Завершение работы");
        closeButton.setBounds(100, 200, 220, 30);
        frame.add(closeButton);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JOptionPane.showMessageDialog(null, "Хорошего дня!", "Сообщение", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        showLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    File logFile = new File(System.getProperty("user.dir") + "/LogMagazinee.txt");
                    if (logFile.exists()) {
                        Desktop.getDesktop().open(logFile);
                    } else {
                        JOptionPane.showMessageDialog(null, "Файл журнала не найден", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка при открытии файла журнала", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Создаем объект для выбора файла
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

                // Открываем диалоговое окно для выбора файла
                int result = fileChooser.showOpenDialog(frame);

                // Если файл был выбран
                // Получаем выбранный файл
                File selectedFile = fileChooser.getSelectedFile();

                // Сохраняем путь к файлу в поле
                try {
                    Client client = new Client();
                    HashMap<String, Reactor> reactors = client.readCommonClass(selectedFile.getAbsolutePath());
                    for (Reactor reactor : reactors.values()) {
                    }
                    DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
                    DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);

                    for (Map.Entry<String, Reactor> entry : reactors.entrySet()) {
                        DefaultMutableTreeNode reactorNode = new DefaultMutableTreeNode(entry.getKey());

                        reactorNode.add(new DefaultMutableTreeNode("Сгорание: " + entry.getValue().burnup));
                        reactorNode.add(new DefaultMutableTreeNode("Класс: " + entry.getValue().reactorClass));
                        reactorNode.add(new DefaultMutableTreeNode("Электрическая мощность: " + entry.getValue().electricalCapacity));
                        reactorNode.add(new DefaultMutableTreeNode("Первая загрузка: " + entry.getValue().firstLoad));
                        reactorNode.add(new DefaultMutableTreeNode("КПД: " + entry.getValue().kpd));
                        reactorNode.add(new DefaultMutableTreeNode("Срок службы: " + entry.getValue().lifeTime));
                        reactorNode.add(new DefaultMutableTreeNode("Тепловая мощность: " + entry.getValue().terminalCapacity));
                        reactorNode.add(new DefaultMutableTreeNode("Тип файла: " + entry.getValue().fileType));
                        treeModel.insertNodeInto((MutableTreeNode) reactorNode, (MutableTreeNode) treeModel.getRoot(), treeModel.getChildCount(treeModel.getRoot()));
                    }

                    JTree tree = new JTree(treeModel);

                    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

                    JScrollPane scrollPane = new JScrollPane(tree);

                    JFrame frame = new JFrame("Дерево реакторов");
                    frame.add(scrollPane);

                    frame.setSize(400, 300);

                    frame.setVisible(true);
                } catch (Exception ex) {
                    String errorMessage = "Ошибка при импорте и чтении данных из файла";
                    logger.error("Ошибка при импорте файла");
                    JOptionPane.showMessageDialog(null, errorMessage, "Ошибка", JOptionPane.INFORMATION_MESSAGE);
                }


            }
        });


        // Отображаем фрейм
        frame.setVisible(true);

    }

}
