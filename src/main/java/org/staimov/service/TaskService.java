package org.staimov.service;

import org.staimov.domain.Status;
import org.staimov.domain.Task;

import java.util.List;

public interface TaskService {
    List<Task> getPage(int offset, int count);
    int count();
    Task edit(int id, String description, Status status);
    Task create(String description, Status status);
    void delete(int id);
}
