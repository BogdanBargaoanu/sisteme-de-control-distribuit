package three_tasks_server;

public interface IServer {
    Task CreateTask(String name);
    Task AssignTask(Task task);
}
