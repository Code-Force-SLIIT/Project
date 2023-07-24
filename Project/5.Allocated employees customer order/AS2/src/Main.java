import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

 class AllocateEmployeesApp extends JFrame {
    private HashMap<String, ArrayList<String>> ordersMap;
    private HashMap<String, ArrayList<String>> employeesMap;
    private DefaultListModel<String> ordersListModel;
    private DefaultListModel<String> employeesListModel;
    private JList<String> ordersList;
    private JList<String> employeesList;

    public AllocateEmployeesApp() {
        // Initialize data structures
        ordersMap = new HashMap<>();
        employeesMap = new HashMap<>();

        // Create GUI components
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        JPanel ordersPanel = new JPanel(new BorderLayout());
        JLabel ordersLabel = new JLabel("Customer Orders");
        ordersListModel = new DefaultListModel<>();
        ordersList = new JList<>(ordersListModel);
        ordersPanel.add(ordersLabel, BorderLayout.NORTH);
        ordersPanel.add(new JScrollPane(ordersList), BorderLayout.CENTER);

        JPanel employeesPanel = new JPanel(new BorderLayout());
        JLabel employeesLabel = new JLabel("Available Employees");
        employeesListModel = new DefaultListModel<>();
        employeesList = new JList<>(employeesListModel);
        employeesPanel.add(employeesLabel, BorderLayout.NORTH);
        employeesPanel.add(new JScrollPane(employeesList), BorderLayout.CENTER);

        mainPanel.add(ordersPanel);
        mainPanel.add(employeesPanel);

        JButton allocateButton = new JButton("Allocate Employees");
        allocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allocateEmployeesToOrders();
            }
        });

        // Add components to the main frame
        add(mainPanel, BorderLayout.CENTER);
        add(allocateButton, BorderLayout.SOUTH);

        // Set frame properties
        setTitle("Allocate Employees to Customer Orders");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Sample data for demonstration purposes
        initializeSampleData();
        updateLists();
    }

    private void initializeSampleData() {
        // Sample customer orders
        ordersMap.put("Order1", new ArrayList<>());
        ordersMap.put("Order2", new ArrayList<>());
        ordersMap.put("Order3", new ArrayList<>());

        // Sample employees
        employeesMap.put("Employee1", new ArrayList<>());
        employeesMap.put("Employee2", new ArrayList<>());
        employeesMap.put("Employee3", new ArrayList<>());
        employeesMap.put("Employee4", new ArrayList<>());
    }

    private void allocateEmployeesToOrders() {
        String selectedOrder = ordersList.getSelectedValue();
        String selectedEmployee = employeesList.getSelectedValue();

        if (selectedOrder != null && selectedEmployee != null) {
            // Add the selected employee to the selected order
            ArrayList<String> employees = ordersMap.getOrDefault(selectedOrder, new ArrayList<>());
            employees.add(selectedEmployee);
            ordersMap.put(selectedOrder, employees);

            // Remove the selected employee from the available employees list
            employeesMap.get(selectedEmployee).remove(selectedOrder);

            // Update the lists
            updateLists();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order and an employee to allocate.");
        }
    }

    private void updateLists() {
        // Update the customer orders list
        ordersListModel.clear();
        for (String order : ordersMap.keySet()) {
            ordersListModel.addElement(order);
        }

        // Update the available employees list
        employeesListModel.clear();
        for (String employee : employeesMap.keySet()) {
            employeesListModel.addElement(employee);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AllocateEmployeesApp().setVisible(true);
            }
        });
    }
}