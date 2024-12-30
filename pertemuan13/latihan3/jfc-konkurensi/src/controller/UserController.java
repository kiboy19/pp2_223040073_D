package controller;

import model.*;
import view.UserPdf;
import view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private UserPdf pdf;
    private ExecutorService executorService; // ExecutorService for concurrency

    public UserController(UserView view, UserMapper mapper, UserPdf pdf) {
        this.view = view;
        this.mapper = mapper;
        this.pdf = pdf;
        this.executorService = Executors.newFixedThreadPool(3); // 3 threads

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addExportListener(new ExportListener());
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            executorService.submit(() -> {
                String name = view.getNameInput();
                String email = view.getEmailInput();

                if (!name.isEmpty() && !email.isEmpty()) {
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    mapper.insertUser(user);
                    JOptionPane.showMessageDialog(view, "User added successfully!");
                } else {
                    JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                }
            });
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            executorService.submit(() -> {
                List<User> users = mapper.getAllUsers();
                String[] userArray = users.stream()
                        .map(u -> u.getName() + " (" + u.getEmail() + ")")
                        .toArray(String[]::new);
                SwingUtilities.invokeLater(() -> view.setUserList(userArray)); // Update UI on Event Dispatch Thread
            });
        }
    }

    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            executorService.submit(() -> {
                List<User> users = mapper.getAllUsers();
                pdf.exportPdf(users);
                JOptionPane.showMessageDialog(view, "PDF Exported Successfully!");
            });
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
