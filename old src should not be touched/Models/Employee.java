package Models;

/**
 * Created by User on 26-09-2017.
 */
public class Employee
{
    private int EmployeeId;
    private int phone;

    public Employee(int employeeId,int phone){
        this.phone=phone;
        this.EmployeeId=employeeId;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPhone() {
        return phone;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }
}
