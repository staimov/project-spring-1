package org.staimov.dao;

import org.staimov.domain.Task;

import java.util.List;

public interface TaskDAO {

    Task getById(int id);

    List<Task> getAll();

    int count();

    List<Task> getPage(int offset, int count);

    void createOrUpdate(Task task);

    void delete(Task task);

    void deleteById(int id);
}
