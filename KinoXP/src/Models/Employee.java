package Models;

public class Employee
{
    private int EmployeeId;
    private String name;
    private String role;



    public Employee(int employeeId,String name, String role){
        this.name=name;
        this.EmployeeId=employeeId;
        this.role = role;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

}
