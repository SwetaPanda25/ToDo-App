import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
public class ToDoApp extends JFrame{
    DefaultListModel<Task> model = new DefaultListModel<>();
    JList<Task> taskList = new JList<>(model);
    private JTextField inputField;
    private JButton addButton, removeButton;

    public ToDoApp(){
        super("To-Do List");
        model = new DefaultListModel<>();
        taskList = new JList<>(model);
        inputField = new JTextField(20);
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");

        taskList.setCellRenderer(new ListCellRenderer<Task>() {
            private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
            @Override
            public Component getListCellRendererComponent(JList<? extends Task> list,Task task, int index, boolean isSelected, boolean cellHasFocus) {
                JCheckBox check = new JCheckBox(task.getText(), task.isDone());
                check.setOpaque(true);
                check.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                check.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
                if (task.isDone()) check.setText("<html><strike>" + task.getText() + "</strike></html>");
                return check;
            }
            
        });


        taskList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = taskList.locationToIndex(e.getPoint());
                if (i != -1) {
                    Task t = model.getElementAt(i);
                    t.setDone(!t.isDone());
                    taskList.repaint();  // update the display
                }
            }
        });


        addButton.addActionListener(e -> {
            addButton.setFocusPainted(false);
            addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            String task = inputField.getText().trim();
            if(!task.isEmpty()){
                model.addElement(new Task(task));
                inputField.setText("");
            }
        });

        removeButton.addActionListener(e -> {
            removeButton.setFocusPainted(false);
            removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            int rem = taskList.getSelectedIndex();
            if(rem >= 0) 
                model.remove(rem);
        });

        JPanel top = new JPanel();
        top.add(inputField);
        top.add(addButton);

        JPanel bottom = new JPanel();
        bottom.add(removeButton);

        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        this.add(new JScrollPane(taskList), BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("List.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("List.cellPadding", new Insets(4, 8, 4, 8));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoApp::new);
    }
}