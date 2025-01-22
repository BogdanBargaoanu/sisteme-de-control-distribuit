package three_tasks_server;

public interface IResourceManager {
    Resource getResource();
    void AllowTask(Task task);
    void RevokeTask(Task task);
}
