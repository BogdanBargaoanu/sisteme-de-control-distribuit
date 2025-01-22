package three_tasks_server;

import java.util.List;

public interface ITask {
    void SendMessageForResource(Resource res);
    Resource AquireResource(ResourceManager resourceManager);
    List<Resource> AquireBothResources(ResourceManager resourceManager1, ResourceManager resourceManager2);
    void ReleaseResource(ResourceManager resourceManager);
    void ReleaseBothResources(ResourceManager resourceManager1, ResourceManager resourceManager2);
}
