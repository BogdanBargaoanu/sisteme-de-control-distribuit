package three_tasks_server;

public class ResourceManager implements IResourceManager{
    @Override
    public Resource getResource() {
        return null;
    }

    @Override
    public void AllowTask(Task task) {

    }

    @Override
    public void RevokeTask(Task task) {

    }
}

/*
function allocateResources(solicitors)

mutex.lock()

for solicitor in solicitors
    if (solicitor.requestedResource = res1) queue1.add(solicitor)
    else if (solicitor.requestedResource = res2) queue2.add(solicitor)
    else queue1.add(solicitor); queue2.add(solicitor)

for solicitor in solicitors
    while (solicitor != queue1.peek() || solicitor != queue2.peek() || res1.isBusy() || res2.isBusy())
        wait()
    if (!checkAccess(solicitor)) continue (skip)
    startTask(solicitor)
    readResourceAndSendMessage(solicitor)
    if (queue1.contains(solicitor)) queue1.remove(solicitor)
    if (queue2.contains(solicitor)) queue2.remove(solicitor)
    notifyAll()

mutex.unlock()

// Helper functions
function checkAccess(solicitor)
    if (!server.assignedTask.contains(solicitor)) return false
    if (solicitor.requestedResource = res1) return res1.isBusy()
    else if (solicitor.requestedResource = res2) return res2.isBusy()
    else return res1.isBusy() || res2.isBusy()

function readResourceAndSendMessage(solicitor)
    solicitor.sendMessage(solicitor.requestedResource.getMessage())
 */