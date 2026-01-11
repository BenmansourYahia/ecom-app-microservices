package net.yahia.mcpserver.tools;
import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;
import java.util.List;


    @Component
    public class McpTools {
        @McpTool(name="getEmployee",
                description = "Get information about given employee")
        public Employee getEmployee(@McpArg(description = "the employee name") String name){
            return new Employee(name,12300,4);
        }
        @McpTool(description = "Get all employees")
        public List<Employee> getALLEmployees(){
            return List.of(
                    new Employee("yahia",18000,1),
                    new Employee("yasmine",12000,1),
                    new Employee("yassine",28000,3));


        }
    }
    record Employee(String name, double salary,int seniority){}

