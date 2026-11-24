#include <bits/stdc++.h>
using namespace std;
class Employee{
public:
    int id;
    string name;
    double salary;
    Employee(int id,string name,double salary){
        this->id=id;
        this->name=name;
        this->salary=salary;
    }
};

class EmployeeManagement{
    private:
        vector<Employee> employees;
    public:
        void addEmployee(int id, string name, double salary) {
            for (auto &e : employees) {
                if (e.id == id) {
                    cout << "Employee Already Exists!" << endl;
                    return;
                }
            }

            employees.emplace_back(id, name, salary);
            cout << "Employee "<<name<<" added successfully!" << endl;
        }
        void displayEmployee(){
            for(auto &e:employees){
                cout<<"==================================="<<endl;
                cout<<"Employee Id: "<<e.id<<endl;
                cout<<"Employee Name: "<<e.name<<endl;
                cout<<"Employee Salary: "<<e.salary<<endl;
                cout<<"==================================="<<endl;
            }
        }
        void searchEmployee(int id){
            for(auto &e:employees){
                if(e.id==id){
                    cout<<"Employee Found: "<<e.name<<endl;
                    return;
                }
            }
            cout<<"Employee Not Found!"<<endl;
        }
        void updateEmployee(int id,string newName,double newSalary){
            for (auto &e : employees) {
            if (e.id == id) {
                e.name = newName;
                e.salary = newSalary;

                cout << "Employee updated successfully\n";
                return;
                }
            }

            cout << "Employee not found\n";
        }
        void deleteEmployee(int id) {
            for (int i = 0; i < employees.size(); i++) {
                if (employees[i].id == id) {
                    employees.erase(employees.begin() + i);

                    cout << "Employee deleted successfully\n";
                    return;
                }
            }

            cout << "Employee not found\n";
        }
};

int main(){
    EmployeeManagement system;
    system.addEmployee(101, "Rahul", 50000);
    system.addEmployee(102, "Amit", 60000);

    cout << "\nAll Employees:\n";
    system.displayEmployee();

    cout << "\nSearching Employee:\n";
    system.searchEmployee(101);

    cout << "\nUpdating Employee:\n";
    system.updateEmployee(101, "Rahul Patel", 70000);

    cout << "\nAfter Update:\n";
    system.displayEmployee();

    cout << "\nDeleting Employee:\n";
    system.deleteEmployee(102);

    cout << "\nFinal Employee List:\n";
    system.displayEmployee();

    return 0;
}