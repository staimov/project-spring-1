package org.staimov.service;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.staimov.dao.TaskDAO;
import org.staimov.domain.Status;
import org.staimov.domain.Task;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskDAO taskDAO;

    public TaskServiceImpl(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public List<Task> getPage(int offset, int count) {
        return taskDAO.getPage(offset, count);
    }

    @Override
    public int count() {
        return taskDAO.count();
    }

    @Override
    @Transactional
    public Task edit(int id, String description, Status status) {
        Task task = taskDAO.getById(id);
        Preconditions.checkState(task != null, "Task not found");
        task.setDescription(description);
        task.setStatus(status);

        // Save is redundant, because
        // when a method is transactional, then entities retrieved within this transaction are in the managed state,
        // which means that all changes made to them will be populated to the database automatically
        // at the end of the transaction
        //return taskDAO.createOrUpdate(task);

        return task;
    }

    @Override
    public Task create(String description, Status status) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        return taskDAO.createOrUpdate(task);
    }

    @Override
    public void delete(int id) {
        taskDAO.deleteById(id);
    }
}
