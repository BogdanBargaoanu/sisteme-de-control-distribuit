package three_tasks_server;

import java.util.List;

public class Task implements ITask{
    @Override
    public void SendMessageForResource(Resource res) {

    }

    @Override
    public Resource AquireResource(ResourceManager resourceManager) {
        return null;
    }

    @Override
    public List<Resource> AquireBothResources(ResourceManager resourceManager1, ResourceManager resourceManager2) {
        return List.of();
    }

    @Override
    public void ReleaseResource(ResourceManager resourceManager) {

    }

    @Override
    public void ReleaseBothResources(ResourceManager resourceManager1, ResourceManager resourceManager2) {

    }
}
