package org.staimov.dao;

import com.google.common.base.Preconditions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.staimov.domain.Task;

import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {
    private final SessionFactory sessionFactory;

    public TaskDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Task getById(int id) {
        return (Task) getSession().get(Task.class, id);
    }

    @Override
    public List<Task> getAll() {
        return getSession().createQuery("from Task", Task.class).list();
    }

    @Override
    public int count() {
        Query<Long> query = getSession().createQuery(
                "select count(*) from Task", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    @Override
    public List<Task> getPage(int offset, int count) {
        Query<Task> query = getSession().createQuery("from Task", Task.class);
        query.setMaxResults(count);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public Task createOrUpdate(Task task) {
        Preconditions.checkNotNull(task, "Task is null");
        getSession().saveOrUpdate(task);
        return task;
    }

    @Override
    public void delete(Task task) {
        Preconditions.checkNotNull(task, "Task is null");
        getSession().remove(task);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        final Task task = getById(id);
        delete(task);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
