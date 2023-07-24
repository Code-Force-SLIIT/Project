package Controller;

import java.util.List;

public class SupplierManagement {
    private SupplierManagement supplierManagement;
    private SupplierView supplierView;

    public SupplierManagement() {
        supplierManagement = new SupplierManagement();
        supplierView = new SupplierView();
    }

    public void addSupplier(int id, String name, String telephone, String description) {
        Supplier supplier = new Supplier(id, name, telephone, description);
        supplierManagement.addSupplier(supplier);
    }

    public void updateSupplier(int id, String name, String telephone, String description) {
        Supplier updatedSupplier = new Supplier(id, name, telephone, description);
        supplierManagement.updateSupplier(updatedSupplier);
    }

    public void deleteSupplier(int id) {
        supplierManagement.deleteSupplier(id);
    }

    public Supplier getSupplierById(int id) {
        return supplierManagement.getSupplierById(id);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierManagement.getAllSuppliers();
    }

    public void displaySupplier(int id) {
        Supplier supplier = supplierManagement.getSupplierById(id);
        if (supplier != null) {
            supplierView.displaySupplier(supplier);
        } else {
            System.out.println("Supplier not found.");
        }
    }

    public void displayAllSuppliers() {
        List<Supplier> suppliers = supplierManagement.getAllSuppliers();
        supplierView.displayAllSuppliers(suppliers);
    }
}
